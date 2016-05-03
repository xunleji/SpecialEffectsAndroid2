package com.example.androidsdk.jar.wheel.test;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.wheel.ArrayWheelAdapter;
import com.example.androidsdk.jar.wheel.IconAdapter;
import com.example.androidsdk.jar.wheel.OnWheelChangedListener;
import com.example.androidsdk.jar.wheel.WheelView;

import android.app.Activity;
import android.os.Bundle;

public class IconActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.icon_layout);
		WheelView country = (WheelView) findViewById(R.id.icon);
		country.setVisibleItems(3);
		IconAdapter iconAdapter = new IconAdapter(this);
		iconAdapter.setTextSize(18);
		country.setViewAdapter(iconAdapter);

		final String cities[][] = new String[][] {
				new String[] { "New York", "Washington", "Chicago", "Atlanta",
						"Orlando" },
				new String[] { "Ottawa", "Vancouver", "Toronto", "Windsor",
						"Montreal" },
				new String[] { "Kiev", "Dnipro", "Lviv", "Kharkiv" },
				new String[] { "Paris", "Bordeaux" }, };

		final WheelView city = (WheelView) findViewById(R.id.name);
		city.setVisibleItems(4);

		country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				ArrayWheelAdapter<String> cityAdapter = new ArrayWheelAdapter<String>(
						IconActivity.this, cities[newValue]);
				cityAdapter.setTextSize(18);
				city.setViewAdapter(cityAdapter);
				city.setCurrentItem(cities[newValue].length / 2);
			}
		});

		country.setCurrentItem(2);

	}

}
