package com.example.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DataBaseHandler;
import Model.Cars;
import Utils.Util;

public class MainActivity extends AppCompatActivity {

    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHandler = new DataBaseHandler();

        dataBaseHandler.addCar(new Cars( "Toyota", "30000$"));
        dataBaseHandler.addCar(new Cars( "Opel", "30000$"));
        dataBaseHandler.addCar(new Cars( "Mercedes", "30000$"));
        dataBaseHandler.addCar(new Cars( "KIA", "30000$"));
        dataBaseHandler.addCar(new Cars( "Honda", "30000$"));

        List<Cars> carList = dataBaseHandler.getAllCars();
        for(Cars car: carList){
            Log.d("CarInfo:", "ID " + car.getId()
                    + ", name " + car.getName()
                    + ", price " + car.getPrice());
        }





    }
}