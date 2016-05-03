package com.example.androidsdk.jar.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.net.ParseException;

/**
 * 工具类
 * @author xujuan
 *
 */
public class WebServiceUtil {

	/**
	 * 获取城市列表
	 * @return
	 */
	public static ArrayList<String> getCityList(){
		//命名空间
		String serviceNamespace = "http://WebXml.com.cn/";
		//请求URL
		String serviceURL = "http://webservice.webxml.com.cn/WebService/WeatherWS.asmx";
		//调用的方法
		String methodName = "getRegionProvince";
		//实例化soapobject
		SoapObject request = new SoapObject(serviceNamespace, methodName);
		//获得序列化的envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);		
		envelope.bodyOut = request;
		(new MarshalBase64()).register(envelope);
		//android传输对象
		AndroidHttpTransport ht = new AndroidHttpTransport(serviceURL);
		ht.debug = true;
		try{
			ht.call("http://WebXml.com.cn/getRegionProvince", envelope);
			if(envelope.getResponse()!=null){
				return parse(envelope.bodyIn.toString());
			}
		}catch (Exception e) {}
		return null;
	}

	/**
	 * 解析器
	 * @param str
	 * @return
	 */
	private static ArrayList<String> parse(String str) {
		// TODO Auto-generated method stub
		String tmp;
		ArrayList<String> list = new ArrayList<String>();
		if(str!=null&&str.length()>0){
			int start = str.indexOf("string");
			int end = str.lastIndexOf(";");
			tmp = str.substring(start, end-3);
			String[] test = tmp.split(";");
			for(int i = 0;i<test.length;i++){
				if(i==0){
					tmp = test[i].substring(7);
				}else {
					tmp = test[i].substring(8);
				}
				int index = tmp.indexOf(",");
				list.add(tmp.substring(0, index));	
			}
		}
		return list;
	}
	/**
	 * 通过传递城市名称获得天气信息
	 * @param cityName
	 * @return
	 */
	public static String getWeatherMsgByCity(String cityName){
		String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather";
		HttpPost request = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("theCityCode", cityName));
		params.add(new BasicNameValuePair("theUserID", ""));
		String result = null;
		try{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			request.setEntity(entity);
			HttpResponse response = new DefaultHttpClient().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				return parse2(result);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;		
	}

	private static String parse2(String result) {
		// TODO Auto-generated method stub
		String temp;
		String[] temps;
		List list = new ArrayList();
		StringBuilder sb = new StringBuilder("");
		if(result!=null&&result.length()>0){
			temp = result.substring(result.indexOf("<string>"));
			temps = temp.split("</string>");
			for(int i = 0;i<temps.length;i++){
				sb.append(temps[i].substring(12));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
