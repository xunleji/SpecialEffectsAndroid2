package com.example.androidsdk.jar.music;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

public class MusicService extends Service {

	String[] mCursorCols = new String[] {
			"audio._id AS _id", // index must match IDCOLIDX below
			MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
			MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION
	};
	private MediaPlayer mMediaPlayer;
	private Cursor mCursor;
	private int mPlayPosition = 0;
	
	public static final String PLAY_ACTION = "com.gtl.data.music.PLAY_ACTION";
	public static final String PAUSE_ACTION = "com.gtl.data.music.PAUSE_ACTION";
	public static final String NEXT_ACTION = "com.gtl.data.music.NEXT_ACTION";
	public static final String PREVIOUS_ACTION = "com.gtl.data.music.PREVIOUS_ACTION";
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		mMediaPlayer = new MediaPlayer();
		//通过一个URI可以获取所有音频文件
		Uri MUSIC_URL = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		//这里我过滤了一下，因为我机里有些音频文件是游戏音频，很短
		//播放不到一秒钟，我这里作了处理，默认大于10秒的可以看作是歌
		mCursor = getContentResolver().query(MUSIC_URL, mCursorCols, "duration > 10000", null, null);
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		String action = intent.getAction();
		if(action.equals(PLAY_ACTION)){
			play();
		}else if(action.equals(PAUSE_ACTION)){
			pause();
		}else if(action.equals(NEXT_ACTION)){
			next();
		}else if(action.equals(PREVIOUS_ACTION)){
			previous();
		}
		super.onStart(intent, startId);
	}
	//play the music
	public void play() {	
		init();
	}
	public void init() {
		mMediaPlayer.reset();
		String dataSource = getDateByPosition(mCursor, mPlayPosition);
		String info = getInfoByPosition(mCursor, mPlayPosition);
		//用Toast显示歌曲信息
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
		try {
			mMediaPlayer.setDataSource(dataSource);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//根据位置来获取歌曲位置
	public String getDateByPosition(Cursor c,int position){
		c.moveToPosition(position);
		int dataColumn = c.getColumnIndex(MediaStore.Audio.Media.DATA);		
		String data = c.getString(dataColumn);
		return data;
	}
	//获取当前播放歌曲演唱者及歌名
	public String getInfoByPosition(Cursor c,int position){
		c.moveToPosition(position);
		int titleColumn = c.getColumnIndex(MediaStore.Audio.Media.TITLE);
		int artistColumn = c.getColumnIndex(MediaStore.Audio.Media.ARTIST);
		String info = c.getString(artistColumn)+" " + c.getString(titleColumn);
		return info;
	}
	//暂停时，结束服务
	public void pause() {
		stopSelf();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		mMediaPlayer.release();
	}
	//下一首
	public void next() {
		if (mPlayPosition == mCursor.getCount() - 1) {
			mPlayPosition = 0;
		} else {
			mPlayPosition++;
		}
		init();
	}
	//上一首
	public void previous() {
		if (mPlayPosition == 0) {
			mPlayPosition = mCursor.getCount() - 1;
		} else {
			mPlayPosition--;
		}
		init();
	}
}
