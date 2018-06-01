package com.ids.team3.messmanager;

import android.content.Intent;
import android.media.Rating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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


public class Feedback extends AppCompatActivity {
    private Spinner messName;
    private EditText description;
    private RatingBar rating;
    private Button submit;
    private String userid;
    private String idType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Bundle bundle = getIntent().getExtras();
        String[] user = bundle.getString("userid").split("@");
        userid = user[0];
        idType = user[1];
        messName = (Spinner) findViewById(R.id.mess);
        description = (EditText) findViewById(R.id.description);
        rating = (RatingBar) findViewById(R.id.feedback_rating_bar);
        submit = (Button) findViewById(R.id.feedback_button);
        new GetMess().execute();
        if(idType.equals("student")){
            messName.setEnabled(false);
            // messName.setClickable(false);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SubmitFeedback().execute();
            }
        });
    }

    class GetMess extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.43.252/IDS/getMess.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")
                        +"&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(idType,"UTF-8");
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
                String messname = obj.getString("messname");
                ArrayAdapter arad = (ArrayAdapter)messName.getAdapter();
                for(int i=0;i<arad.getCount();i++){
                    String s = arad.getItem(i).toString();
                    if(s.contains(messname)){
                        messName.setSelection(i);
                        break;
                    }
                }
            } catch (Exception e) {
                Log.d("Error!",e.toString());
            }
            return null;
        }
    }

    class SubmitFeedback extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.43.252/IDS/submitFeedback.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String messname = messName.getSelectedItem().toString().split(" ")[1];
                messname=messname.substring(1,3);
                String post_data =
                        URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description.getText().toString(),"UTF-8")
                                +"&"+URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")
                                +"&"+URLEncoder.encode("rating","UTF-8")+"="+URLEncoder.encode(Integer.toString((int)rating.getRating()),"UTF-8")
                                +"&"+URLEncoder.encode("messname","UTF-8")+"="+URLEncoder.encode(messname,"UTF-8");
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
                Log.d("result",result);
            } catch (Exception e) {
                Log.d("Error!",e.toString());
            }
            Intent intent = new Intent(com.ids.team3.messmanager.Feedback.this, MainDisplay.class);
            intent.putExtra("userid",userid+"@"+idType);
            startActivity(intent);
            return null;
        }
    }
}
