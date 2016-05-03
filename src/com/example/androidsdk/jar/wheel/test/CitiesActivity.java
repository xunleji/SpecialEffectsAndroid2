package com.example.androidsdk.jar.wheel.test;

import android.app.Activity;
import android.os.Bundle;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.wheel.ArrayWheelAdapter;
import com.example.androidsdk.jar.wheel.OnWheelChangedListener;
import com.example.androidsdk.jar.wheel.WheelView;

public class CitiesActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.cities_layout);

		WheelView country = (WheelView) findViewById(R.id.country);
		String countries[] = new String[] { "USA", "Canada", "Ukraine",
				"France" };
		country.setVisibleItems(3);
		ArrayWheelAdapter<String> countryAdapter = new ArrayWheelAdapter<String>(
				this, countries);
		countryAdapter.setTextSize(18);
		country.setViewAdapter(countryAdapter);

		final String cities[][] = new String[][] {
				new String[] { "New York", "Washington", "Chicago", "Atlanta",
						"Orlando" },
				new String[] { "Ottawa", "Vancouver", "Toronto", "Windsor",
						"Montreal" },
				new String[] { "Kiev", "Dnipro", "Lviv", "Kharkiv" },
				new String[] { "Paris", "Bordeaux" }, };

		final WheelView city = (WheelView) findViewById(R.id.city);
		city.setVisibleItems(4);

		country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				ArrayWheelAdapter<String> cityAdapter = new ArrayWheelAdapter<String>(
						CitiesActivity.this, cities[newValue]);
				cityAdapter.setTextSize(18);
				city.setViewAdapter(cityAdapter);
				city.setCurrentItem(cities[newValue].length / 2);
			}
		});

		country.setCurrentItem(2);
	}
}
