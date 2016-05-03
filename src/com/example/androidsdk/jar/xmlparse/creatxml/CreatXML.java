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
 * ����xml�ļ�
 * @author xujuan
 *
 */
public class CreatXML {
	
	public void BuildXMLDoc(){
	       // �������ڵ� list;
	        Element root = new Element("list");
	       // ���ڵ���ӵ��ĵ��У�
	        Document Doc = new Document(root);
	       // �˴� for ѭ�����滻�� ���� ���ݿ��Ľ��������;
	       for (int i = 0; i < 5; i++) {
	           // �����ڵ� user;
	           Element elements = new Element("user");
	           // �� user �ڵ�������� id;
	           elements.setAttribute("id", "" + i);
	           // �� user �ڵ�����ӽڵ㲢��ֵ��
	           // new Element("name")�е� "name" �滻�ɱ�����Ӧ�ֶΣ�setText("xuehui")�� "xuehui �滻�ɱ��м�¼ֵ��
	           elements.addContent(new Element("name").setText("xuehui"));
	           elements.addContent(new Element("age").setText("28"));
	           elements.addContent(new Element("sex").setText("Male"));
	           // �����ڵ�list���user�ӽڵ�;
	           root.addContent(elements);
	       }
	        XMLOutputter XMLOut = new XMLOutputter();
	       // ��� user.xml �ļ���
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
