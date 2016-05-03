package com.example.androidsdk.jar.downfile;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.downfile.BreakpointDownload;
import com.example.androidsdk.jar.downfile.DirectDownloader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DownloadAc extends Activity implements OnClickListener{

	private Button btn1,btn2,btn3,btn4,btn5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.down);
		btn1 = (Button)findViewById(R.id.downbtn1);
		btn1.setOnClickListener(this);
		btn2 = (Button)findViewById(R.id.downbtn11);
		btn2.setOnClickListener(this);
		btn3 = (Button)findViewById(R.id.downbtn2);
		btn3.setOnClickListener(this);
		btn4 = (Button)findViewById(R.id.downbtn21);
		btn4.setOnClickListener(this);
		btn5 = (Button)findViewById(R.id.downbtn22);
		btn5.setOnClickListener(this);
	}
	private DirectDownloader directdown ;
	private BreakpointDownload breakpoitndown;
	private String url = "http://gdown.baidu.com/data/wisegame/b74724906fc72b84/baiduliulanqi.apk";
	@Override
	public void onClick(View v) {
		if(v==btn1){
			//直接下载
			directdown = new DirectDownloader(this);
			directdown.saveFile(url);
		}else if(v==btn2){
			//停止下载
			directdown.stopdown();
		}else if(v==btn3){
			//断点下载
			breakpoitndown = new BreakpointDownload(this);
			breakpoitndown.saveFile(url);
		}else if(v==btn4){
			//暂停下载
			breakpoitndown.pauseDownload();
		}else if(v==btn5){
			//继续下载
			breakpoitndown.saveFile(url);
		}
		
	}

}
