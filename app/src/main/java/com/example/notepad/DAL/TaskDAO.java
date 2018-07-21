package com.example.notepad.DAL;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.notepad.models.Task;

import java.util.List;

public interface TaskDAO {

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE id = :categoryId")
    List<Task> getTasksByCategoryId(int categoryId);

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void done(Task task);
}
