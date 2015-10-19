package com.tyolar.inc.relax;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {
	PlayerFragment a;

	public TimePickerFragment(PlayerFragment a) {
		super();
		this.a = a;
	}

	@SuppressLint("NewApi")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker

		final Calendar c = Calendar.getInstance();
		int hour = 0;
		int minute = 1;
		// Create a new instance of TimePickerDialog and return it
		TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour,
				minute, DateFormat.is24HourFormat(getActivity()));
		tpd.setTitle(getActivity().getResources().getString(R.string.label_duration));
		return tpd;
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
		a.setTimer(hourOfDay, minute);
		a.context.closedrwwer();
		a.contaner.invalidate();

	}
}
