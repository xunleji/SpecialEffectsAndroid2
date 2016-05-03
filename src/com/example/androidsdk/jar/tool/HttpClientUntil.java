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
 * 获取网络数据
 * 
 * @author xujuan
 * 
 */
public class HttpClientUntil {

	/**
	 * HttpURLConnection的GET方式传参
	 */
	public static void HttpURLGet(String strurl) {
		String resultData = null;
		try {
			URL url = new URL(strurl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// 得到读取的内容流
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			// 为输出创建BufferedReader
			BufferedReader br = new BufferedReader(in);
			String inputline = null;
			// 循环来读取获得的数据
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
	 * HttpURLConnection的POST方式传参
	 */
	public static void HttpURLPost(String strurl) {
		try {
			URL url = new URL(strurl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// 因为这是个POST请求，设立需要设置为TRUR;
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			// POST请求不能使用缓存
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.connect();
			// DataOutputStream流
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			// 要上传的参数
			String content = "参数";
			out.writeBytes(content);
			out.flush();
			// 读取服务器返回的数据
			InputStream in = conn.getInputStream();
			out.close();
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * HttpClient的GET方式传参
	 */
	public static String HttpClientGet(String strurl) {
		try {
			// HttpGet连接对象
			HttpGet httpget = new HttpGet(strurl);
			// 取得HttpClient对象
			org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpget);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = strResult.replaceAll("\r\n|\n\r|\r|\n", "");// 去掉信息中的回车和换行
				return strResult;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * HttpClient的POST方式传参
	 */
	public static String HttpClientPost(String strurl) {
		HttpPost httpRequest = new HttpPost(strurl);
		try {
			// 使用NameValuePair来保存要传递的Post参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 添加要传递的参数
			params.add(new BasicNameValuePair("", ""));
			BasicHttpParams localBasicHttpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
					20000);
			HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient(
					localBasicHttpParams).execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 取出回应字串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = strResult.replaceAll("\r\n|\n\r|\r|\n", "");// 去掉信息中的回车和换行
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

	// 上传图片
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
	 * 以流的方式上传文件
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
	 * HttpGet方式通过代理访问 HTTPS网站
	 */
	public static void HttpClientHost() {
		try {
			DefaultHttpClient dfhttpClient = new DefaultHttpClient();
			dfhttpClient.getCredentialsProvider().setCredentials(
					new AuthScope("your_auth_host", 80, "your_realm"),
					new UsernamePasswordCredentials("username", "password"));

			// 设置服务器地址，端口，访问协议:
			HttpHost targetHost = new HttpHost("www.verisign.com", 443, "https");
			// 设置代理:
			HttpHost proxy = new HttpHost("192.168.1.1", 80);
			dfhttpClient.getParams().setParameter(
					ConnRoutePNames.DEFAULT_PROXY, proxy);
			// 创建一个 HttpGet 实例:
			HttpGet httpGet = new HttpGet("/a/b/c");
			// 连接服务器并获取应答数据:
			HttpResponse response = dfhttpClient.execute(targetHost, httpGet);
			// 读取应答数据:
			int statusCode = response.getStatusLine().getStatusCode();
			Header[] headers = response.getAllHeaders();
			HttpEntity entity = response.getEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String params) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
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
