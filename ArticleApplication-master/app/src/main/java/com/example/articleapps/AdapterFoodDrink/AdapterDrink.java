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

import java.util.List;

public class AdapterDrink extends RecyclerView.Adapter<AdapterDrink.ViewHolder> {
    RootObject drinkList;
    Context context;
    private View.OnClickListener mOnItemClickListener;
    public AdapterDrink(RootObject drinkList, Context context) {
        this.drinkList = drinkList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_drink,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.drinkname.setText(drinkList.getData().get(i).getFoodName());
        viewHolder.price.setText("Rp."+drinkList.getData().get(i).getPrice());
        String URL=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(drinkList.getData().get(i).getImage()).toString();
        Picasso.get()
                .load(URL)
                .into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return drinkList.getData().size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView drinkname,price;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkname   = itemView.findViewById(R.id.tvNameDrink);
            price       = itemView.findViewById(R.id.tvPriceDrink);
            img         = itemView.findViewById(R.id.imgdrink);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
