package com.example.androidsdk.jar.xmlparse.data;

import java.util.List;

public class Weather {

	public String version;
	
	public String section;
	public String row;
	public String mobilezipped;
	public String moblierow;
	public String tabid;
	public String moduleid;
	
	public String city;
	public String postal_code;
	public String latitude;
	public String longitude;
	public String forecastdate;
	public String getForecastdate() {
		return forecastdate;
	}

	public void setForecastdate(String forecastdate) {
		this.forecastdate = forecastdate;
	}

	public String currentdatetime;
	public String unitsystem;
	
	public String condition;
	public String tempf;
	public String tempc;
	public String humidity;
	public String icon;
	public String windcondition;
	
	public List<WeatherItem> weatherItem;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getMobilezipped() {
		return mobilezipped;
	}

	public void setMobilezipped(String mobilezipped) {
		this.mobilezipped = mobilezipped;
	}

	public String getMoblierow() {
		return moblierow;
	}

	public void setMoblierow(String moblierow) {
		this.moblierow = moblierow;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCurrentdatetime() {
		return currentdatetime;
	}

	public void setCurrentdatetime(String currentdatetime) {
		this.currentdatetime = currentdatetime;
	}

	public String getUnitsystem() {
		return unitsystem;
	}

	public void setUnitsystem(String unitsystem) {
		this.unitsystem = unitsystem;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTempf() {
		return tempf;
	}

	public void setTempf(String tempf) {
		this.tempf = tempf;
	}

	public String getTempc() {
		return tempc;
	}

	public void setTempc(String tempc) {
		this.tempc = tempc;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWindcondition() {
		return windcondition;
	}

	public void setWindcondition(String windcondition) {
		this.windcondition = windcondition;
	}

	public List<WeatherItem> getWeatherItem() {
		return weatherItem;
	}

	public void setWeatherItem(List<WeatherItem> weatherItem) {
		this.weatherItem = weatherItem;
	}
	
}
