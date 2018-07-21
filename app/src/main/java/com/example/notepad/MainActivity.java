package com.example.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.notepad.DAL.DbConnector;

import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        io.reactivex.Observable<DbConnector> connectorObservable =
    }
}
