package com.example.androidsdk.jar.xmlparse.dom;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.example.androidsdk.jar.xmlparse.data.ChannelItem;
import com.example.androidsdk.jar.xmlparse.data.RSSItem;
import com.example.androidsdk.jar.xmlparse.data.Weather;
import com.example.androidsdk.jar.xmlparse.data.WeatherItem;

public class DomParser {

	public static List<RSSItem> getRssParser(InputStream is){
		List<RSSItem> listRssitem = new ArrayList<RSSItem>();
		RSSItem rssitem = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			NodeList nodelist =element.getChildNodes();
			for(int i = 0;i<nodelist.getLength();i++){
				
				Node nodechild = nodelist.item(i);
				if(nodechild.getNodeType()==Element.ELEMENT_NODE&&
						nodechild.getNodeName().equals("item")){
					NodeList nodechildlist = nodechild.getChildNodes();
					for(int j = 0;j<nodechildlist.getLength();j++){
						Node nodes = nodechildlist.item(j);
						
						if(nodes.getNodeType()==Element.ELEMENT_NODE&&
								nodes.getNodeName().equals("title")){
							rssitem = new RSSItem();
							rssitem.setTitle(nodes.getFirstChild().getNodeValue());
						}else if(nodes.getNodeType()==Element.ELEMENT_NODE&&
								nodes.getNodeName().equals("link")){
							rssitem.setLink(nodes.getFirstChild().getNodeValue());
						}else if(nodes.getNodeType()==Element.ELEMENT_NODE&&
								nodes.getNodeName().equals("category")){
							rssitem.setCategory(nodes.getFirstChild().getNodeValue());
						}else if(nodes.getNodeType()==Element.ELEMENT_NODE&&
								nodes.getNodeName().equals("description")){
							rssitem.setDescription(nodes.getFirstChild().getNodeValue());
						}else if(nodes.getNodeType()==Element.ELEMENT_NODE&&
								nodes.getNodeName().equals("pubDate")){
							rssitem.setPubDate(nodes.getFirstChild().getNodeValue());
						}
					}
					listRssitem.add(rssitem);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listRssitem;
	}
	
	public static List<ChannelItem> getChannelParser(InputStream is){
		List<ChannelItem> listRssitem = new ArrayList<ChannelItem>();
		ChannelItem rssitem = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			NodeList nodelist =element.getElementsByTagName("item");
			for(int i = 0;i<nodelist.getLength();i++){
				rssitem = new ChannelItem();
				Element elements =(Element)nodelist.item(i);
				rssitem.setId(elements.getAttribute("id"));
				rssitem.setUrl(elements.getAttribute("url"));
				rssitem.setContent(elements.getFirstChild().getNodeValue());
				listRssitem.add(rssitem);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listRssitem;
	}

	public static Weather getWeatherDomParser(InputStream is){
		Weather weather = new Weather();
		List<WeatherItem> listweatherItem = new ArrayList<WeatherItem>();
		WeatherItem weatherItem = null;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			weather.setVersion(element.getAttribute("version"));
			Element zielement = (Element)element.getElementsByTagName("weather").item(0);
			weather.setSection(zielement.getAttribute("section"));
			weather.setRow(zielement.getAttribute("row"));
			weather.setMobilezipped(zielement.getAttribute("mobile_zipped"));
			weather.setMoblierow(zielement.getAttribute("mobile_row"));
			weather.setTabid(zielement.getAttribute("tab_id"));
			weather.setModuleid(zielement.getAttribute("module_id"));
			NodeList nodelist = zielement.getChildNodes();
			int nodelistsize = nodelist.getLength();
			for(int i = 0;i<nodelistsize;i++){
				
				Node node =nodelist.item(i);
				if(node.getNodeName().equals("forecast_information")){
					NodeList nodelistchild = node.getChildNodes();
					int size = nodelistchild.getLength();
					for(int j = 0;j<size;j++){
						
						Node nodechild = nodelistchild.item(j);
						if(nodechild.getNodeName().equals("city")){
							weather.setCity(((Element)nodechild).getAttribute("data"));
							NodeList nodelists = nodechild.getChildNodes();
							int sizes = nodelists.getLength();
							for(int m = 0;m<sizes;m++){
								
								Node nodes = nodelists.item(m);
								if(nodes.getNodeName().equals("postal_code")){
									weather.setPostal_code(((Element)nodes).getAttribute("data"));
									NodeList nodelistss = nodes.getChildNodes();
									int sizess = nodelistss.getLength();
									for(int n = 0;n<sizess;n++){
										
										Node nodess = nodelistss.item(n);
										if(nodess.getNodeName().equals("latitude_e6")){
											weather.setLatitude(((Element)nodess).getAttribute("data"));
											NodeList nodelistsss = nodess.getChildNodes();
											int sizesss = nodelistsss.getLength();
											for(int a = 0;a<sizesss;a++){
												
												Node nodesss = nodelistsss.item(a);
												if(nodesss.getNodeName().equals("longitude_e6")){
													weather.setLongitude(((Element)nodesss).getAttribute("data"));
													NodeList nodelistssss = nodesss.getChildNodes();
													int sizessss = nodelistssss.getLength();
													for(int b = 0;b<sizessss;b++){
														
														Node nodessss = nodelistssss.item(b);
														if(nodessss.getNodeName().equals("forecast_date")){
															weather.setForecastdate(((Element)nodessss).getAttribute("data"));
															NodeList nodelistsssss = nodessss.getChildNodes();
															int sizesssss = nodelistsssss.getLength();
															for(int c = 0;c<sizesssss;c++){
																
																Node nodesssss = nodelistsssss.item(c);
																if(nodesssss.getNodeName().equals("current_date_time")){
																	weather.setCurrentdatetime(((Element)nodesssss).getAttribute("data"));
																	NodeList nodelistssssss = nodesssss.getChildNodes();
																	int sizessssss = nodelistssssss.getLength();
																	for(int d = 0;d<sizessssss;d++){
																		
																		Node nodessssss = nodelistssssss.item(c);
																		if(nodessssss.getNodeName().equals("unit_system")){
																			weather.setUnitsystem(((Element)nodessssss).getAttribute("data"));
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
				}else if(node.getNodeName().equals("current_conditions")){
					NodeList nodelistchild = node.getChildNodes();
					int size = nodelistchild.getLength();
					for(int j = 0;j<size;j++){
						
						Node nodechild = nodelistchild.item(j);
						if(nodechild.getNodeName().equals("condition")){
							weather.setCondition(((Element)nodechild).getAttribute("data"));
							NodeList nodelists = nodechild.getChildNodes();
							int sizes = nodelists.getLength();
							for(int m = 0;m<sizes;m++){
								
								Node nodes = nodelists.item(m);
								if(nodes.getNodeName().equals("temp_f")){
									weather.setTempf(((Element)nodes).getAttribute("data"));
									NodeList nodelistss = nodes.getChildNodes();
									int sizess = nodelistss.getLength();
									for(int n = 0;n<sizess;n++){
										
										Node nodess = nodelistss.item(n);
										if(nodess.getNodeName().equals("temp_c")){
											weather.setTempc(((Element)nodess).getAttribute("data"));
											NodeList nodelistsss = nodess.getChildNodes();
											int sizesss = nodelistsss.getLength();
											for(int a = 0;a<sizesss;a++){
												
												Node nodesss = nodelistsss.item(a);
												if(nodesss.getNodeName().equals("humidity")){
													weather.setHumidity(((Element)nodesss).getAttribute("data"));
													NodeList nodelistssss = nodesss.getChildNodes();
													int sizessss = nodelistssss.getLength();
													for(int b = 0;b<sizessss;b++){
														
														Node nodessss = nodelistssss.item(b);
														if(nodessss.getNodeName().equals("icon")){
															weather.setIcon(((Element)nodessss).getAttribute("data"));
															NodeList nodelistsssss = nodessss.getChildNodes();
															int sizesssss = nodelistsssss.getLength();
															for(int c = 0;c<sizesssss;c++){
																
																Node nodesssss = nodelistsssss.item(c);
																if(nodesssss.getNodeName().equals("wind_condition")){
																	weather.setWindcondition(((Element)nodesssss).getAttribute("data"));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}else if(node.getNodeName().equals("forecast_conditions")){
					weatherItem = new WeatherItem();
					
					NodeList nodelistchild = node.getChildNodes();
					int size = nodelistchild.getLength();
					for(int j = 0;j<size;j++){
						
						Node nodechild = nodelistchild.item(j);
						if(nodechild.getNodeName().equals("day_of_week")){
							weatherItem.setWeek(((Element)nodechild).getAttribute("data"));
							NodeList nodelists = nodechild.getChildNodes();
							int sizes = nodelists.getLength();
							for(int m = 0;m<sizes;m++){
								
								Node nodes = nodelists.item(m);
								if(nodes.getNodeName().equals("low")){
									weatherItem.setLow(((Element)nodes).getAttribute("data"));
									NodeList nodelistss = nodes.getChildNodes();
									int sizess = nodelistss.getLength();
									for(int n = 0;n<sizess;n++){
										
										Node nodess = nodelistss.item(n);
										if(nodess.getNodeName().equals("high")){
											weatherItem.setHigh(((Element)nodess).getAttribute("data"));
											NodeList nodelistsss = nodess.getChildNodes();
											int sizesss = nodelistsss.getLength();
											for(int a = 0;a<sizesss;a++){
												
												Node nodesss = nodelistsss.item(a);
												if(nodesss.getNodeName().equals("icon")){
													weatherItem.setIcon(((Element)nodesss).getAttribute("data"));
													NodeList nodelistssss = nodesss.getChildNodes();
													int sizessss = nodelistssss.getLength();
													for(int b = 0;b<sizessss;b++){
														
														Node nodessss = nodelistssss.item(b);
														if(nodessss.getNodeName().equals("condition")){
															weatherItem.setCondition(((Element)nodessss).getAttribute("data"));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					listweatherItem.add(weatherItem);
				}
			}
			weather.setWeatherItem(listweatherItem);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}
}
