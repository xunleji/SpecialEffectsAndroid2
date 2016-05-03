package com.example.androidsdk.jar.contentprovider;

import android.net.Uri;

public class RuiXin {
	
	public static final String DBNAME = "ruixinonlinedb"; 
    public static final String TNAME = "ruixinonline";
    public static final int VERSION = 3;
    
    public static String TID = "tid";
    public static final String USERNAME = "username";
    public static final String DATE = "date";
    public static final String SEX = "sex";
    
    public static final String AUTOHORITY = "com.example.androidsdk.jar.contentprovider";
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;
    
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ruixin.login";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ruixin.login";
    
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/ruixinonline");
}
