package com.example.androidsdk.jar.tool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.widget.Toast;

/**
 * ��ȡ��������
 * 
 * @author xujuan
 * 
 */
public class HttpClientUntil {

	/**
	 * HttpURLConnection��GET��ʽ����
	 */
	public static void HttpURLGet(String strurl) {
		String resultData = null;
		try {
			URL url = new URL(strurl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// �õ���ȡ��������
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			// Ϊ�������BufferedReader
			BufferedReader br = new BufferedReader(in);
			String inputline = null;
			// ѭ������ȡ��õ�����
			while ((inputline = br.readLine()) != null) {
				resultData += inputline + "\n";
			}
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * HttpURLConnection��POST��ʽ����
	 */
	public static void HttpURLPost(String strurl) {
		try {
			URL url = new URL(strurl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// ��Ϊ���Ǹ�POST����������Ҫ����ΪTRUR;
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			// POST������ʹ�û���
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.connect();
			// DataOutputStream��
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			// Ҫ�ϴ��Ĳ���
			String content = "����";
			out.writeBytes(content);
			out.flush();
			// ��ȡ���������ص�����
			InputStream in = conn.getInputStream();
			out.close();
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * HttpClient��GET��ʽ����
	 */
	public static String HttpClientGet(String strurl) {
		try {
			// HttpGet���Ӷ���
			HttpGet httpget = new HttpGet(strurl);
			// ȡ��HttpClient����
			org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
			// ����HttpClient��ȡ��HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpget);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ȡ�÷��ص��ַ���
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = strResult.replaceAll("\r\n|\n\r|\r|\n", "");// ȥ����Ϣ�еĻس��ͻ���
				return strResult;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * HttpClient��POST��ʽ����
	 */
	public static String HttpClientPost(String strurl) {
		HttpPost httpRequest = new HttpPost(strurl);
		try {
			// ʹ��NameValuePair������Ҫ���ݵ�Post����
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// ���Ҫ���ݵĲ���
			params.add(new BasicNameValuePair("", ""));
			BasicHttpParams localBasicHttpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
					20000);
			HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient(
					localBasicHttpParams).execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// ȡ����Ӧ�ִ�
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = strResult.replaceAll("\r\n|\n\r|\r|\n", "");// ȥ����Ϣ�еĻس��ͻ���
				return strResult;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// �ϴ�ͼƬ
	public boolean unload(File filebmp) {
		String urlPath = "http://114.80.178.59:8081/guesss/api_upload";
		PostMethod postMethod = new PostMethod(urlPath);
		Part[] part = new Part[1];
		try {
			part[0] = new FilePart("image", filebmp);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		MultipartRequestEntity mrp = new MultipartRequestEntity(part,
				postMethod.getParams());
		postMethod.setRequestEntity(mrp);
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		try {
			client.executeMethod(postMethod);
			System.err.println("result:........"
					+ postMethod.getResponseBodyAsString());
			if ("false".equals(postMethod.getRequestEntity().toString())) {
				return false;
			} else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (client != null) {
				client = null;
			}
			if (postMethod != null) {
				postMethod = null;
			}
		}
	}

	/**
	 * �����ķ�ʽ�ϴ��ļ�
	 * 
	 * @param srcPath
	 */
	private void uploadFile(String srcPath) {

		String uploadUrl = "http://192.168.1.21:9090/upload_file_service/UploadServlet";
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		try {
			URL url = new URL(uploadUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			DataOutputStream dos = new DataOutputStream(
					httpURLConnection.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
					+ srcPath.substring(srcPath.lastIndexOf("/") + 1)
					+ "\""
					+ end);
			dos.writeBytes(end);

			FileInputStream fis = new FileInputStream(srcPath);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);

			}
			fis.close();

			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			dos.flush();

			InputStream is = httpURLConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String result = br.readLine();
			dos.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * HttpGet��ʽͨ��������� HTTPS��վ
	 */
	public static void HttpClientHost() {
		try {
			DefaultHttpClient dfhttpClient = new DefaultHttpClient();
			dfhttpClient.getCredentialsProvider().setCredentials(
					new AuthScope("your_auth_host", 80, "your_realm"),
					new UsernamePasswordCredentials("username", "password"));

			// ���÷�������ַ���˿ڣ�����Э��:
			HttpHost targetHost = new HttpHost("www.verisign.com", 443, "https");
			// ���ô���:
			HttpHost proxy = new HttpHost("192.168.1.1", 80);
			dfhttpClient.getParams().setParameter(
					ConnRoutePNames.DEFAULT_PROXY, proxy);
			// ����һ�� HttpGet ʵ��:
			HttpGet httpGet = new HttpGet("/a/b/c");
			// ���ӷ���������ȡӦ������:
			HttpResponse response = dfhttpClient.execute(targetHost, httpGet);
			// ��ȡӦ������:
			int statusCode = response.getStatusLine().getStatusCode();
			Header[] headers = response.getAllHeaders();
			HttpEntity entity = response.getEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param params
	 *            ����������������Ӧ����name1=value1&name2=value2����ʽ��
	 * @return URL������Զ����Դ����Ӧ
	 */
	public static String sendGet(String url, String params) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// ����ʵ�ʵ�����
			conn.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = conn.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
