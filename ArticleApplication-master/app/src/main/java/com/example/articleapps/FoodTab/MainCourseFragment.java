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
import com.example.articleapps.AdapterFoodDrink.AdapterMainCourse;
import com.example.articleapps.AdapterFoodDrink.AdapterMoreFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularFood;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularMainCourse;
import com.example.articleapps.FoodTab.Model.RootObject;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.Objectt.GioHang;
import com.example.articleapps.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;
import static com.example.articleapps.FoodTab.MainFood.sl;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCourseFragment extends Fragment {
RecyclerView rvMainCourse , rvPopularMainCourse;
AdapterMainCourse mAdapter;
AdapterPopularMainCourse madapter2;
RootObject rootObject;

    private final String Link_JSON = "https://smartrestaurantntd.herokuapp.com/api/Food/getFoodByType?type=2";
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            final int position = viewHolder.getAdapterPosition();


            Toast.makeText(getActivity(), "You Clicked: " + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
                //id=2
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Cua chiên nước mắm")
                        .setMessage("Thành phần : Cua chiên ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.chickengrill)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                boolean a=false;

                                for(int i=0;i<giohangdoan.size();i++){
                                    if(giohangdoan.get(i).getId()==2){
                                        a=true;
                                    }
                                }
                                if(a==true){
                                    Toast.makeText(getActivity(), "mon an da duoc dat", Toast.LENGTH_SHORT).show();
                                }else {
                                    GioHang giohang = new GioHang(2, "Thăn cá cuốn gừng", 380000, url, 1);
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
                //id=3
                new TTFancyGifDialog.Builder(getActivity())
                        .setTitle("Thăn cá cuốn gừng")
                        .setMessage("Thành phần: Thăn cá cuốn gừng  ")
                        .setPositiveBtnText("Đặt")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Trở về")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.beef)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                                String url=(new StringBuilder()).append("https://smartrestaurantntd.herokuapp.com").append(rootObject.getData().get(position).getImage()).toString();
                                boolean a=false;

                                for(int i=0;i<giohangdoan.size();i++){
                                    if(giohangdoan.get(i).getId()==3){
                                        a=true;
                                    }
                                }
                                if(a==true){
                                    Toast.makeText(getActivity(), "mon an da duoc dat", Toast.LENGTH_SHORT).show();
                                }else {
                                    GioHang giohang = new GioHang(3, "Thăn cá cuốn gừng", 196000, url, 1);
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
    public MainCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_main_course, container,false);
       rvMainCourse=v.findViewById(R.id.rvMainCourse); rvMainCourse.setHasFixedSize(true);
       rvPopularMainCourse=v.findViewById(R.id.rvPopularMainCourse);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false);
        rvPopularMainCourse.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvMainCourse.setLayoutManager(layoutManager2);
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
                rvMainCourse.setAdapter(adapter);
                rvPopularMainCourse.setAdapter(popularFood);
                adapter.setOnItemClickListener(onItemClickListener);
                popularFood.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        };


        loadRSSAsync.execute(Link_JSON);
    }
}
