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
 * 直接下载
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
		//检测sd是否可用
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
			showNotification("正在获取数据......","正在获取下载数据......","");
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
							//间隔一段时间再显示，否则会卡死
							if(System.currentTimeMillis()-time>1000){
								((DownloadAc)context).runOnUiThread(new  Runnable() {
									
									@Override
									public void run() {
										try{
											time = System.currentTimeMillis();
											showNotification("下载中......",name[name.length-1] ,
													Until.FormetFileSize(total)+"/"+Until.FormetFileSize(length) );
										}catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
							}
						}
						showNotification("下载完成",name[name.length-1] ,
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
	//打开通知栏
	private void showNotification(String text,String title,String content){
		
		NotificationManager manager = (NotificationManager)context
							.getSystemService(Context.NOTIFICATION_SERVICE);
		// 创建一个Notification
		Notification notification = new Notification();
		// 设置显示在手机最上边的状态栏的图标
		notification.icon = R.drawable.ic_launcher;
		// 当当前的notification被放到状态栏上的时候，提示内容
		notification.tickerText = text;
		/***
		 * notification.contentIntent:一个PendingIntent对象，当用户点击了状态栏上的图标时，该Intent会被触发
		 * notification.contentView:我们可以不在状态栏放图标而是放一个view
		 * notification.deleteIntent 当前notification被移除时执行的intent
		 * notification.vibrate 当手机震动时，震动周期设置
		 */
		//添加声音提示
//		notification.defaults=Notification.DEFAULT_SOUND;
		//audioStreamType的值必须AudioManager中的值，代表着响铃的模式
//		notification.audioStreamType= android.media.AudioManager.ADJUST_LOWER;
		
		//下边的两个方式可以添加音乐
		//notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3"); 
		//notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"); 
		Intent intent = new Intent(context, NotificationCancel.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		//点击状态栏的图标出现的提示信息设置
		notification.setLatestEventInfo(context, title, content, pendingIntent);
		manager.notify(1, notification);
	}
	//关闭通知栏
	public void cancelNotification(){
		NotificationManager manager = (NotificationManager)context
			.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(1);
	}
	//停止下载
	public void stopdown(){
		if(conn!=null)conn.disconnect();
		download = false;
	}

}
