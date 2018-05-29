package com.ids.team3.messmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MessAllot extends AppCompatActivity {
    private String userid;
    private String idType;
    private float rating=0;
    private RatingBar rateBar;
    private Spinner messSelected;
    private TextView loggedUser;
    private TextView changeUser;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_allot);
        rateBar = (RatingBar) findViewById(R.id.mess_rating_bar);
        messSelected = (Spinner) findViewById(R.id.mess_dropdown);
        loggedUser = (TextView) findViewById(R.id.loggeduser);
        changeUser = (TextView) findViewById(R.id.changeuser);
        submit = (Button) findViewById(R.id.submit);
        Bundle bundle = getIntent().getExtras();
        String[] user = bundle.getString("userid").split("@");
        userid = user[0];
        idType = user[1];
        //new getRating().execute();
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.ids.team3.messmanager.MessAllot.this, LoginPage.class);
                intent.putExtra("userid",userid+"@"+idType);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new submitAllotment().execute();
                Intent intent = new Intent(com.ids.team3.messmanager.MessAllot.this, MainDisplay.class);
                startActivity(intent);
            }
        });
        messSelected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new getRating().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new getRating().execute();
            }
        });

    }

    class getRating extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.8.7.217/IDS/getRating.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String messname = messSelected.getSelectedItem().toString().split(" ")[1];
                messname=messname.substring(1,3);
                String post_data =
                        URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8")
                                +"&"+URLEncoder.encode("messname", "UTF-8") + "=" + URLEncoder.encode(messname, "UTF-8");
                bfw.write(post_data);
                bfw.flush();
                bfw.close();
                InputStream is = urlConnection.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                String result = "";
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    result += line;
                }
                bfr.close();
                is.close();
                Log.d("result",result);
                urlConnection.disconnect();
                JSONObject obj = new JSONObject(result);
                rating = (float)obj.getDouble("rating");
                String username = obj.getString("username");
                loggedUser.setText("Logged in as: "+username);
            } catch (Exception e) {
                Log.d("Error!", e.toString());
            }
            rateBar.setRating(rating);
            return null;
        }
    }

    class submitAllotment extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.8.7.217/IDS/getMessChoice.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String messname = messSelected.getSelectedItem().toString().split(" ")[1];
                messname=messname.substring(1,3);
                String post_data =
                        URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8")
                                +"&"+URLEncoder.encode("messname", "UTF-8") + "=" + URLEncoder.encode(messname, "UTF-8");
                bfw.write(post_data);
                bfw.flush();
                bfw.close();
                InputStream is = urlConnection.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                String result = "";
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    result += line;
                }
                bfr.close();
                is.close();
                Log.d("result",result);
                urlConnection.disconnect();
                JSONObject obj = new JSONObject(result);
                rating = (float)obj.getDouble("rating");
                String username = obj.getString("username");
                loggedUser.setText("Logged in as: "+username);
            } catch (Exception e) {
                Log.d("Error!", e.toString());
            }
            rateBar.setRating(rating);
            return null;
        }
    }
}
