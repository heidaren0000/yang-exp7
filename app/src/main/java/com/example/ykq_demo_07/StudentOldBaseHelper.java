package com.example.ykq_demo_07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ykq_demo_07.StudentOldDbSchema.StudentTable;

public class StudentOldBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "studentBase.db";

    public StudentOldBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + StudentTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                StudentTable.Cols.ID + ", " +
                StudentTable.Cols.NAME + ", " +
                StudentTable.Cols.AGE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
