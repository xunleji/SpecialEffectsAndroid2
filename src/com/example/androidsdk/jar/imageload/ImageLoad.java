package com.example.androidsdk.jar.imageload;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.androidsdk.jar.tool.Until;
/**
 * ����ͼƬ
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
	//����ͼƬ��Ŀ¼��
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
	//����Ŀ¼
	protected File createSDDir(String paths){
		File path = new File(Until.getSDpath() + paths);// ����Ŀ¼
		if (!path.exists()) {// Ŀ¼���ڷ���false
			path.mkdirs();// ����һ��Ŀ¼
		}
		return path;
	}
	//�鿴Ŀ¼���Ƿ����ͼƬ
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
	//��������ͼƬ
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
