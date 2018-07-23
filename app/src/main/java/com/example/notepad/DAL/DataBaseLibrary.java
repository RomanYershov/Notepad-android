package com.example.notepad.DAL;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.notepad.models.Category;
import com.example.notepad.models.Task;

@Database(entities = {Category.class, Task.class}, version = 1)
public abstract class DataBaseLibrary extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();
    public abstract TaskDAO taskDAO();
}
