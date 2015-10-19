package com.tyolar.inc.relax.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyolar.inc.relax.MainActivity;
import com.tyolar.inc.relax.R;
import com.tyolar.inc.relax.clazz.Menu;


public class NavDrawerListAdapter extends BaseAdapter {
	
	private MainActivity context;
	private ArrayList<Menu> navDrawerItems;
	
	public NavDrawerListAdapter(MainActivity context, ArrayList<Menu> navDrawerItems){
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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.menu_item, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
    
        imgIcon.setImageResource(navDrawerItems.get(position).getImageresource());        
        txtTitle.setText(navDrawerItems.get(position).getTitel());
        convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.settheme(navDrawerItems.get(position));
			}
		});
        
        return convertView;
	}

}
