package com.example.androidsdk.jar.downfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.tool.Until;

/**
 * ֱ������
 * @author xujuan
 *
 */
public class DirectDownloader {

	private Context context;
	private static boolean download = false;
	private int FILESIZE = 1024<<7;
	
	public DirectDownloader(Context context) {
		super();
		this.context = context;
	}
	
	private String getPath(){
		String sdStatus = Environment.getExternalStorageState();
		//���sd�Ƿ����
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
		String sdPath = Environment.getExternalStorageDirectory().toString()+"/DirectDownloader/";
		return sdPath;
	}

	private HttpURLConnection conn = null;
	private int total =0;
	private long time ;
	
	public void saveFile(final String downUrl){
		if(!download){
			showNotification("���ڻ�ȡ����......","���ڻ�ȡ��������......","");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					String path = getPath();
					try {
						File filepath = new File(path);
						if(!filepath.exists()){
							filepath.mkdirs();
						}
						final String [] name = downUrl.split("/");
						File files = new File(path+name[name.length-1]+".temp");
						FileOutputStream fos = new FileOutputStream(files);
						String urlStred = ((String)downUrl).replaceAll(" ", "%20");
						URL urls = new URL(urlStred);
						conn = (HttpURLConnection) urls.openConnection();
						conn.setConnectTimeout(20000);
						conn.disconnect();
						final int length = conn.getContentLength();
						InputStream in = conn.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0; 
						time = System.currentTimeMillis();
						while ((len = in.read(buffer)) != -1) {
							total+=len;
							fos.write(buffer, 0, len);
							//���һ��ʱ������ʾ������Ῠ��
							if(System.currentTimeMillis()-time>1000){
								((DownloadAc)context).runOnUiThread(new  Runnable() {
									
									@Override
									public void run() {
										try{
											time = System.currentTimeMillis();
											showNotification("������......",name[name.length-1] ,
													Until.FormetFileSize(total)+"/"+Until.FormetFileSize(length) );
										}catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
							}
						}
						showNotification("�������",name[name.length-1] ,
								Until.FormetFileSize(total)+"/"+Until.FormetFileSize(length) );
						fos.close();
						in.close();
						String filename =path+name[name.length-1];
						files.renameTo(new File(filename));
						download = false;
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.parse("file://" +filename),
							"application/vnd.android.package-archive");
						context.startActivity(intent);
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						if(conn != null)conn.disconnect();
					}
				}
			}).start();
		}
		
	}
	//��֪ͨ��
	private void showNotification(String text,String title,String content){
		
		NotificationManager manager = (NotificationManager)context
							.getSystemService(Context.NOTIFICATION_SERVICE);
		// ����һ��Notification
		Notification notification = new Notification();
		// ������ʾ���ֻ����ϱߵ�״̬����ͼ��
		notification.icon = R.drawable.ic_launcher;
		// ����ǰ��notification���ŵ�״̬���ϵ�ʱ����ʾ����
		notification.tickerText = text;
		/***
		 * notification.contentIntent:һ��PendingIntent���󣬵��û������״̬���ϵ�ͼ��ʱ����Intent�ᱻ����
		 * notification.contentView:���ǿ��Բ���״̬����ͼ����Ƿ�һ��view
		 * notification.deleteIntent ��ǰnotification���Ƴ�ʱִ�е�intent
		 * notification.vibrate ���ֻ���ʱ������������
		 */
		//���������ʾ
//		notification.defaults=Notification.DEFAULT_SOUND;
		//audioStreamType��ֵ����AudioManager�е�ֵ�������������ģʽ
//		notification.audioStreamType= android.media.AudioManager.ADJUST_LOWER;
		
		//�±ߵ�������ʽ�����������
		//notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3"); 
		//notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"); 
		Intent intent = new Intent(context, NotificationCancel.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		//���״̬����ͼ����ֵ���ʾ��Ϣ����
		notification.setLatestEventInfo(context, title, content, pendingIntent);
		manager.notify(1, notification);
	}
	//�ر�֪ͨ��
	public void cancelNotification(){
		NotificationManager manager = (NotificationManager)context
			.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(1);
	}
	//ֹͣ����
	public void stopdown(){
		if(conn!=null)conn.disconnect();
		download = false;
	}

}
