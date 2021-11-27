package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        TextView nameTv = findViewById(R.id.details_name_tv);
        TextView idTv = findViewById(R.id.details_id_tv);
        CheckBox cb = findViewById(R.id.edit_cb);
        Button EditBtn = findViewById(R.id.edit_save_btn);
        List<Student> data = Model.instance.getAllStudents();

        cb.setClickable(false);

        String pos = getIntent().getStringExtra("EXTRA_POSITION");
        Log.d("DETAILS","row was clicked " + pos);
        Student curr = data.get(Integer.parseInt(pos));

        nameTv.setText(curr.getName());
        idTv.setText((curr.getId()));
        cb.setChecked(curr.isFlag());

        EditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(StudentDetailsActivity.this, EditStudentActivity.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                Log.d("DETAILS","Edit student " + pos);
                sendIntent.putExtra("EXTRA_POSITION", String.valueOf(pos));
                // Start the activity
                startActivity(sendIntent);
            }
        });
    }
}