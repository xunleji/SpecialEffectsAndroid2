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
	 * Params ��������ִ�е��������������HTTP�����URL��
	 * Progress ��̨����ִ�еİٷֱ�
	 * Result ��ִ̨���������շ��صĽ��������String��
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
					//���½���ֵ
					publishProgress(sum);
					sum ++;
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			return "Loading Fibished..";
		}
		
		public void onPreExecute(){
			/**����һ���Ի�����ʾ*/
			progressDialog = ProgressDialog.show(UseAsyncTask.this,
					"AsyncTask Demo", "Loading....", true);
		}
		public void onProgressUpdate(Integer... values){
			/**����publishProgress���ݹ�����ֵ���и�ʽ������ʾ��TextView���*/
			tv.setText(values[0] +"%");
		}
		public void onPostExecute(String result){
			/**���ضԻ���*/
			progressDialog.dismiss();
			/**����onInBackground���ص�result���õ�TextView���*/
			tv.setText(result);
		}
		
	}

}
