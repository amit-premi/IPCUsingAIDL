package com.amit.pracdemoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amit.pracdemoapp.R;

import java.util.ArrayList;

/**
 * Created by M1036398 on 17-Jun-17.
 */

public class StudentAdapter extends BaseAdapter {

    private ArrayList<Student> studentList;
    private LayoutInflater inflater;

    StudentAdapter(Context context,ArrayList<Student> studentList) {
        this.studentList = studentList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StudentViewHolder studentViewHolder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.student_list_item,parent,false);
            //convertView = inflater.inflate(R.layout.student_list_item, null);
            studentViewHolder = new StudentViewHolder();

            studentViewHolder.txtStdId = (TextView) convertView.findViewById(R.id.txtView_stId);
            studentViewHolder.txtStdName = (TextView) convertView.findViewById(R.id.txtView_stName);
            studentViewHolder.txtStdDOB = (TextView) convertView.findViewById(R.id.txtView_stDOB);
            studentViewHolder.txtStdCourse = (TextView) convertView.findViewById(R.id.txtView_stCourse);

            convertView.setTag(studentViewHolder);
        }else {
            studentViewHolder = (StudentViewHolder)convertView.getTag();
        }
            studentViewHolder.txtStdId.setText(studentList.get(position).getStId());
            studentViewHolder.txtStdName.setText(studentList.get(position).getStName());
            studentViewHolder.txtStdDOB.setText(studentList.get(position).getStDOB());
            studentViewHolder.txtStdCourse.setText(studentList.get(position).getStCourse());

        return convertView;
    }

    static class StudentViewHolder {
        TextView txtStdId, txtStdName, txtStdDOB, txtStdCourse;

    }
}
