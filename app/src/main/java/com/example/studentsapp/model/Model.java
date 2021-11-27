package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
        for(int i=0;i<10;i++){
            com.example.studentsapp.model.Student s = new com.example.studentsapp.model.Student("name",""+i,false);
            data.add(s);
        }
    }

    List<com.example.studentsapp.model.Student> data = new LinkedList<com.example.studentsapp.model.Student>();

    public List<com.example.studentsapp.model.Student> getAllStudents(){
        return data;
    }

    public void addStudent(com.example.studentsapp.model.Student student){
        data.add(student);
    }

    public void deleteStudent(int position){ data.remove(position); }
}
