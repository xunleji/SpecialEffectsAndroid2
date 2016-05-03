package com.example.androidsdk.jar.xmlparse.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.androidsdk.jar.xmlparse.data.Weather;
import com.example.androidsdk.jar.xmlparse.data.WeatherItem;

public class WeatherContentHandler extends DefaultHandler {

	private Weather weather;
	private List<WeatherItem> weatherItem;
	private WeatherItem item;
	private int count = 0;
	
	public WeatherContentHandler() {
		super();
		weather = new Weather();
		weatherItem = new ArrayList<WeatherItem>();
	}
	
	public Weather getItem(){
		return weather;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(count==0){
			if(localName.equals("xml_api_reply")){
				weather.setVersion(attributes.getValue("version"));
			}else if(localName.equals("weather")){
				weather.setSection(attributes.getValue("section"));
				weather.setRow(attributes.getValue("row"));
				weather.setMobilezipped(attributes.getValue("mobile_zipped"));
				weather.setMoblierow(attributes.getValue("mobile_row"));
				weather.setTabid(attributes.getValue("tab_id"));
				weather.setModuleid(attributes.getValue("module_id"));
			}else if(localName.equals("city")){
				weather.setCity(attributes.getValue("data"));
			}else if(localName.equals("postal_code")){
				weather.setPostal_code(attributes.getValue("data"));
			}else if(localName.equals("latitude_e6")){
				weather.setLatitude(attributes.getValue("data"));
			}else if(localName.equals("longitude_e6")){
				weather.setLongitude(attributes.getValue("data"));
			}else if(localName.equals("forecast_date")){
				weather.setForecastdate(attributes.getValue("data"));
			}else if(localName.equals("current_date_time")){
				weather.setCurrentdatetime(attributes.getValue("data"));
			}else if(localName.equals("unit_system")){
				weather.setUnitsystem(attributes.getValue("data"));
			}else if(localName.equals("condition")){
				weather.setCondition(attributes.getValue("data"));
			}else if(localName.equals("temp_f")){
				weather.setTempf(attributes.getValue("data"));
			}else if(localName.equals("temp_c")){
				weather.setTempc(attributes.getValue("data"));
			}else if(localName.equals("humidity")){
				weather.setHumidity(attributes.getValue("data"));
			}else if(localName.equals("icon")){
				weather.setIcon(attributes.getValue("data"));
			}else if(localName.equals("wind_condition")){
				weather.setWindcondition(attributes.getValue("data"));
			}
		}
		if(localName.equals("forecast_conditions")){
			item = new WeatherItem();
			count++;
		}
		if(count>0){
			if(localName.equals("day_of_week")){
				item.setWeek(attributes.getValue("data"));
			}else if(localName.equals("low")){
				item.setLow(attributes.getValue("data"));
			}else if(localName.equals("high")){
				item.setHigh(attributes.getValue("data"));
			}else if(localName.equals("icon")){
				item.setIcon(attributes.getValue("data"));
			}else if(localName.equals("condition")){
				item.setCondition(attributes.getValue("data"));
			}
		}
		super.startElement(uri, localName, qName, attributes);
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("forecast_conditions")){
			weatherItem.add(item);
		}
		if(count==3){
			weather.setWeatherItem(weatherItem);
		}
		super.endElement(uri, localName, qName);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

}
