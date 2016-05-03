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
 * �ϵ�����
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
		//���sd�Ƿ����
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
						//����User-Agent
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
							//���һ��ʱ������ʾ������Ῠ��
							if(System.currentTimeMillis()-time>1000){
								((DownloadAc)context).runOnUiThread(new  Runnable() {
									
									@Override
									public void run() {
										try{
											Diary.eLog("55555");
											time = System.currentTimeMillis();
											showNotification("������......",name[name.length-1] ,
													Until.FormetFileSize(raf.length())+"/"+Until.FormetFileSize(length) );
										}catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
							}
						}
						showNotification("�������",name[name.length-1] ,
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
	//��ͣ����
	public void pauseDownload(){
		if(conn!=null) conn.disconnect();
		download = false;
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
						fileSize = conn.getContentLength();// ������Ӧ��ȡ�ļ���С
						if (fileSize <= 0)
							throw new RuntimeException("Unkown file size ");
						// �õ���С�����ý�����
						Message msg = new Message();
						String filename = getFileName(path, conn);
						this.saveFile = new File(dir, filename);/* �����ļ� */
					}
				} catch (Exception e) {
					// throw new RuntimeException("����ʧ��");
					// ���ش���
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
						// ���½���
						Message msg = new Message();
					}
					// �������
					Message msg = new Message();
					threadfile.close();
					inStream.close();
				} catch (Exception e) {
					// throw new RuntimeException("����ʧ��");
					// ���ش���
					Message msg = new Message();
					throw new RuntimeException();
				}
			}
		}).start();
	}
	
	//��ȡ�ļ���
	private String getFileName(String path, HttpURLConnection conn) {
		String filename = path.substring(path.lastIndexOf('/') + 1);
		if (filename == null || "".equals(filename.trim())) {// �����ȡ�����ļ�����
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
			filename = UUID.randomUUID() + ".tmp";// Ĭ��ȡһ���ļ���
		}
		return filename;
	}

}
