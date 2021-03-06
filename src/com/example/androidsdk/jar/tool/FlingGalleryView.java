package com.example.androidsdk.jar.tool;

import com.example.androidsdk.R;

import android.R.string;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.Scroller;

public class FlingGalleryView extends ViewGroup {

	private static final int SNAP_VELOCITY = 1000;
	// 记录当前屏幕下标
	private int mCurrentScreen;
	private Scroller mScroller;
	// 速度追踪器，
	private VelocityTracker mVelocityTracker;
	// 记录滑动时上次手指所处的位置
	private float mLastMotionX;
	private float mLastMotionY;
	private final static int TOUCH_STATE_REST = 0;
	private final static int TOUCH_STATE_SCROLLING = 1;
	// 记录当前touch事件状态--滑动（TOUCH_STATE_SCROLLING）/静止（TOUCH_STATE_REST 默认)
	private int mTouchState = TOUCH_STATE_REST;
	// 记录touch事件中被认为是滑动事件前的最大可滑动距离
	private int mTouchSlop;
	// 手指抛动作的�?��速度px/s 每秒多少像素
	private int mMaximumVelocity;
	// 滚动到指定屏幕的事件
	private OnScrollToScreenListener mScrollToScreenListener;
	// 自定义touch事件
	private OnCustomTouchListener mCustomTouchListener;
	//滚动到每个屏幕时是否都要触发OnScrollToScreenListener事件
	private boolean isEveryScreen=false;

	public FlingGalleryView(Context context) {
		super(context);
		init();
		mCurrentScreen = 0;
	}

