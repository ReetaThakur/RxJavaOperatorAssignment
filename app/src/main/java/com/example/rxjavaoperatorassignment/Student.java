package com.example.rxjavaoperatorassignment;

public class Student {
    private int student_id;
    private String student_name;

    public Student(int student_id, String student_name) {
        this.student_id = student_id;
        this.student_name = student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }
}
