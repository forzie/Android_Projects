package com.example.foramshah.studentinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Result extends AppCompatActivity {
    TextView name,course,sem,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        name=findViewById(R.id.txtName);
        marks=findViewById(R.id.txtMarks);
        sem=findViewById(R.id.txtSem);
        course=findViewById(R.id.txtCourse);

        String StudentName = getIntent().getStringExtra("Name");
        String StudentMarks = getIntent().getStringExtra("marks");
        String StudentSem = getIntent().getStringExtra("sem");
        String StudentCourse = getIntent().getStringExtra("course");
        name.setText(StudentName);
        marks.setText(StudentMarks);
        sem.setText(StudentSem);
        course.setText(StudentCourse);
    }
}
