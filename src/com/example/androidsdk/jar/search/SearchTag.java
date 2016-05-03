package com.example.androidsdk.jar.search;

import java.util.Random;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.search.KeywordsFlow;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchTag extends Activity implements OnClickListener{
	
	public String[] keywords = {
			"QQ", "Sodino", "APK", "GFW", "Ǧ��", 
            "����", "���澫��", "MacBook Pro", "ƽ�����", "��ʫ����",
            "����ŷ TR-100", "�ʼǱ�", "SPY Mouse", "Thinkpad E40", "�������", 
            "�ڴ�����", "��ͼ", "����", "����", "����",   
            "ͨѶ¼", "������", "CSDN leak", "��ȫ", "3D",   
            "��Ů", "����", "4743G", "����", "����",   
            "ŷ��", "�����", "��ŭ��С��", "mmShow", "���׹�����",   
            "iciba", "��ˮ��ϵ", "����App", "������", "365����",   
            "����ʶ��", "Chrome", "Safari", "�й���Siri", "A5������",   
            "iPhone4S", "Ħ�� ME525", "���� M9", "�῵ S2500" 
	};
	private KeywordsFlow keywordsFlow;
	private Button btnIn, btnOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_tag);
		btnIn = (Button) findViewById(R.id.in);  
	    btnOut = (Button) findViewById(R.id.out);  
	    btnIn.setOnClickListener(this);  
	    btnOut.setOnClickListener(this);  
	    keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
	    keywordsFlow.setDuration(1000);  
	    // ���   
	    feedKeywordsFlow(keywordsFlow, keywords);  
	    keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
	}
	
	private void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {  
        Random random = new Random();  
        for (int i = 0; i < KeywordsFlow.MAX; i++) {  
            int ran = random.nextInt(arr.length);  
            String tmp = arr[ran];  
            keywordsFlow.feedKeyword(tmp);  
        }  
    }

	@Override
	public void onClick(View v) {
		if (v == btnIn) {  
            keywordsFlow.rubKeywords();  
            // keywordsFlow.rubAllViews();   
            feedKeywordsFlow(keywordsFlow, keywords);  
            keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);  
        } else if (v == btnOut) {  
            keywordsFlow.rubKeywords();  
            // keywordsFlow.rubAllViews();   
            feedKeywordsFlow(keywordsFlow, keywords);  
            keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);  
        } 
		
	}

}
