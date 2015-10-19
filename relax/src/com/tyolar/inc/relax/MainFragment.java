package com.tyolar.inc.relax;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.tyolar.inc.relax.adapters.NavFbuttonDrawerListAdapter;


@SuppressLint("NewApi")
public class MainFragment extends Fragment {
	ListView m;
	NavFbuttonDrawerListAdapter adapter;

	public MainFragment(NavFbuttonDrawerListAdapter adapter) {
		// TODO Auto-generated constructor stub
		this.adapter = adapter;
	}

	@Override
	public void onResume() {
		super.onResume();
		App mApplication = (App) getActivity().getApplicationContext();
		mApplication.getInstance().trackScreenView("MainFragment");
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		m = (ListView) rootView.findViewById(R.id.listmunu);
		setMenu(this.adapter);
		getActivity().setTitle(
				getActivity().getResources().getString(R.string.app_name));
		return rootView;
	}

	public void setMenu(NavFbuttonDrawerListAdapter adapter) {
		m.setAdapter(adapter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Do something that differs the Activity's menu here
		inflater.inflate(R.menu.main_header_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.sharemain) {
			Intent intent2 = new Intent();
			intent2.setAction("android.intent.action.SEND");
			intent2.setType("text/plain");
			intent2.putExtra(
					"android.intent.extra.TEXT",
					getResources().getString(R.string.label_share_text)
							+ (" https://play.google.com/store/apps/details?id="
									+ getPackageName()
									+ " http://www.amazon.com/gp/mas/dl/android?p=" + getPackageName()));
			startActivity(Intent.createChooser(intent2, getResources()
					.getString(R.string.label_menu_share)));
		}

		return true;

	}

	private String getPackageName() {

		return this.getActivity().getApplicationContext().getPackageName();
	}

}