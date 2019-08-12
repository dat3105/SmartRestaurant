package com.example.articleapps.FoodTab;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.articleapps.R;
import com.example.articleapps.ShoppingCart.ShoppingCartActivity;
import com.example.articleapps.TableMenu.Fragment.DeluxeTableFragment;

import java.util.ArrayList;


public class MainFood extends AppCompatActivity {


    LinearLayout footer;
    TabLayout tabMenu;
    ViewPager viewPager;
    ImageView img_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
        tabMenu     = findViewById(R.id.tabMenu);
        viewPager   = findViewById(R.id.viewPager);
        footer = findViewById(R.id.include_cart);
        img_cart= footer.findViewById(R.id.img_cart);
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainFood.this, ShoppingCartActivity.class);
                startActivity(i);
            }
        });
        setupViewPager(viewPager);
        tabMenu.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);  // 0 = drink , 1=food

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DrinkFragment(),"Đồ Uống");
        adapter.addFragment(new AppetizerFragment(),"Khai Vị");
        adapter.addFragment(new MainCourseFragment(),"Món Chính");
        adapter.addFragment(new DessertFragment(),"Tráng Miệng");

        viewPager.setAdapter(adapter);
    }


}
