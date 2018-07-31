package com.example.notepad.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.icu.util.LocaleData;

@Entity(tableName = "tasks", foreignKeys =
@ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = ForeignKey.CASCADE))
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "create_date")
    private String createDate;
    @ColumnInfo(name = "is_done")
    private boolean isDone = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }



    private int sortedByisDone(Task task_1){
        return this.getDescription().compareTo(task_1.getDescription());
    }
}
