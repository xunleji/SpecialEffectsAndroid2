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
 * ����ͼ/��״ͼ
 * @author xujuan
 *
 */
public class LineChart extends View{

	private int XPoint=0;    //ԭ���X����
	private int YPoint=0;   //ԭ���Y����
	private int XScale=0;     //X�Ŀ̶ȳ���
	private int YScale=0;     //Y�Ŀ̶ȳ���
	private int XLength=0;   //X��ĳ���
	private int YLength=0;   //Y��ĳ���
	private String[] XLabel;    //X�Ŀ̶�
	private String[] YLabel;    //Y�Ŀ̶�
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
	     //X,y�ử��
	     paint= new Paint();
	     paint.setStyle(Paint.Style.STROKE);
	     paint.setAntiAlias(true);//ȥ���
	     paint.setColor(Color.BLACK);//��ɫ
	     //��״ͼ
	     paint.setStyle(Style.FILL);
	    
	}
	
	public void SetInfo(String[] XLabels,String[] YLabels,List<Integer> lineincomedata){
		 XLabel=XLabels;
	     YLabel=YLabels;
	     this.linedata = lineincomedata;
	 }

	@Override
	protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas);//��дonDraw����     
		 canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint-3,paint);    //��ͷ
	     canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint+3,paint);  
	     //����X��
	     canvas.drawLine(XPoint,YPoint,XPoint+XLength,YPoint,paint);   //����
	     //����Y��
	     canvas.drawLine(XPoint, YPoint-YLength, XPoint, YPoint, paint);   //����
	     for(int i=0;i<5;i++)                
	     {
	         canvas.drawLine(XPoint,YPoint-i*YScale, XPoint+5, YPoint-i*YScale, paint);  //�̶�
	         try{
	             canvas.drawText(YLabel[i] , XPoint-20, YPoint-i*YScale+5, paint);  //����
	         }catch(Exception e){}
	     }
	     canvas.drawLine(XPoint,YPoint-YLength,XPoint-3,YPoint-YLength+6,paint);  //��ͷ
	     canvas.drawLine(XPoint,YPoint-YLength,XPoint+3,YPoint-YLength+6,paint);  
	     //����ͼ
	     for(int i=0;i<12;i++){
	         canvas.drawLine(XPoint+i*XScale, YPoint, XPoint+i*XScale, YPoint-5, paint);  //�̶�
	         canvas.drawText(XLabel[i] , XPoint+i*XScale-10, YPoint+20, paint);  //����
	     }
	     
	     //��״ͼ
	     for(int i=0;i<12;i++){
	         canvas.drawLine(XPoint+i*XScale, YPoint, XPoint+i*XScale, YPoint-5, paint);  //�̶�
	         canvas.drawText(XLabel[i] , XPoint+i*XScale-10, YPoint+20, paint);  //����
	     }
	     int money = 0;
	     for(int j = 0;j<12;j++){
    		 money+=linedata.get(j);
    	 }    	 
    	 if(money!=0)money+=12;
	     for(int i = 0;i<12;i++){
	    	if(money==0){	 
	    	}else{
	    		 //��״ͼ
		    	 canvas.drawRect(XPoint+i*XScale, 
		    	 	 YPoint-(int)((float)YScale/25)*(int)(((linedata.get(i)+1)*100)/(float)money),
		    	 	 XPoint+(i+1)*XScale,YPoint, paint);
		    	 
		    	 //����ͼ
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
