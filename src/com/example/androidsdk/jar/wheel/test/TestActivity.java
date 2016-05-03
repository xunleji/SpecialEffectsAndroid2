package com.example.androidsdk.jar.wheel.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestActivity extends ListActivity {

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String[] str = new String[] { "CitiesActivity", "PasswActivity",
				"TimeActivity","IconActivity" };
		setListAdapter(new ArrayAdapter<String>(TestActivity.this,
				android.R.layout.simple_list_item_1, str));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (position == 0) {
			intent = new Intent(TestActivity.this, CitiesActivity.class);
		} else if (position == 1) {
			intent = new Intent(TestActivity.this, PasswActivity.class);
		} else if (position == 2) {
			intent = new Intent(TestActivity.this, TimeActivity.class);
		} else if (position == 3) {
			intent = new Intent(TestActivity.this, IconActivity.class);
		}
		startActivity(intent);
	}

}
