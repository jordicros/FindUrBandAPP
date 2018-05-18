package com.jordicros997.findurband.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordicros997.findurband.Fragments.GrupFragment;
import com.jordicros997.findurband.Models.Grup;
import com.jordicros997.findurband.R;
import com.jordicros997.findurband.Data.UserData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MenuInicial extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        this.getSupportActionBar().setTitle(UserData.getInstance().getUser().nickname + "|   FindUrBand");
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_menu_inicial);
        TextView t = headerView.findViewById(R.id.userBarra);
        t.setText(UserData.getInstance().getUser().nickname);
        TextView e = headerView.findViewById(R.id.correuBarra);
        e.setText(UserData.getInstance().getUser().emailAddr);

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        getGroups();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicial, menu);
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

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_band) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            GrupFragment fragment = new GrupFragment();
            transaction.replace(R.id.grup_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_jam) {

        }else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getGroups() {
        String query = String.format("http://192.168.43.54:9000/Application/carregarGrups?");
        new getGroups().execute(query);
    }
    public class getGroups extends AsyncTask<String, Void, String> {
        Context context;
        InputStream stream = null;
        //protected SendRegAsync(Context context){this.context = context;}
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

                EditText estilMusical = (EditText) findViewById(R.id.editText);
                EditText comarca = (EditText) findViewById(R.id.editText2);
                EditText email = (EditText) findViewById(R.id.editText3);
                String urlParameters = "username="+UserData.getInstance().getUser().nickname.toString();
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
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "-1";
            }
        }
        @Override
        protected void onPostExecute(String resultat){
            final String result;
            if(resultat.equals("404"))
                result = "No groups found, you can create one!";
            else
            {
                result = "Showing groups...";
                ObjectMapper mapper = new ObjectMapper();;
                try {
                    UserData.getInstance().setGrups((List<Grup>) mapper.readValue(resultat, new TypeReference<List<Grup>>(){}));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void userWannaFind(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void userCreatesGroup(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void userWannaJoin(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void userUndoJoining(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void memberAcceptsDeclines(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void modGrup(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void modUser(){}
    //public class getGroups extends AsyncTask<String, Void, String>
    public void sortirGrup(){}
    //public class getGroups extends AsyncTask<String, Void, String>
}
