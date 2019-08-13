package com.example.articleapps.ShoppingCart;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularFood;
import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.LoginForm.Activity_Login;
import com.example.articleapps.LoginForm.Activity_Register;
import com.example.articleapps.MainMenu.MainMenu;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.Adapter.ListProductAdapter;
import android.app.ProgressDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
                String formattedDate = df.format(c);
                HashMap<String,String> zxc=new HashMap<>();
                zxc.put("datePay",formattedDate);
                zxc.put("status","false");
                zxc.put("idTable",giohang.get(0).getId()+"");
                zxc.put( "sumPrice",tinhtien.getText().toString());
                List<doituong1> dt1=new ArrayList<>();
                for(int i=0;i<giohangdoan.size();i++){
                    dt1.add(new doituong1(giohangdoan.get(i).getId(),giohangdoan.get(i).getSl()));
                }
                String gson= new Gson().toJson(zxc);
                String gson1=new Gson().toJson(dt1);

              String ketqua=(gson.substring(0,gson.length()-1)+","+'"'+"arrayFood"+'"'+":"+gson1+"}");
                System.out.println(ketqua);
                new Request10().execute(ketqua);
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

    public class Request10 extends AsyncTask<String,String,String> {
        private static final String USER_AGENT = "";

        com.gc.materialdesign.widgets.ProgressDialog progressDialog;
        protected void onPreExecute() {
            progressDialog = new com.gc.materialdesign.widgets.ProgressDialog(ShoppingCartActivity.this,"Loading");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonData=params[0];
            HttpURLConnection con=null;
            BufferedReader reader=null;
            try{
                URL url=new URL("https://smartrestaurantntd.herokuapp.com/api/bill/postBill");
                con=(HttpURLConnection)url.openConnection();
                con.setDoOutput(true);

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type","application/json");
                con.setRequestProperty("Accept","application/json");

                Writer writer=new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
                writer.write(JsonData);
                writer.close();
                int responseCode=0;
                try {
                    responseCode = con.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader in = null;
                try {
                    in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String inputLine = null;
                StringBuffer response = new StringBuffer();

                while (true) {
                    try {
                        if (!((inputLine = in.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    response.append(inputLine);
                }

                JSONObject obj=new JSONObject(String.valueOf(response));

                String result=obj.getString("msg");
                return result;



            }catch (Exception e){
                return "error";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog != null)
                progressDialog.dismiss();
            System.out.println(result);
            if(result.equalsIgnoreCase("insert successfull")){
                Intent i=new Intent(ShoppingCartActivity.this, MainMenu.class);
                startActivity(i);
                Toast.makeText(ShoppingCartActivity.this, "Đặt chỗ thành công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ShoppingCartActivity.this, "Đặt chỗ thất bại", Toast.LENGTH_SHORT).show();
            }


            super.onPostExecute(result);
        }
    }
}
class doituong1{
   public  int id;
    public int sl;
    public doituong1(int id, int sl){
        this.id=id;
        this.sl=sl;
    }


}

