package com.example.admin1.etxebalmovil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Dialog to choose a start and an end date for a new reservation
 */
public class DatePickerDialog extends DialogFragment {
    private static final String ARG_DATE = "date";
    private static final String ARG_EXIT = "exit";
    private DatePicker mDatePicker;

    public static final String EXTRA_START_DATE = "com.example.admin1.etxebalmovil.dialog.date";
    public static final String EXTRA_END_DATE = "com.example.admin1.etxebalmovil.dialog.date2";

    public static DatePickerDialog newInstance(Date date, boolean exitOnOk) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putBoolean(ARG_EXIT, exitOnOk);

        DatePickerDialog fragment = new DatePickerDialog();
        fragment.setArguments(args);
        return fragment;
    }
    public static DatePickerDialog newInstance(Date date) {
        return newInstance(date, false);
    }

    private void sendResult(int resultCode, Date startDate, Date endDate) {
        if (getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_START_DATE, startDate);
            intent.putExtra(EXTRA_END_DATE, endDate);
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

    // Creates a second dialog to select the end date
    private void reOpenDialog(Date date, Fragment target, int targetRequestCode, String tag) {
        DatePickerDialog dialog = DatePickerDialog.newInstance(date, true);
        dialog.setTargetFragment(target, targetRequestCode);
        dialog.show(getFragmentManager(), tag);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceSate) {
        final Date date = (Date) getArguments().getSerializable(ARG_DATE);
        final boolean exit = getArguments().getBoolean(ARG_EXIT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_picker, null);
        mDatePicker = v.findViewById(R.id.dialog_date_picker);
        mDatePicker.setMinDate(calendar.getTimeInMillis());
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(exit ? getString(R.string.dialog_end_date) : getString(R.string.dialog_start_date))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date selectedDate = new GregorianCalendar(year, month, day).getTime();
                        if (exit) { // End date selected
                            sendResult(Activity.RESULT_OK, date, selectedDate);
                        } else { // Start date selected
                            reOpenDialog(selectedDate, getTargetFragment(), getTargetRequestCode(), getTag());
                        }
                    }
                })
                .create();
    }
}

