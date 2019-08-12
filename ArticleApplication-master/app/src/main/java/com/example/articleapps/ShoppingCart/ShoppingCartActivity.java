package com.example.articleapps.ShoppingCart;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularFood;
import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.Adapter.ListProductAdapter;
import android.app.ProgressDialog;
import android.widget.TextView;

import com.google.gson.Gson;

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;
import static com.example.articleapps.TableMenu.Adapter.AdapterPopularDeluxeTable.giohang;

public class ShoppingCartActivity extends AppCompatActivity {
ListView lv_product;
ListProductAdapter adapter;
Button btnSend;
RootObject rootObject;
public static TextView tinhtien;

    private final String Link_JSON = "https://smartrestaurantntd.herokuapp.com/api/Food/getAllFood";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        btnSend=findViewById(R.id.btnSend);
        lv_product = findViewById(R.id.lv_product);
        tinhtien=findViewById(R.id.giatien);
        loadRss();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gson= new Gson().toJson(giohangdoan);
                System.out.println(gson);
            }
        });





    }
    private void loadRss() {
        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog mDialog = new ProgressDialog(ShoppingCartActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("XIN CHỜ....");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTODataHandler http = new HTTODataHandler();
                result = http.GetHTTPData(params[0]);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rootObject = new Gson().fromJson(s, RootObject.class);
               adapter = new ListProductAdapter(ShoppingCartActivity.this,rootObject);
                lv_product.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };


        loadRSSAsync.execute(Link_JSON);
    }
}
