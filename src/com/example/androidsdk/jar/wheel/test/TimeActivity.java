package com.example.androidsdk.jar.wheel.test;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;
import com.example.androidsdk.R;
import com.example.androidsdk.jar.wheel.ArrayWheelAdapter;
import com.example.androidsdk.jar.wheel.OnWheelChangedListener;
import com.example.androidsdk.jar.wheel.OnWheelScrollListener;
import com.example.androidsdk.jar.wheel.WheelView;

public class TimeActivity extends Activity {
	// Time changed flag
	private boolean timeChanged = false;
	private boolean timeScrolled = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time_layout);
	
		final WheelView hours = (WheelView) findViewById(R.id.hour);
		Integer[] ints = new Integer[90];
		for(int i = 0;i<90;i++){
			ints[i] = i;
		}
        ArrayWheelAdapter<Integer> intAdapter = new ArrayWheelAdapter<Integer>(this, ints);
        intAdapter.setTextSize(18);
		hours.setViewAdapter(intAdapter);
	
		final WheelView mins = (WheelView) findViewById(R.id.mins);
        ArrayWheelAdapter<Integer> intsAdapter = new ArrayWheelAdapter<Integer>(this, ints);
        intsAdapter.setTextSize(18);
        mins.setViewAdapter(intsAdapter);
//		mins.setAdapter(new NumericWheelAdapter(10, 100, "%02d"));
//		mins.setLabel("mins");
		mins.setCyclic(true);
	
		final TimePicker picker = (TimePicker) findViewById(R.id.time);
		picker.setIs24HourView(true);
	
		// set current time
		Calendar c = Calendar.getInstance();
		int curHours = c.get(Calendar.HOUR_OF_DAY);
		int curMinutes = c.get(Calendar.MINUTE);
	
		hours.setCurrentItem(curHours);
		mins.setCurrentItem(curMinutes);
	
		picker.setCurrentHour(curHours);
		picker.setCurrentMinute(curMinutes);
	
		// add listeners
		addChangingListener(mins, "min");
		addChangingListener(hours, "hour");
	
		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!timeScrolled) {
					timeChanged = true;
					picker.setCurrentHour(hours.getCurrentItem());
					picker.setCurrentMinute(mins.getCurrentItem());
					timeChanged = false;
				}
			}
		};

		hours.addChangingListener(wheelListener);
		mins.addChangingListener(wheelListener);

		OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				timeScrolled = true;
			}
			public void onScrollingFinished(WheelView wheel) {
				timeScrolled = false;
				timeChanged = true;
				picker.setCurrentHour(hours.getCurrentItem());
				picker.setCurrentMinute(mins.getCurrentItem());
				timeChanged = false;
			}
		};
		
		hours.addScrollingListener(scrollListener);
		mins.addScrollingListener(scrollListener);
		
		picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			public void onTimeChanged(TimePicker  view, int hourOfDay, int minute) {
				if (!timeChanged) {
					hours.setCurrentItem(hourOfDay, true);
					mins.setCurrentItem(minute, true);
				}
			}
		});
	}

	/**
	 * Adds changing listener for wheel that updates the wheel label
	 * @param wheel the wheel
	 * @param label the wheel label
	 */
	private void addChangingListener(final WheelView wheel, final String label) {
		wheel.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
	}
}
