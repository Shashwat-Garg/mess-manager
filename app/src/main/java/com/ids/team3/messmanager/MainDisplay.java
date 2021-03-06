package com.ids.team3.messmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainDisplay extends AppCompatActivity {
    private String userid;
    private String idType;
    private Button MessAllotBtn;
    private Button FeedbackBtn;
    private Button AdvancedOrderBtn;
    private Button ChangeMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        Bundle bundle = getIntent().getExtras();
        String[] user = bundle.getString("userid").split("@");
        userid = user[0];
        idType = user[1];
        MessAllotBtn = (Button) findViewById(R.id.mess_allot_btn);
        FeedbackBtn = (Button) findViewById(R.id.feedback_btn);
        AdvancedOrderBtn = (Button) findViewById(R.id.advanced_order_btn);
        ChangeMenuBtn = (Button) findViewById(R.id.change_menu_btn);

        if(!idType.equals("student")){
            ViewGroup vg = (ViewGroup) MessAllotBtn.getParent();
            if(vg!=null){
                vg.removeView(MessAllotBtn);
            }
        }
        MessAllotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idType.equals("student")) {
                    launchMessAllotActivity();
                }
            }
        });
        AdvancedOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAdvancedOrderActivity();
            }
        });
        ChangeMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchChangeMenuActivity();
            }
        });
        FeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFeedbackActivity();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private void launchMessAllotActivity() {
        Intent intent = new Intent(this, MessAllot.class);
        intent.putExtra("userid",userid+"@"+idType);
        startActivity(intent);
    }

    private void launchChangeMenuActivity() {
        Intent intent = new Intent(this, ChangeMenu.class);
        intent.putExtra("userid",userid+"@"+idType);
        startActivity(intent);
    }

    private void launchAdvancedOrderActivity() {
        Intent intent = new Intent(this, AdvancedOrder.class);
        intent.putExtra("userid",userid+"@"+idType);
        startActivity(intent);
    }
    private void launchFeedbackActivity() {
        Intent intent = new Intent(this, Feedback.class);
        intent.putExtra("userid",userid+"@"+idType);
        startActivity(intent);
    }
}