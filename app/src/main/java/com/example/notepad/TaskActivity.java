package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.notepad.DAL.QueryOperations;
import com.example.notepad.adapters.TaskViewAdapter;
import com.example.notepad.models.Task;

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
                    TaskViewAdapter adapter = new TaskViewAdapter(tasks);
                    RecyclerView recyclerView = findViewById(R.id.task_content_rv);
                    recyclerView.setAdapter(adapter);
                });

    }
}
