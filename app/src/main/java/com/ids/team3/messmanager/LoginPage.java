package com.ids.team3.messmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginPage extends AppCompatActivity {
    private Button mBtLaunchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        mBtLaunchActivity = (Button) findViewById(R.id.bt_launch_activity);

        mBtLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });

    }
    private void launchActivity() {

        Intent intent = new Intent(this, MainDisplay.class);
        startActivity(intent);
    }
}
