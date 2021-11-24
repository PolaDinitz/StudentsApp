package com.example.studentsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        data = Model.instance.getAllStudents();

        MyAdapter adapter = new MyAdapter();

        ListView listv = findViewById(R.id.studentlist_listv);
        listv.setAdapter(adapter);

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG","row was clicked" + position);
            }
        });


    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.student_list_row,null);
                CheckBox cb = convertView.findViewById(R.id.listrow_cb);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = Integer.parseInt(v.getTag().toString());
                        Log.d("TAG","cbn position " + pos);
                        Student s = data.get(pos);
                        s.setFlag(cb.isChecked());
                    }
                });
            }
            TextView nameTv = convertView.findViewById(R.id.listrow_name_tv);
            TextView idTv = convertView.findViewById(R.id.listrow_id_tv);
            CheckBox cb = convertView.findViewById(R.id.listrow_cb);
            cb.setTag(position);

            Student student = data.get(position);
            nameTv.setText(student.getName());
            idTv.setText(student.getId());

            cb.setChecked(student.isFlag());
            return convertView;
        }
    }
}