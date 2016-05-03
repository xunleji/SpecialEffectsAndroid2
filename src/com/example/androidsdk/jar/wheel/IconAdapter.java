package com.example.androidsdk.jar.wheel;

import com.example.androidsdk.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class IconAdapter extends AbstractWheelTextAdapter {

	private String countries[] = new String[] { "a", "b", "c", "d" };
	// Countries flags
	private int flags[] = new int[] { R.drawable.composer_thought,
			R.drawable.composer_camera, R.drawable.composer_music,
			R.drawable.composer_place };

	public IconAdapter(Context context) {
		super(context, R.layout.item_level, NO_RESOURCE);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		View view = super.getItem(index, cachedView, parent);
		ImageView img = (ImageView) view.findViewById(R.id.iv);
		img.setImageResource(flags[index]);
		TextView tv = (TextView) view.findViewById(R.id.tv);
		tv.setText(countries[index]);
		return view;
	}

	@Override
	protected CharSequence getItemText(int index) {
		return null;
	}

	@Override
	public int getItemsCount() {
		return flags.length;
	}

}
