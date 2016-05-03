package com.example.androidsdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {

	private Context context;
	private String[] str={  "listview��ק�������ƶ�","listview��ҳ����",
							"ѡ�����ͼƬ������ͼƬʱ��ͼƬ��������","�û�����ҳ",
							"����ͼ","��״ͼ",
							"�Զ������ּ���","�Զ���Checked��ť",
							"�����ļ�","Gallery��Բ������һ���",
							"Gallery������߿�ʼ����","��������ͼƬ",
							"������iphone�Ļ���Ч��","�������",
							"","�Ʊ�ǩ",
							"Android ��ת��һ��Ӧ�ð�װ���������ķ���","GestureDetector�¼�",
							"listview����ˢ��","android����������service�ķ���",
							"��ȡϵͳ�ļ���������ļ������ֲ�����","",
							"webservice��ʹ��","xml����",
							"menu�˵�����չ��","��ƽ̨���ݹ���Contentprovider��ʹ��",
							"��ϵͳ������ӱ����¼�","����ϵͳ�绰��",
							"aidl��ʹ��:�����д���Ŀ��Ȼ��������ContentProviderTest��Ŀ","Parcelable��Serializable��ʹ��",
							"�Զ���Progress","AsyncTask�߳�����",
							"����̵��Զ�������ر�","��������ͼƬ������ʧ",
							"����ʱ�Զ�����","���ý�����xmlʵ��",
							"����ϵͳ��ͼƬ,��Ƶ�ļ���",
							"��������ʹ��","��ť��һ����·��չ������",
							"android����","�������",
							"��ť���������ƶ�","��͸��Menu",
							"�Զ�������","Tab�ڵײ��Ŀ��",
							"Tab�ڶ����Ŀ��","������QQ����ʽ�Ļ���",
							"�ؼ�����ӱ�ǩ","����������Ŀ�е���Դ�ļ�",
							"","����Ч��",
							"activity֮�����ת"};
	
	public TestAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 52;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv ;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.tv, null);
			tv = (TextView)convertView.findViewById(R.id.tv);
			convertView.setTag(tv);
		}else{
			tv=(TextView)convertView.getTag();
		}
		tv.setPadding(10, 10, 10, 10);
		tv.setText(str[position]);
		return convertView;
	}
	

}