	public FlingGalleryView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlingGalleryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FlingGalleryView, defStyle, 0);
		mCurrentScreen = a
				.getInt(R.styleable.FlingGalleryView_defaultScreen, 0);
		a.recycle();
		init();
	}

	private void init() {
		mScroller = new Scroller(getContext());
		final ViewConfiguration configuration = ViewConfiguration
				.get(getContext());
		mTouchSlop = configuration.getScaledTouchSlop();
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}

	private int count = -1;
	private int defaultScreen = -1;

	// 当滚动条滑动时调用，startScroll()设置的是参数，实际滑动，在其里执行，
	@Override
	public void computeScroll() {
		// mScroller.computeScrollOffset计算当前新的位置，true表示还在滑动，仍�?���?
		if (mScroller.computeScrollOffset()) {
			// 返回true，说明scroll还没有停�?
			scrollTo(mScroller.getCurrX(), 0);
			if(isEveryScreen)singleScrollToScreen();
			postInvalidate();
		}
	}

	private void singleScrollToScreen() {
		final int screenWidth = getWidth();
		int whichScreen = (getScrollX() + (screenWidth / 2)) / screenWidth;
		if (whichScreen > (getChildCount() - 1)) {
			return;
		}
		if (defaultScreen == -1) {
			defaultScreen = whichScreen;
			count = 1;
		} else {
			if (defaultScreen == whichScreen && count == 0) {
				count = 1;
			} else {
				if (defaultScreen != whichScreen) {
					defaultScreen = whichScreen;
					count = 0;
				}
			}
		}
		if (count == 0) {
			if (mScrollToScreenListener != null) {
				mScrollToScreenListener.operation(whichScreen, getChildCount());
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"Workspace can only be used in EXACTLY mode.");
		}
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"Workspace can only be used in EXACTLY mode.");
		}
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		scrollTo(mCurrentScreen * width, 0);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		int childLeft = 0;
		// 横向平铺childView
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			child.setOnTouchListener(childTouchListener);
			if (child.getVisibility() != View.GONE) {
				final int childWidth = child.getMeasuredWidth();
				child.layout(childLeft, 0, childLeft + childWidth,
						child.getMeasuredHeight());
				childLeft += childWidth;
			}
		}
	}

	// 设定childView的Touch事件返回true，这样可以在parentView中截获touch（即onInterceptTouchEvent）的move,up等事�?
	private OnTouchListener childTouchListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			return true;
		}
	};
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(ev);
		final int action = ev.getAction();
		final float x = ev.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				// 终止滚动条的滑动动画
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			count = -1;
			defaultScreen = -1;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mTouchState == TOUCH_STATE_SCROLLING) {	
				if (getScrollX() >= 0) {
					break;
				}	
				if (getScrollX() <= 0) {
					break;
				}	
				final int deltaX = (int) (mLastMotionX - x);
				mLastMotionX = x;
				scrollBy(deltaX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				final VelocityTracker velocityTracker = mVelocityTracker;
				velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);// 使用pix/s为单�?
				int velocityX = (int) velocityTracker.getXVelocity();
				if (velocityX > SNAP_VELOCITY && mCurrentScreen > 0) {
					// 向右移动
					snapToScreen(mCurrentScreen - 1, false);
				} else if (velocityX < -SNAP_VELOCITY
						&& mCurrentScreen < getChildCount() - 1) {
					// 向左移动
					snapToScreen(mCurrentScreen + 1, false);
				} else {
					snapToDestination();
				}
				if (mVelocityTracker != null) {
					mVelocityTracker.recycle();
					mVelocityTracker = null;
				}
			}
			mTouchState = TOUCH_STATE_REST;
			break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST;
		}
		return true;
	}
	// 计算应该去哪个屏
	private void snapToDestination() {
		final int screenWidth = getWidth();
		// 如果超过屏幕的一半就算是下一个屏
		final int whichScreen = (getScrollX() + (screenWidth / 2))
				/ screenWidth;
		snapToScreen(whichScreen, false);
	}
	// 切换屏幕
	private void snapToScreen(int whichScreen, boolean isJump) {
		// 判断下一个屏幕是否有效，并纠�?
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		if (getScrollX() != (whichScreen * getWidth())) {
			final int delta = whichScreen * getWidth() - getScrollX();
			count = -1;
			defaultScreen = -1;
			// �?��滚动动画
			mScroller.startScroll(getScrollX(), 0, delta, 0,
					Math.abs(delta) * 2);
			final int t_mCurrentScreen = mCurrentScreen;
			mCurrentScreen = whichScreen;
			// 判断是否在同�?��屏幕，不在则执行切换屏幕
			if (t_mCurrentScreen != whichScreen) {
				// 防止重复执行切换屏幕事件
				if (Math.abs(t_mCurrentScreen - whichScreen) == 1 && !isJump) {
					doOnScrollToScreen();
				}
			}
			invalidate();
		}
	}
	private void doOnScrollToScreen() {
		if (mScrollToScreenListener != null) {
			mScrollToScreenListener.operation(mCurrentScreen, getChildCount());
		}
	}
	//设置切换到的指定下标屏幕0至getChildCount()-1
	public void setToScreen(int whichScreen, boolean isAnimation) {
		if (isAnimation) {
			snapToScreen(whichScreen, true);
		} else {
			whichScreen = Math.max(0,Math.min(whichScreen, getChildCount() - 1));
			mCurrentScreen = whichScreen;
			// 直接滚动到该位置
			scrollTo(whichScreen * getWidth(), 0);
			if (whichScreen != mCurrentScreen) {
				doOnScrollToScreen();
			}
			invalidate();
		}
	}
	//设置默认屏幕
	public void setDefaultScreen(int defaultScreen) {
		mCurrentScreen = defaultScreen;
	}
	//获取当前屏幕
	public int getCurrentScreen() {
		return mCurrentScreen;
	}
	//注册滚动到指定屏幕的事件
	public void setOnScrollToScreenListener(
			OnScrollToScreenListener scrollToScreenListener) {
		if (scrollToScreenListener != null) {
			this.mScrollToScreenListener = scrollToScreenListener;
		}
	}
	//注册自定义Touch事件
	public void setOnCustomTouchListener(
			OnCustomTouchListener customTouchListener) {
		if (customTouchListener != null) {
			this.mCustomTouchListener = customTouchListener;
		}
	}
	//滚动到指定屏幕的事件
	public interface OnScrollToScreenListener {
		public void operation(int currentScreen, int screenCount);
	}
	//自定义的�?��Touch事件
	public interface OnCustomTouchListener {
		public void operation(MotionEvent event);
	}
	// 滚动到每个屏幕时是否都要触发OnScrollToScreenListener事件
	public void setEveryScreen(boolean isEveryScreen) {
		this.isEveryScreen = isEveryScreen;
	}
}
