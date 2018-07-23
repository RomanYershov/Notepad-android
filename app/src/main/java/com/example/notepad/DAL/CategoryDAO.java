package com.example.notepad.DAL;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.notepad.models.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Insert
    void insert(Category... category);
}
