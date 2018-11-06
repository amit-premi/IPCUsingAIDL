package com.amit.aidl.adapter;

/**
 * Created by M1036398 on 17-Jun-17.
 */

public class Student {

    private String stId, stName, stDOB, stCourse;

    public Student(String stId, String stName, String stDOB, String stCourse) {
        this.stId = stId;
        this.stName = stName;
        this.stDOB = stDOB;
        this.stCourse = stCourse;
    }

    public String getStId() {
        return stId;
    }

    public String getStName() {
        return stName;
    }

    public String getStDOB() {
        return stDOB;
    }

    public String getStCourse() {
        return stCourse;
    }
}
