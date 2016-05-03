package com.example.androidsdk.jar.customlist;

import com.example.androidsdk.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class PullToRefreshListView extends ListView implements OnScrollListener {

	private static final int TAP_TO_REFRESH = 1; //��ʼ״̬
	private static final int PULL_TO_REFRESH = 2; //����ˢ��
	private static final int RELEASE_TO_REFRESH = 3; //�ͷ�ˢ��
	private static final int REFRESHING = 4; //����ˢ��

	private static final String TAG = "PullToRefreshListView";

	private OnRefreshListener mOnRefreshListener; //ˢ�½ӿ�
	private OnScrollListener mOnScrollListener; // listview ����������
	private LayoutInflater mInflater; // ��ͼ������

	/**
	 * ͷ����ͼ ���� -- start
	 */
	private RelativeLayout mRefreshView;
	private TextView mRefreshViewText;
	private ImageView mRefreshViewImage;
	private ProgressBar mRefreshViewProgress; // ˢ�½���
	private TextView mRefreshViewLastUpdated;
	/**
	 * ͷ����ͼ ���� -- end
	 */
	private int mCurrentScrollState; // ��ǰlistivew �Ĺ���״̬
	private int mRefreshState; // ��ǰlistview ��ˢ��״̬
	// ����Ч��
	private RotateAnimation mFlipAnimation; // ��Ϊ���µļ�ͷ
	private RotateAnimation mReverseFlipAnimation; // ��Ϊ����ļ�ͷ

	private int mRefreshViewHeight; // ͷ��ͼ�ĸ߶�
	private int mRefreshOriginalTopPadding; // ͷ��ͼ ԭʼ��top padding ����ֵ
	private int mLastMotionY; // ���´����¼�ʱ����ָ��ʼy��λ��

	private boolean mBounceHack; // �Ƿ񷴵�

	public PullToRefreshListView(Context context) {
		super(context);
		init(context);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		// Load all of the animations we need in code rather than through XML
		// ��ʼ������
		mFlipAnimation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mFlipAnimation.setInterpolator(new LinearInterpolator());
		mFlipAnimation.setDuration(250);
		mFlipAnimation.setFillAfter(true);
		mReverseFlipAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
		mReverseFlipAnimation.setDuration(250);
		mReverseFlipAnimation.setFillAfter(true);

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mRefreshView = (RelativeLayout) mInflater.inflate(
				R.layout.pull_to_refresh_header, this, false);
		mRefreshViewText = (TextView) mRefreshView
				.findViewById(R.id.pull_to_refresh_text);
		mRefreshViewImage = (ImageView) mRefreshView
				.findViewById(R.id.pull_to_refresh_image);
		mRefreshViewProgress = (ProgressBar) mRefreshView
				.findViewById(R.id.pull_to_refresh_progress);
		mRefreshViewLastUpdated = (TextView) mRefreshView
				.findViewById(R.id.pull_to_refresh_updated_at);

		mRefreshViewImage.setMinimumHeight(50);
		mRefreshView.setOnClickListener(new OnClickRefreshListener());
		mRefreshOriginalTopPadding = mRefreshView.getPaddingTop();

		mRefreshState = TAP_TO_REFRESH;

		addHeaderView(mRefreshView);

		super.setOnScrollListener(this);

		measureView(mRefreshView);
		mRefreshViewHeight = mRefreshView.getMeasuredHeight(); // ��ȡͷ�ļ��Ĳ����߶�
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		setSelection(1);
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);

		setSelection(1);
	}

	/**
	 * Set the listener that will receive notifications every time the list
	 * scrolls.
	 * 
	 * @param l
	 *            The scroll listener.
	 */
	@Override
	public void setOnScrollListener(AbsListView.OnScrollListener l) {
		mOnScrollListener = l;
	}

	/**
	 * Register a callback to be invoked when this list should be refreshed.
	 * 
	 * @param onRefreshListener
	 *            The callback to run.
	 */
	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		mOnRefreshListener = onRefreshListener;
	}

	/**
	 * Set a text to represent when the list was last updated.
	 * 
	 * @param lastUpdated
	 *            Last updated at.
	 */
	public void setLastUpdated(CharSequence lastUpdated) {
		if (lastUpdated != null) {
			mRefreshViewLastUpdated.setVisibility(View.VISIBLE);
			mRefreshViewLastUpdated.setText(lastUpdated);
		} else {
			mRefreshViewLastUpdated.setVisibility(View.GONE);
		}
	}

	/**
	 * �����¼�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int y = (int) event.getY(); // ��ǰ��ָ��Yֵ
		mBounceHack = false; // ������

		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			// ����ֱ����������Ϊ����״̬
			if (!isVerticalScrollBarEnabled()) {
				setVerticalScrollBarEnabled(true);
			}
			// ���ͷ��ˢ�������֣����Ҳ�������ˢ��״̬
			if (getFirstVisiblePosition() == 0 && mRefreshState != REFRESHING) {
				if ((mRefreshView.getBottom() >= mRefreshViewHeight || mRefreshView
						.getTop() >= 0) && mRefreshState == RELEASE_TO_REFRESH) { // ���ͷ����ͼ�������붥�������
					// Initiate the refresh
					mRefreshState = REFRESHING; // ����������Ϊ������ˢ��
					prepareForRefresh(); // ׼��ˢ��
					onRefresh(); // ˢ��
				} else if (mRefreshView.getBottom() < mRefreshViewHeight
						|| mRefreshView.getTop() <= 0) {
					// Abort refresh and scroll down below the refresh view
					// ֹͣˢ�£����ҹ�����ͷ��ˢ����ͼ����һ����ͼ
					resetHeader();
					setSelection(1);// ��λ�ڵڶ����б���
				}
			}
			break;
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = y; // ������ָ��Yֵ
			break;
		case MotionEvent.ACTION_MOVE:
			applyHeaderPadding(event); // ����ͷ��ͼ��toppadding ����
			break;
		}
		return super.onTouchEvent(event);
	}

	/****
	 * ���ϵ�ͷ����top padding ����
	 * 
	 * @param ev
	 */
	private void applyHeaderPadding(MotionEvent ev) {
		// getHistorySize has been available since API 1
		int pointerCount = ev.getHistorySize(); // ��ȡ�ۻ��Ķ�����

		for (int p = 0; p < pointerCount; p++) {
			if (mRefreshState == RELEASE_TO_REFRESH) { // ������ͷŽ�Ҫˢ��״̬
				if (isVerticalFadingEdgeEnabled()) {
					setVerticalScrollBarEnabled(false);
				}

				int historicalY = (int) ev.getHistoricalY(p); // ��ʷ�ۻ��ĸ߶�

				// Calculate the padding to apply, we divide by 1.7 to
				// simulate a more resistant effect during pull.
				int topPadding = (int) (((historicalY - mLastMotionY) - mRefreshViewHeight) / 1.7);

				mRefreshView.setPadding(mRefreshView.getPaddingLeft(),
						topPadding, mRefreshView.getPaddingRight(),
						mRefreshView.getPaddingBottom());
			}
		}
	}

	/**
	 * Sets the header padding back to original size. ʹͷ����ͼ��toppadding �ָ�����ʼֵ
	 */
	private void resetHeaderPadding() {
		mRefreshView.setPadding(mRefreshView.getPaddingLeft(),
				mRefreshOriginalTopPadding, mRefreshView.getPaddingRight(),
				mRefreshView.getPaddingBottom());
	}

	/**
	 * Resets the header to the original state. ��ʼ��ͷ����ͼ ״̬
	 */
	private void resetHeader() {
		if (mRefreshState != TAP_TO_REFRESH) {
			mRefreshState = TAP_TO_REFRESH; // ��ʼˢ��״̬

			resetHeaderPadding(); // ʹͷ����ͼ��toppadding �ָ�����ʼֵ

			// Set refresh view text to the pull label
			mRefreshViewText.setText(R.string.pull_to_refresh_tap_label); // �����ֳ�ʼ��
			// Replace refresh drawable with arrow drawable
			mRefreshViewImage
					.setImageResource(R.drawable.ic_pulltorefresh_arrow); // ���ó�ʼͼƬ
			// Clear the full rotation animation
			mRefreshViewImage.clearAnimation(); // �������
			// Hide progress bar and arrow.
			mRefreshViewImage.setVisibility(View.GONE); // ����ͷ��ͼ
			mRefreshViewProgress.setVisibility(View.GONE); // ���ؽ�����
		}
	}

	// ������ͼ�ĸ߶�
	private void measureView(View child) {
		// ��ȡͷ����ͼ����
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {// �����ͼ�ĸ߶ȴ���0
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	/****
	 * �����¼� ����ʱ������
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// When the refresh view is completely visible, change the text to say
		// "Release to refresh..." and flip the arrow drawable.
		// ����ǽӴ�����״̬,���Ҳ�������ˢ�µ�״̬
		if (mCurrentScrollState == SCROLL_STATE_TOUCH_SCROLL
				&& mRefreshState != REFRESHING) {
			if (firstVisibleItem == 0) { // �����ʾ�����˵�һ���б���
				// ��ʾˢ��ͼƬ
				mRefreshViewImage.setVisibility(View.VISIBLE);
				if ((mRefreshView.getBottom() >= mRefreshViewHeight + 20 || mRefreshView
						.getTop() >= 0) && mRefreshState != RELEASE_TO_REFRESH) { // ���������listiview,����ʾ����ˢ�¶���
					mRefreshViewText
							.setText(R.string.pull_to_refresh_release_label);
					mRefreshViewImage.clearAnimation();
					mRefreshViewImage.startAnimation(mFlipAnimation);
					mRefreshState = RELEASE_TO_REFRESH;
				} else if (mRefreshView.getBottom() < mRefreshViewHeight + 20
						&& mRefreshState != PULL_TO_REFRESH) { // ���û�е������ˢ�¾��룬��ع�ԭ����״̬
					mRefreshViewText
							.setText(R.string.pull_to_refresh_pull_label);
					if (mRefreshState != TAP_TO_REFRESH) {
						mRefreshViewImage.clearAnimation();
						mRefreshViewImage.startAnimation(mReverseFlipAnimation);
					}
					mRefreshState = PULL_TO_REFRESH;
				}
			} else {
				mRefreshViewImage.setVisibility(View.GONE); // ����ˢ��ͼƬ
				resetHeader(); // ��ʼ����ͷ��
			}
		}
		// ������Լ�����״̬+ ��һ����ͼ�Ѿ���ʾ+ ����ˢ��״̬
		else if (mCurrentScrollState == SCROLL_STATE_FLING
				&& firstVisibleItem == 0 && mRefreshState != REFRESHING) {
			setSelection(1);
			mBounceHack = true; // ״̬Ϊ�ص�
		} else if (mBounceHack && mCurrentScrollState == SCROLL_STATE_FLING) {
			setSelection(1);
		}

		if (mOnScrollListener != null) {
			mOnScrollListener.onScroll(view, firstVisibleItem,
					visibleItemCount, totalItemCount);
		}
	}

	/**
	 * ����״̬�ı�ʱ������
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mCurrentScrollState = scrollState;

		if (mCurrentScrollState == SCROLL_STATE_IDLE) { // �������ͣ��
			mBounceHack = false;
		}

		if (mOnScrollListener != null) {
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	// ׼��ˢ��
	public void prepareForRefresh() {
		resetHeaderPadding(); // ��ʼ����ͷ���ļ�

		mRefreshViewImage.setVisibility(View.GONE);
		// We need this hack, otherwise it will keep the previous drawable.
		mRefreshViewImage.setImageDrawable(null);
		mRefreshViewProgress.setVisibility(View.VISIBLE);

		// Set refresh view text to the refreshing label
		mRefreshViewText.setText(R.string.pull_to_refresh_refreshing_label);

		mRefreshState = REFRESHING;
	}

	// ˢ��
	public void onRefresh() {
		Log.d(TAG, "onRefresh");

		if (mOnRefreshListener != null) {
			mOnRefreshListener.onRefresh();
		}
	}

	/**
	 * ˢ����� �Ļص����� Resets the list to a normal state after a refresh.
	 * 
	 * @param lastUpdated
	 *            Last updated at.
	 */
	public void onRefreshComplete(CharSequence lastUpdated) {
		setLastUpdated(lastUpdated);
		onRefreshComplete();
	}

	/**
	 * ˢ����ɻص����� Resets the list to a normal state after a refresh.
	 */
	public void onRefreshComplete() {
		Log.d(TAG, "onRefreshComplete");

		resetHeader();

		if (mRefreshView.getBottom() > 0) {
			invalidateViews();
			setSelection(1);
		}
	}

	private class OnClickRefreshListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (mRefreshState != REFRESHING) {
				prepareForRefresh();
				onRefresh();
			}
		}

	}
	
	public interface OnRefreshListener {
		public void onRefresh();
	}
}