package com.example.articleapps.TableMenu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.articleapps.FoodTab.AppetizerFragment;
import com.example.articleapps.FoodTab.DessertFragment;
import com.example.articleapps.FoodTab.DrinkFragment;
import com.example.articleapps.FoodTab.MainCourseFragment;
import com.example.articleapps.FoodTab.ViewPagerAdapter;
import com.example.articleapps.R;
import com.example.articleapps.TableMenu.Adapter.AdapterPopularDeluxeTable;
import com.example.articleapps.TableMenu.Fragment.DeluxeTableFragment;
import com.example.articleapps.TableMenu.Fragment.PremiumTableFragment;
import com.example.articleapps.TableMenu.Model.DataPopularDeluxeTable;

import java.util.ArrayList;

public class TableMenu extends AppCompatActivity {

    TabLayout tabMenu;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_table);
        tabMenu     = findViewById(R.id.tabMenu2);
        viewPager   = findViewById(R.id.viewPager2);

        setupViewPager(viewPager);

        tabMenu.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);  // 0 = drink , 1=food

        }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DeluxeTableFragment(),"Bàn thường");
        adapter.addFragment(new PremiumTableFragment(),"Bàn sang");

        viewPager.setAdapter(adapter);
    }
}
