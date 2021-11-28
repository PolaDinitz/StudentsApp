package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListRvAcivity extends AppCompatActivity {

    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_rv_acivity);

        data = Model.instance.getAllStudents();

        RecyclerView list = findViewById(R.id.studentlist_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Create the text message with a string
                Intent sendIntent = new Intent(StudentListRvAcivity.this, StudentDetailsActivity.class);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                Log.d("MAIN","row was clicked " + position);
                sendIntent.putExtra("EXTRA_POSITION", String.valueOf(position));
                // Start the activity
                startActivity(sendIntent);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.listrow_name_tv);
            idTv = itemView.findViewById(R.id.listrow_id_tv);
            cb = itemView.findViewById(R.id.listrow_cb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public void onAddStudentClick(View view) {
        Intent createStudentActivityIntent = new Intent(StudentListRvAcivity.this, AddNewStudentActivity.class);
        startActivity(createStudentActivityIntent);
    }
}