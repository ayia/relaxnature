package com.tyolar.inc.relax;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.tyolar.inc.relax.adapters.NavDrawerListAdapter;
import com.tyolar.inc.relax.adapters.NavFbuttonDrawerListAdapter;
import com.tyolar.inc.relax.clazz.Menu;
import com.tyolar.inc.relax.clazz.Menu.MenuType;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private InterstitialAd mInterstitialAd;

	private ListView mDrawerList;
	Toolbar toolbar;
	private PlayerFragment PlayerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.navdrawer);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			setSupportActionBar(toolbar);
			toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
		}
		
		drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.app_name, R.string.app_name);
		mDrawerLayout.setDrawerListener(drawerToggle);
		ArrayList<Menu> df = new ArrayList<Menu>();
		df.add(new Menu(MenuType.Rain, this));
		df.add(new Menu(MenuType.Sae, this));
		df.add(new Menu(MenuType.Country, this));
		df.add(new Menu(MenuType.Forest, this));
		df.add(new Menu(MenuType.Mountain, this));
		df.add(new Menu(MenuType.Night, this));

		NavDrawerListAdapter adapter = new NavDrawerListAdapter(this, df);
		mDrawerList.setAdapter(adapter);
		NavFbuttonDrawerListAdapter dds = new NavFbuttonDrawerListAdapter(this,
				df);

		MainFragment MainFragment = new MainFragment(dds);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, MainFragment)
				.addToBackStack(null).commit();

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-6154259985120414/8565696106");
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(
				"SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID").build();
		mInterstitialAd.loadAd(adRequest);
		try {
			AdView mAdView = (AdView) findViewById(R.id.adView);
			AdRequest adRequests = new AdRequest.Builder().build();
			mAdView.loadAd(adRequests);
		} catch (Exception s) {
			s.printStackTrace();
		}
	}

	public void settheme(Menu i) {
//		mDrawerList.setBackgroundColor(getResources().getColor(
//				i.getBackgrounfcolor()));
		toolbar.setBackgroundColor(getResources().getColor(
				i.getBackgrounfcolor()));
		if (PlayerFragment != null)
			PlayerFragment.stopPlaying();

		PlayerFragment = new PlayerFragment(this);
		setTitle(i.getTitel());
		mDrawerLayout.closeDrawer(Gravity.START);
		PlayerFragment.setSelectedMenu(i);
		addPlayer();
	

	}

	public void closedrwwer() {
		mDrawerLayout.closeDrawer(Gravity.START);
	}

	public void addPlayer() {
		mInterstitialAd.show();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, PlayerFragment)
				.addToBackStack(null).commit();

		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawerLayout.openDrawer(Gravity.START);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onBackPressed() {
		if (PlayerFragment != null)
			PlayerFragment.stopPlaying();
		android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() <= 1) {
			super.finish();
		}
		super.onBackPressed();
	}

}
