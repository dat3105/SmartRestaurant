package com.example.articleapps.AdapterMainMenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.articleapps.Objectt.MainMenuObject;
import com.example.articleapps.R;

import java.util.ArrayList;

public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.ViewHolder> {
    private View.OnClickListener mOnItemClickListener;
    ArrayList<MainMenuObject> list;
    Context context;
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    public AdapterMainMenu(ArrayList<MainMenuObject> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_articles, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.tvTitle.setText(list.get(i).toString());
        viewHolder.tv_menu.setText(list.get(i).getTitle());
        viewHolder.imgArticle.setImageResource(list.get(i).getImg());
        viewHolder.imgicon.setImageResource(list.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgArticle,imgicon;
        public TextView tv_menu;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_menu = itemView.findViewById(R.id.tv_title_menu);
            imgArticle  = itemView.findViewById(R.id.img_view);
            imgicon = itemView.findViewById(R.id.iconmenu);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
}
