package com.example.androidsdk.jar.customkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidsdk.R;

/**
 * 自定义数字键盘
 * @author xujuan
 *
 */
public class NumericKeyboardActivity extends Activity {
	/** Called when the activity is first created. */
	private GridView gridView;
	public PopupWindow window;
	public boolean isOpenPop = false;
	public int displayHeight = 0;
	public int displayWidth = 0;
	EditText ed_text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customkey);
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		displayHeight = display.getHeight();
		displayWidth = display.getWidth();
		ed_text = (EditText) findViewById(R.id.editText);
		ed_text.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (!isOpenPop) {
					isOpenPop = true;
					showPopWindow(v);
				}
				return false;
			}
		});

	}

	public void showPopWindow(View parent) {
		if (window == null) {
			LayoutInflater lay = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = lay.inflate(R.layout.pop_keyboard_view, null);
			gridView = (GridView) v.findViewById(R.id.gv_keyboard);
			window = new PopupWindow(v, displayWidth, (displayHeight / 5));
			window.setBackgroundDrawable(this.getResources().getDrawable(
					R.drawable.ic_launcher));
			window.setFocusable(true);
			window.setOutsideTouchable(true);
			gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridView.setClickable(true);
			gridView.setOnItemClickListener(gridClickListener);
			window.setOnDismissListener(new OnDismissListener() {
				public void onDismiss() {
					isOpenPop = false;
				}
			});

		}
		gridView.setAdapter(new SimpleAdapter(this, CreateData(),
				R.layout.griditems, new String[] { "key" },
				new int[] { R.id.bt_item }));
		window.update();
		window.showAtLocation(parent, Gravity.CENTER_HORIZONTAL
				| Gravity.BOTTOM, 0, 0);

	}

	public OnItemClickListener gridClickListener = new OnItemClickListener() {

		@SuppressWarnings("unchecked")
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Map<String, Object> map = (Map<String, Object>) parent
					.getItemAtPosition(position);
			String itemNum = map.get("key").toString();
			String str = ed_text.getText().toString();
			int iSelection = ed_text.getSelectionEnd();
			int iLen = str.length();
			if (itemNum.equals("del")) {
				if (iLen > 0) {
					if (iSelection != 0) {
						ed_text.setText(str.substring(0, iSelection - 1)
								+ str.substring(iSelection, iLen));
						ed_text.setSelection(iSelection - 1);
					} else {
						Log.e("gridClickListener", "光标前没有字符不能删除");
					}

				} else {
					Toast.makeText(NumericKeyboardActivity.this, "已全部删除！",
							Toast.LENGTH_SHORT).show();
				}
			} else if (itemNum.equals("完成")) {
				window.dismiss();
				ed_text.setSelection(ed_text.getText().length());
			} else {
				if (iSelection == iLen) {
					ed_text.append(itemNum);
				} else {
					ed_text.setText("");
					ed_text.setText(str.substring(0, iSelection) + itemNum
							+ str.substring(iSelection, iLen));
					ed_text.setSelection(iSelection + 1);
				}
			}
		}
	};

	/**
	 * 将自定义键盘值的存入arraylist
	 * 
	 * @return items 存放键盘值的list
	 */
	private ArrayList<Map<String, Object>> CreateData() {
		ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String[] strKeyboard_nums = { "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9" };
		ArrayList<String> keyboard_nums = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			keyboard_nums.add(strKeyboard_nums[i]);
		}
		Random random = new Random();
		putNum: for (int i = 0; i < 12; i++) {

			Map<String, Object> map = new HashMap<String, Object>();
			if (i == 5) {
				map.put("key", "del");
				items.add(map);
			} else if (i == 11) {
				map.put("key", "完成");
				items.add(map);
			} else {
				boolean isExist = false;
				while (!isExist) {
					String rand = String.valueOf(random.nextInt(10));
					for (int j = 0; j < keyboard_nums.size(); j++) {
						if (keyboard_nums.get(j).equals(rand)) {
							map.put("key", rand);
							items.add(map);
							for (int m = 0; m < keyboard_nums.size(); m++) {
								if (keyboard_nums.get(m).equals(rand)) {
									keyboard_nums.remove(m);
									continue putNum;
								}
							}
							isExist = true;
							break;
						}
					}
				}
			}
		}
		return items;

	}

}