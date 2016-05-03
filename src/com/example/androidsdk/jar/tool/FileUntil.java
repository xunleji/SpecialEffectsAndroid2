package com.example.androidsdk.jar.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;

public class FileUntil {

	private Context context;  
    private String SDPATH;  
    private String FILESPATH;  
    
    public FileUntil(Context context) {
    	super();
    	this.context = context;
    	SDPATH = Environment.getExternalStorageDirectory().getPath() + "/";  
        FILESPATH = this.context.getFilesDir().getPath() + "/";  
    	// TODO Auto-generated constructor stub
    }
    
    //创建文件
    public File createSDFile(String fileName){
    	try{
    		File file = new File(SDPATH + fileName);  
    		file.createNewFile();  
    		return file;    		
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }
    
    //删除文件
    public boolean delSDFile(String fileName){
    	try{
    		 File file = new File(SDPATH + fileName);  
    	     if(file == null || !file.exists() || file.isDirectory())  
    	            return false;  
    	     file.delete();  
    	     return true; 
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return false;
    }
    
    //创建文件夹
    public File createSDDir(String dirName){
    	File dir = new File(SDPATH + dirName);  
        dir.mkdir();  
        return dir; 
    }
    
    //删除目录
    public boolean delSDDir(String dirName){
    	File dir = new File(SDPATH + dirName);  
        return delDir(dir);  
    }
    
    public boolean delDir(File dir) {  
        if (dir == null || !dir.exists() || dir.isFile()) {  
            return false;  
        }  
        for (File file : dir.listFiles()) {  
            if (file.isFile()) {  
                file.delete();  
            } else if (file.isDirectory()) {  
                delDir(file);// 递归  
            }  
        }  
        dir.delete();  
        return true;  
    }  

	//判断SD卡是否插入
	public static boolean sdCardExit(){
		if(Environment.getExternalStorageState().equals(  
		        android.os.Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}
	
	//重命名文件
	public boolean renameSDFile(String oldfileName,String newfileName){
		 File oleFile = new File(SDPATH + oldfileName);  
	     File newFile = new File(SDPATH + newfileName);  
	     return oleFile.renameTo(newFile);  
	}
	
	//复制文件到指定的文件
	public boolean copySDFileTo(String srcFileName,String destFileName){
		 File srcFile = new File(SDPATH + srcFileName);  
	     File destFile = new File(SDPATH + destFileName);  
	     return copyFileTo(srcFile, destFile);  

	}
	
	public boolean copyFileTo(File srcFile, File destFile){  
		try{
			if (srcFile.isDirectory() || destFile.isDirectory())  
				return false;// 判断是否是文件  
			FileInputStream fis = new FileInputStream(srcFile);  
			FileOutputStream fos = new FileOutputStream(destFile);  
			int readLen = 0;  
			byte[] buf = new byte[1024];  
			while ((readLen = fis.read(buf)) != -1) {  
				fos.write(buf, 0, readLen);  
			}  
			fos.flush();  
			fos.close();  
			fis.close();  
			return true;  			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
    }  
	
	//复制目录到指定目录
	public boolean copySDFilesTo(String srcDirName,String destDirName){
		File srcDir = new File(SDPATH + srcDirName);  
	    File destDir = new File(SDPATH + destDirName);  
	    return copyFilesTo(srcDir, destDir);  
	}
	
	public boolean copyFilesTo(File srcDir, File destDir){  
		try{
			if(!srcDir.isDirectory() || !destDir.isDirectory())  
				return false;// 判断是否是目录  
			if(!destDir.exists())  
				return false;// 判断目标目录是否存在  
			File[] srcFiles = srcDir.listFiles();  
			for (int i = 0; i<srcFiles.length;i++) {  
				if (srcFiles[i].isFile()) {  
					// 获得目标文件  
					File destFile = new File(destDir.getPath() + "/" 
							+ srcFiles[i].getName());  
					copyFileTo(srcFiles[i], destFile);  
				} else if (srcFiles[i].isDirectory()) {  
					File theDestDir = new File(destDir.getPath() + "/" 
							+ srcFiles[i].getName());  
					copyFilesTo(srcFiles[i], theDestDir);  
				}  
			}  
			return true;  
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}  
	
	//移动文件到指定 的文件
	public boolean moveSDFileTo(String srcFileName, String destFileName){
		 File srcFile = new File(SDPATH + srcFileName);  
	     File destFile = new File(SDPATH + destFileName);  
	     return moveFileTo(srcFile, destFile);  
	}
	
	public boolean moveFileTo(File srcFile, File destFile){  
		try{
			boolean iscopy = copyFileTo(srcFile, destFile);  
			if (!iscopy)  
				return false;  
			delFile(srcFile);  
			return true;  		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	} 
	
	public boolean delFile(File file) {  
	   if (file.isDirectory())  
	       return false;  
	   return file.delete();  
	} 
	
	//移动目录到指定 的目录
	public boolean moveSDFilesTo(String srcDirName, String destDirName){
	   File srcDir = new File(SDPATH + srcDirName);  
	   File destDir = new File(SDPATH + destDirName);  
	   return moveFilesTo(srcDir, destDir);  

	}
	public boolean moveFilesTo(File srcDir, File destDir){  
		try{
			if (!srcDir.isDirectory() || !destDir.isDirectory()) {  
				return false;  
			}  
			File[] srcDirFiles = srcDir.listFiles();  
			for (int i = 0; i<srcDirFiles.length; i++) {  
				if (srcDirFiles[i].isFile()) {  
					File oneDestFile = new File(destDir.getPath() + "/" 
							+ srcDirFiles[i].getName());  
					moveFileTo(srcDirFiles[i], oneDestFile);  
					delFile(srcDirFiles[i]);  
				} else if (srcDirFiles[i].isDirectory()) {  
					File oneDestFile = new File(destDir.getPath() + "/" 
							+ srcDirFiles[i].getName());  
					moveFilesTo(srcDirFiles[i], oneDestFile);  
					delDir(srcDirFiles[i]);  
				}  			
			}  
			return true; 		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}  
	
	//写内容到文件中
	public OutputStreamWriter writeSDFile(String fileName){  
		try{			
			File file = new File(SDPATH + fileName);  
			FileOutputStream fos = new FileOutputStream(file);  
			return new OutputStreamWriter(fos);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
    }  
	
	 public OutputStreamWriter appendSDFile(String fileName){
		 try{
			 File file = new File(SDPATH + fileName);
			 FileOutputStream fos = new FileOutputStream(file, true);
			 return new OutputStreamWriter(fos);		 
		 }catch (Exception e) {
			// TODO: handle exception
		 }
		 return null;
	 } 
	 
	 public InputStreamReader readSDFile(String fileName){
		 try{
			 File file = new File(SDPATH + fileName);
			 FileInputStream fis = new FileInputStream(file);
			 return new InputStreamReader(fis);		 
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 return null;
	 }
	 
	 /** *//**以文件流的方式复制文件
	  * @param src 文件源目录
	  * @param dest 文件目的目录
	  * @throws IOException  
	  */
	 public void copyFile(String src,String dest){
		 try{
			 FileInputStream in=new FileInputStream(src);
			 File file=new File(dest);
			 if(!file.exists())
				 file.createNewFile();
			 FileOutputStream out=new FileOutputStream(file);
			 int c;
			 byte buffer[]=new byte[1024];
			 while((c=in.read(buffer))!=-1){
				 for(int i=0;i<c;i++)
					 out.write(buffer[i]);        
			 }
			 in.close();
			 out.close();		 
		 }catch (Exception e) {
			// TODO: handle exception
		}
	 }
	 
	  /** *//**读文件
	  * @param path
	  * @return
	  * @throws IOException
	  */
	 public String FileInputStreamDemo(String path){
		 try{
			 File file=new File(path);
			 if(!file.exists()||file.isDirectory())
				 return null;
			 FileInputStream fis=new FileInputStream(file);
			 byte[] buf = new byte[1024];
			 StringBuffer sb=new StringBuffer();
			 while((fis.read(buf))!=-1){
				 sb.append(new String(buf));    
				 buf=new byte[1024];//重新生成，避免和上次读取的数据重复
			 }
			 return sb.toString();	 
		 }catch (Exception e) {
			// TODO: handle exception
		 }
		 return null;
	 }

	 /** *//**读文件
	  * @param path
	  * @return
	  * @throws IOException
	  */
	 public String BufferedReaderDemo(String path){
		 try{
			 File file=new File(path);
			 if(!file.exists()||file.isDirectory())
				 return null;
			 BufferedReader br=new BufferedReader(new FileReader(file));
			 String temp=null;
			 StringBuffer sb=new StringBuffer();
			 temp=br.readLine();
			 while(temp!=null){
				 sb.append(temp+" ");
				 temp=br.readLine();
			 }
			 return sb.toString();		 
		 }catch (Exception e) {
			// TODO: handle exception
		 }
		 return null;
	 }
	 
	 /** *
	  * **递归删除文件夹
	  * @param path
	  */
	 public void delDir(String path){
	     File dir=new File(path);
	     if(dir.exists()){
	         File[] tmp=dir.listFiles();
	         for(int i=0;i<tmp.length;i++){
	             if(tmp[i].isDirectory()){
	                 delDir(path+"/"+tmp[i].getName());
	             }
	             else{
	                 tmp[i].delete();
	             }
	         }
	         dir.delete();
	     }
	 }

}
