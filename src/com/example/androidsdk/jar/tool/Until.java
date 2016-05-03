package com.example.androidsdk.jar.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.example.androidsdk.Diary;

import android.R.style;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

/**
 * 图片之间的转换
 * @author xujuan
 *
 */
public class Until {
	
	private Dialog dialog;
	private View view;
	//显示弹出框
	public void showDialg(Context context){
		dialog = new Dialog(context, style.Theme_Translucent_NoTitleBar);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.CENTER);
		window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		dialog.getWindow().setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		dialog.show();		
		//自定义View
		//////////////
		dialog.setContentView(view);
	}
	
	//px转换成dip    
	public static int dip2px(Context context, float dipValue){                      
		final float scale = context.getResources().getDisplayMetrics().density;                      
		return (int)(dipValue * scale + 0.5f);                   
	} 
	//byte转换成String
	public static String toHex(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String x16 = Integer.toHexString(bytes[i]);
            if (x16.length() < 2) {
                sb.append("0" + x16);
            } else if (x16.length() > 2) {
                sb.append(x16.substring(x16.length() - 2));
            } else
                sb.append(x16);
        }
        return sb.toString();
    }
	//String转换成byte
    public static byte[] fromHex(String hexString) {
        if (hexString.length() % 2 == 1)
            hexString = "0" + hexString;
        byte[] raw = new byte[hexString.length() / 2];
        for (int i = 0; i < raw.length; i++) {
            raw[i] = (byte) Integer.parseInt(hexString.substring(i * 2,
                i * 2 + 2), 16);
        }
        return raw;
    }
    //将byte转换成Bitmap
    public static Bitmap byteArrayToBitmap(byte[] array){
        if (null == array){
            return null;
        }  
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
    //将Bitmap转换成byte
    public static byte[] bitampToByteArray(Bitmap bitmap){
        byte[] array = null;
        try{
            if (null != bitmap){
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                array = os.toByteArray();
                os.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } 
        return array;
    }
    //将drawable转换成bitmap
    public static Bitmap drawableToBitmap(Drawable drawable){
        if (null == drawable){
            return null;
        }    
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Config config = (drawable.getOpacity() != PixelFormat.OPAQUE) ? Config.ARGB_8888 : Config.RGB_565;  
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);   
        if (null != bitmap){
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
        }  
        return bitmap;
    }
    //将bitmap保存到sd卡中
    public static void saveBitmapToSDCard(Bitmap bmp, String strPath){
        if (null != bmp && null != strPath && !strPath.equalsIgnoreCase("")){
            try{
                File file = new File(strPath);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = Until.bitampToByteArray(bmp);
                fos.write(buffer);
                fos.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //将sd卡中的图片转换成bitmap
    public static Bitmap loadBitmapFromSDCard(String strPath){
        File file = new File(strPath);
        try{
            FileInputStream fis = new FileInputStream(file);            
            Bitmap bmp = BitmapFactory.decodeStream(fis);
            return bmp;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }  
        return null;
    }
    //获取照相的图片并将其保存到sd中获取其路径
    public static String savePic(Intent data){
    	String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return null;
        }
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        FileOutputStream b = null;
        String strImgPath = Environment.getExternalStorageDirectory().toString() + "/dlion/";// 存放照片的文件夹
    	String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + ".jpg";// 照片命名
    	File out = new File(strImgPath);
    	if (!out.exists()) {
    	    out.mkdirs();
    	}
    	out = new File(strImgPath, fileName);
    	strImgPath = strImgPath + fileName;// 该照片的绝对路径	
        try {
            b = new FileOutputStream(strImgPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            return strImgPath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return strImgPath;
    }
    //获取SD卡路径
    public static String getSDpath(){
    	File sdDir = null;
    	try {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED); 
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdDir == null?"null":sdDir.toString();
    }
    //B,K,M,G大小转换
	public static String FormetFileSize(long fileS) { 
      DecimalFormat df = new DecimalFormat("##0.00"); 
      String fileSizeString = ""; 
      fileSizeString = df.format((double) fileS / 1024/1024) + "M";   
      return fileSizeString; 
	}
    
}
