package com.example.androidsdk.jar.xmlparse.sax;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.androidsdk.jar.xmlparse.data.ChannelItem;

public class ChannelContent extends DefaultHandler {

	private List<ChannelItem> channelList;
	private ChannelItem rss;
	private StringBuilder sb;
	
	public ChannelContent() {
		super();
		channelList = new ArrayList<ChannelItem>();
	}
	
	public List<ChannelItem> getItem(){
		return channelList;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("item")){
			rss = new ChannelItem();
			rss.setId(attributes.getValue("id"));
			rss.setUrl(attributes.getValue("url"));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		sb = new StringBuilder();
		sb.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		String value = sb.toString();
		if(localName.equals("item")){
			rss.setContent(value);
			channelList.add(rss);
		}
	}
	

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

}
