package com.tyolar.inc.relax.adapters;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.tyolar.inc.relax.MainActivity;
import com.tyolar.inc.relax.R;
import com.tyolar.inc.relax.clazz.Menu;

public class NavFbuttonDrawerListAdapter extends BaseAdapter {

	private MainActivity context;
	private ArrayList<Menu> navDrawerItems;

	public NavFbuttonDrawerListAdapter(MainActivity context,
			ArrayList<Menu> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		convertView = mInflater.inflate(R.layout.flat_button, parent,false);;

		Button d = (Button) convertView.findViewById(R.id.btn);
		 d.setText("  "+navDrawerItems.get(position).getTitel());
		Drawable img = context.getResources().getDrawable(
				navDrawerItems.get(position).getImageresource());
		img.setBounds(10, 0, 60, 60);
		 d.setCompoundDrawables( img, null, null, null );
		
		 Bitmap backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), navDrawerItems.get(position).getImagebackgrond());
		 BitmapDrawable backgroundDrawable = new BitmapDrawable(context.getResources(), backgroundBitmap);
		 backgroundDrawable.setGravity(Gravity.LEFT|Gravity.RIGHT); // also LEFT, CENTER_VERTICAL, etc.
		 d.setBackground(backgroundDrawable);
	
		d.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.settheme(navDrawerItems.get(position));
			}
		});

		return convertView;
	}

}
