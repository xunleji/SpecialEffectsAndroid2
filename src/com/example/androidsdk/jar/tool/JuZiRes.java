package com.example.androidsdk.jar.tool;

import java.lang.reflect.Field;

import android.content.Context;

/**
 * android反射
 * 获取布局名称
 * @author xujuan
 *
 */
public class JuZiRes {
	
	private Context context;
	
	public JuZiRes(Context context){
		this.context = context;
	}
	
	protected int getStringId(String str){
		try {
			   Class paramClass = Class.forName(context.getPackageName() + ".R$string");
			   Field localField = paramClass.getField(str);
			   int k = localField.getInt(str);
			   return k;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	protected int getLayoutId(String str){
		try {
			   Class paramClass = Class.forName(context.getPackageName() + ".R$layout");
			   Field localField = paramClass.getField(str);
			   int k = localField.getInt(str);
			   return k;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	protected int getId(String str){
		try {
			   Class paramClass = Class.forName(context.getPackageName() + ".R$id");
			   Field localField = paramClass.getField(str);
			   int k = localField.getInt(str);
			   return k;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	protected int getDrawableId(String str){
		try {
			   Class paramClass = Class.forName(context.getPackageName() + ".R$drawable");
			   Field localField = paramClass.getField(str);
			   int k = localField.getInt(str);
			   return k;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
