package com.example.androidsdk.jar.xmlparse.creatxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

import android.util.Log;

import com.example.androidsdk.jar.tool.Until;

/**
 * 生成xml文件
 * @author xujuan
 *
 */
public class CreatXML {
	
	public void BuildXMLDoc(){
	       // 创建根节点 list;
	        Element root = new Element("list");
	       // 根节点添加到文档中；
	        Document Doc = new Document(root);
	       // 此处 for 循环可替换成 遍历 数据库表的结果集操作;
	       for (int i = 0; i < 5; i++) {
	           // 创建节点 user;
	           Element elements = new Element("user");
	           // 给 user 节点添加属性 id;
	           elements.setAttribute("id", "" + i);
	           // 给 user 节点添加子节点并赋值；
	           // new Element("name")中的 "name" 替换成表中相应字段，setText("xuehui")中 "xuehui 替换成表中记录值；
	           elements.addContent(new Element("name").setText("xuehui"));
	           elements.addContent(new Element("age").setText("28"));
	           elements.addContent(new Element("sex").setText("Male"));
	           // 给父节点list添加user子节点;
	           root.addContent(elements);
	       }
	        XMLOutputter XMLOut = new XMLOutputter();
	       // 输出 user.xml 文件；
	        try{
	        	Log.e("dddd", "BuildXMLDoc");
	        	File file = new File(Until.getSDpath()+"/XML");
	        	if(!file.exists())file.mkdirs();
	        	File files = new File(Until.getSDpath()+"/XML", "user.xml");
	        	if(!files.exists())files.createNewFile();
	        	XMLOut.output(Doc, new FileOutputStream(files));
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
