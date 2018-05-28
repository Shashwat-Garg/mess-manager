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
        MessAllotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, MessAllot.class);
        startActivity(intent);
    }
}
