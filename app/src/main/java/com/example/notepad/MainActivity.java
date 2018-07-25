package com.example.notepad;

import android.annotation.SuppressLint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad.DAL.DataBaseLibrary;
import com.example.notepad.DAL.DbConnector;
import com.example.notepad.DAL.QueryOperations;
import com.example.notepad.adapters.CategoriesViewAdapter;
import com.example.notepad.models.Category;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DbConnector instance = DbConnector.getInstance(getApplicationContext());
        DataBaseLibrary db = instance.getLibraryDb();

        QueryOperations.getCategories(db)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        categories -> {
                            CategoriesViewAdapter adapter = new CategoriesViewAdapter(categories);
                            RecyclerView recyclerView = findViewById(R.id.ma_content_rv);
                            recyclerView.setAdapter(adapter);
                        }
                );




    }

    public void test(View view) {
        String name =  ((TextView)view.findViewById(R.id.category_name_tv)).getText().toString();

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

}
