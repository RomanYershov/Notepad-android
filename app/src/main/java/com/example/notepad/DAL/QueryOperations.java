package com.example.notepad.DAL;

import android.util.Log;

import com.example.notepad.models.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Emitter;
import io.reactivex.Observable;

public class QueryOperations {

    public static Observable<Void> addCategory(DataBaseLibrary dataBaseLibrary, Category category){
          return Observable.create(subscriber -> {
              dataBaseLibrary.categoryDAO().insert(category);
              subscriber.onComplete();
          //subscriber.onNext();
        });
    }

    public static Observable<List<Category>> getCategories(DataBaseLibrary dataBaseLibrary){

        return Observable.create(subscriber -> subscriber.onNext(dataBaseLibrary.categoryDAO().getAll()));
    }
}
