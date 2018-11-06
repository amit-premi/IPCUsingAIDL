package com.amit.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PREM AMIT on 25-Jun-17.
 */

public class StudentInfo implements Parcelable {

    public String name;
    public String course;

    public StudentInfo(){}

    public StudentInfo(Parcel source){
        name = source.readString();
        course = source.readString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(course);
    }

    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>(){
        @Override
        public StudentInfo createFromParcel(Parcel source) {
            return new StudentInfo(source);
        }

        @Override
        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };
}
