package com.example.notepad;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notepad.DAL.QueryOperations;
import com.example.notepad.adapters.TaskViewAdapter;
import com.example.notepad.models.Task;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.notepad.MainActivity.db;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Intent intent = getIntent();
        String category_id = intent.getStringExtra("CATEGORY_ID");

        QueryOperations queryOperations = new QueryOperations(db);

        queryOperations.getTasksByCategoryId(Integer.parseInt(category_id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(tasks -> {
                    Collections.sort(tasks, (o1, o2) -> o1.isDone() != o2.isDone() ? 0 : -1);
                    TaskViewAdapter adapter = new TaskViewAdapter( getApplicationContext(),tasks);
                    RecyclerView recyclerView = findViewById(R.id.task_content_rv);
                    recyclerView.setAdapter(adapter);
                });



        FloatingActionButton btnAddTask = findViewById(R.id.task_add_button);
        btnAddTask.setOnClickListener(view -> {

            AddTaskFragment addDialog = new AddTaskFragment();
            Bundle args = new Bundle();
            args.putInt("categoryId", Integer.parseInt(category_id));
            addDialog.setArguments(args);
            addDialog.show(getSupportFragmentManager(), "custom");
        });

    }

}
