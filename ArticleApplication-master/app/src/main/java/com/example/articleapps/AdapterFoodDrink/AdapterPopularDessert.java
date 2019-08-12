package com.example.articleapps.AdapterFoodDrink;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPopularDessert extends RecyclerView.Adapter<AdapterPopularDessert.ViewHolder> {
    RootObject dessertsList;
    Context context;

    public AdapterPopularDessert(RootObject dessertsList, Context context) {
        this.dessertsList = dessertsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_popular_dessert,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.dessertname.setText(dessertsList.getData().get(i).getFoodName());
        viewHolder.price.setText("Rp."+dessertsList.getData().get(i).getPrice());
        String URL=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(dessertsList.getData().get(i).getImage()).toString();
        Picasso.get()
                .load(URL)
                .into(viewHolder.imgpopulardessert);

    }

    @Override
    public int getItemCount() {
        return dessertsList.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dessertname,price;
        public ImageView imgpopulardessert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dessertname    = itemView.findViewById(R.id.tvPopularDessert);
            price       = itemView.findViewById(R.id.tvPricePopularDessert);
            imgpopulardessert = itemView.findViewById(R.id.img_popular_dessert);
        }
    }
}
