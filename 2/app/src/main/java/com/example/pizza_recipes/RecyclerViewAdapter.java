package com.example.pizza_recipes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    ArrayList<RecyclerViewItem> pizzaArrayList;
    Context context;

    public RecyclerViewAdapter (ArrayList<RecyclerViewItem> pizzaArrayList, Context context){
        this.pizzaArrayList = pizzaArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {

        RecyclerViewItem recyclerViewItem = pizzaArrayList.get(position);

        holder.imageView.setImageResource(recyclerViewItem.getImageResource());
        holder.textView1.setText(recyclerViewItem.getText1());
        holder.textView2.setText(recyclerViewItem.getText2());

    }

    @Override
    public int getItemCount() {
        return pizzaArrayList.size();
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

        }


        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            RecyclerViewItem recyclerViewItem = pizzaArrayList.get(position);

            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("imageResource", recyclerViewItem.getImageResource());
            intent.putExtra("title", recyclerViewItem.getText1());
            intent.putExtra("recipe", recyclerViewItem.getRecipe());
            context.startActivity(intent);


        }
    }
}
