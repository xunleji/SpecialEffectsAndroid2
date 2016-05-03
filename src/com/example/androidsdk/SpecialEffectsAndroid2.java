package com.example.androidsdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.androidsdk.jar.activity.Activtyone;
import com.example.androidsdk.jar.activity.Intents;
import com.example.androidsdk.jar.aidlserver.AidlServerService;
import com.example.androidsdk.jar.animation.AnimationAc;
import com.example.androidsdk.jar.autolaunch.BootupDemoActivity;
import com.example.androidsdk.jar.btnmove.DraftTest;
import com.example.androidsdk.jar.carman.PicCutActivity;
import com.example.androidsdk.jar.chart.LineChartAc;
import com.example.androidsdk.jar.chart.PieChartAc;
import com.example.androidsdk.jar.contentprovider.CalendarDemo;
import com.example.androidsdk.jar.contentprovider.ContentProviderTest;
import com.example.androidsdk.jar.customcarman.CarmanTest;
import com.example.androidsdk.jar.customcheck.CheckedCus;
import com.example.androidsdk.jar.customkey.NumericKeyboardActivity;
import com.example.androidsdk.jar.customlist.LoadMore;
import com.example.androidsdk.jar.customlist.Lvone;
import com.example.androidsdk.jar.customlist.PullToRefreshActivity;
import com.example.androidsdk.jar.custommenu.MenuTest;
import com.example.androidsdk.jar.customprogressbar.ProgressTest;
import com.example.androidsdk.jar.downfile.DownloadAc;
import com.example.androidsdk.jar.facejiance.FaceTestActivity;
import com.example.androidsdk.jar.gallery.AlignLeftGalleryActivity;
import com.example.androidsdk.jar.gallery.CustomGalleryAc;
import com.example.androidsdk.jar.gallery.CustomGesture;
import com.example.androidsdk.jar.huadong.TabBottom;
import com.example.androidsdk.jar.imageload.ImageLoadAc;
import com.example.androidsdk.jar.inputmethodmanager.InputMethodManagerTest;
import com.example.androidsdk.jar.lineargradient.TopFrame;
import com.example.androidsdk.jar.loading.LoadingAc;
import com.example.androidsdk.jar.menuslowopen.Mainanimation;
import com.example.androidsdk.jar.mobiecontroller.NumberMobieTest;
import com.example.androidsdk.jar.music.MusicDemo;
import com.example.androidsdk.jar.otheractivity.OtherAc;
import com.example.androidsdk.jar.parcelableserializable.TestParcelable;
import com.example.androidsdk.jar.path.PathBtnAc;
import com.example.androidsdk.jar.preference.SettingActivity;
import com.example.androidsdk.jar.qqhuadong.QQmini2Activity;
import com.example.androidsdk.jar.search.SearchTag;
import com.example.androidsdk.jar.sensor.Zhinanzheng;
import com.example.androidsdk.jar.startservice.ServiceAc;
import com.example.androidsdk.jar.useryindao.YinDao;
import com.example.androidsdk.jar.viewflow.test.ViewFlowExample;
import com.example.androidsdk.jar.viewtag.ViewTagTest;
import com.example.androidsdk.jar.waterwave.WaterWave;
import com.example.androidsdk.jar.webservice.WebDemoActivity;
import com.example.androidsdk.jar.wheel.test.TestActivity;
import com.example.androidsdk.jar.xmlparse.ParserXmlTest;

public class SpecialEffectsAndroid2 extends Activity implements OnItemClickListener{
	
