package com.ids.team3.messmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

public class Signup extends AppCompatActivity {
    ActionBar actionbar;
    private Button SignupBtn;
    private EditText name;
    private EditText ID;
    private Spinner idType;
    private EditText Password;
    private EditText hostel;
    private EditText contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        name = (EditText) findViewById(R.id.name);
        Password = (EditText) findViewById(R.id.password);
        ID = (EditText) findViewById(R.id.ID);
        idType = (Spinner) findViewById(R.id.spinner);
        hostel = (EditText) findViewById(R.id.hostel);
        contact = (EditText) findViewById(R.id.contact);
        SignupBtn = (Button) findViewById(R.id.button);

        SignupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new SignupClicked().execute();
            }
        });
    }

    class SignupClicked extends AsyncTask<Void,Void,Void>
    {
//        protected void onPreExecute() {
            //Log.d("Pre-execute","Kya haal ?");
//        }

        @Override
        protected Void doInBackground(Void... voids) {
            //boolean success=false;
            try {
                URL url = new URL("http://10.8.7.217/IDS/getCredentials.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data =
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name.getText().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password.getText().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(idType.getSelectedItem().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("hostel","UTF-8")+"="+URLEncoder.encode(hostel.getText().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact.getText().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(ID.getText().toString(),"UTF-8");
                bfw.write(post_data);
                bfw.flush();
                bfw.close();
                InputStream is = urlConnection.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is,"ISO-8859-1"));
                String result = "";
                String line = "";
                while((line = bfr.readLine())!=null){
                    result+=line;
                }
                Log.d("result",result);
                bfr.close();
                is.close();
                urlConnection.disconnect();
            } catch (Exception e) {
                Log.d("Error!",e.toString());
            }
            /*
            if(success){
                Intent intent = new Intent(com.ids.team3.messmanager.LoginPage.this, MainDisplay.class);
                startActivity(intent);
            }
            else{
                Log.d("Credentials!","Wrong Credentials!");
            }*/
            Intent intent = new Intent(com.ids.team3.messmanager.Signup.this, MainDisplay.class);
            startActivity(intent);
            return null;
        }

//        protected void onPostExecute() {
            //Log.d("Post-execute","Let's see!");
//        }
    }
}