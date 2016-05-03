package com.example.androidsdk.jar.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

/**
 * 将assets下的数据库写到sd卡中
 * @author xujuan
 *
 */
public class SqliteUntil {

	//数据库存储路径
	String filePath = "data/data/com.modao.moneymanager/moneymis.db";
	//数据库存放的文件夹 data/data/com.main.jh 下面
	String pathStr = "data/data/com.modao.moneymanager";
	//打开数据库,如果路径中没有的话就创建
	public SQLiteDatabase openDatabase(Context context){
		File dbPath=new File(filePath);
		if(dbPath.exists()){
			System.out.println("打开数据库");
			return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
		}else{
			File path=new File(pathStr);
			if (path.mkdirs()){
				System.out.println("创建成功");
			}else{
				System.out.println("创建失败");
			};
			try {
				//得到资源
				AssetManager am= context.getAssets();
				//得到数据库的输入流
				InputStream is=am.open("moneymis.db");
				//用输出流写到SDcard上面  
				FileOutputStream fos=new FileOutputStream(dbPath);
				//创建byte数组  用于1KB写一次
				byte[] buffer=new byte[1024];
				int count = 0;
				while((count = is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				//最后关闭就可以了
				fos.flush();
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//如果没有这个数据库  我们已经把他写到SD卡上
			return openDatabase(context);
		}
	}
}
