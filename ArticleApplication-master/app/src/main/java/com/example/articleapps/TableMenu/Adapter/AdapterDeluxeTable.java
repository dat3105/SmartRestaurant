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
import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.ShoppingCartActivity;
import com.example.articleapps.TableMenu.Model.DataDeluxeTable;
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

public class AdapterDeluxeTable extends RecyclerView.Adapter<AdapterDeluxeTable.ViewHolder> {
    List<DataDeluxeTable> tableList;
    private static final String SERVER = "http://10.0.2.2:3000/";
    Context context;
    private View.OnClickListener mOnItemClickListener;
    public AdapterDeluxeTable(List<DataDeluxeTable> tableList, Context context) {
        this.tableList = tableList;
        this.context = context;
    }

    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_more_deluxe_table,viewGroup,false);
   ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
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
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.deluxe_table_title.setText(tableList.get(i).getTitle());
        viewHolder.deluxe_table_price.setText("Rp."+tableList.get(i).getPrice());
        viewHolder.img.setImageResource(tableList.get(i).getImg());
    }



    public int getItemCount() {
        return tableList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deluxe_table_title,deluxe_table_price;
        public Button deluxe_table_button_buy;
        public RatingBar ratingBar;
        public ImageView img;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            deluxe_table_title= itemView.findViewById(R.id.deluxe_table_tile);
            deluxe_table_price=itemView.findViewById(R.id.deluxe_table_price);
            deluxe_table_button_buy=itemView.findViewById(R.id.deluxe_table_button_Buy);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            img=itemView.findViewById(R.id.img_deluxe_table);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
            deluxe_table_button_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    insertProductToCart();
                     giohang = new ArrayList<>();
                    ban bands=new ban();

                    for(int j=0;j<dsbansang.size();j++){
                        if(dsbansang.get(j).getName().equalsIgnoreCase(deluxe_table_title.getText().toString())){
                            bands=dsbansang.get(j);
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
