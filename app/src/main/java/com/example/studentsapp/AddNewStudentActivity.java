package com.example.studentsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewStudentActivity extends AppCompatActivity  {
    EditText nameEt;
    EditText idEt;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEt = findViewById(R.id.main_name_et);
        idEt = findViewById(R.id.main_id_et);
        cb = findViewById(R.id.main_cb);
        Button saveBtn = findViewById(R.id.main_save_btn);
        Button cancelBtn = findViewById(R.id.main_cancel_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        String name = nameEt.getText().toString();
        String id = idEt.getText().toString();
        boolean flag = cb.isChecked();
        Log.d("TAG","saved name:" + name + " id:" + id + " flag:" + flag);
    }
}