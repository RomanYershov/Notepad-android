package com.example.notepad;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.notepad.DAL.DataBaseLibrary;
import com.example.notepad.DAL.DbConnector;
import com.example.notepad.DAL.QueryOperations;
import com.example.notepad.models.Category;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
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
        Category category = new Category();
        category.setName("Покупки");

        QueryOperations.addCategory(db, category);

        //db.categoryDAO().insert(category);


        TextView showTv = findViewById(R.id.ma_category_tv);
        Button btnShowCategory = findViewById(R.id.ma_show_categories_btn);

        btnShowCategory.setOnClickListener(view -> {
            //List<Category> all = db.categoryDAO().getAll();
            //showTv.setText(all.get(0).getName());
            QueryOperations.getCategories(db)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(result -> {
                        Log.e("", result.toString());
                    });





        });
        // LiveData<List<Category>> all = db.categoryDAO().getAll();


        // io.reactivex.Observable<List<Category>> observable2 =  QueryOperations.getCategories(db);
        Log.d("test", "test");
       /* observable.subscribe(result -> {
         List<Category> categories = result;
        });*/

    }
}
