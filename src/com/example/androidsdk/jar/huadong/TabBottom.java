package com.example.androidsdk.jar.huadong;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.huadong.FlingGalleryView.OnScrollToScreenListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class TabBottom extends Activity {

	private FlingGalleryView fgv_list_main;
	private int screenIndex = 0;
	private ViewGroup[] vg_list_tab_item = new ViewGroup[5];
	private ImageView[] tab_item = new ImageView[5];
	private int[] screenitem ={R.drawable.box,R.drawable.good,R.drawable.phone,
			R.drawable.list,R.drawable.main};
	private int[] screenitemed ={R.drawable.box_c,R.drawable.good_c,R.drawable.phone_c,
			R.drawable.list_c,R.drawable.main_c};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bottomtab);
		
		if (fgv_list_main == null)
			fgv_list_main = (FlingGalleryView) findViewById(R.id.bottomtabtop);
		fgv_list_main.setDefaultScreen(screenIndex);
		fgv_list_main.setOnScrollToScreenListener(scrollToScreenListener);
		
		// 底部导航栏选项卡数组 实例化
		vg_list_tab_item[0] = (ViewGroup) this.findViewById(R.id.bottom1);
		tab_item[0] = (ImageView) vg_list_tab_item[0].findViewById(R.id.bottomiv);
		vg_list_tab_item[1] = (ViewGroup) this.findViewById(R.id.bottom2);
		tab_item[1] = (ImageView) vg_list_tab_item[1].findViewById(R.id.bottomiv);
		vg_list_tab_item[2] = (ViewGroup) this.findViewById(R.id.bottom3);
		tab_item[2] = (ImageView) vg_list_tab_item[2].findViewById(R.id.bottomiv);
		vg_list_tab_item[3] = (ViewGroup) this.findViewById(R.id.bottom4);
		tab_item[3] = (ImageView) vg_list_tab_item[3].findViewById(R.id.bottomiv);
		vg_list_tab_item[4] = (ViewGroup) this.findViewById(R.id.bottom5);
		tab_item[4] = (ImageView) vg_list_tab_item[4].findViewById(R.id.bottomiv);
		for (int i = 0; i < vg_list_tab_item.length; i++) {
			vg_list_tab_item[i].setOnClickListener(tabClickListener);
			tab_item[i].setImageResource(screenitem[i]);
			if (screenIndex == i) {
				tab_item[i].setImageResource(screenitemed[i]);
			}
		}
	}
	// 设置地图窗口
	private void setWindow(int state) {
		Parameter.MENU_STATE = state;
	}
	// 主屏幕左右滑动事件
	private OnScrollToScreenListener scrollToScreenListener = new OnScrollToScreenListener() {

		public void operation(int currentScreen, int screenCount) {
			tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
			screenIndex = currentScreen;
			tab_item[screenIndex].setImageResource(screenitemed[screenIndex]);
			setWindow(currentScreen + 1);
		}
	};
	// 导航栏选项卡切换事件
	private OnClickListener tabClickListener = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bottom1:
				if (screenIndex == 0) {
					return;
				}
				tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
				screenIndex = 0;
				setWindow(Parameter.MENU_MAP);
				break;
			case R.id.bottom2:
				if (screenIndex == 1) {
					return;
				}
				tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
				screenIndex = 1;
				setWindow(Parameter.MENU_SHOPINFO);
				break;
			case R.id.bottom3:
				if (screenIndex == 2) {
					return;
				}
				tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
				screenIndex = 2;
				setWindow(Parameter.MENU_SHOPFREE);
				break;
			case R.id.bottom4:
				if (screenIndex == 3) {
					return;
				}
				tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
				screenIndex = 3;
				setWindow(Parameter.MENU_PHONEINFO);
				break;
			case R.id.bottom5:
				if (screenIndex == 4) {
					return;
				}
				tab_item[screenIndex].setImageResource(screenitem[screenIndex]);
				screenIndex = 4;
				setWindow(Parameter.MENU_SETTING);
				break;
			}
			tab_item[screenIndex].setImageResource(screenitemed[screenIndex]);
			fgv_list_main.setToScreen(screenIndex, true);
		}
	};


}
