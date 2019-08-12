package com.example.articleapps.FoodTab;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.articleapps.AdapterFoodDrink.AdapterDrink;
import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularDrink;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularFood;
import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.Objectt.GioHang;
import com.example.articleapps.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkFragment extends Fragment {
    RootObject rootObject;
    RecyclerView rvDrink,rvPoplarDrink;
    AdapterDrink mAdapter;
    AdapterPopularDrink madapter2;
    private final String Link_JSON = "https://smartrestaurantntd.herokuapp.com/api/Food/getFoodByType?type=4";
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            final int position = viewHolder.getAdapterPosition();


            Toast.makeText(getActivity(), "You Clicked: " + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
                //id=6
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Tequila Sunrise Cocktail")
                        .setMessage("Thành phần : Rượu, trái cây nhiệt đới ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.coca)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                GioHang giohang=new GioHang(6,"Tequila Sunrise Cocktail",69000,url);
                                giohangdoan.add(giohang);
                            }
                        })
                        .OnNegativeClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            } else if (position == 1) {
                //id=7
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Rượu vang Pháp Le Carmes")
                        .setMessage("Thành phần: rượu vang  ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.coffee)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                GioHang giohang=new GioHang(7,"Rượu vang Pháp Le Carmes",3999000,url);
                                giohangdoan.add(giohang);
                            }
                        })
                        .OnNegativeClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            } else if (position == 2) {
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Thịt bò nướng")
                        .setMessage("Thành phần: khoai tây chiên ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.potato)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            }
        }
    };
    public DrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drink, container, false);
        rvDrink = v.findViewById(R.id.rvDrink);
        rvPoplarDrink = v.findViewById(R.id.rvPopularDrink);
        rvDrink.setHasFixedSize(true);
        rvPoplarDrink.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvPoplarDrink.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvDrink.setLayoutManager(layoutManager2);


loadRss();

        return v;
    }
    private void loadRss() {
        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog mDialog = new ProgressDialog(getActivity());

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
                AdapterMoreFood adapter = new AdapterMoreFood(rootObject, getActivity().getBaseContext());
                AdapterPopularFood popularFood = new AdapterPopularFood(rootObject, getActivity().getBaseContext());
                rvDrink.setAdapter(adapter);
                rvPoplarDrink.setAdapter(popularFood);
                adapter.setOnItemClickListener(onItemClickListener);
                popularFood.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        };


        loadRSSAsync.execute(Link_JSON);
    }

}
