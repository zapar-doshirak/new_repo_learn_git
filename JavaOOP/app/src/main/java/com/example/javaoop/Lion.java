package com.example.javaoop;

import android.util.Log;

public class Lion extends Cat implements Movable, Printable {
    @Override
    public void draw() {
        Log.i("draw", "Draw Lion!!!");
    }

    @Override
    public void move() {
        Log.i("move", "Move override Lion!!!");
    }



    @Override
    public void print() {
        Log.i("print", "Print Lion!!!");
    }
}
