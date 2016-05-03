package com.example.androidsdk.jar.xmlparse.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.androidsdk.jar.xmlparse.data.RSSItem;

import android.util.Log;

public class SaxContentHandler extends DefaultHandler{

	private List<RSSItem> rssItem;
	private RSSItem rss;
	private StringBuilder sb;
	private final int ITEM = 1;
	private int currentState = 0;
	
	public SaxContentHandler() {
		super();
		rssItem = new ArrayList<RSSItem>();
	}
	
	public List<RSSItem> getItem(){
		return rssItem;
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("item")){
			rss = new RSSItem();
			currentState = ITEM;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		sb = new StringBuilder();
		sb.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		String value = sb.toString();
		if(currentState==ITEM){
			if(localName.equals("title")){
				rss.setTitle(value);
			}else if(localName.equals("link")){
				rss.setLink(value);
			}else if(localName.equals("category")){
				rss.setCategory(value);
			}else if(localName.equals("description")){
				rss.setDescription(value);
			}else if(localName.equals("pubDate")){
				rss.setPubDate(value);
			}else if(localName.equals("item")){
				rssItem.add(rss);
				currentState = 0;
			}
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

}
