package com.example.androidsdk.jar.parcelableserializable;

import java.io.Serializable;

public class PersonSerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//���ﶨ��������������˵������д��˳��Ҫһ��    
	private Integer id; 
	private String name; 
	
	public PersonSerializable() { 
	} 
	
	public PersonSerializable(Integer id, String name) { 
	    this.id = id; 
	    this.name = name; 
	} 
	
	public Integer getId() { 
	    return id; 
	} 
	
	public void setId(Integer id) { 
	    this.id = id; 
	} 
	
	public String getName() { 
	    return name; 
	} 
	
	public void setName(String name) { 
	    this.name = name; 
	} 

}
