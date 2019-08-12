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

public class AdapterMoreFood extends RecyclerView.Adapter<AdapterMoreFood.ViewHolder> {
    RootObject foodList;
    Context context;
    private View.OnClickListener mOnItemClickListener;
    public AdapterMoreFood(RootObject foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_food,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.foodname.setText(foodList.getData().get(i).getFoodName());

        viewHolder.price.setText("Rp."+foodList.getData().get(i).getPrice());
  String URL=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(foodList.getData().get(i).getImage()).toString();
        Picasso.get()
                .load(URL)
                .into(viewHolder.imgfood);

    }

    @Override
    public int getItemCount() {
        return foodList.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView foodname,price;
        public ImageView imgfood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname    = itemView.findViewById(R.id.tvNameFood);
            price       = itemView.findViewById(R.id.tvPrice);
            imgfood = itemView.findViewById(R.id.imgfood);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
