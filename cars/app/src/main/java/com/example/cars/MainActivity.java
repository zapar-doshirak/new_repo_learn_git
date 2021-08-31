package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Data.DatabaseHandlerStudents;
import Model.Car;
import Model.Students;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        DatabaseHandlerStudents DS = new DatabaseHandlerStudents(this);

        Log.d("CarsCount", String.valueOf(databaseHandler.getCarsCount()));
        Log.d("StudentsCount", String.valueOf(DS.getStudentsCount()));

        /*databaseHandler.addCar(new Car( "Toyota", "30000$"));
        databaseHandler.addCar(new Car( "Opel", "30000$"));
        databaseHandler.addCar(new Car( "Mercedes", "30000$"));
        databaseHandler.addCar(new Car( "KIA", "30000$"));
        databaseHandler.addCar(new Car( "Honda", "30000$"));

        List<Car> carList = databaseHandler.getAllCars();
        for(Car car: carList){
            Log.d("CarInfo:", "ID " + car.getId()
                    + ", name " + car.getName()
                    + ", price " + car.getPrice());
        }*/


        Car car = databaseHandler.getCar(3);
        /*Log.d("CarInfo:", "ID " + car.getId()
                + ", name " + car.getName()
                + ", price " + car.getPrice());*/

        car.setPrice("55 000$");
        car.setName("Mercedes Camri");

        databaseHandler.updateCar(car);
        Log.d("CarInfo:", "ID " + car.getId()
                + ", name " + car.getName()
                + ", price " + car.getPrice());

        DS.addStudent(new Students("Medical", "Kate", "Smith", 243));
        DS.addStudent(new Students("Surgical", "Jane", "Eyre", 210));
        DS.addStudent(new Students("Language", "Alexandr", "Pushkin", 196));
        DS.addStudent(new Students("Psyhology", "Khaled", "Hosseiny", 250));
        DS.addStudent(new Students("Economical", "Alan", "Piz", 180));

        List<Students> studentsList = DS.getAllStudents();
        for (Students students : studentsList) {
            Log.d("StudentsInfo:", "ID " + students.getId()
                    + ", department: " + students.getDepartment()
                    + ", name: " + students.getName()
                    + ", surname: " + students.getSurname()
                    + ", score: " + students.getScore());

        }

        Students deleteStudent = DS.getStudents(6);
        DS.deleteStudents(deleteStudent);




    }
}