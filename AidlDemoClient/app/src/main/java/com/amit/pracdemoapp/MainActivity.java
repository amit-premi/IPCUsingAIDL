package com.amit.pracdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.pracdemoapp.adapter.StudentActivity;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    TextView txtViewDetails;
    Button btnClickMe, btnStdDetails, btnAidlTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String msgFloatSnack = getString(R.string.float_snack_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, msgFloatSnack, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtViewDetails = findViewById(R.id.main_txtView_details);
        btnClickMe = findViewById(R.id.main_btn_click);
        btnStdDetails = findViewById(R.id.main_btn_std_details);
        btnAidlTest = findViewById(R.id.main_btn_AidlTest);
        btnClickMe.setOnClickListener(this);
        btnStdDetails.setOnClickListener(this);
        btnAidlTest.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_btn_click:
                Toast.makeText(getBaseContext(),"Service called...",Toast.LENGTH_SHORT).show();

                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "http://192.168.0.3:8080/SampleRestWS/api/employee";
                //String url = "http://172.23.88.81:8080/SampleRestWS/api/employee";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getBaseContext(),"Service call success",Toast.LENGTH_SHORT).show();
                                txtViewDetails.setText(response.toString());
                                //responseJSON(response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtViewDetails.setText("Server error occured!");
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;

            case R.id.main_btn_std_details:
                Intent intent = new Intent(this, StudentActivity.class);
                startActivity(intent);
                break;

            case R.id.main_btn_AidlTest:
                Toast.makeText(getBaseContext(),"Aidl Test", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,AidlTestActivity.class));
                break;
        }
    }

    private void responseJSON(String respose){

        try {
            Log.e("MainActivity", "** JSON Response String: "+respose.toString());

            JSONObject jObject = new JSONObject(respose);
            String deptName = jObject.getString("deptName");

            JSONArray jArray = jObject.getJSONArray("empList");

            String name;
            String prof;
            String addr;

            for(int i=0; i<jArray.length(); i++){
                JSONObject empJSONObj = jArray.getJSONObject(i);
                name = empJSONObj.getString("name");
                prof = empJSONObj.getString("profession");
                addr = empJSONObj.getString("address");

                Log.e("MainActivity", name +" : "+ prof +" : "+ addr);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }
    }
}
