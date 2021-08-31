package com.example.myfamily.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.myfamily.data.MyFamilyContract.MemberEntry;



public class FamilyProvider extends ContentProvider {

    //const:
    private static  final int MEMBERS = 111;
    private static  final int MEMBER_ID = 222;
    //end of const

        MyFamilyDBOpenHelper dbOpenHelper;

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(MyFamilyContract.AUTHORITY, MyFamilyContract.PATH_MEMBERS, MEMBERS);
        uriMatcher.addURI(MyFamilyContract.AUTHORITY, MyFamilyContract.PATH_MEMBERS
                + "/#", MEMBER_ID);

    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyFamilyDBOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor;

        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                cursor = db.query(MemberEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            case MEMBER_ID:
                s = MemberEntry.KEY_ID + "=?";
                strings1 = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            default:
                throw new IllegalArgumentException("Can't query incorrect URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert( Uri uri, ContentValues contentValues) {
        //проверка валидности аргументов
        String lastName = contentValues.getAsString(MemberEntry.KEY_LAST_NAME);
        if(lastName == null){
            throw new IllegalArgumentException("You have to input last name");
        }

        String firstName = contentValues.getAsString(MemberEntry.KEY_FIRST_NAME);
        if(firstName == null){
            throw new IllegalArgumentException("You have to input first name");
        }

        String employment = contentValues.getAsString(MemberEntry.KEY_EMPLOYMENT);
        if(employment == null){
            throw new IllegalArgumentException("You have to input employment");
        }

        Integer gender = contentValues.getAsInteger(MemberEntry.KEY_GENDER);
            if(gender == null || !(gender == MemberEntry.GENDER_UNKNOWN
                    || gender == MemberEntry.GENDER_MALE
                    || gender == MemberEntry.GENDER_FEMALE)){
                throw new IllegalArgumentException("You have to input correct gender");
        }

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);

        switch(match){
            case MEMBERS:
                long id = db.insert(MemberEntry.TABLE_NAME, null, contentValues);
                if(id == -1){
                    Log.e("InsertMethod", "Insertation of data in the table failed for "+ uri);
                    return null;
                }

                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);

            default:
                throw new IllegalArgumentException("Can't query incorrect URI" + uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int rowsDeleted;
        switch(match){
            case MEMBERS:
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, s, strings);
                break;

            case MEMBER_ID:
                s = MemberEntry.KEY_ID + "=?";
                strings = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, s, strings);
                break;

            default:
                throw new IllegalArgumentException("Can't delete this URI" + uri);
        }
        if(rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        } return rowsDeleted;
    }

    @Override
    public int update( Uri uri, ContentValues contentValues, String s, String[] strings) {
        //проверка валидности аргументов
        if(contentValues.containsKey(MemberEntry.KEY_LAST_NAME)){
            String lastName = contentValues.getAsString(MemberEntry.KEY_LAST_NAME);
            if(lastName == null){
                throw new IllegalArgumentException("You have to input last name");
            }
        }
        if(contentValues.containsKey(MemberEntry.KEY_LAST_NAME)){
            String firstName = contentValues.getAsString(MemberEntry.KEY_FIRST_NAME);
            if(firstName == null){
                throw new IllegalArgumentException("You have to input first name");
            }
        }
        if(contentValues.containsKey(MemberEntry.KEY_LAST_NAME)){
            String employment = contentValues.getAsString(MemberEntry.KEY_EMPLOYMENT);
            if(employment == null){
                throw new IllegalArgumentException("You have to input employment");
            }
        }
        if(contentValues.containsKey(MemberEntry.KEY_LAST_NAME)){
            Integer gender = contentValues.getAsInteger(MemberEntry.KEY_GENDER);
            if(gender == null || !(gender == MemberEntry.GENDER_UNKNOWN
                    || gender == MemberEntry.GENDER_MALE
                    || gender == MemberEntry.GENDER_FEMALE)){
                throw new IllegalArgumentException("You have to input correct gender");
            }
        }

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int rowsUpdated;
        switch(match){
            case MEMBERS:
                rowsUpdated = db.update(MemberEntry.TABLE_NAME, contentValues, s, strings);
                break;

            case MEMBER_ID:
                s = MemberEntry.KEY_ID + "=?";
                strings = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = db.update(MemberEntry.TABLE_NAME, contentValues, s, strings);
                break;

            default:
                throw new IllegalArgumentException("Can't update this URI" + uri);
        }
        if(rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        } return rowsUpdated;
    }



    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                return MemberEntry.CONTENT_MULTIPLE_ITEMS;

            case MEMBER_ID:
                return  MemberEntry.CONTENT_SINGLE_ITEM;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }


}
