package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Model.Students;
import Utils.Util;
import Utils.UtilS;

public class DatabaseHandlerStudents extends SQLiteOpenHelper {
    public DatabaseHandlerStudents(Context context) {
        super(context, UtilS.DATABASE_NAME, null, UtilS.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        //SQL - Structured  Query Language
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + UtilS.TABLE_NAME+ "("
                + UtilS.KEY_ID + " INTEGER PRIMARY KEY,"
                + UtilS.KEY_DEPARTMENT + " TEXT,"
                + UtilS.KEY_NAME + " TEXT,"
                + UtilS.KEY_SURNAME + " TEXT,"
                + UtilS.KEY_SCORE + " TEXT" + ")";

        sqLiteDb.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDb, int i, int i1) {
        sqLiteDb.execSQL("DROP TABLE IF EXISTS " + UtilS.TABLE_NAME);
        onCreate(sqLiteDb);
    }

    public void addStudent(Students students) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilS.KEY_DEPARTMENT, students.getDepartment());
        contentValues.put(UtilS.KEY_NAME, students.getName());
        contentValues.put(UtilS.KEY_SURNAME, students.getSurname());
        contentValues.put(UtilS.KEY_SCORE, students.getScore());


        db.insert(UtilS.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Students getStudents (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UtilS.TABLE_NAME,
                new String[]{UtilS.KEY_ID, UtilS.KEY_DEPARTMENT, UtilS.KEY_NAME, UtilS.KEY_SURNAME ,
                UtilS.KEY_SCORE}, UtilS.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        Students students = new Students();

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                students = new Students (Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4));
            } finally {
                cursor.close();
            }

        }
        return students;
    }

    public List<Students> getAllStudents(){

        SQLiteDatabase db = this.getReadableDatabase();

        List<Students> studentsList = new ArrayList<>();

        String selectAllStudents = "SELECT * FROM " + UtilS.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);
        if(cursor.moveToFirst()){
            try{
                do{
                    Students students = new Students();
                    students.setId(Integer.parseInt(cursor.getString(0)));
                    students.setDepartment(cursor.getString(1));
                    students.setName(cursor.getString(2));
                    students.setSurname(cursor.getString(3));
                    students.setScore(cursor.getInt(4));

                    studentsList.add(students);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        } return studentsList;

    }

    public int updateStudents (Students students){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilS.KEY_DEPARTMENT, students.getDepartment());
        contentValues.put(UtilS.KEY_NAME, students.getName());
        contentValues.put(UtilS.KEY_SURNAME, students.getSurname());
        contentValues.put(UtilS.KEY_SCORE, students.getScore());

        return db.update(UtilS.TABLE_NAME, contentValues, UtilS.KEY_ID + "=?",
                new String[] {String.valueOf(students.getId())});
    }

    public void deleteStudents (Students students){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(UtilS.TABLE_NAME, UtilS.KEY_ID + "=?",
                new String[] {String.valueOf(students.getId())});
        db.close();
    }

    public int getStudentsCount(){
        SQLiteDatabase db = this.getWritableDatabase();

        String countQuery = "SELECT * FROM " + UtilS.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return  cursor.getCount();
    }


}

