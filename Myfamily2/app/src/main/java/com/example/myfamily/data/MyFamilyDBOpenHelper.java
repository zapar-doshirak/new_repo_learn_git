package com.example.myfamily.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myfamily.data.MyFamilyContract.MemberEntry;

import androidx.annotation.Nullable;

public class MyFamilyDBOpenHelper extends SQLiteOpenHelper {
    public MyFamilyDBOpenHelper(Context context) {
        super(context, MyFamilyContract.DATABASE_NAME, null, MyFamilyContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAMILY_TABLE = "CREATE TABLE "+ MemberEntry.TABLE_NAME + "("
                + MemberEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + MemberEntry.KEY_LAST_NAME + " TEXT,"
                + MemberEntry.KEY_FIRST_NAME + " TEXT,"
                + MemberEntry.KEY_EMPLOYMENT + " TEXT,"
                + MemberEntry.KEY_GENDER + " INTEGER NOT NULL" + ")";
        db.execSQL(CREATE_FAMILY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MemberEntry.TABLE_NAME);
        onCreate(db);
    }
}
