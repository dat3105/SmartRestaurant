package com.example.articleapps.TableMenu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.articleapps.R;
import com.example.articleapps.TableMenu.Model.DataPopularDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataPopularPremiumTable;

import java.util.ArrayList;
import java.util.List;

public class AdapterPopularPremiumTable extends RecyclerView.Adapter<AdapterPopularPremiumTable.ViewHolder> {
    List<DataPopularPremiumTable> tableList;
    Context context;
    private View.OnClickListener mOnItemClickListener;

    public AdapterPopularPremiumTable(ArrayList<DataPopularPremiumTable> tableList, Context context) {
        this.tableList = tableList;
        this.context = context;
    }


    @NonNull

    @Override
    public AdapterPopularPremiumTable.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_popular_premium_table,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterPopularPremiumTable.ViewHolder viewHolder, int i) {
        viewHolder.tablename.setText(tableList.get(i).getTitle());
        viewHolder.price.setText(tableList.get(i).getPrice());
        viewHolder.imgtable.setImageResource(tableList.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tablename,price;
        public ImageView imgtable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tablename    = itemView.findViewById(R.id.tvPopular_premium_table);
            price       = itemView.findViewById(R.id.tvPrice_Popular_Premium_table);
            imgtable = itemView.findViewById(R.id.img_popular_premium_table);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
