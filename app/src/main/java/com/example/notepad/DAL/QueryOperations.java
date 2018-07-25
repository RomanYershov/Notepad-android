package com.example.notepad.DAL;

import android.util.Log;

import com.example.notepad.models.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QueryOperations {

    public static void addCategory(DataBaseLibrary dataBaseLibrary, Category category){
      Completable.fromAction(() -> dataBaseLibrary.categoryDAO().insert(category))
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .subscribe();

    }




    public static Observable<List<Category>> getCategories(DataBaseLibrary dataBaseLibrary){

        return Observable.create(subscriber -> subscriber.onNext(dataBaseLibrary.categoryDAO().getAll()));

    }
}
