package com.example.androidsdk.jar.custommenu;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;

public class MenuTest extends Activity {

	private PopupWindow pw ;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		View view = inflater.inflate(R.layout.menu, null);
		GridView grid = (GridView)view.findViewById(R.id.menuGridChange);
		grid.setAdapter(new ImageAdapter(this));
		pw = new PopupWindow(view,LinearLayout.LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		pw.showAtLocation(findViewById(R.id.tv), Gravity.CENTER, 0, 300);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		if(pw!=null){
			if(pw.isShowing()){
				pw.dismiss();
				pw=null;
			}
		}
		super.onPause();
	}

}