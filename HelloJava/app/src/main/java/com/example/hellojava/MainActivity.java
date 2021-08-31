package com.example.hellojava;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setHelloJavaText(View view) {
        TextView helloTextView = findViewById(R.id.nameTextView);
        helloTextView.setText("Хоть чему-то ты научилась, лентяйка");
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Slimamif.ttf");
        helloTextView.setTypeface(type);
    }
}