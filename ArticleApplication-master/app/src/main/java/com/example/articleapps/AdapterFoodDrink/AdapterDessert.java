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
import com.example.articleapps.Objectt.GioHang;

import com.example.articleapps.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterDessert extends RecyclerView.Adapter<AdapterDessert.ViewHolder> {
    public static ArrayList<GioHang> giohangdoan=new ArrayList<>();
    RootObject dessertsList;
    Context context;

    private View.OnClickListener mOnItemClickListener;
    public AdapterDessert(RootObject dessertsList, Context context) {
        this.dessertsList = dessertsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_dessert,viewGroup,false);
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
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return dessertsList.getData().size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dessertname,price;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dessertname   = itemView.findViewById(R.id.tvDessert);
            price       = itemView.findViewById(R.id.tvPriceDessert);
            img         = itemView.findViewById(R.id.imgdessert);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
