package com.example.javaoop;

import android.util.Log;

public interface Movable {
    default void move(){

        Log.i("move", "Move Lion!!!");

    }
}
