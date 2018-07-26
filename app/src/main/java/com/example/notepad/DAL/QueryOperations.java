package com.example.notepad.DAL;

import android.util.Log;

import com.example.notepad.models.Category;
import com.example.notepad.models.Task;

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

    private DataBaseLibrary dataBaseLibrary;

    public QueryOperations(DataBaseLibrary dataBaseLibrary) {
        this.dataBaseLibrary = dataBaseLibrary;
    }

   /* public  void addCategory(Category category){
      Completable.fromAction(() -> dataBaseLibrary.categoryDAO().insert(category))
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .subscribe();
    }*/

   public Observable<Long> addCategory(Category category){
       return Observable.create(x -> x.onNext(dataBaseLibrary.categoryDAO().insert(category)));
   }



    public  Observable<List<Category>> getCategories(){
        return Observable.create(subscriber ->
                subscriber.onNext(dataBaseLibrary.categoryDAO().getAll()));
    }

    public  Observable<List<Task>> getTasksByCategoryId(int categoryId){
        return Observable.create(subscribe ->
                subscribe.onNext( dataBaseLibrary.taskDAO().getTasksByCategoryId(categoryId)));
    }

    public void addTask(Task task){
         Completable.fromAction(() -> dataBaseLibrary.taskDAO().insert(task))
         .observeOn(AndroidSchedulers.mainThread())
         .subscribeOn(Schedulers.newThread())
         .subscribe();
    }

   /* public Observable<Long> deleteTask(Task task){
        return Observable.create(x -> x.onNext(dataBaseLibrary.taskDAO().delete(task)));
    }*/


}
