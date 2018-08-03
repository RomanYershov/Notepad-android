package com.example.notepad.DAL;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.notepad.models.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Category category);
}
