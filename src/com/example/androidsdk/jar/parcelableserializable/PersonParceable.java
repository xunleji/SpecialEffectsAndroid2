package com.example.androidsdk.jar.parcelableserializable;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonParceable implements Parcelable {

	//这里定义了两个变量来说明读和写的顺序要一致    
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
	    // 把javanbean中的数据写到Parcel。先写id然后写name  
	    dest.writeInt(this.id); 
	    dest.writeString(this.name); 
	} 
	
	// 添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口  
	public static final Parcelable.Creator<PersonParceable> CREATOR = new Parcelable.Creator<PersonParceable>() { 
	    @Override 
	    public PersonParceable createFromParcel(Parcel source) { 
	        // 从Parcel中读取数据，返回person对象  
	        return new PersonParceable(source.readInt(), source.readString()); 
	    } 
	
	    @Override 
	    public PersonParceable[] newArray(int size) { 
	        return new PersonParceable[size]; 
	    } 
	}; 

}
