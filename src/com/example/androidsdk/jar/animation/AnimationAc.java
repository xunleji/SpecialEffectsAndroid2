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
			* �����ߴ�仯����
			* ��һ������fromX ,�ڶ�������toX:�ֱ��Ƕ�����ʼ������ʱX�����ϵ������ߴ硣
			* ����������fromY ,���ĸ�����toY:�ֱ��Ƕ�����ʼ������ʱY�����ϵ������ߴ硣
			* 0.0��ʾ������û��,1.0��ʾ����������,ֵС��1.0��ʾ����,ֵ����1.0��ʾ�Ŵ�
			* ���⻹������������ģʽpivotXType��pivotYType�� 
			* �������������x,y ����Ŀ�ʼλ��pivotXValue��pivotYValue�ȡ�
			* ����ֵ˵������0%-100%��ȡֵ��50%Ϊ�����X��Y���������ϵ��е�λ��
			*/
			scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF,
					0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			scaleAnimation.setDuration(3000);
			
//			scaleAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.scalemy);
			iv.startAnimation(scaleAnimation);
			
		}else if(v==btnalpha){
			/*
			 * �������䶯��
			 * ����AlphaAnimation���һ������fromAlpha��ʾ������ʼʱ��͸���ȣ�
			 * �ڶ�������toAlpha��ʾ��������ʱ��͸���ȡ�
			 * setDuration�������ö�������ʱ�䡣
			 */
			alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
			alphaAnimation.setDuration(3000);
			
//			alphaAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.alphamy);
			iv.startAnimation(alphaAnimation);
			
		}else if(v==btntranslate){
			/*
			 * ��һ������fromXDelta ,�ڶ�������toXDelta:�ֱ��Ƕ�����ʼ������ʱX���ꡣ
			 * ����������fromYDelta ,���ĸ�����toYDelta:�ֱ��Ƕ�����ʼ������ʱY���ꡣ
			 */
			translateAnimation = new TranslateAnimation(0.1f, 100.0f, 0.1f, 100.0f);
			translateAnimation.setDuration(3000);
			
//			translateAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.translatemy);
			iv.startAnimation(translateAnimation);
			
		}else if(v==btnrotate){
			/*
			 * ��һ������fromDegrees��ʾ������ʼʱ�ĽǶȣ� �ڶ�������toDegrees��ʾ��������ʱ�ĽǶȡ� 
			 * ���Ƕ�Ϊ����������ʾ��ʱ����ת
			 * ���Ƕ�Ϊ����������ʾ˳ʱ����ת
			 * (����from����to����:˳ʱ����ת)
			 * (����from����to����:��ʱ����ת)
			 * (����from����to����:˳ʱ����ת)
			 * (����from����to����:��ʱ����ת)
			 * ��������ģʽpivotXType��pivotYType�� �������������x,y ����Ŀ�ʼλ��pivotXValue��pivotYValue
			 * ������������ֵ ��0%-100%��ȡֵ,50%Ϊ�����X��Y���������ϵ��е�λ��
			 */
			rotateAnimation = new RotateAnimation(0f,360f);
			rotateAnimation.setDuration(3000);
			
//			rotateAnimation = AnimationUtils.loadAnimation(AnimationAc.this, R.anim.rotatemy);
			iv.startAnimation(rotateAnimation);
		}
		
	}

}
