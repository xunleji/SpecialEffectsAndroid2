package com.example.androidsdk.jar.xmlparse.sax;

import java.util.List;

import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.xmlparse.data.ChannelItem;
import com.example.androidsdk.jar.xmlparse.data.RSSItem;
import com.example.androidsdk.jar.xmlparse.data.Weather;

import android.content.Context;

public class SaxParser {

	public static Weather saxweatherParser(Context context){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			WeatherContentHandler contenthandler = new WeatherContentHandler();
			reader.setContentHandler(contenthandler);
			InputSource is = new InputSource(context.getResources().openRawResource(R.raw.weather));
			reader.parse(is);
			return contenthandler.getItem();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<ChannelItem> saxchannelParser(Context context){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			ChannelContent contenthandler = new ChannelContent();
			reader.setContentHandler(contenthandler);
			InputSource is = new InputSource(context.getResources().openRawResource(R.raw.channels));
			reader.parse(is);
			return contenthandler.getItem();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<RSSItem> saxrssParser(Context context){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			SaxContentHandler contenthandler = new SaxContentHandler();
			reader.setContentHandler(contenthandler);
			InputSource is = new InputSource(context.getResources().openRawResource(R.raw.rss));
			reader.parse(is);
			return contenthandler.getItem();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
