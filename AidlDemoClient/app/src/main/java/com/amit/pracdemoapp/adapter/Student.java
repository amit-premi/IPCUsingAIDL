package com.amit.pracdemoapp.adapter;

/**
 * Created by M1036398 on 17-Jun-17.
 */

class Student {

    private String stId;
    private String stName;
    private String stDOB;
    private String stCourse;

    Student(String stId, String stName, String stDOB, String stCourse) {
        this.stId = stId;
        this.stName = stName;
        this.stDOB = stDOB;
        this.stCourse = stCourse;
    }

    String getStId() {
        return stId;
    }

    String getStName() {
        return stName;
    }

    String getStDOB() {
        return stDOB;
    }

    String getStCourse() {
        return stCourse;
    }
}
