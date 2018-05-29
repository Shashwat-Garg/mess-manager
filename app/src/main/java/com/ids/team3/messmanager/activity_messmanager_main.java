package com.ids.team3.messmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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


public class activity_messmanager_main extends AppCompatActivity{
//        implements NavigationView.OnNavigationItemSelectedListener {
    private Button vieworders;
    private String userid;
    private float rating=0;
    private TextView messName;
    private RatingBar rateBar;
    private String messname="";
    private String idType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messmanager_main);
        messName = (TextView) findViewById(R.id.messname);
        vieworders=(Button)findViewById(R.id.vieworders);
        rateBar = (RatingBar) findViewById(R.id.mess_rating_bar);
        Bundle bundle = getIntent().getExtras();
        String[] user = bundle.getString("userid").split("@");
        userid = user[0];
        idType = user[1];
        new getMess().execute();
        new getRating().execute();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */
        vieworders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //;
            }
        });

    }

    @Override
    public void onBackPressed() {
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        */
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_messmanager_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    class getMess extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.8.7.217/IDS/getMess.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data =
                        URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8")
                                +"&"+URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(idType, "UTF-8");
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
                messname = obj.getString("messname");
            } catch (Exception e) {
                Log.d("Error!", e.toString());
            }
            messName.setText(messname);
            return null;
        }
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
            } catch (Exception e) {
                Log.d("Error!", e.toString());
            }
            rateBar.setRating(rating);
            return null;
        }
    }

}
