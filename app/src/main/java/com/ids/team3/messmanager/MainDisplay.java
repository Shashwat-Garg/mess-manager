package com.ids.team3.messmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        Button MessAllotBtn = (Button) findViewById(R.id.mess_allot_btn);
        Button ChangeMenuBtn = (Button) findViewById(R.id.change_menu_btn);
        Button AdvancedOrderBtn = (Button) findViewById(R.id.advanced_order_btn);
        Button FeedbackBtn = (Button) findViewById(R.id.feedback_btn);

        MessAllotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMessAllot();
            }
        });
        FeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFeedback();
            }
        });
        ChangeMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchChangeMenu();
            }
        });
        AdvancedOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAdvancedOrder();
            }
        });
    }

    private void launchMessAllot() {
        Intent intent = new Intent(this, MessAllot.class);
        startActivity(intent);
    }
    private void launchChangeMenu() {
        Intent intent = new Intent(this, ChangeMenu.class);
        startActivity(intent);
    }
    private void launchAdvancedOrder() {
        Intent intent = new Intent(this, AdvancedOrder.class);
        startActivity(intent);
    }
    private void launchFeedback() {
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);
    }
}
