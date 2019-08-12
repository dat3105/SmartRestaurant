package com.example.articleapps.TableMenu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.articleapps.AdapterFoodDrink.AdapterDrink;
import com.example.articleapps.AdapterFoodDrink.AdapterPopularDrink;

import com.example.articleapps.R;
import com.example.articleapps.TableMenu.Adapter.AdapterDeluxeTable;
import com.example.articleapps.TableMenu.Adapter.AdapterPopularDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataPopularDeluxeTable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeluxeTableFragment extends Fragment {
RecyclerView rvDeluxeTable;
RecyclerView rvPopularDeluxeTable;
AdapterDeluxeTable adapterDeluxeTable;
AdapterPopularDeluxeTable adapterPopularDeluxeTable;
String tabletitle[] = {"Bàn số 1","Bàn số 2","Bàn số 3","Bàn số 4","Bàn số 5"};
String price[]={"5000","6000","7000","8000","9000"};


int img[]={R.drawable.table3,R.drawable.table3,R.drawable.table4,R.drawable.table3,R.drawable.table2};

    public DeluxeTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deluxe_table, container, false);
        rvDeluxeTable = v.findViewById(R.id.rvDeluxeTable);
        rvPopularDeluxeTable = v.findViewById(R.id.rvPopularDeluxeTable);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvPopularDeluxeTable.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvDeluxeTable.setLayoutManager(layoutManager2);
        rvDeluxeTable.setHasFixedSize(true);

        ArrayList<DataDeluxeTable> dataDeluxeTables = getDataDeluxe();
        ArrayList<DataPopularDeluxeTable> dataPopularDeluxeTables = getDataPopular();
        adapterPopularDeluxeTable = new AdapterPopularDeluxeTable(dataPopularDeluxeTables,getActivity());
        adapterDeluxeTable = new AdapterDeluxeTable(dataDeluxeTables,getActivity());
        rvDeluxeTable.setAdapter(adapterDeluxeTable);
        rvPopularDeluxeTable.setAdapter(adapterPopularDeluxeTable);

        return v;
    }
    private ArrayList<DataDeluxeTable> getDataDeluxe() {
        ArrayList<DataDeluxeTable> dataTable = new ArrayList<>();
        for (int i = 0; i<tabletitle.length;i++){
            DataDeluxeTable table = new DataDeluxeTable();
            table.setTitle(tabletitle[i]);
            table.setPrice(price[i]);
            table.setImg(img[i]);
            dataTable.add(table);
        }
        return dataTable;
    }
    private ArrayList<DataPopularDeluxeTable> getDataPopular() {
        ArrayList<DataPopularDeluxeTable> dataTable = new ArrayList<>();
        for (int i = 0; i<tabletitle.length;i++){
            DataPopularDeluxeTable table = new DataPopularDeluxeTable();
            table.setTitle(tabletitle[i]);
            table.setPrice(price[i]);
            table.setImg(img[i]);
            dataTable.add(table);
        }
        return dataTable;
    }
}