	private ListView lv;
	private TestAdapter testAdapter;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_lv);
		lv = (ListView)findViewById(R.id.testlv);
		testAdapter = new TestAdapter(this);
		lv.setAdapter(testAdapter);
		lv.setOnItemClickListener(this);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(position==0){
			intent = new Intent(this, Lvone.class);
			startActivity(intent);
		}else if(position==1){
			intent = new Intent(this, LoadMore.class);
			startActivity(intent);
		}else if(position==2){
			intent = new Intent(this, PicCutActivity.class);
			startActivity(intent);
		}else if(position==3){
			intent = new Intent(this, YinDao.class);
			startActivity(intent);
		}else if(position==4){
			intent = new Intent(this, LineChartAc.class);
			startActivity(intent);
		}else if(position==5){
			intent = new Intent(this, PieChartAc.class);
			startActivity(intent);
		}else if(position==6){
			intent = new Intent(this, NumericKeyboardActivity.class);
			startActivity(intent);
		}else if(position==7){
			intent = new Intent(this, CheckedCus.class);
			startActivity(intent);
		}else if(position==8){
			intent = new Intent(this, DownloadAc.class);
			startActivity(intent);
		}else if(position==9){
			intent = new Intent(this, CustomGalleryAc.class);
			startActivity(intent);
		}else if(position==10){
			intent = new Intent(this, AlignLeftGalleryActivity.class);
			startActivity(intent);
		}else if(position==11){
			intent = new Intent(this, ImageLoadAc.class);
			startActivity(intent);
		}else if(position==12){
			intent = new Intent(this, TestActivity.class);
			startActivity(intent);
		}else if(position==13){
			intent = new Intent(this, FaceTestActivity.class);
			startActivity(intent);
		}else if(position==14){
			
		}else if(position==15){
			intent = new Intent(this, SearchTag.class);
			startActivity(intent);
		}else if(position==16){
			intent = new Intent();
			intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS"); 
			intent.setData(Uri.parse("package:"+getPackageName()));
			intent.putExtra("cmp", "com.android.settings/.applications.InstalledAppDetails");
			intent.addCategory("android.intent.category.DEFAULT");
			startActivity(intent);
		}else if(position==17){
			intent = new Intent(this, CustomGesture.class);
			startActivity(intent);
		}else if(position==18){
			intent = new Intent(this, PullToRefreshActivity.class);
			startActivity(intent);
		}else if(position==19){
			intent = new Intent(this, ServiceAc.class);
			startActivity(intent);
		}else if(position==20){
			intent = new Intent(this, MusicDemo.class);
			startActivity(intent);
		}else if(position==22){
			intent = new Intent(this, WebDemoActivity.class);
			startActivity(intent);
		}else if(position==23){
			intent = new Intent(this, ParserXmlTest.class);
			startActivity(intent);
		}else if(position==24){
			intent = new Intent(this, Mainanimation.class);
			startActivity(intent);
		}else if(position==25){
			intent = new Intent(this, ContentProviderTest.class);
			startActivity(intent);
		}else if(position==26){
			intent = new Intent(this, CalendarDemo.class);
			startActivity(intent);
		}else if(position==27){
			intent = new Intent(this, NumberMobieTest.class);
			startActivity(intent);
		}else if(position==28){
			intent = new Intent(this, AidlServerService.class);
			startService(intent);
		}else if(position==29){
			intent = new Intent(this, TestParcelable.class);
			startActivity(intent);
		}else if(position==30){
			intent = new Intent(this, ProgressTest.class);
			startActivity(intent);
		}else if(position==31){
			intent = new Intent(this, UseAsyncTask.class);
			startActivity(intent);
		}else if(position==32){
			intent = new Intent(this, InputMethodManagerTest.class);
			startActivity(intent);
		}else if(position==33){
			intent = new Intent(this, LoadingAc.class);
			startActivity(intent);
		}else if(position==34){
			intent = new Intent(this, BootupDemoActivity.class);
			startActivity(intent);
		}else if(position==35){
			intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
		}else if(position==36){
			intent = new Intent(this, Intents.class);
			startActivity(intent);
		}else if(position==37){
			intent = new Intent(this, Zhinanzheng.class);
			startActivity(intent);
		}else if(position==38){
			intent = new Intent(this, PathBtnAc.class);
			startActivity(intent);
		}else if(position==39){
			intent = new Intent(this, AnimationAc.class);
			startActivity(intent);
		}else if(position==40){
			intent = new Intent(this, TopFrame.class);
			startActivity(intent);
		}else if(position==41){
			intent = new Intent(this, DraftTest.class);
			startActivity(intent);
		}else if(position==42){
			intent = new Intent(this, MenuTest.class);
			startActivity(intent);
		}else if(position==43){
			intent = new Intent(this, CarmanTest.class);
			startActivity(intent);
		}else if(position==44){
			intent = new Intent(this, TabBottom.class);
			startActivity(intent);
		}else if(position==45){
			intent = new Intent(this, ViewFlowExample.class);
			startActivity(intent);
		}else if(position==46){
			intent = new Intent(this, QQmini2Activity.class);
			startActivity(intent);
		}else if(position==47){
			intent = new Intent(this, ViewTagTest.class);
			startActivity(intent);
		}else if(position==48){
			intent = new Intent(this, OtherAc.class);
			startActivity(intent);
		}else if(position==50){
			intent = new Intent(this, WaterWave.class);
			startActivity(intent);
		}else if(position==51){
			intent = new Intent(this, Activtyone.class);
			startActivity(intent);
		}
	}
	
}
