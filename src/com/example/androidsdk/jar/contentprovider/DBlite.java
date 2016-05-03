package com.example.androidsdk.jar.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBlite extends SQLiteOpenHelper {

	public DBlite(Context context) {
		super(context, RuiXin.DBNAME, null, RuiXin.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+RuiXin.TNAME+"(" +
                 RuiXin.TID+" integer primary key autoincrement not null,"+
                 RuiXin.USERNAME+" text not null," +
                 RuiXin.DATE+" interger not null,"+
                 RuiXin.SEX+" text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+RuiXin.TNAME);
		onCreate(db);
	}
	
	public void add(String username,String date,String sex){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RuiXin.USERNAME, username);
        values.put(RuiXin.DATE, date);
        values.put(RuiXin.SEX, sex);
        db.insert(RuiXin.TNAME,"",values);
	}
	
	public void del(){
		 SQLiteDatabase db = getWritableDatabase();
		 db.delete(RuiXin.TNAME,RuiXin.TID+"=?" , new String[]{"2"});
	}
	
	public void update(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RuiXin.USERNAME, "louis koo");
	    values.put(RuiXin.DATE, "today");
	    values.put(RuiXin.SEX, "ÄÐ");
		db.update(RuiXin.TNAME, values, RuiXin.TID+"=?" , new String[]{"1"});
	}
}
