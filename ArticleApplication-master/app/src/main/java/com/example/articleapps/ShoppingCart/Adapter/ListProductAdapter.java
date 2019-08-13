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
        final TextView item_name = convertView.findViewById(R.id.item_name);
        final TextView item_price = convertView.findViewById(R.id.item_price);
        final TextView remove_item=convertView.findViewById(R.id.remove_item);
        TextView add_item=convertView.findViewById(R.id.add_item);
        final TextView item_amount=convertView.findViewById(R.id.item_amount);
//        id=rootObject.getData().get(position).getId();



            item_name.setText(giohangdoan.get(position).getName());
            item_price.setText(giohangdoan.get(position).getPrice()+"");
            item_amount.setText(giohangdoan.get(position).getSl()+"");

            Picasso.get()
                    .load(giohangdoan.get(position).getImg())
                    .into(product_thumb);


//



            tongtien=giohang.get(0).getPrice();

        final int[] tongtien1 = {tongtien};
//        final int[] count = {0};
        for(int i=0;i<giohangdoan.size();i++){
             tongtien1[0] = tongtien1[0]+giohangdoan.get(i).getPrice();
        }
        tinhtien.setText( tongtien1[0]+"");


        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=0;
                for(int i=0;i<giohangdoan.size();i++){
                    if(giohangdoan.get(i).getName().equalsIgnoreCase(item_name.getText().toString())){
                        id=i;
                        break;
                    }
                }
                giohangdoan.get(id).setSl(giohangdoan.get(id).getSl()+1);
                item_amount.setText(giohangdoan.get(id).getSl()+"");
                tinhtien.setText((tongtien1[0]+=giohangdoan.get(id).getPrice())+"");

            }
        });

        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=0;
                for(int i=0;i<giohangdoan.size();i++){
                    if(giohangdoan.get(i).getName().equalsIgnoreCase(item_name.getText().toString())){
                        id=i;
                        break;
                    }
                }
                if(giohangdoan.get(id).getSl()==0){
                    tinhtien.setText(""+(tongtien1[0]));
                    giohangdoan.remove(giohangdoan.get(id));
                    notifyDataSetChanged();

                }
                else{

                    giohangdoan.get(id).setSl(giohangdoan.get(id).getSl()-1);
                    item_amount.setText(giohangdoan.get(id).getSl()+"");
                    tinhtien.setText((tongtien1[0]-=giohangdoan.get(id).getPrice())+"");
                    Toast.makeText(c, giohangdoan.get(id).getSl()+"", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

}
