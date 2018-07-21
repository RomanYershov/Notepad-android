package com.example.notepad.DAL;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.notepad.models.Category;
import com.example.notepad.models.Task;

public class DbConnector {
    private static final String DATABASE_NAME = "libraryDatabase";
    private static DbConnector dataBaseConnector;
    private DataBaseLibrary dataBaseLibrary;
    private CategoryDAO categoryDAO;

    private DbConnector(Context context) {
        dataBaseLibrary = Room.databaseBuilder(context,DataBaseLibrary.class, DATABASE_NAME).build();
    }

    private DbConnector(){}

    public DataBaseLibrary getLibraryDb() {
        return dataBaseLibrary;
    }

    public static DbConnector  getInstance(Context context){
        if(dataBaseConnector == null){
            return  new DbConnector(context);
        }
        return dataBaseConnector;
    }


    public void createCategory()
}
