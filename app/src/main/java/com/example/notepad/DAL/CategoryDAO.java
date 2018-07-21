package com.example.notepad.DAL;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.notepad.models.Category;

import java.util.List;

public interface CategoryDAO {
    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Insert
    void insert(Category category);
}
