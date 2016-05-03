package com.example.androidsdk.jar.customlist;

import java.util.Arrays;
import java.util.LinkedList;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.customlist.PullToRefreshListView;
import com.example.androidsdk.jar.customlist.PullToRefreshListView.OnRefreshListener;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PullToRefreshActivity extends ListActivity {
	
	private LinkedList<String> mListItems;
	private String[] mStrings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh);
		mStrings = initString();
		((PullToRefreshListView)getListView())
				.setOnRefreshListener(new OnRefreshListener() {
					@Override
					public void onRefresh() {
						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);
		setListAdapter(adapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {
		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				;
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			// Call onRefreshComplete when the list has been refreshed.
			((PullToRefreshListView) getListView()).onRefreshComplete();
			super.onPostExecute(result);
		}
	}

	private String[] initString() {
		String[] initStrings = new String[20];
		for (int i = 0; i < initStrings.length; i++) {
			initStrings[i] = "Roco_" + i;
		}
		return initStrings;
	}
}
