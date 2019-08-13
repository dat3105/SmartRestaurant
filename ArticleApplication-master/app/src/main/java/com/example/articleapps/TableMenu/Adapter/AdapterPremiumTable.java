package com.example.articleapps.TableMenu.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.articleapps.FoodTab.MainFood;
import com.example.articleapps.MainMenu.MainMenu;
import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.ShoppingCartActivity;
import com.example.articleapps.TableMenu.Model.DataDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataPremiumTable;
import com.example.articleapps.TableMenu.Model.ban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.articleapps.TableMenu.Adapter.AdapterPopularDeluxeTable.giohang;
import static com.example.articleapps.TableMenu.Fragment.DeluxeTableFragment.dsbansang;
import static com.example.articleapps.TableMenu.Fragment.PremiumTableFragment.dsban;

public class AdapterPremiumTable extends RecyclerView.Adapter<AdapterPremiumTable.ViewHolder> {
    List<DataPremiumTable> tableList;

    Context context;
    private static final String SERVER = "http://10.0.2.2:3000/";
    private View.OnClickListener mOnItemClickListener;
    public AdapterPremiumTable(List<DataPremiumTable> tableList, Context context) {
        this.tableList = tableList;
        this.context = context;
    }

    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_premium_table,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.premium_table_tile.setText(tableList.get(i).getTitle());
        viewHolder.premium_table_price.setText(tableList.get(i).getPrice());
        viewHolder.img.setImageResource(tableList.get(i).getImg());
    }
    public void insertProductToCart(){
        HttpGetRequest request = new HttpGetRequest();
        request.execute();
    }

    public class HttpGetRequest extends AsyncTask<Void, Void, String> {

        static final String REQUEST_METHOD = "GET";
        static final int READ_TIMEOUT = 15000;
        static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(Void... params){
            String result;
            String inputLine;

            try {
                // connect to the server
                URL myUrl = new URL(SERVER);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.connect();

                // get the string from the input stream
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();

            } catch(IOException e) {
                e.printStackTrace();
                result = "error";
            }

            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
          
        }
    }

    public int getItemCount() {
        return tableList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView premium_table_tile,premium_table_price;
        public Button premium_table_button_Buy;
        public RatingBar ratingBar;
        public ImageView img;
        public Button btnbuy;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            btnbuy=itemView.findViewById(R.id.premium_table_button_Buy);
            premium_table_tile= itemView.findViewById(R.id.premium_table_tile);
            premium_table_price=itemView.findViewById(R.id.premium_table_price);
            premium_table_button_Buy=itemView.findViewById(R.id.premium_table_button_Buy);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            img=itemView.findViewById(R.id.img_premium_table);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

            btnbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    insertProductToCart(); // ham chuyen du lieu vao gio hang
                    giohang=new ArrayList<>();
                    ban bands=new ban();
                    for(int j=0;j<dsban.size();j++){
                        if(dsban.get(j).getName().equalsIgnoreCase(premium_table_tile.getText().toString())){
                            bands=dsban.get(j);
                        }
                    }
                    giohang.add(bands);
                    Intent i = new Intent(itemView.getContext(), MainFood.class);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
