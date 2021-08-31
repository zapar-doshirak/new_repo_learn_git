package com.example.javaoop;

import android.util.Log;

public abstract class Cat {

    String name;
    int age;
    static int numberOfLegs = 4;
    static int count = 0;

    String helloText;
    CatMood catMood;

    abstract public void draw();

    class CatMood {
        int levelOfMood;

        CatMood() {
            if (Cat.this.age < 2) {
                levelOfMood = 100;
            } else if (Cat.this.age >= 2 && Cat.this.age < 7) {
                levelOfMood = 50;
            } else if (Cat.this.age >= 7) {
                levelOfMood = 20;
            }
        }

    }

    public Cat() {
        count++;
        this.name = "Krendel";
        this.age = 3;
        catMood = new CatMood();

        switch (catMood.levelOfMood) {
            case 100:
                helloText = "Meow! I'm happy cat! My name is " + name + ", and I'm " + age + " years old)";
                break;
            case 50:
                helloText = "Meow! I'm  cat. My name is " + name + ", and I'm " + age + " years old.";
                break;
            case 20:
                helloText = "Meow! I'm old and sick cat! My name is " + name + ", and I'm " + age + " years old(";
                break;
        }
    }

    public Cat(int age, String name) {
        this.name = name;
        this.age = age;
        catMood = new CatMood();

        switch (catMood.levelOfMood) {
            case 100:
                helloText = "Meow! I'm happy cat! My name is " + name + ", and I'm " + age + " years old)";
                break;
            case 50:
                helloText = "Meow! I'm  cat. My name is " + name + ", and I'm " + age + " years old.";
                break;
            case 20:
                helloText = "Meow! I'm old and sick cat! My name is " + name + ", and I'm " + age + " years old(";
                break;
        }
    }

    public void talk() {
        Log.i("talk", "" + helloText);

    }

    public static String whatCatsLike() {
        return "I like playing, jumping and sometimes scratching!";
    }


        public void catchMouse(int mouseWeight) {
            class Mouse {
                String color;
                int weight;

                public Mouse(String color, int weight){
                    this.color = color;
                    this.weight = weight;
                }

                String mouseVoice() {
                    return "Pi-pi-pi";
                }
            }

            Mouse mouse = new Mouse("White", mouseWeight);

            if(mouse.weight < 2){
                Log.i("cat say", "I'll eat you!" + mouse.mouseVoice());
            } else {
                Log.i("cat say", "I afraid you." + mouse.mouseVoice());
            }
        }






    }
