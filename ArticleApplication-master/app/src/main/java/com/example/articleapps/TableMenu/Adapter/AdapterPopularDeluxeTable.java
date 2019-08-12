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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterPopularDeluxeTable extends RecyclerView.Adapter<AdapterPopularDeluxeTable.ViewHolder> {
    List<DataPopularDeluxeTable> tableList;
    Context context;
    private View.OnClickListener mOnItemClickListener;
    public static HashMap<String,String> giohang=new HashMap<>();

    public AdapterPopularDeluxeTable(ArrayList<DataPopularDeluxeTable> tableList, Context context) {
        this.tableList = tableList;
        this.context = context;
    }


    @NonNull

    @Override
    public AdapterPopularDeluxeTable.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_popular_deluxe_table,viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterPopularDeluxeTable.ViewHolder viewHolder, int i) {
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
            tablename    = itemView.findViewById(R.id.tvPopular_table);
            price       = itemView.findViewById(R.id.tvPrice_Popular_table);
            imgtable = itemView.findViewById(R.id.img_popular_table);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
}
