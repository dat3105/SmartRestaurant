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
import com.example.articleapps.AdapterFoodDrink.AdapterDessert;
import com.example.articleapps.AdapterFoodDrink.AdapterDrink;
import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularDessert;
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
public class DessertFragment extends Fragment {
    RootObject rootObject;
RecyclerView rvDessert,rvPopularDessert;
AdapterDessert mAdapter;
AdapterPopularDessert madapter2;

    private final String Link_JSON = "https://smartrestaurantntd.herokuapp.com/api/Food/getFoodByType?type=3";
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            final int position = viewHolder.getAdapterPosition();


            Toast.makeText(getActivity(), "You Clicked: " + position, Toast.LENGTH_SHORT).show();
            //id=4
            if (position == 0) {
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Panna Cotta")
                        .setMessage("Thành phần : Trứng, đường , sữa ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.plangif)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                GioHang giohang=new GioHang(4,"Panna Cotta",130000,url);
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
                //id =5
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Semifreddo")
                        .setMessage("Thành phần: Rau câu  ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.jellygif)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                GioHang giohang=new GioHang(5,"Semifreddo",145000,url);
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
                //id=1
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Suờn xào chua ngọt")
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

    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dessert, container, false);
        rvDessert = v.findViewById(R.id.rvMoreDessert);        rvDessert.setHasFixedSize(true);
        rvPopularDessert = v.findViewById(R.id.rvPopularDessert);        rvPopularDessert.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvPopularDessert.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvDessert.setLayoutManager(layoutManager2);

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
                rvDessert.setAdapter(adapter);
                rvPopularDessert.setAdapter(popularFood);
                adapter.setOnItemClickListener(onItemClickListener);
                popularFood.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        };


        loadRSSAsync.execute(Link_JSON);
    }
}
