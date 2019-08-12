package com.example.articleapps.ShoppingCart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.ShoppingCartActivity;
import com.squareup.picasso.Picasso;

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;
import static com.example.articleapps.ShoppingCart.ShoppingCartActivity.tinhtien;
import static com.example.articleapps.TableMenu.Adapter.AdapterPopularDeluxeTable.giohang;

public class ListProductAdapter extends BaseAdapter {
private Context c;
private RootObject rootObject;
int tongtien=0;
public ListProductAdapter(Context c, RootObject rootObject){
    this.c=c;
    this.rootObject=rootObject;
}
    @Override
    public int getCount() {
        return giohangdoan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    int id ;
    convertView = LayoutInflater.from(c).inflate(R.layout.item_product_list,parent,false);
        ImageView product_thumb = convertView.findViewById(R.id.product_thumb);
        TextView item_name = convertView.findViewById(R.id.item_name);
        final TextView item_price = convertView.findViewById(R.id.item_price);
        TextView remove_item=convertView.findViewById(R.id.remove_item);
        TextView add_item=convertView.findViewById(R.id.add_item);
        final TextView item_amount=convertView.findViewById(R.id.item_amount);
//        id=rootObject.getData().get(position).getId();



            item_name.setText(giohangdoan.get(position).getName());
            item_price.setText(giohangdoan.get(position).getPrice()+"");


            Picasso.get()
                    .load(giohangdoan.get(position).getImg())
                    .into(product_thumb);


//         {
//            item_name.setText(rootObject.getData().get(position).getFoodName());
//            item_price.setText(rootObject.getData().get(position).getPrice());
//            String URL = (new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
//            Picasso.get()
//                    .load(URL)
//                    .into(product_thumb);
//        }

        try{
            tongtien=Integer.parseInt(giohang.get("ban"));
        }catch (Exception e){

        }
        final int[] tongtien1 = {tongtien};
//        final int[] count = {0};
        for(int i=0;i<giohangdoan.size();i++){
             tongtien1[0] = tongtien1[0]+giohangdoan.get(i).getPrice();
        }
        tinhtien.setText("tong tien: "+ tongtien1[0]);

//        add_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count[0] = count[0] + 1;
//                item_amount.setText(count[0]+"");
//                tongtien+=Integer.parseInt(item_price.getText().toString());
//                tinhtien.setText("tong tien: "+tongtien);
//                Toast.makeText(c, ""+position, Toast.LENGTH_SHORT).show();
//            }
//        });
        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    giohangdoan.remove(position);
                    tongtien1[0] =tongtien;
                for(int i=0;i<giohangdoan.size();i++){
                    tongtien1[0]+=giohangdoan.get(i).getPrice();
                }
                    tinhtien.setText("tong tien: "+tongtien1[0]);
                Toast.makeText(c, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

}
