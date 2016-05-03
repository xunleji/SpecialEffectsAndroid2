package com.example.androidsdk.jar.xmlparse.pull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import android.util.Log;
import android.util.Xml;
import com.example.androidsdk.jar.xmlparse.data.ChannelItem;
import com.example.androidsdk.jar.xmlparse.data.RSSItem;
import com.example.androidsdk.jar.xmlparse.data.Weather;
import com.example.androidsdk.jar.xmlparse.data.WeatherItem;

public class PullParser {
	
	public static List<ChannelItem> getchannelParser(InputStream is){
		List<ChannelItem> listChannel = null;
		ChannelItem channelItem = null;
		try{
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "UTF-8");
			int event = parser.getEventType();
			while(event!=XmlPullParser.END_DOCUMENT){
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					listChannel = new ArrayList<ChannelItem>();
					break;
				case XmlPullParser.START_TAG:
					if("item".equals(parser.getName())){
						channelItem = new ChannelItem();
						channelItem.setId(parser.getAttributeValue(0));
						channelItem.setUrl(parser.getAttributeValue(1));
					}else if("name".equals(parser.getName())){
						channelItem.setContent(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if("item".equals(parser.getName())){
						listChannel.add(channelItem);
						channelItem = null;
					}
					break;
				}
				event = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listChannel;
	}
	
	public static List<RSSItem> getrssParser(InputStream is){
		List<RSSItem> listRss = null;
		RSSItem rssItem = null;
		try{
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "UTF-8");
			int event = parser.getEventType();
			while(event!=XmlPullParser.END_DOCUMENT){
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					listRss = new ArrayList<RSSItem>();
					break;
				case XmlPullParser.START_TAG:
					if("item".equals(parser.getName())){
						rssItem = new RSSItem();
					}
					if(rssItem!=null){
						if("title".equals(parser.getName())){
							rssItem.setTitle(parser.nextText());
						}else if("link".equals(parser.getName())){
							rssItem.setLink(parser.nextText());
						}else if("category".equals(parser.getName())){
							rssItem.setCategory(parser.nextText());
						}else if("description".equals(parser.getName())){
							rssItem.setDescription(parser.nextText());
						}else if("pubDate".equals(parser.getName())){
							rssItem.setPubDate(parser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if("item".equals(parser.getName())){
						listRss.add(rssItem);
						rssItem = null;
					}
					break;
				}
				event = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listRss;
	}
	
	public static Weather getWeatherParser(InputStream is){
		int count = 0;
		Weather weather = null;
		List<WeatherItem> listweatherItem = null;
		WeatherItem weatherItem = null;
		try{
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "UTF-8");
			int event = parser.getEventType();
			while (event!=XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					weather = new Weather();
					listweatherItem = new ArrayList<WeatherItem>();
					break;
				case XmlPullParser.START_TAG:
					if("xml_api_reply".equals(parser.getName())){
						weather.setVersion(parser.getAttributeValue(0));
					}
					if("weather".equals(parser.getName())){
						weather.setSection(parser.getAttributeValue(0));
						weather.setRow(parser.getAttributeValue(1));
						weather.setMobilezipped(parser.getAttributeValue(2));
						weather.setMoblierow(parser.getAttributeValue(3));
						weather.setTabid(parser.getAttributeValue(4));
						weather.setModuleid(parser.getAttributeValue(5));
					}
					if(count==0){
						if("city".equals(parser.getName())){
							weather.setCity(parser.getAttributeValue(0));
						}
						if("postal_code".equals(parser.getName())){
							weather.setPostal_code(parser.getAttributeValue(0));
						}
						if("latitude_e6".equals(parser.getName())){
							weather.setLatitude(parser.getAttributeValue(0));
						}
						if("longitude_e6".equals(parser.getName())){
							weather.setLongitude(parser.getAttributeValue(0));
						}
						if("forecast_date".equals(parser.getName())){
							weather.setForecastdate(parser.getAttributeValue(0));
						}
						if("current_date_time".equals(parser.getName())){
							weather.setCurrentdatetime(parser.getAttributeValue(0));
						}
						if("unit_system".equals(parser.getName())){
							weather.setUnitsystem(parser.getAttributeValue(0));
						}
						
						if("condition".equals(parser.getName())){
							weather.setCondition(parser.getAttributeValue(0));
						}
						if("temp_f".equals(parser.getName())){
							weather.setTempf(parser.getAttributeValue(0));
						}
						if("temp_c".equals(parser.getName())){
							weather.setTempc(parser.getAttributeValue(0));
						}
						if("humidity".equals(parser.getName())){
							weather.setHumidity(parser.getAttributeValue(0));
						}
						if("icon".equals(parser.getName())){
							weather.setIcon(parser.getAttributeValue(0));
						}
						if("wind_condition".equals(parser.getName())){
							weather.setWindcondition(parser.getAttributeValue(0));
						}
					}
					if("forecast_conditions".equals(parser.getName())){
						weatherItem = new WeatherItem();
						count++;
					}
					if(count>0){
						if("day_of_week".equals(parser.getName())){
							weatherItem.setWeek(parser.getAttributeValue(0));
						}
						if("low".equals(parser.getName())){
							weatherItem.setLow(parser.getAttributeValue(0));
						}
						if("high".equals(parser.getName())){
							weatherItem.setHigh(parser.getAttributeValue(0));
						}
						if("icon".equals(parser.getName())){
							weatherItem.setIcon(parser.getAttributeValue(0));
						}
						if("condition".equals(parser.getName())){
							weatherItem.setCondition(parser.getAttributeValue(0));
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if("forecast_conditions".equals(parser.getName())){
						listweatherItem.add(weatherItem);
					}
					if(count==3){
						weather.setWeatherItem(listweatherItem);
					}
					break;
				}
				event = parser.next();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return weather; 
	}
}
