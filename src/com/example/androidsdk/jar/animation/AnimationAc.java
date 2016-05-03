package com.example.androidsdk.jar.animation;

import com.example.androidsdk.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationAc extends Activity implements OnClickListener{

	private ImageView iv;
	private Button btnscale,btnalpha,btntranslate,btnrotate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animationtest);
		
		iv = (ImageView)findViewById(R.id.animationiv);
		btnscale = (Button)findViewById(R.id.animationbtn1);
		btnalpha = (Button)findViewById(R.id.animationbtn2);
		btntranslate = (Button)findViewById(R.id.animationbtn3);
		btnrotate = (Button)findViewById(R.id.animationbtn4);
		
		btnscale.setOnClickListener(this);
		btnalpha.setOnClickListener(this);
		btntranslate.setOnClickListener(this);
		btnrotate.setOnClickListener(this);
		
	}

	private Animation scaleAnimation,alphaAnimation,
					translateAnimation,rotateAnimation;
	@Override
	public void onClick(View v) {
		if(v==btnscale){
			/*
			* 创建尺寸变化动画
			* 第一个参数fromX ,第二个参数toX:分别是动画起始、结束时X坐标上的伸缩尺寸。
			* 第三个参数fromY ,第四个参数toY:分别是动画起始、结束时Y坐标上的伸缩尺寸。
			* 0.0表示收缩到没有,1.0表示正常无伸缩,值小于1.0表示收缩,值大于1.0表示放大
			* 另外还可以设置伸缩模式pivotXType、pivotYType， 
			* 伸缩动画相对于x,y 坐标的开始位置pivotXValue、pivotYValue等。
			* 属性值说明：从0%-100%中取值，50%为物件的X或Y方向坐标上的中点位置
			*/
			scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF,
					0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			scaleAnimation.setDuration(3000);
			
//			scaleAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.scalemy);
			iv.startAnimation(scaleAnimation);
			
		}else if(v==btnalpha){
			/*
			 * 创建渐变动画
			 * 其中AlphaAnimation类第一个参数fromAlpha表示动画起始时的透明度，
			 * 第二个参数toAlpha表示动画结束时的透明度。
			 * setDuration用来设置动画持续时间。
			 */
			alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
			alphaAnimation.setDuration(3000);
			
//			alphaAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.alphamy);
			iv.startAnimation(alphaAnimation);
			
		}else if(v==btntranslate){
			/*
			 * 第一个参数fromXDelta ,第二个参数toXDelta:分别是动画起始、结束时X坐标。
			 * 第三个参数fromYDelta ,第四个参数toYDelta:分别是动画起始、结束时Y坐标。
			 */
			translateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
			translateAnimation.setDuration(3000);
			
//			translateAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.translatemy);
			iv.startAnimation(translateAnimation);
			
		}else if(v==btnrotate){
			/*
			 * 第一个参数fromDegrees表示动画起始时的角度， 第二个参数toDegrees表示动画结束时的角度。 
			 * 当角度为负数——表示逆时针旋转
			 * 当角度为正数——表示顺时针旋转
			 * (负数from——to正数:顺时针旋转)
			 * (负数from——to负数:逆时针旋转)
			 * (正数from——to正数:顺时针旋转)
			 * (正数from——to负数:逆时针旋转)
			 * 设置伸缩模式pivotXType、pivotYType， 伸缩动画相对于x,y 坐标的开始位置pivotXValue、pivotYValue
			 * 以上两个属性值 从0%-100%中取值,50%为物件的X或Y方向坐标上的中点位置
			 */
			rotateAnimation = new RotateAnimation(0f,360f);
			rotateAnimation.setDuration(3000);
			
//			rotateAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.rotatemy);
			iv.startAnimation(rotateAnimation);
		}
		
	}

}
