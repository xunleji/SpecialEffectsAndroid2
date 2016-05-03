package com.example.androidsdk.jar.parcelableserializable;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonParceable implements Parcelable {

	//���ﶨ��������������˵������д��˳��Ҫһ��    
	private Integer id; 
	private String name; 
	
	public PersonParceable() { 
	} 
	
	public PersonParceable(Integer id, String name) { 
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
	
	@Override 
	public int describeContents() { 
	    return 0; 
	} 
	
	@Override 
	public void writeToParcel(Parcel dest, int flags) { 
	    // ��javanbean�е�����д��Parcel����дidȻ��дname  
	    dest.writeInt(this.id); 
	    dest.writeString(this.name); 
	} 
	
	// ���һ����̬��Ա,��ΪCREATOR,�ö���ʵ����Parcelable.Creator�ӿ�  
	public static final Parcelable.Creator<PersonParceable> CREATOR = new Parcelable.Creator<PersonParceable>() { 
	    @Override 
	    public PersonParceable createFromParcel(Parcel source) { 
	        // ��Parcel�ж�ȡ���ݣ�����person����  
	        return new PersonParceable(source.readInt(), source.readString()); 
	    } 
	
	    @Override 
	    public PersonParceable[] newArray(int size) { 
	        return new PersonParceable[size]; 
	    } 
	}; 

}
