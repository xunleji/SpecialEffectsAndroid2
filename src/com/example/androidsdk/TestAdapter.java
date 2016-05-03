package com.example.androidsdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {

	private Context context;
	private String[] str={  "listview拖拽着上下移动","listview分页加载",
							"选择相册图片或拍照图片时对图片进行缩放","用户引导页",
							"折线图","饼状图",
							"自定义数字键盘","自定义Checked按钮",
							"下载文件","Gallery带圆点的左右滑动",
							"Gallery从最左边开始滑动","加载网络图片",
							"类似于iphone的滑轮效果","人脸检测",
							"","云标签",
							"Android 跳转到一个应用安装的详情界面的方法","GestureDetector事件",
							"listview上拉刷新","android中两种启动service的方法",
							"获取系统文件里的音乐文件的音乐播放器","",
							"webservice的使用","xml解析",
							"menu菜单慢慢展开","跨平台数据共享Contentprovider的使用",
							"给系统日历添加备忘事件","控制系统电话簿",
							"aidl的使用:先运行此项目，然后在运行ContentProviderTest项目","Parcelable和Serializable的使用",
							"自定义Progress","AsyncTask线程运行",
							"软键盘的自动弹出与关闭","闪屏界面图片慢慢消失",
							"开机时自动启动","设置界面用xml实现",
							"调用系统的图片,音频文件等",
							"傳感器的使用","按钮按一定的路径展开收缩",
							"android动画","悬浮歌词",
							"按钮随着手势移动","半透明Menu",
							"自定义拍照","Tab在底部的框架",
							"Tab在顶部的框架","类似于QQ抽屉式的滑动",
							"控件上添加标签","调用其他项目中的资源文件",
							"","波纹效果",
							"activity之间的跳转"};
	
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
