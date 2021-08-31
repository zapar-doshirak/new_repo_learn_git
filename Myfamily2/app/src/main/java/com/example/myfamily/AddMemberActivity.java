package com.example.myfamily;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfamily.data.MyFamilyContract.MemberEntry;

public class AddMemberActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText fistNameEditText;
    private EditText lastNameEditText;
    private EditText employmentEditText;
    private Spinner genderSpinner;
    private int gender = 0;
    private ArrayAdapter spinnerAdapter;
    public static final int EDIT_MEMBER_LOADER = 111;
    Uri currentMemberUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        Intent intent = getIntent();
        currentMemberUri = intent.getData();
        if(currentMemberUri == null){
            setTitle("Add a member");
            invalidateOptionsMenu();
        } else {
            setTitle("Edit the member");
            getSupportLoaderManager().initLoader(EDIT_MEMBER_LOADER, null,this);
        }

        fistNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        employmentEditText = findViewById(R.id.employmentEditText);
        genderSpinner = findViewById(R.id.genderSpinner);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedGender = (String) adapterView.getItemAtPosition(i);
                if(!TextUtils.isEmpty(selectedGender)){
                    if(selectedGender.equals("Мужской")){
                        gender = MemberEntry.GENDER_MALE;
                    } else if(selectedGender.equals("Женский")){
                        gender = MemberEntry.GENDER_FEMALE;
                    } else {
                        gender = MemberEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.array_gender,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if(currentMemberUri == null){
            MenuItem menuItem = menu.findItem(R.id.delete_member);
            menuItem.setVisible(false);
        } return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_member:
                saveMember();
                return true;
            case R.id.delete_member:
                showDeleteMemberDialog();
                return true;
            case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void saveMember(){
        String lastName = lastNameEditText.getText().toString().trim();
        String firstName = fistNameEditText.getText().toString().trim();
        String employment = employmentEditText.getText().toString().trim();

        if(TextUtils.isEmpty(lastName)){
            Toast.makeText(this, "Введите фамилию", Toast.LENGTH_LONG).show();
            return;
        } else if(TextUtils.isEmpty(firstName)){
            Toast.makeText(this, "Введите имя", Toast.LENGTH_LONG).show();
            return;
        } else if(TextUtils.isEmpty(employment)){
            Toast.makeText(this, "Введите деятельность", Toast.LENGTH_LONG).show();
            return;
        } else if(gender == MemberEntry.GENDER_UNKNOWN){
            Toast.makeText(this, "Укажите пол", Toast.LENGTH_LONG).show();
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(MemberEntry.KEY_LAST_NAME, lastName);
        contentValues.put(MemberEntry.KEY_FIRST_NAME, firstName);
        contentValues.put(MemberEntry.KEY_EMPLOYMENT, employment);
        contentValues.put(MemberEntry.KEY_GENDER, gender);


        if(currentMemberUri == null){
            ContentResolver contentResolver = getContentResolver();
            Uri uri = contentResolver.insert(MemberEntry.CONTENT_URI, contentValues);

            if(uri == null){
                Toast.makeText(this, "Insertion of data on the table failed", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();
            }
        } else {
            int rowsChanged = getContentResolver().update(currentMemberUri, contentValues, null, null);

            if(rowsChanged == 0){
                Toast.makeText(this, "Saving of data is failed", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Member updated", Toast.LENGTH_LONG).show();
            }
        }



    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String[] projection = {
                MemberEntry.KEY_ID,
                MemberEntry.KEY_LAST_NAME,
                MemberEntry.KEY_FIRST_NAME,
                MemberEntry.KEY_EMPLOYMENT,
                MemberEntry.KEY_GENDER
        };
        return new CursorLoader(this,
                currentMemberUri, projection,
                null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if(data.moveToFirst()){
            int lastNameColumnIndex = data.getColumnIndex(MemberEntry.KEY_LAST_NAME);
            int firstNameColumnIndex = data.getColumnIndex(MemberEntry.KEY_FIRST_NAME);
            int employmentColumnIndex = data.getColumnIndex(MemberEntry.KEY_EMPLOYMENT);
            int genderColumnIndex = data.getColumnIndex(MemberEntry.KEY_GENDER);

            String lastName = data.getString(lastNameColumnIndex);
            String firstName = data.getString(firstNameColumnIndex);
            String employment = data.getString(employmentColumnIndex);
            Integer gender = data.getInt(lastNameColumnIndex);

            lastNameEditText.setText(lastName);
            fistNameEditText.setText(firstName);
            employmentEditText.setText(employment);
            switch(gender){
                case MemberEntry.GENDER_MALE:
                    genderSpinner.setSelection(1);
                    break;
                case MemberEntry.GENDER_FEMALE:
                    genderSpinner.setSelection(2);
                    break;
                case MemberEntry.GENDER_UNKNOWN:
                    genderSpinner.setSelection(0);
                    break;
            }

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    }
    private void showDeleteMemberDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Вы хотите удалить этого участника?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteMember();
            }
        });
        builder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dialogInterface != null){
                    dialogInterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void deleteMember(){
        if(currentMemberUri != null){
            int rowsDeleted = getContentResolver().delete(currentMemberUri, null, null);

            if(rowsDeleted == 0){
                Toast.makeText(this, "Deleting failed", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Member is deleted", Toast.LENGTH_LONG).show();
            }
        } finish();
    }
}