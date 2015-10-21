package com.tyolar.inc.relax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tyolar.inc.relax.clazz.Menu;
import com.tyolar.inc.relax.composant.TimerView;

@SuppressLint("NewApi")
public class PlayerFragment extends Fragment {
	View player;
	View contaner;
	ImageView back_image;
	ImageView next_image;
	ImageView play_pause_image;
	Menu selectedMenu;
	int selextedindex = 0;
	MediaPlayer MediaPlayer = null;
	Time conferenceTime;
	public MainActivity context;
	CountDownTimer CountDownTimer = null;
	android.view.Menu mymenu;

	public PlayerFragment(MainActivity ctx) {
		super();

		this.context = ctx;
		this.context.toolbar.getMenu().clear();

	}

	public PlayerFragment() {
		// TODO Auto-generated constructor stub
	}

	public void setSelectedMenu(Menu selectedMenu) {
		this.selectedMenu = selectedMenu;
		selextedindex = 0;
	}

	@Override
	public void onResume() {
		super.onResume();
		App mApplication = (App) getActivity().getApplicationContext();
		mApplication.getInstance().trackScreenView(
				"Player: " + selectedMenu.getTitel());

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		int id = item.getItemId();
		MenuItem bedMenuItem = mymenu.findItem(R.id.timertext);
		if (id == R.id.timer) {
			if (!bedMenuItem.isVisible()) {

				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				final TimerView frameView = new TimerView(context);
				builder.setView(frameView);

				final AlertDialog alertDialog = builder.create();
				alertDialog.show();

				frameView.mStart.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int a = frameView.getHour();
						int b = frameView.getMinut();
						int C = frameView.getMinut2();
						int k = Integer.valueOf(String.valueOf(b)
								+ String.valueOf(C));
						setTimer(a, k);

						alertDialog.cancel();
					}
				});

				frameView.mCancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.cancel();
					
						item.setIcon(R.drawable.ic_timer_white_48dp);
					}
				});

				item.setIcon(R.drawable.ic_timer_off_white_48dp);
			} else {
				CountDownTimer.cancel();
				CountDownTimer = null;
				bedMenuItem.setVisible(false);
				item.setIcon(R.drawable.ic_timer_white_48dp);
				updateui();
			}
		}
		if (id == R.id.share) {
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

		return super.onOptionsItemSelected(item); // important line
	}

	private String getPackageName() {

		return this.getActivity().getApplicationContext().getPackageName();
	}

	@Override
	public void onCreateOptionsMenu(android.view.Menu menu,
			MenuInflater inflater) {
		inflater.inflate(R.menu.player_header_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
		mymenu = menu;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page, container, false);
		player = rootView.findViewById(R.id.playerzone);
		play_pause_image = (ImageView) rootView
				.findViewById(R.id.play_pause_image);
		back_image = (ImageView) rootView.findViewById(R.id.back_image);
		next_image = (ImageView) rootView.findViewById(R.id.next_image);
		contaner = rootView.findViewById(R.id.content_frame);
		selextedindex = 0;
		stopPlaying();
		updateui();
		return rootView;
	}

	private PlayerFragment getme() {
		return this;
	}

	void stopPlaying() {
		if (MediaPlayer != null) {
			MediaPlayer.reset();
			MediaPlayer.release();
			MediaPlayer = null;
		}
		if (CountDownTimer != null) {
			CountDownTimer.cancel();
			CountDownTimer = null;
		}
	}

	public void setTimer(int hour, int minute) {
		if (CountDownTimer != null)
			CountDownTimer.cancel();
		final MenuItem bedMenuItem = mymenu.findItem(R.id.timertext);
		bedMenuItem.setVisible(true);
		this.conferenceTime = new Time(Time.getCurrentTimezone());
		this.conferenceTime.setToNow();
		int year = conferenceTime.year;
		int monthDay = conferenceTime.monthDay;
		int month = conferenceTime.month;
		conferenceTime.set(0, minute, hour, monthDay, month, year);
		conferenceTime.normalize(true);
		long confMillis = conferenceTime.toMillis(true);

		Time nowTime = new Time(Time.getCurrentTimezone());
		nowTime.setToNow();
		nowTime.normalize(true);
		long nowMillis = nowTime.toMillis(true);
		long milliDiff = confMillis - nowMillis;
		CountDownTimer = new CountDownTimer(confMillis, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// decompose difference into days, hours, minutes and seconds

				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				String formatted = df.format(millisUntilFinished);
				bedMenuItem.setTitle(formatted);
				if (formatted.toLowerCase().equalsIgnoreCase("00:00:00")) {
					getActivity().finish();
					System.exit(0);

				}

			}

			@Override
			public void onFinish() {
				getActivity().finish();
				System.exit(0);

			}
		}.start();

	}

	public void updateui() {
		contaner.setBackground(getResources().getDrawable(
				selectedMenu.getImagebackgrond()));

		if (selextedindex == 0)
			back_image.setEnabled(false);
		else
			back_image.setEnabled(true);

		next_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selextedindex++;
				if (selextedindex == selectedMenu.getListsongs().size())
					selextedindex = 0;
				updateui();

			}
		});

		back_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selextedindex--;
				updateui();
			}
		});

		play_pause_image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (MediaPlayer.isPlaying()) {
					MediaPlayer.stop();
					play_pause_image
							.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
				} else {
					updateui();
					play_pause_image
							.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_48dp);
				}
			}
		});
		stopPlaying();
		MediaPlayer = MediaPlayer.create(getActivity(), selectedMenu
				.getListsongs().get(selextedindex));
		MediaPlayer.setLooping(true);
		MediaPlayer.start();
		play_pause_image
				.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_48dp);
	}

}