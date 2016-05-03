package com.example.androidsdk.jar.viewflow.test;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.viewflow.TitleFlowIndicator;
import com.example.androidsdk.jar.viewflow.ViewFlow;

import android.app.Activity;
import android.os.Bundle;

public class AsyncDataFlowExample extends Activity {

	private ViewFlow viewFlow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.async_title);
		setContentView(R.layout.title_layout);

		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		AsyncAdapter adapter = new AsyncAdapter(this);
		viewFlow.setAdapter(adapter, adapter.getTodayId());
		
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		
		viewFlow.setFlowIndicator(indicator);
    }
    
}
