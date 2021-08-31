package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.musicshop.R;
import com.example.musicshop.Order;
import com.example.musicshop.OrderActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity<quantity> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.nameEditText);

    }

    void createSpinner() {

        spinner = findViewById(R.id.spinner);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Шаурма");
        spinnerArrayList.add("Шаурма бомба");
        spinnerArrayList.add("Донер");
        spinnerArrayList.add("Самса");


        spinnerAdapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    void createMap() {

        goodsMap = new HashMap();
        goodsMap.put("Шаурма", 100.0);
        goodsMap.put("Шаурма бомба", 180.0);
        goodsMap.put("Донер", 120.0);
        goodsMap.put("Самса", 60.0);

    }


    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity*price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if(quantity < 0){
            quantity = 0;
        };

        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity*price);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "Шаурма":
                goodsImageView.setImageResource(R.drawable.k);
                break;
            case "Шаурма бомба":
                goodsImageView.setImageResource(R.drawable.shaurmabomba);
                break;
            case "Донер":
                goodsImageView.setImageResource(R.drawable.doner);
                break;
            case "Самса":
                goodsImageView.setImageResource(R.drawable.samsa);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.k);
                break;

        }
    }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }

    public void addToCart(View view) {

        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodsName;

        order.quantity = quantity;

        order.orderPrice = quantity*price;

        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);





    }
}