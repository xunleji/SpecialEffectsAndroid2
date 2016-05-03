package com.example.androidsdk.jar.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataDao implements SqliteInter {

	private SqliteUntil dbHpler;
	private Context context;
	
	public DataDao(Context context) {
		super();
		dbHpler=new SqliteUntil();
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONArray searchAll() {
		JSONArray jsonArray = new JSONArray();
		SQLiteDatabase db=dbHpler.openDatabase(context);
		Cursor cr=db.rawQuery("SELECT * FROM table",null);
		while(cr.moveToNext()){
			JSONObject js = new JSONObject();
			try{
				js.put("", "");			
			}catch (Exception e) {
				e.printStackTrace();
			}
			jsonArray.put(js);
		}
		cr.close();
		db.close();
		return jsonArray;
	}

	@Override
	public void add(JSONObject json) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbHpler.openDatabase(context);
		ContentValues values=new ContentValues();
		try{	
			values.put("", json.getString(""));
		}catch (JSONException e) {
			e.printStackTrace();
		}
		db.insert("table",null, values);
		db.close();
	}

	@Override
	public void delete(String key, String id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbHpler.openDatabase(context);
		db.delete("table",key+"=?",new String[]{id});
		db.close();
	}

	@Override
	public void update(JSONObject json,String key,String id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbHpler.openDatabase(context);
		ContentValues values=new ContentValues();
		try{
			values.put("", json.getString(""));
		}catch (JSONException e) {
			e.printStackTrace();
		}
		db.update("table", values,key +"=?", new String[]{id});
		db.close();
	}

	@Override
	public JSONArray find(String key, String id) {
		JSONArray js = new JSONArray();
		SQLiteDatabase db=dbHpler.openDatabase(context);
		Cursor cr = db.query("table", null, key + "=?",new String[] {id}, null, null, null);
		while(cr.moveToNext()){
			JSONObject json = new JSONObject();
			try{
				json.put("", "");		
			}catch (Exception e) {
				e.printStackTrace();
			}
			js.put(json);
		}
		cr.close();
		db.close();
		return js;
	}

}
