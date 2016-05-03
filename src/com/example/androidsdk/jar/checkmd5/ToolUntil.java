package com.example.androidsdk.jar.checkmd5;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.Environment;

public class ToolUntil {

	//获取sd卡路径
	protected static String getSDPath() {
		File sdDir = null;
		try {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();// 获取存储卡根目录
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sdDir == null ? "null" : sdDir.toString();
	}
	
	public static String HttpClientGet(String strurl){
		try{
			//HttpGet连接对象
			String urlstr = strurl;
			urlstr = urlstr.replaceAll(" ", "%20");
			HttpGet httpget = new HttpGet(urlstr);
			//取得HttpClient对象
			org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
			//请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpget);
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				return strResult;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
