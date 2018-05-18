package com.jordicros997.findurband.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.jordicros997.findurband.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void resultat(String result){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setMessage(result).setTitle("Register");
        Dialog dialeg = builder.create();
        dialeg.show();

    }
    public void sendPost(View view){
        String query = String.format("http://192.168.43.54:9000/Application/registre?");
        new SendRegAsync().execute(query);
    }
    public class SendRegAsync extends AsyncTask<String, Void, String>{
        Context context;
        InputStream stream = null;
        // protected SendRegAsync(Context context){this.context = context;}
        @Override
        protected String doInBackground(String... urls){
            try {
                String result;
                String query = String.format(urls[0]);
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 );
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                EditText usuari = (EditText) findViewById(R.id.editText);
                EditText password = (EditText) findViewById(R.id.editText2);
                EditText email = (EditText) findViewById(R.id.editText3);
                String urlParameters = "username="+usuari.getText().toString()+"&password="+password.getText().toString()+"&email="+email.getText().toString();
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                stream = conn.getInputStream();

                BufferedReader reader = null;

                StringBuilder sb = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(stream));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
                //Codi correcte
                Log.i("Missatge del servidor:", result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "-1";
            }
        }
        @Override
        protected void onPostExecute(String resultat){
            final String result;
            Handler handler = new Handler();
            if(resultat.equals("200"))
                result = "Register succesful.";
            else
                result = "Register failed, username already exists.";
            handler.post(new Runnable() {
                public void run() {
                    resultat(result);
                }
            });

        }
    }
}
