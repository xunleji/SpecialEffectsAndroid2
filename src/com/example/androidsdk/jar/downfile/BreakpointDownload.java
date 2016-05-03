package com.example.androidsdk.jar.downfile;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;

import com.example.androidsdk.Diary;
import com.example.androidsdk.R;
import com.example.androidsdk.jar.tool.Until;

/**
 * 断点下载
 * @author xujuan
 *
 */
public class BreakpointDownload {

	private Context context;
	public static boolean download = false;
	
	public BreakpointDownload(Context context) {
		super();
		this.context = context;
	}
	
	private String getPath(){
		String sdStatus = Environment.getExternalStorageState();
		//检测sd是否可用
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
		String sdPath = Environment.getExternalStorageDirectory().toString()+"/BreakpointDownload/";
		return sdPath;
	}
	
	private HttpURLConnection conn = null;
	private long time ;
	private RandomAccessFile raf ;
	private long length = 0;
	
	public void saveFile(final String downUrl){
		if(!download){
			download = true;
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
						raf = new RandomAccessFile(files, "rwd");
						long start = raf.length();
						Diary.eLog("start=="+start);
						String urlStred = ((String)downUrl).replaceAll(" ", "%20");
						URL urls = new URL(urlStred);
						
						if (length == 0) {
							conn = (HttpURLConnection) urls.openConnection();
							length = conn.getContentLength();
							conn.disconnect();
						}
						Diary.eLog("222222");
						
						conn = (HttpURLConnection) urls.openConnection();
						//设置User-Agent
						conn.setRequestProperty("User-Agent","NetFox");
						conn.setRequestProperty("RANGE", "bytes=" + start
								+ "-" + length);
						conn.setConnectTimeout(20000); 
						Diary.eLog("333333");
						InputStream in = conn.getInputStream();
						Diary.eLog("444444");
						byte[] buffer = new byte[1024];
						int len = 0; 
						raf.seek(start);
						time = System.currentTimeMillis();
						while ((len = in.read(buffer)) != -1) {
							raf.write(buffer, 0, len);
							//间隔一段时间再显示，否则会卡死
							if(System.currentTimeMillis()-time>1000){
								((DownloadAc)context).runOnUiThread(new  Runnable() {
									
									@Override
									public void run() {
										try{
											Diary.eLog("55555");
											time = System.currentTimeMillis();
											showNotification("下载中......",name[name.length-1] ,
													Until.FormetFileSize(raf.length())+"/"+Until.FormetFileSize(length) );
										}catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
							}
						}
						showNotification("下载完成",name[name.length-1] ,
								Until.FormetFileSize(raf.length())+"/"+Until.FormetFileSize(length) );
						raf.close();
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
	//暂停下载
	public void pauseDownload(){
		if(conn!=null) conn.disconnect();
		download = false;
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
	
	private void download(final String path, final File dir) {
		new Thread(new Runnable() {
			private File saveFile;
			private long fileSize;

			@Override
			public void run() {
				if (!dir.exists())
					dir.mkdirs();
				HttpURLConnection conn = null;
				try {
					URL url = new URL(path);
					conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5 * 1000);
					conn.setRequestMethod("GET");
					conn.setRequestProperty(
							"Accept",
							"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
					conn.setRequestProperty("Accept-Language", "zh-CN");
					conn.setRequestProperty("Referer", path);
					conn.setRequestProperty("Charset", "UTF-8");
					conn.setRequestProperty(
							"User-Agent",
							"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
					conn.setRequestProperty("Connection", "Keep-Alive");
					conn.connect();

					if (conn.getResponseCode() == 200) {
						fileSize = conn.getContentLength();// 根据响应获取文件大小
						if (fileSize <= 0)
							throw new RuntimeException("Unkown file size ");
						// 得到大小，设置进度条
						Message msg = new Message();
						String filename = getFileName(path, conn);
						this.saveFile = new File(dir, filename);/* 保存文件 */
					}
				} catch (Exception e) {
					// throw new RuntimeException("连接失败");
					// 下载错误
					Message msg = new Message();
					throw new RuntimeException();
				}
				int downLength = 0;
				try {
					InputStream inStream = conn.getInputStream();
					byte[] buffer = new byte[2048];
					int offset = 0;
					RandomAccessFile threadfile = new RandomAccessFile(
							saveFile, "rwd");
					threadfile.seek(0);
					while ((offset = inStream.read(buffer, 0, 2048)) != -1) {
						threadfile.write(buffer, 0, offset);
						downLength += offset;
						// 更新进度
						Message msg = new Message();
					}
					// 下载完成
					Message msg = new Message();
					threadfile.close();
					inStream.close();
				} catch (Exception e) {
					// throw new RuntimeException("下载失败");
					// 下载错误
					Message msg = new Message();
					throw new RuntimeException();
				}
			}
		}).start();
	}
	
	//获取文件名
	private String getFileName(String path, HttpURLConnection conn) {
		String filename = path.substring(path.lastIndexOf('/') + 1);
		if (filename == null || "".equals(filename.trim())) {// 如果获取不到文件名称
			for (int i = 0;; i++) {
				String mine = conn.getHeaderField(i);
				if (mine == null)
					break;
				if ("content-disposition".equals(conn.getHeaderFieldKey(i)
						.toLowerCase())) {
					Matcher m = Pattern.compile(".*filename=(.*)").matcher(
							mine.toLowerCase());
					if (m.find())
						return m.group(1);
				}
			}
			filename = UUID.randomUUID() + ".tmp";// 默认取一个文件名
		}
		return filename;
	}

}
