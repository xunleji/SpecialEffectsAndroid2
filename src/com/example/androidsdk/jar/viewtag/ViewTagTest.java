package com.example.androidsdk.jar.viewtag;

import com.example.androidsdk.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ViewTagTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tagview);
		
		Button btn = (Button)findViewById(R.id.tagviewbtn);
		final BadgeView badge = new BadgeView(this, btn);
		badge.setText("1");
		badge.show();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				badge.toggle();
			}
		});
        
		ImageView iv = (ImageView)findViewById(R.id.tagviewiv);
		BadgeView badge1 = new BadgeView(this, iv);
	    badge1.setText("12");
	    badge1.setBadgePosition(BadgeView.POSITION_CENTER);
	    badge1.show();
		
	}

}
