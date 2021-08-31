package com.example.mytaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ChooseModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

    }

    public void goToPassengerSignIn(View view) {
        startActivity(new Intent(ChooseModeActivity.this, PassengerSignIn.class));
    }

    public void goToDriverSignIn(View view) {
        startActivity(new Intent(ChooseModeActivity.this, DriverSignIn.class));
    }
}