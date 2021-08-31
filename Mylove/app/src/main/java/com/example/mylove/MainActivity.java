package com.example.mylove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isKebabVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eraseKebab(View view) {

        ImageView kebabImageView = findViewById(R.id.kebabImageView);
        ImageView shaurmaImageView = findViewById(R.id.shaurmaImageView);

        if (isKebabVisible) {
            kebabImageView.animate().alpha(0).rotation(kebabImageView.getRotation()+3600).scaleX(0).scaleY(0).setDuration(2000);
            shaurmaImageView.animate().alpha(1).rotation(shaurmaImageView.getRotation()+3600).scaleX(1).scaleY(1).setDuration(2000);
            isKebabVisible = false;
        } else {
            kebabImageView.animate().alpha(1).rotation(kebabImageView.getRotation()+3600).scaleX(1).scaleY(1).setDuration(3000);
            shaurmaImageView.animate().alpha(0).rotation(shaurmaImageView.getRotation()+3600).scaleX(0).scaleY(0).setDuration(3000);
            isKebabVisible = true;
        }
    }
}