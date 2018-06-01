package com.ids.team3.messmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvancedOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_order);
        Button HolidayBtn = (Button) findViewById(R.id.holiday_btn);
        Button AddOnBtn = (Button) findViewById(R.id.addon_btn);

        AddOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddOn();
            }
        });
        HolidayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHoliday();
            }
        });
    }
    private void launchAddOn() {
        Intent intent = new Intent(this, AddOns.class);
        startActivity(intent);
    }
    private void launchHoliday() {
        Intent intent = new Intent(this, HolidayOrder.class);
        startActivity(intent);
    }
}



