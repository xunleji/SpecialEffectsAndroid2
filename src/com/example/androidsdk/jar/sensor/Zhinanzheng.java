package com.example.androidsdk.jar.sensor;

import com.example.androidsdk.Diary;
import com.example.androidsdk.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
public class Zhinanzheng extends Activity implements SensorEventListener{
	
	ImageView image;  //指南针图片
	float currentDegree = 0f; //指南针图片转过的角度
	SensorManager mSensorManager; //管理器
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainsenser);
        
        image = (ImageView)findViewById(R.id.znzImage);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE); //获取管理服务
    }
    
    /*
     *  Sensor.TYPE_ORIENTATION・・・方向感应器
     *  
     *  SensorManager.SENSOR_DELAY_NORMAL：默认的获得传感器数据的速度。
	 * 	SensorManager.SENSOR_DELAY_GAME：如果利用传感器开发游戏，建议使用该值。
	 * 	SensorManager.SENSOR_DELAY_UI：如果使用传感器更新UI中的数据，建议使用该值。
     * @see android.app.Activity#onResume()
     */
    @Override 
    protected void onResume(){
    	super.onResume();
    	//注册监听器
    	mSensorManager.registerListener(this
    			, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }
    
    //取消注册
    @Override
    protected void onPause(){
    	mSensorManager.unregisterListener(this);
    	super.onPause();
    }
    
    @Override
    protected void onStop(){
    	mSensorManager.unregisterListener(this);
    	super.onStop();
    }

    //传感器值改变
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		Log.e("onAccuracyChanged", "accuracy="+accuracy);
	}
    //精度改变
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//获取触发event的传感器类型
		int sensorType = event.sensor.getType();
		
		switch(sensorType){
			case Sensor.TYPE_ORIENTATION:
				float degree = event.values[0]; //获取z转过的角度
				float direction = normalizeDegree(degree);
				if (direction > 22.5f && direction < 157.5f) {
					// east
					Diary.eLog("东");
				} else if (direction > 202.5f && direction < 337.5f) {
					// west
					Diary.eLog("西");
				}
				if (direction > 112.5f && direction < 247.5f) {
					// south
					Diary.eLog("南");
				} else if (direction < 67.5 || direction > 292.5f) {
					// north
					Diary.eLog("北");
				}
				Diary.eLog("direction=="+direction);
				//穿件旋转动画
				RotateAnimation ra = new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF,0.5f
						,Animation.RELATIVE_TO_SELF,0.5f);
			 ra.setDuration(100);//动画持续时间
			 image.startAnimation(ra);
			 currentDegree = -degree;
			 break;
		
		}
	}
	
	// 调整方向传感器获取的值
	private float normalizeDegree(float degree) {
		return (degree + 720) % 360;
	}
}