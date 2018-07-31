package com.example.notepad;

import android.annotation.SuppressLint;

import android.content.Intent;
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
import com.example.notepad.models.Task;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static DataBaseLibrary db;
    QueryOperations queryOperations;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Category products = new Category();
        products.setName("Медитация");





        DbConnector instance = DbConnector.getInstance(getApplicationContext());
        db = instance.getLibraryDb();
        queryOperations = new QueryOperations(db);

       /* queryOperations.addCategory(products)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe();*/

        queryOperations.getCategories()
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

    public void onClickCategoryCard(View view) {
        TextView textView = ((TextView) view.findViewById(R.id.category_name_tv));
        String name = textView.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        int categoryId = (int) textView.getTag();
        Intent taskActivityIntent = new Intent(getApplicationContext(), TaskActivity.class);
        taskActivityIntent.putExtra("CATEGORY_ID", String.valueOf(categoryId));

        startActivity(taskActivityIntent);
    }

}
