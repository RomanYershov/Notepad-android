package com.example.notepad.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.icu.util.LocaleData;

@Entity(tableName = "tasks", foreignKeys =
@ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id"))
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "create_date")
    private LocaleData createDate;
    @ColumnInfo(name = "is_done")
    private boolean isDone;
}
