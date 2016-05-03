package com.example.androidsdk.jar.chart;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.androidsdk.Diary;
import com.example.androidsdk.jar.tool.Until;

/**
 * 饼状图
 * @author xujuan
 *
 */
public class PieChart extends View{

	private Paint paint,painttext;
	private Context context;
	private int allmoneys = 0;
	private JSONArray js ;
	private int size = 0;
	private int colors [] = {
			Color.RED,Color.rgb(176, 48, 96),
			Color.rgb(255, 97, 0),Color.GREEN,
			Color.rgb(106, 90, 205),Color.BLUE,
			Color.rgb(160, 32, 240),Color.rgb(128, 42, 42),
			Color.rgb(163, 148, 128),Color.rgb(255, 128, 0),
			Color.rgb(227, 168, 105),Color.rgb(56, 94,15),
			Color.rgb(61, 145, 64),Color.rgb(0, 201, 87),
			Color.rgb(235, 142, 85),Color.rgb(124, 252, 0),
			Color.rgb(156, 102,31),Color.rgb(188, 143, 143),
			Color.rgb(221, 160,221),Color.rgb(115, 74,18)	
	};
	private boolean isnot = false;
	private int xPoint = 0;
	private int xLength = 0;
	private int yPoint = 0;
	private int yLength = 0;
	private int width,height;
	
	public PieChart(Context context,JSONArray jsa,int allmoney,int width,int height) {
		super(context);
		this.context = context;
		this.width = width;
		this.height = height;
		xPoint = Until.dip2px(context, 25);
		if(allmoney==0){
			isnot = true;
		}else{
			isnot = false;
			js = jsa;
			size =jsa.length();
		}
		this.allmoneys = allmoney+size;
		xPoint = Until.dip2px(context, 20);
		xLength = Until.dip2px(context, 70);
		
		yPoint = height-Until.dip2px(context, 170);
		yLength = Until.dip2px(context, 20);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		painttext = new Paint();
		painttext.setAntiAlias(true);
		painttext.setTextSize(17);
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		RectF rectf = new RectF(50, 50, width-Until.dip2px(context, 50),
				height-Until.dip2px(context, 200));
		if(isnot){
		}else{
			Diary.eLog("size==="+size);
			//0,100,200,260表示开始的弧度,100,100,60,100表示弯曲的弧度
			int a = 0;
			for(int i = 0;i<size;i++){
				try{
					JSONObject json = js.getJSONObject(i);	
					int ar = (int)((float)((json.getInt("money")+1)*360)/(allmoneys));	
					painttext.setColor(colors[i%20]);
					canvas.drawText(json.getString("name"), xPoint+(i%4)*xLength, yPoint+(i/4)*yLength, painttext);	
					paint.setColor(colors[i%20]);
					if(i==size-1){
						Diary.eLog("111111111111");
						canvas.drawArc(rectf, a, 360-a, true,paint);
					}else{
						Diary.eLog("222222222222");
						canvas.drawArc(rectf, a, ar, true,paint);
						a+=ar;		
					}
				}catch (Exception e) {
					e.printStackTrace();
				}		
			}		
		}
	}
	
	public void updateUI(JSONArray jsa,int allmoney){
		if(allmoney==0){
			isnot = true;
		}else{
			isnot = false;
			js = jsa;
			size =jsa.length();
		}
		this.allmoneys = allmoney+size;
		invalidate();
	}

}
