package com.example.articleapps.TableMenu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.articleapps.R;
import com.example.articleapps.TableMenu.Adapter.AdapterPopularPremiumTable;
import com.example.articleapps.TableMenu.Adapter.AdapterPremiumTable;
import com.example.articleapps.TableMenu.Model.DataDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataPopularDeluxeTable;
import com.example.articleapps.TableMenu.Model.DataPopularPremiumTable;
import com.example.articleapps.TableMenu.Model.DataPremiumTable;
import com.example.articleapps.TableMenu.Model.ban;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PremiumTableFragment extends Fragment {
RecyclerView rvPopularPremiumTable;
RecyclerView rvPremiumTable;
AdapterPopularPremiumTable adapterPopularPremiumTable;
AdapterPremiumTable adapterPremiumTable;
   public static ArrayList<ban> dsban=new ArrayList<>();

    int img[]={R.drawable.table3,R.drawable.table3,R.drawable.table4,R.drawable.table3,R.drawable.table2};


    public PremiumTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_premium_table, container, false);
        rvPremiumTable = v.findViewById(R.id.rvPremiumTable);
        rvPopularPremiumTable = v.findViewById(R.id.rvPopularPremiumTable);
        ban ban=new ban(4,"Bàn số 3 luxury",1500000,R.drawable.table3);

        ban ban1=new ban(5,"Queen size table",2500000,R.drawable.table4);
        ban ban2=new ban(6,"King size table",3000000,R.drawable.table4);
        dsban.add(ban);
        dsban.add(ban1);
        dsban.add(ban2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvPopularPremiumTable.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        rvPremiumTable.setLayoutManager(layoutManager2);
        rvPopularPremiumTable.setHasFixedSize(true);
        rvPremiumTable.setHasFixedSize(true);
        ArrayList<DataPremiumTable> dataPremiumTables = getDataPremium();
        ArrayList<DataPopularPremiumTable> dataPopularPremiumTables = getDataPopularPremium();
        adapterPopularPremiumTable = new AdapterPopularPremiumTable(dataPopularPremiumTables,getActivity());
        adapterPremiumTable = new AdapterPremiumTable(dataPremiumTables,getActivity());
        rvPremiumTable.setAdapter(adapterPremiumTable);
        rvPopularPremiumTable.setAdapter(adapterPopularPremiumTable);
        return v;
    }
    private ArrayList<DataPremiumTable> getDataPremium() {
        ArrayList<DataPremiumTable> dataTable = new ArrayList<>();
        for (int i = 0; i<dsban.size();i++){
            DataPremiumTable table = new DataPremiumTable();
            table.setTitle(dsban.get(i).getName());
            table.setPrice(dsban.get(i).getPrice()+"");
            table.setImg(dsban.get(i).getImg());
            dataTable.add(table);
        }
        return dataTable;
    }
    private ArrayList<DataPopularPremiumTable> getDataPopularPremium() {
        ArrayList<DataPopularPremiumTable> dataTable = new ArrayList<>();
        for (int i = 0; i<dsban.size();i++){
            DataPopularPremiumTable table = new DataPopularPremiumTable();
            table.setTitle(dsban.get(i).getName());
            table.setPrice(dsban.get(i).getPrice()+"");
            table.setImg(dsban.get(i).getImg());
            dataTable.add(table);
        }
        return dataTable;
    }
}
