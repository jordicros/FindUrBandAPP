package com.jordicros997.findurband.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jordicros997.findurband.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void loginButton(View button)
    {
        Intent obrirLogin = new Intent(this, LoginActivity.class);
        startActivity(obrirLogin);
    }
    public void registerButton(View button)
    {
        Intent obrirRegistre = new Intent(this, RegisterActivity.class);
        startActivity(obrirRegistre);
    }
}
