package com.example.ykq_demo_07;

import static com.example.ykq_demo_07.StudentBaseHelper.getVersion;

import android.content.ContentValues;
import android.content.Context;
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
            }
        });

        mBtnAlterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.update(StudentDbSchema.StudentTable.NAME, null, mValues);
            }
        });

        mBtnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
