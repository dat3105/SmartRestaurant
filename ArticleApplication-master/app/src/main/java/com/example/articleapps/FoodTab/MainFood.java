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

import static com.example.articleapps.AdapterFoodDrink.AdapterDessert.giohangdoan;


public class MainFood extends AppCompatActivity {


    LinearLayout footer;
    TabLayout tabMenu;
    ViewPager viewPager;
    ImageView img_cart;
    public static TextView sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
        tabMenu     = findViewById(R.id.tabMenu);
        viewPager   = findViewById(R.id.viewPager);
        footer = findViewById(R.id.include_cart);
        img_cart= footer.findViewById(R.id.img_cart);
        sl=footer.findViewById(R.id.item_count);
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(giohangdoan.isEmpty()){
                    Toast.makeText(MainFood.this, "Bạn chưa chọn đồ ăn", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(MainFood.this, ShoppingCartActivity.class);
                    startActivity(i);
                }
            }
        });
        setupViewPager(viewPager);
        tabMenu.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);  // 0 = drink , 1=food
        sl.setText(giohangdoan.size()+"");
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
