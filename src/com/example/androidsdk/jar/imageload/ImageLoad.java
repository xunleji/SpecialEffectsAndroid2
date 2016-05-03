package com.example.androidsdk.jar.imageload;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.androidsdk.jar.tool.Until;
/**
 * 加载图片
 * @author xujuan
 *
 */
public class ImageLoad{

	private String url;
	private String name;
	private String path;
	private int FILESIZE = 1024 << 6; 
	private ImageListening li;
	
	public ImageLoad(){
		super();
	}
	//保存图片到目录下
	public void setImageLoad(String url,ImageListening li,String filename) {
		this.url = url;
		this.li = li;
		String[] str = url.split("/");
		name = str[str.length-1];
		path = Until.getSDpath()+ "/"+filename+"/" + name;
		createSDDir("/"+filename);
		if(getImgae()){
			return;
		}else{
			run();
		}
	}
	//创建目录
	protected File createSDDir(String paths){
		File path = new File(Until.getSDpath() + paths);// 创建目录
		if (!path.exists()) {// 目录存在返回false
			path.mkdirs();// 创建一个目录
		}
		return path;
	}
	//查看目录下是否存在图片
	public boolean getImgae(){
		File paths = new File(path);
		if(paths.length() == 0){
			paths.delete();
			return false;
		}
		if (!paths.exists()) {
			return false;
		}else{
            return true;
		}
	}
	//加载网络图片
	public void run(){
		HttpURLConnection conn = null;
		try {
	        RandomAccessFile savedFile = new RandomAccessFile(path + ".temp", "rwd");		
	        URL urls = new URL(url);
			conn = (HttpURLConnection) urls.openConnection();
	        conn.setConnectTimeout(20000);
	        conn.disconnect();
	        InputStream in = conn.getInputStream();
	        byte[] buffer = new byte[FILESIZE];
	        int len = 0; 
	        while ((len = in.read(buffer)) != -1) {
	           savedFile.write(buffer, 0, len);
	        }
	        savedFile.close();
	        in.close();
	        File file = new File(path +".temp");
	        file.renameTo(new File(path));
	        if(li!= null)li.onResultListener();
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }finally{
	    	if(conn != null)conn.disconnect();
	    }
	}
	
	
}
