package com.ids.team3.messmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class LoginPage extends AppCompatActivity {
    private Button mBtLaunchActivity;
    private EditText Username;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        mBtLaunchActivity = (Button) findViewById(R.id.bt_launch_activity);

        mBtLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new loginClickedClass().execute();
            }
        });
    }
    class loginClickedClass extends AsyncTask<Void,Void,Void>
    {
        protected void onPreExecute() {
            Log.d("Pre-execute","Kya haal ?");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            boolean success=false;
            try {
                URL url = new URL("http://10.8.7.217/IDS/checkCredentials.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(Username.getText().toString(),"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password.getText().toString(),"UTF-8");
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
                bfr.close();
                is.close();
                urlConnection.disconnect();
                JSONObject obj = new JSONObject(result);
                success=obj.getBoolean("success");
            } catch (Exception e) {
                Log.d("Error!",e.toString());
            }
            if(success){
                Intent intent = new Intent(com.ids.team3.messmanager.LoginPage.this, MainDisplay.class);
                startActivity(intent);
            }
            else{
                Log.d("Credentials!","Wrong Credentials!");
            }
            return null;
        }

        protected void onPostExecute() {
            Log.d("Post-execute","Let's see!");
        }
    }
}
