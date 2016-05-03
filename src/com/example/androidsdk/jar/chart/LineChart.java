package com.example.androidsdk.jar.chart;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

import com.example.androidsdk.jar.tool.Until;

/**
 * 折线图/柱状图
 * @author xujuan
 *
 */
public class LineChart extends View{

	private int XPoint=0;    //原点的X坐标
	private int YPoint=0;   //原点的Y坐标
	private int XScale=0;     //X的刻度长度
	private int YScale=0;     //Y的刻度长度
	private int XLength=0;   //X轴的长度
	private int YLength=0;   //Y轴的长度
	private String[] XLabel;    //X的刻度
	private String[] YLabel;    //Y的刻度
	private List<Integer> linedata ;
	private Paint paint;
	
	public LineChart(Context context,int width,int height) {
		super(context);
		 XPoint =Until.dip2px(context, 25);
	     YPoint = height-Until.dip2px(context, 200);
	     XLength = width-Until.dip2px(context, 35);
	     XScale = Until.dip2px(context, 23);
	     YScale = Until.dip2px(context, 50);
	     YLength = height-Until.dip2px(context, 240);
	     //X,y轴画笔
	     paint= new Paint();
	     paint.setStyle(Paint.Style.STROKE);
	     paint.setAntiAlias(true);//去锯齿
	     paint.setColor(Color.BLACK);//颜色
	     //柱状图
	     paint.setStyle(Style.FILL);
	    
	}
	
	public void SetInfo(String[] XLabels,String[] YLabels,List<Integer> lineincomedata){
		 XLabel=XLabels;
	     YLabel=YLabels;
	     this.linedata = lineincomedata;
	 }

	@Override
	protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas);//重写onDraw方法     
		 canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint-3,paint);    //箭头
	     canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint+3,paint);  
	     //设置X轴
	     canvas.drawLine(XPoint,YPoint,XPoint+XLength,YPoint,paint);   //轴线
	     //设置Y轴
	     canvas.drawLine(XPoint, YPoint-YLength, XPoint, YPoint, paint);   //轴线
	     for(int i=0;i<5;i++)                
	     {
	         canvas.drawLine(XPoint,YPoint-i*YScale, XPoint+5, YPoint-i*YScale, paint);  //刻度
	         try{
	             canvas.drawText(YLabel[i] , XPoint-20, YPoint-i*YScale+5, paint);  //文字
	         }catch(Exception e){}
	     }
	     canvas.drawLine(XPoint,YPoint-YLength,XPoint-3,YPoint-YLength+6,paint);  //箭头
	     canvas.drawLine(XPoint,YPoint-YLength,XPoint+3,YPoint-YLength+6,paint);  
	     //折线图
	     for(int i=0;i<12;i++){
	         canvas.drawLine(XPoint+i*XScale, YPoint, XPoint+i*XScale, YPoint-5, paint);  //刻度
	         canvas.drawText(XLabel[i] , XPoint+i*XScale-10, YPoint+20, paint);  //文字
	     }
	     
	     //柱状图
	     for(int i=0;i<12;i++){
	         canvas.drawLine(XPoint+i*XScale, YPoint, XPoint+i*XScale, YPoint-5, paint);  //刻度
	         canvas.drawText(XLabel[i] , XPoint+i*XScale-10, YPoint+20, paint);  //文字
	     }
	     int money = 0;
	     for(int j = 0;j<12;j++){
    		 money+=linedata.get(j);
    	 }    	 
    	 if(money!=0)money+=12;
	     for(int i = 0;i<12;i++){
	    	if(money==0){	 
	    	}else{
	    		 //柱状图
		    	 canvas.drawRect(XPoint+i*XScale, 
		    	 	 YPoint-(int)((float)YScale/25)*(int)(((linedata.get(i)+1)*100)/(float)money),
		    	 	 XPoint+(i+1)*XScale,YPoint, paint);
		    	 
		    	 //折线图
		    	 if(i>0){
        			 canvas.drawLine(XPoint+(i-1)*XScale, 
        					 YPoint-(int)((float)YScale/25)*(int)(((linedata.get(i-1)+1)*100)/(float)money), 
        					 XPoint+i*XScale,
        					 YPoint-(int)((float)YScale/25)*(int)(((linedata.get(i)+1)*100)/(float)money), paint);		 
        		 }
	             canvas.drawCircle(XPoint+i*XScale,
	            		 YPoint-(int)((float)YScale/25)*(int)(((linedata.get(i)+1)*100)/(float)money), 2, paint);

	    	}    	    	 
	     }
	}
	
	public void updateUI(List<Integer> lineincomedata){
		this.linedata = lineincomedata;
		invalidate();
	}

}
