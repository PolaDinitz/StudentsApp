package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {
    List<Student> data;
    EditText nameEt;
    EditText idEt;
    CheckBox cb;
    Button cancelBtn;
    Button deleteBtn;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        nameEt = findViewById(R.id.edit_name_et);
        idEt = findViewById(R.id.edit_id_et);
        cb = findViewById(R.id.edit_cb);
        data = Model.instance.getAllStudents();
        cancelBtn = findViewById(R.id.edit_cancel_btn);
        deleteBtn = findViewById(R.id.edit_delete_btn);
        saveBtn = findViewById(R.id.edit_save_btn);

        String pos = getIntent().getStringExtra("EXTRA_POSITION");
        Log.d("EDIT","row was clicked " + pos);

        Student curr = data.get(Integer.parseInt(pos));
        Log.d("EDIT","Got student getting the contents ");
        cb.setChecked(curr.isFlag());
        nameEt.setText(curr.getName());
        idEt.setText(curr.getId());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EDIT","Setting cancel button");
                Intent sendIntent = new Intent(EditStudentActivity.this, StudentListRvAcivity.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                // Start the activity
                startActivity(sendIntent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EDIT","Setting save btn");
                String newName = nameEt.getText().toString();
                String newID = idEt.getText().toString();
                Boolean newCB = cb.isChecked();

                data.remove(Integer.parseInt(pos));
                data.add(new Student(newName, newID, newCB));

                Intent sendIntent = new Intent(EditStudentActivity.this, StudentListRvAcivity.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra("EXTRA_POSITION", String.valueOf(data.size() - 1));
                // Start the activity
                startActivity(sendIntent);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EDIT","Setting Delete button");
                data.remove(Integer.parseInt(pos));

                Intent sendIntent = new Intent(EditStudentActivity.this, StudentListRvAcivity.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                // Start the activity
                startActivity(sendIntent);

            }
        });
    }
}