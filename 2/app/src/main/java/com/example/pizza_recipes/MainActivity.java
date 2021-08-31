package com.example.pizza_recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    GridLayoutManager layoutManager;
    private int columnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        columnCount = getResources().getInteger(R.integer.column_count);

        ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        recyclerViewItems.add(new RecyclerViewItem(R.drawable.morkovnye_kotlety,
                getResources().getString(R.string.pizza_1_name),
                getResources().getString(R.string.pizza_1_description),
                getResources().getString(R.string.pizza_1_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.zapekanka_makaron,
                getResources().getString(R.string.pizza_2_name),
                getResources().getString(R.string.pizza_2_description),
                getResources().getString(R.string.pizza_2_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.rybnye_kotlety,
                getResources().getString(R.string.pizza_3_name),
                getResources().getString(R.string.pizza_3_description),
                getResources().getString(R.string.pizza_3_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.lenivye_golubci,
                getResources().getString(R.string.pizza_4_name),
                getResources().getString(R.string.pizza_4_description),
                getResources().getString(R.string.pizza_4_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.basbusa,
                getResources().getString(R.string.pizza_5_name),
                getResources().getString(R.string.pizza_5_description),
                getResources().getString(R.string.pizza_5_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.pechen_po_stroganovski,
                getResources().getString(R.string.pizza_6_name),
                getResources().getString(R.string.pizza_6_description),
                getResources().getString(R.string.pizza_6_recipe)));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.kartofelnyi_graten,
                Utils.REC_7_TITLE, Utils.REC_7_DESCRIPTION, Utils.REC_7_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.salat_obzhorka,
                Utils.REC_8_TITLE, Utils.REC_8_DESCRIPTION, Utils.REC_8_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.salat_mimoza,
                Utils.REC_9_TITLE, Utils.REC_9_DESCRIPTION, Utils.REC_9_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.zapekanka_iz_kabachka,
                Utils.REC_10_TITLE, Utils.REC_10_DESCRIPTION, Utils.REC_10_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.chebureki_prostie,
                Utils.REC_11_TITLE, Utils.REC_11_DESCRIPTION, Utils.REC_11_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.salat_cezar,
                Utils.REC_12_TITLE, Utils.REC_12_DESCRIPTION, Utils.REC_12_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.fetuchchini,
                Utils.REC_13_TITLE, Utils.REC_13_DESCRIPTION, Utils.REC_13_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.borsch_osennii,
                Utils.REC_14_TITLE, Utils.REC_14_DESCRIPTION, Utils.REC_14_RECIPE));

        recyclerViewItems.add(new RecyclerViewItem(R.drawable.tomatnyi_sok,
                Utils.REC_15_TITLE, Utils.REC_15_DESCRIPTION, Utils.REC_15_RECIPE));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(recyclerViewItems, this);
        layoutManager  = new GridLayoutManager(this, columnCount);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


}