package com.example.articleapps.MainMenu.News.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.MainMenu.News.Interface.ItemClickListener;
import com.example.articleapps.MainMenu.News.model.RSSObject;
import com.example.articleapps.R;
import com.squareup.picasso.Picasso;
class FeedViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    public TextView txtTitle, txtPubDate, txtContent;
    public ImageView img;
    private ItemClickListener itemClickListener;

    public FeedViewHolder2(View itemView){
        super(itemView);
        txtTitle= itemView.findViewById(R.id.tv_news_title);
        txtPubDate= itemView.findViewById(R.id.tv_news_date);
//        txtContent= itemView.findViewById(R.id.tvpopular_news_content);
        img = itemView.findViewById(R.id.img_popular_news);
        // set event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class PopularNewsAdapter extends RecyclerView.Adapter<FeedViewHolder2> {
    @NonNull

    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public PopularNewsAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }
    @Override
    public FeedViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_popular_news,parent,false);
        return new FeedViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder2 feedViewHolder, int i) {
//        String plainText = Html.fromHtml(rssObject.getItems().get(i).getContent()).toString();

        Picasso.get()
                .load(rssObject.getItems().get(i).getThumbnail())
                .into(feedViewHolder.img);

        feedViewHolder.txtTitle.setText(rssObject.getItems().get(i).getTitle());
        feedViewHolder.txtPubDate.setText(rssObject.getItems().get(i).getPubDate());
//        feedViewHolder.txtContent.setText(plainText);
        feedViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    browserIntent.setData(Uri.parse(rssObject.getItems().get(position).getLink()));
                    Intent chooserIntent = Intent.createChooser(browserIntent,"Open with");
                    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(chooserIntent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
