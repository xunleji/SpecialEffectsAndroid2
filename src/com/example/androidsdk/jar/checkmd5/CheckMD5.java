package com.example.androidsdk.jar.checkmd5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import dalvik.system.DexClassLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckMD5 {
	
	private String outfilepath = null;
	private File optimizedDexOutputPath = null;
	private String md5str = null;
	private boolean isok = false;
	private Context context;
	protected static JuzisdkInter lib;
	
	public CheckMD5(Context context) {
		super();
		this.context = context;
		outfilepath = context.getDir("outdex", Context.MODE_PRIVATE).toString();
	}
	
	private String checkFile() {
		String result = null;
		File files = new File(ToolUntil.getSDPath() + "/datas");
		if (!files.exists()) {
			files.mkdirs();
		}
		optimizedDexOutputPath = new File(ToolUntil.getSDPath() + "/datas",
				"data.jar");
		if (optimizedDexOutputPath.exists()) {
			md5str = JuziMd5.md5sum(optimizedDexOutputPath
					.getAbsolutePath());
		} else {
			md5str = null;
		}
		result = ToolUntil.HttpClientGet("http://ansi.m2c.mobi/ws/chk/" + md5str);
		
		return result;
	}
	//检查MD5
	public void TocheckMD5(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					String msg = checkFile();
					if(msg==null)return;
					if(msg.equals("")){
						isok = urlDownloadToFile(msg, ToolUntil.getSDPath()+ "/datas/data.jar");
						if(isok){
							onlaucher();
						}else{
							Toast.makeText(context, "下载失败!", 2000).show();
						}
					}else{
						onlaucher();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	//启动开始界面
	private void onlaucher(){
		try {
			DexClassLoader cl = new DexClassLoader(
					optimizedDexOutputPath.getAbsolutePath(),
					outfilepath, null, context.getClassLoader());
			Class libProviderClazz = null;
			libProviderClazz = cl.loadClass("com.juzidata.sdk.DynamicTest");
			lib = (JuzisdkInter) libProviderClazz.newInstance();
			lib.start();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Proxy mProxy = null;
	private int connectTimeout = 30 * 1000;
	private int readTimeout = 30 * 1000;

	//下载文件
	private boolean urlDownloadToFile(String strurl, String path) {
		boolean bRet = false;
		detectProxy();
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strurl);
			if (mProxy != null) {
				conn = (HttpURLConnection) url.openConnection(mProxy);
			} else {
				conn = (HttpURLConnection) url.openConnection();
			}
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.connect();

			File fl = new File(path);
			if (fl.exists()) {
				fl.delete();
				fl.deleteOnExit();
			}

			InputStream is = conn.getInputStream();
			File file = new File(path + ".temp");
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);

			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			file.renameTo(new File(path));
			fos.close();
			is.close();
			bRet = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return bRet;
	}

	// 检查代理，是否cnwap接入
	private void detectProxy() {
		ConnectivityManager cm = (ConnectivityManager)context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni != null && ni.isAvailable()
				&& ni.getType() == ConnectivityManager.TYPE_MOBILE) {
			String proxyHost = android.net.Proxy.getDefaultHost();
			int port = android.net.Proxy.getDefaultPort();
			if (proxyHost != null) {
				final InetSocketAddress sa = new InetSocketAddress(proxyHost,
						port);
				mProxy = new Proxy(Proxy.Type.HTTP, sa);
			}
		}
	}

}
