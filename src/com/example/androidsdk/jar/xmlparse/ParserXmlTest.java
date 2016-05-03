package com.example.androidsdk.jar.xmlparse;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.xmlparse.creatxml.CreatXML;
import com.example.androidsdk.jar.xmlparse.data.ChannelItem;
import com.example.androidsdk.jar.xmlparse.data.RSSItem;
import com.example.androidsdk.jar.xmlparse.data.Weather;
import com.example.androidsdk.jar.xmlparse.data.WeatherItem;
import com.example.androidsdk.jar.xmlparse.dom.DomParser;
import com.example.androidsdk.jar.xmlparse.pull.PullParser;
import com.example.androidsdk.jar.xmlparse.sax.SaxParser;

public class ParserXmlTest extends Activity implements OnClickListener{

	private Button sax,dom,pull,createxml;
	private Weather weather;
	private List<RSSItem> rsslist;
	private List<ChannelItem> channellist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parserxml);
		sax = (Button)findViewById(R.id.sax);
		dom = (Button)findViewById(R.id.dom);
		pull = (Button)findViewById(R.id.pull);
		createxml = (Button)findViewById(R.id.createxml);
		sax.setOnClickListener(this);
		dom.setOnClickListener(this);
		pull.setOnClickListener(this);
		createxml.setOnClickListener(onclick);
	}
	
	private OnClickListener onclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v==createxml){
				CreatXML creatxml = new CreatXML();
				creatxml.BuildXMLDoc();
			}
		}
	};
	
	@Override
	public void onClick(View v) {
		if(v==sax){
			//channel解析
			channellist = SaxParser.saxchannelParser(ParserXmlTest.this);
			//RSS解析
			rsslist = SaxParser.saxrssParser(ParserXmlTest.this);
			//Weather解析
			weather = SaxParser.saxweatherParser(ParserXmlTest.this);
		}else if(v==dom){
			//channel解析
			InputStream is1 = ParserXmlTest.this.getResources().openRawResource(R.raw.channels);
			channellist = DomParser.getChannelParser(is1);
			//RSS解析
			InputStream is2 = ParserXmlTest.this.getResources().openRawResource(R.raw.rss);
			rsslist = DomParser.getRssParser(is2); 
			//Weather解析
			InputStream is = ParserXmlTest.this.getResources().openRawResource(R.raw.weather);
			weather = DomParser.getWeatherDomParser(is);
		}else if(v==pull){
			//channel解析
			InputStream is1 = ParserXmlTest.this.getResources().openRawResource(R.raw.channels);
			channellist = PullParser.getchannelParser(is1);
			//RSS解析
			InputStream is2 = ParserXmlTest.this.getResources().openRawResource(R.raw.rss);
			rsslist = PullParser.getrssParser(is2);
			//Weather解析
			InputStream is = ParserXmlTest.this.getResources().openRawResource(R.raw.weather);
			weather = PullParser.getWeatherParser(is);
		}
		//channel解析
		for(int i = 0;i<channellist.size();i++){
			String id = channellist.get(i).getId();
			String url = channellist.get(i).getUrl();
			String content = channellist.get(i).getContent();
			Log.e("parserxml", "id="+id+" url="+url+" content="+content);
		}
		Log.e("parserxml", "==============================================");
		//RSS解析
		for(int i = 0;i<rsslist.size();i++){
			String title = rsslist.get(i).getTitle();
			String link = rsslist.get(i).getLink();
			String category = rsslist.get(i).getCategory();
			String description = rsslist.get(i).getDescription();
			String pubDate = rsslist.get(i).getPubDate();
			Log.e("parserxml", "title="+title+" link="+link+" category="
					+category+" description="+description+" pubDate="+pubDate);
		}
		Log.e("parserxml", "==============================================");
		//Weather解析
		Log.e("DOMPARSER", "version="+weather.getVersion());
		Log.e("DOMPARSER", "section="+weather.getSection());
		Log.e("DOMPARSER", "row="+weather.getRow());
		Log.e("DOMPARSER", "mobile_zipped="+weather.getMobilezipped());
		Log.e("DOMPARSER", "mobile_row="+weather.getMoblierow());
		Log.e("DOMPARSER", "tab_id="+weather.getTabid());
		Log.e("DOMPARSER", "module_id="+weather.getModuleid());
		Log.e("DOMPARSER", "city="+weather.getCity());
		Log.e("DOMPARSER", "postal_code="+weather.getPostal_code());
		Log.e("DOMPARSER", "latitude_e6="+weather.getLatitude());
		Log.e("DOMPARSER", "longitude_e6="+weather.getLongitude());
		Log.e("DOMPARSER", "forecast_date="+weather.getForecastdate());
		Log.e("DOMPARSER", "current_date_time="+weather.getCurrentdatetime());
		Log.e("DOMPARSER", "unit_system="+weather.getUnitsystem());
		Log.e("DOMPARSER", "condition="+weather.getCondition());
		Log.e("DOMPARSER", "temp_f="+weather.getTempf());
		Log.e("DOMPARSER", "temp_c="+weather.getTempc());
		Log.e("DOMPARSER", "humidity="+weather.getHumidity());
		Log.e("DOMPARSER", "icon="+weather.getIcon());
		Log.e("DOMPARSER", "wind_condition="+weather.getWindcondition());
		List<WeatherItem> item = weather.getWeatherItem();
		for(int i = 0;i<item.size();i++){
			Log.e("DOMPARSER", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			Log.e("DOMPARSER", "day_of_week="+item.get(i).getWeek());
			Log.e("DOMPARSER", "low="+item.get(i).getLow());
			Log.e("DOMPARSER", "high="+item.get(i).getHigh());
			Log.e("DOMPARSER", "icon="+item.get(i).getIcon());
			Log.e("DOMPARSER", "condition="+item.get(i).getCondition());
		}
	}

}
