package com.example.notepad.DAL;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.notepad.models.Task;

import java.util.List;


@Dao
public interface TaskDAO {

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE id = :categoryId")
    List<Task> getTasksByCategoryId(int categoryId);

    @Insert
    long insert(Task task);

    @Delete
    void delete(Task task);

    @Query("UPDATE  tasks SET is_done = :isDone WHERE id = :taskId")
    void done(int taskId, boolean isDone);
}
