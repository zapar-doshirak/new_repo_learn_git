package com.example.carsdatabase.;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.carsdatabase.R;

import java.util.List;

import Data.DatabaseHandler;
import Model.Car;
import Utils.Util;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHandler = new DatabaseHandler();

        dataBaseHandler.addCar(new Car( "Toyota", "30000$"));
        dataBaseHandler.addCar(new Car( "Opel", "30000$"));
        dataBaseHandler.addCar(new Car( "Mercedes", "30000$"));
        dataBaseHandler.addCar(new Car( "KIA", "30000$"));
        dataBaseHandler.addCar(new Car( "Honda", "30000$"));

        List<Car> carList = dataBaseHandler.getAllCars();
        for(Car car: carList){
            Log.d("CarInfo:", "ID " + car.getId()
                    + ", name " + car.getName()
                    + ", price " + car.getPrice());
        }





    }
}