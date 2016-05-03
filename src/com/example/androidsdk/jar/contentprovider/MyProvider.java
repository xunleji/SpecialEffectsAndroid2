package com.example.androidsdk.jar.contentprovider;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 该方法可以用于别的项目工程运用此数据库
 * @author xujuan
 *
 */
public class MyProvider extends ContentProvider {
	
	DBlite dBlite;
    SQLiteDatabase db;
    private static final UriMatcher sMatcher;
    static{
            sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            sMatcher.addURI(RuiXin.AUTOHORITY,RuiXin.TNAME, RuiXin.ITEM);
            sMatcher.addURI(RuiXin.AUTOHORITY, RuiXin.TNAME+"/#", RuiXin.ITEM_ID);

    }
	@Override
	public boolean onCreate() {
		this.dBlite = new DBlite(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 db = dBlite.getWritableDatabase();                
         Cursor c;
         switch (sMatcher.match(uri)) {
	         case RuiXin.ITEM:
	                 c = db.query(RuiXin.TNAME, projection, selection, selectionArgs, null, null, null);
	                 break;
	         case RuiXin.ITEM_ID:
	                 String id = uri.getPathSegments().get(1);
	                 c = db.query(RuiXin.TNAME, projection, 
	                		 RuiXin.TID+"="+id+(!TextUtils.isEmpty(selection)?"AND("+selection+')':""),
	                		 selectionArgs, null, null, sortOrder);
	             break;
	         default:
	                 throw new IllegalArgumentException("Unknown URI"+uri);
         }
         c.setNotificationUri(getContext().getContentResolver(), uri);
         return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (sMatcher.match(uri)) {
		    case RuiXin.ITEM:
		        return RuiXin.CONTENT_TYPE;
		    case RuiXin.ITEM_ID:
		        return RuiXin.CONTENT_ITEM_TYPE;
		    default:
		        throw new IllegalArgumentException("Unknown URI"+uri);
	    }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db = dBlite.getWritableDatabase();
        long rowId;
        if(sMatcher.match(uri)!=RuiXin.ITEM){
             throw new IllegalArgumentException("Unknown URI"+uri);
        }
        rowId = db.insert(RuiXin.TNAME,RuiXin.TID,values);
        if(rowId>0){
            Uri noteUri=ContentUris.withAppendedId(RuiXin.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        throw new IllegalArgumentException("Unknown URI"+uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		 db = dBlite.getWritableDatabase();
         int count = 0;
         switch (sMatcher.match(uri)) {
	         case RuiXin.ITEM:
	              count = db.delete(RuiXin.TNAME,selection, selectionArgs);
	              break;
	         case RuiXin.ITEM_ID:
	              String id = uri.getPathSegments().get(1);
	              count = db.delete(RuiXin.TID, 
	             		 RuiXin.TID+"="+id+(!TextUtils.isEmpty(RuiXin.TID="?")?"AND("+selection+')':""), 
	             		 selectionArgs);
	             break;
	         default:
	                 throw new IllegalArgumentException("Unknown URI"+uri);
         }
         getContext().getContentResolver().notifyChange(uri, null);
         return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dBlite.getWritableDatabase();
		int count;
		switch (sMatcher.match(uri)){
			case RuiXin.ITEM:
				count = db.update(RuiXin.TNAME, values, selection, selectionArgs);
				break;
			case RuiXin.ITEM_ID:
				String Id = uri.getPathSegments().get(1);
				count = db.update(RuiXin.TNAME, values,
						RuiXin.TID + "=" + Id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
						selectionArgs);
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
