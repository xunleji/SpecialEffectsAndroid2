package com.example.androidsdk.jar.aidlserver;

import com.example.androidsdk.Diary;
import com.example.androidsdk.jar.aidlserver.IAIDLServerService.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlServerService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	/**
	 * 在AIDL文件中定义的接口实现。
	 */
	private IAIDLServerService.Stub mBinder = new Stub() {

		public String sayHello() throws RemoteException {
			return "Hello";
		}

		public Book getBook() throws RemoteException {
			Diary.eLog("getBook");
			Book mBook = new Book();
			mBook.setBookName("Android应用开发");
			mBook.setBookPrice(50);
			Diary.eLog("name==" + mBook.getBookName() + "price=="
					+ mBook.getBookPrice());
			return mBook;
		}
	};
}
