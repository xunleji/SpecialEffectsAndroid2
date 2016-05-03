package com.example.androidsdk.jar.gallery;

import com.example.androidsdk.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

	private int pic[] = {R.drawable.img1,R.drawable.img2,R.drawable.img3,
			R.drawable.img4};
	private Context context;
	public GalleryAdapter(Context context) {
		super();
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){ 
			vh = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.top_gallery_iv,null);
			convertView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            vh.background = (ImageView) convertView.findViewById(R.id.gallery_image);
            convertView.setTag(vh);
		}else{
			vh = (ViewHolder)convertView.getTag();
		}
		int pos = position%pic.length;
		Log.e("dddd", "pos==="+position);
		vh.background.setBackgroundResource(pic[position]);
		vh.background.setScaleType(ImageView.ScaleType.FIT_XY); 
		((CustomGalleryAc)context).changePointView(position);
		return convertView;
	}
	
	private class ViewHolder{
		public ImageView background;
	}

}
