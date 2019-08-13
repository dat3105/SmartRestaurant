package com.example.articleapps.FoodTab;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularFood;
import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.MainMenu.News.Adapter.FeedAdapter;
import com.example.articleapps.MainMenu.News.Adapter.PopularNewsAdapter;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.MainMenu.News.NewsActivity;
import com.example.articleapps.MainMenu.News.model.RSSObject;
import com.example.articleapps.Objectt.GioHang;

import com.example.articleapps.R;
import com.example.articleapps.TableMenu.Model.DataPopularPremiumTable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;
import static com.example.articleapps.FoodTab.MainFood.sl;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppetizerFragment extends Fragment {
    RootObject rootObject;
    RecyclerView rvPopularFood, rvMoreFood;

    AdapterPopularFood mAdapter;
    AdapterMoreFood mAdapter2;
    private final String Link_JSON = "https://smartrestaurantntd.herokuapp.com/api/Food/getFoodByType?type=1";

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();


            Toast.makeText(getActivity(), "You Clicked: " + position, Toast.LENGTH_SHORT).show();
            //id=0
            if (position == 0) {
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Mì xào hải sản")
                        .setMessage("Thành phần : cơm , trứng , rau xà lách")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.noodle)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Bạn đã đặt món", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(0).getImage()).toString();
                                    boolean a=false;

                                for(int i=0;i<giohangdoan.size();i++){
                                    if(giohangdoan.get(i).getId()==0){
                                        a=true;
                                    }
                                }
                                if(a==true){
                                    Toast.makeText(getActivity(), "mon an da duoc dat", Toast.LENGTH_SHORT).show();
                                }else {
                                    GioHang giohang = new GioHang(0, "Mì xào hải sản", 80000, url, 1);
                                    giohangdoan.add(giohang);
                                    sl.setText(giohangdoan.size() + "");
                                }
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
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Sườn xào chua ngọt")
                        .setMessage("Thành phần: thịt bò, ớt cay ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.meat)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Bạn đã đặt món", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(1).getImage()).toString();
                                boolean a=false;

                                for(int i=0;i<giohangdoan.size();i++){
                                    if(giohangdoan.get(i).getId()==1){
                                        a=true;
                                    }
                                }
                                if(a==true){
                                    Toast.makeText(getActivity(), "mon an da duoc dat", Toast.LENGTH_SHORT).show();
                                }else {
                                    GioHang giohang = new GioHang(1, "Sườn xào chua ngọt", 150000, url, 1);
                                    giohangdoan.add(giohang);
                                    sl.setText(giohangdoan.size() + "");
                                }
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
                                Toast.makeText(getContext(), "Bạn đã đặt món", Toast.LENGTH_SHORT).show();
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

    public AppetizerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);


        rvPopularFood = v.findViewById(R.id.rvPopularFood);
        rvMoreFood = v.findViewById(R.id.rvMoreFood);

        rvPopularFood.setHasFixedSize(true);
        rvMoreFood.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvPopularFood.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvMoreFood.setLayoutManager(layoutManager2);
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
                rvPopularFood.setAdapter(popularFood);
                rvMoreFood.setAdapter(adapter);
                adapter.setOnItemClickListener(onItemClickListener);
                popularFood.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        };


        loadRSSAsync.execute(Link_JSON);
    }
}
