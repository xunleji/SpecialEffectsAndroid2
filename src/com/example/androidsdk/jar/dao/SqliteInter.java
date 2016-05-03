package com.example.androidsdk.jar.dao;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 数据库操作接口
 * @author xujuan
 *
 */
public interface SqliteInter {

	//查询
	public JSONArray searchAll();
	//添加
	public void add(JSONObject json);
	//删除
	public void delete(String key,String id);
	//更新
	public void update(JSONObject json,String key,String id);
	//查找
	public JSONArray find(String key,String id);
}
