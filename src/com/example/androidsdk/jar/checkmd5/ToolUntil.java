package com.example.androidsdk.jar.checkmd5;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.Environment;

public class ToolUntil {

	//��ȡsd��·��
	protected static String getSDPath() {
		File sdDir = null;
		try {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();// ��ȡ�洢����Ŀ¼
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sdDir == null ? "null" : sdDir.toString();
	}
	
	public static String HttpClientGet(String strurl){
		try{
			//HttpGet���Ӷ���
			String urlstr = strurl;
			urlstr = urlstr.replaceAll(" ", "%20");
			HttpGet httpget = new HttpGet(urlstr);
			//ȡ��HttpClient����
			org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
			//����HttpClient��ȡ��HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpget);
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//ȡ�÷��ص��ַ���
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				return strResult;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
