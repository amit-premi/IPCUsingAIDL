package com.amit.pracdemoapp.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amit.pracdemoapp.R;

import java.util.ArrayList;

/**
 * Created by M1036398 on 17-Jun-17.
 */

public class StudentActivity extends Activity implements AdapterView.OnItemClickListener {

    ArrayList<Student> studentList;
    ListView listView;
    StudentAdapter studentAdapter;
    Student studentObj;
    String stdId, stdName, stdDOB, stdCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);
        listView = (ListView)findViewById(R.id.listview_student);

        createStudentList();
        studentAdapter = new StudentAdapter(this, studentList);
        listView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
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
