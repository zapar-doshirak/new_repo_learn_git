package com.example.javaoop;

import android.util.Log;

public class Puma extends Cat {

    private String pumaHelloText;


    @Override
    public void draw() {
        Log.i("draw", "Draw puma!!!");
    }

    public Puma(){
    age = 3;
    name = "Puma";
    pumaHelloText = "I'm a cool cat!";
    }

    public Puma(int age, String name, String pumaHelloText){
        this.age = age;
        this.name = name;
        this.pumaHelloText = pumaHelloText;
    }

    public void talk(){
        Log.i("talk",createPumaTalkText());

    }

    private String createPumaTalkText(){
        String pumaTalkText = "R-r-r-r!"+ this.pumaHelloText + " I'm " + this.name +"! I'm" +
                this.age + "years old. " + whatCatsLike();
        return pumaTalkText;
    }

}
