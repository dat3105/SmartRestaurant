package com.example.articleapps.MainMenu.News.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.articleapps.MainMenu.News.Interface.ItemClickListener;
import com.example.articleapps.MainMenu.News.model.RSSObject;
import com.example.articleapps.R;

import com.squareup.picasso.Picasso;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
public TextView txtTitle, txtPubDate, txtContent;
public ImageView img;
private ItemClickListener itemClickListener;

public FeedViewHolder(View itemView){
    super(itemView);
    txtTitle= itemView.findViewById(R.id.txtTitle);
    txtPubDate= itemView.findViewById(R.id.txtPubDate);
    txtContent= itemView.findViewById(R.id.txtContent);
    img = itemView.findViewById(R.id.imageView4);
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

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    @NonNull

    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_more_news,parent,false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {
        String plainText = Html.fromHtml(rssObject.getItems().get(i).getContent()).toString();

        Picasso.get()
                .load(rssObject.getItems().get(i).getThumbnail())
                .into(feedViewHolder.img);

        feedViewHolder.txtTitle.setText(rssObject.getItems().get(i).getTitle());
        feedViewHolder.txtPubDate.setText(rssObject.getItems().get(i).getPubDate());
        feedViewHolder.txtContent.setText(plainText);
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
