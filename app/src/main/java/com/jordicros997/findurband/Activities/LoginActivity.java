package com.jordicros997.findurband.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordicros997.findurband.R;
import com.jordicros997.findurband.Data.UserData;
import com.jordicros997.findurband.Models.Usuari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void resultat(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setMessage(result).setTitle("Register");
        Dialog dialeg = builder.create();
        dialeg.show();

    }

    public void sendLogin(View view) {
        EditText usuari = (EditText) findViewById(R.id.editText);
        EditText password = (EditText) findViewById(R.id.editText2);
        String query = "http://192.168.43.54:9000/Application/login?username=" + usuari.getText().toString() + "&password=" + password.getText().toString();
        new SendLoginAsync().execute(query);
    }

    private class SendLoginAsync extends AsyncTask<String, Void, String> {
        Context context;
        InputStream stream = null;

        // private SendLoginAsync(Context context){this.context = context;}
        @Override
        protected String doInBackground(String... urls) {
            try {
                String result;
                EditText usuari = (EditText) findViewById(R.id.editText);
                EditText password = (EditText) findViewById(R.id.editText2);
                String query = urls[0];
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
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
        protected void onPostExecute(String resultat) {
            final String result;
            if (resultat.equals("404")) {
                result = "Login failed, check all fields.";
                resultat(result);
            } else {

                ObjectMapper mapper = new ObjectMapper();
                try {
                    UserData.getInstance().setUser(mapper.readValue(resultat, Usuari.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result = "Login succesful.";
                resultat(result);
                Intent mainMenu = new Intent(LoginActivity.this, MenuInicial.class);
                mainMenu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainMenu);

            }

        }
    }
}
