package com.example.notepad.DAL;

import android.util.Log;

import com.example.notepad.models.Category;

import java.util.List;

import io.reactivex.Observable;

public class QueryOperations {

    public static Observable<Void> addCategory(DataBaseLibrary dataBaseLibrary, Category category){
        return Observable.create(subscriber -> {
            dataBaseLibrary.categoryDAO().insert(category);
            Log.i("tat", "Добалена новая категория: " + category.getName());
        });
    }

    public static Observable<List<Category>> getCategories(DataBaseLibrary dataBaseLibrary){

        return Observable.create(subscriber -> subscriber.onNext(dataBaseLibrary.categoryDAO().getAll()));
    }
}
