package com.amit.aidl;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.amit.aidl.adapter.Student;
import com.amit.aidl.adapter.StudentAdapter;
import java.util.ArrayList;

/**
 * Created by PREM AMIT on 21-Jun-17.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    ArrayList<Student> studentList;
    ListView listView;
    StudentAdapter studentAdapter;
    Student studentObj;
    String stdId, stdName, stdDOB, stdCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toobar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = findViewById(R.id.listview_student);
        listView.setNestedScrollingEnabled(true);

        createStudentList();
        studentAdapter = new StudentAdapter(this, studentList);
        listView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.nav_camera){
            Toast.makeText(getBaseContext(),"Camera", Toast.LENGTH_LONG).show();
        }else if(itemId == R.id.nav_gallery){
            Toast.makeText(getBaseContext(),"Gallery", Toast.LENGTH_LONG).show();
        }else if(itemId == R.id.nav_slideshow){
            Toast.makeText(getBaseContext(),"SlideShow", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createStudentList(){
        studentList = new ArrayList<Student>();
        for(int i=0; i<30; i++){

            if(i%2==0){
                stdCourse = "Computer";
            }else if(i%3==0){
                stdCourse = "Electronics";
            }else{
                stdCourse = "Civil";
            }
            studentObj = new Student("Id_"+i, "Amit_"+i, "01-01-1990", stdCourse);
            studentList.add(studentObj);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(studentList != null){
            stdId = studentList.get(position).getStId();
            stdName = studentList.get(position).getStName();
            stdDOB = studentList.get(position).getStDOB();
            stdCourse = studentList.get(position).getStCourse();

            Toast.makeText(getBaseContext(),"Item clicked: "+"\n"+"Id:"+ stdId +"\n"+"Name:"+ stdName +"\n"+"DOB:"+ stdDOB +"\n"+"Course:"+ stdCourse,Toast.LENGTH_LONG).show();
        }
    }
}
