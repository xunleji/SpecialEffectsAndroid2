package com.example.androidsdk.jar.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

/**
 * ��assets�µ����ݿ�д��sd����
 * @author xujuan
 *
 */
public class SqliteUntil {

	//���ݿ�洢·��
	String filePath = "data/data/com.modao.moneymanager/moneymis.db";
	//���ݿ��ŵ��ļ��� data/data/com.main.jh ����
	String pathStr = "data/data/com.modao.moneymanager";
	//�����ݿ�,���·����û�еĻ��ʹ���
	public SQLiteDatabase openDatabase(Context context){
		File dbPath=new File(filePath);
		if(dbPath.exists()){
			System.out.println("�����ݿ�");
			return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
		}else{
			File path=new File(pathStr);
			if (path.mkdirs()){
				System.out.println("�����ɹ�");
			}else{
				System.out.println("����ʧ��");
			};
			try {
				//�õ���Դ
				AssetManager am= context.getAssets();
				//�õ����ݿ��������
				InputStream is=am.open("moneymis.db");
				//�������д��SDcard����  
				FileOutputStream fos=new FileOutputStream(dbPath);
				//����byte����  ����1KBдһ��
				byte[] buffer=new byte[1024];
				int count = 0;
				while((count = is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				//���رվͿ�����
				fos.flush();
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//���û��������ݿ�  �����Ѿ�����д��SD����
			return openDatabase(context);
		}
	}
}
