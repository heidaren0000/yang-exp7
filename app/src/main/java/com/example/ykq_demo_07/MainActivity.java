package com.example.ykq_demo_07;

import static com.example.ykq_demo_07.StudentBaseHelper.getVersion;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtnCreateDB;
    private Button mBtnUpdateDB;
    private Button mBtnInsertData;
    private Button mBtnAlterData;
    private Button mBtnDeleteData;
    private Button mBtnQueryData;

    private Context mContext;

    private ContentValues mValues;
    private SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mBtnCreateDB = findViewById(R.id.btn_create);
        mBtnUpdateDB = findViewById(R.id.btn_update);
        mBtnInsertData = findViewById(R.id.btn_insert);
        mBtnAlterData = findViewById(R.id.btn_alter);
        mBtnDeleteData = findViewById(R.id.btn_delete);
        mBtnQueryData = findViewById(R.id.btn_query);

        mBtnCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = new StudentOldBaseHelper(mContext).getWritableDatabase();
                Toast.makeText(getApplicationContext(), getString(R.string.toast_db_created), Toast.LENGTH_SHORT).show();
            }
        });

        mBtnUpdateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = new StudentBaseHelper(mContext).getWritableDatabase();
                Toast.makeText(getApplicationContext(), getString(R.string.toast_db_updated) + Integer.toString(getVersion()), Toast.LENGTH_SHORT).show();
            }
        });

        mBtnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create content values
                mValues = new ContentValues();
                mValues.put(StudentDbSchema.StudentTable.Cols.ID, "1");
                mValues.put(StudentDbSchema.StudentTable.Cols.AGE, "22");
                mValues.put(StudentDbSchema.StudentTable.Cols.NAME, "yangkaiqi");
                mValues.put(StudentDbSchema.StudentTable.Cols.MAJOR, "Computer Science");

                mDatabase.insert(StudentDbSchema.StudentTable.NAME, null, mValues);

                Toast.makeText(getApplicationContext(), getString(R.string.toast_data_inserted) + mValues.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        mBtnAlterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = "yangkaiqi2";
                String oldName = "yangkaiqi";
                ContentValues alterValues = new ContentValues();
                alterValues.put(StudentDbSchema.StudentTable.Cols.ID, "1");
                alterValues.put(StudentDbSchema.StudentTable.Cols.AGE, "22");
                alterValues.put(StudentDbSchema.StudentTable.Cols.NAME, "yangkaiqi2");
                alterValues.put(StudentDbSchema.StudentTable.Cols.MAJOR, "Computer Science");
                mDatabase.update(StudentDbSchema.StudentTable.NAME, alterValues, StudentDbSchema.StudentTable.Cols.NAME + " = ?",
                        new String[] { oldName });

                Toast.makeText(getApplicationContext(), getString(R.string.toast_data_updated) + newName, Toast.LENGTH_SHORT).show();
            }
        });

        mBtnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name4delete = "yangkaiqi";
                mDatabase.delete(StudentDbSchema.StudentTable.NAME, StudentDbSchema.StudentTable.Cols.NAME + " = ?",
                        new String[] {name4delete});
                Toast.makeText(getApplicationContext(), getString(R.string.btn_text_db_delete) + name4delete, Toast.LENGTH_SHORT).show();


            }
        });

        mBtnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mDatabase.query(StudentDbSchema.StudentTable.NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                cursor.moveToPosition(0);
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(StudentDbSchema.StudentTable.Cols.NAME));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(StudentDbSchema.StudentTable.Cols.AGE));
                @SuppressLint("Range") String major = cursor.getString(cursor.getColumnIndex(StudentDbSchema.StudentTable.Cols.MAJOR));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(StudentDbSchema.StudentTable.Cols.ID));
                Toast.makeText(getApplicationContext(), getString(R.string.toast_data_query) + " " + name + " " + age + " " + major + " " + id , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
