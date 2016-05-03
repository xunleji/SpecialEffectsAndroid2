package com.example.androidsdk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UseAsyncTask extends Activity implements OnClickListener{

	private Button btn;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.use_async);
		
		btn = (Button)findViewById(R.id.button_async);
		tv = (TextView)findViewById(R.id.text_async);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		LoadTast loadTast = new LoadTast();
		loadTast.execute();
	}
	/**
	 * Params 启动任务执行的输入参数，比如HTTP请求的URL。
	 * Progress 后台任务执行的百分比
	 * Result 后台执行任务最终返回的结果，比如String。
	 * @author xujuan
	 *
	 */
	public class LoadTast extends AsyncTask<Void, Integer, String>{
		
		private ProgressDialog progressDialog;
		
		@Override
		protected String doInBackground(Void... params) {
			int sum = 1;
			while(sum <= 100 ){
				try{
					Thread.sleep(100);
					//更新进度值
					publishProgress(sum);
					sum ++;
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			return "Loading Fibished..";
		}
		
		public void onPreExecute(){
			/**创建一个对话框并显示*/
			progressDialog = ProgressDialog.show(UseAsyncTask.this,
					"AsyncTask Demo", "Loading....", true);
		}
		public void onProgressUpdate(Integer... values){
			/**将从publishProgress传递过来的值进行格式化后显示到TextView组件*/
			tv.setText(values[0] +"%");
		}
		public void onPostExecute(String result){
			/**隐藏对话框*/
			progressDialog.dismiss();
			/**将从onInBackground返回的result设置到TextView组件*/
			tv.setText(result);
		}
		
	}

}
