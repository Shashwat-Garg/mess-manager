package com.ids.team3.messmanager;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ChangeMenu extends AppCompatActivity implements View.OnClickListener {
    private static final String tag = "Main Activity";
    //    private DatePickerDialog.OnDateSetListener mDateListener;
    EditText startDate, endDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_menu);
        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        //startDate.setEnabled(false);
        //endDate.setEnabled(false);
    }

    @Override
    public void onClick(final View view) {
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String monthprefix = "", dayprefix = "";
                if (month < 10) monthprefix = "0";
                if (day < 10) dayprefix = "0";
                String date = year + "-" + monthprefix + month + "-" + dayprefix + day;
                if (view.getId() == R.id.start_date) startDate.setText(date);
                if (view.getId() == R.id.end_date) endDate.setText(date);
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }
}

