package com.example.androidsdk.jar.dao;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ���ݿ�����ӿ�
 * @author xujuan
 *
 */
public interface SqliteInter {

	//��ѯ
	public JSONArray searchAll();
	//���
	public void add(JSONObject json);
	//ɾ��
	public void delete(String key,String id);
	//����
	public void update(JSONObject json,String key,String id);
	//����
	public JSONArray find(String key,String id);
}
