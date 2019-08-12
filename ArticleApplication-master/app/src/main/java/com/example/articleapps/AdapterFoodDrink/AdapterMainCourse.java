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

public class AdapterMainCourse extends RecyclerView.Adapter<AdapterMainCourse.ViewHolder> {
    RootObject mainCourseList;
    Context context;
    private View.OnClickListener mOnItemClickListener;
    public AdapterMainCourse(RootObject mainCourseList, Context context) {
        this.mainCourseList = mainCourseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_maincourse,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.maincoursename.setText(mainCourseList.getData().get(i).getFoodName());
        viewHolder.price.setText("Rp."+mainCourseList.getData().get(i).getPrice());
        String URL=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(mainCourseList.getData().get(i).getImage()).toString();
        Picasso.get()
                .load(URL)
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return mainCourseList.getData().size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView maincoursename,price;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maincoursename   = itemView.findViewById(R.id.tvNameMainCourse);
            price       = itemView.findViewById(R.id.tvPriceMainCourse);
            img         = itemView.findViewById(R.id.imgmaincourse);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
