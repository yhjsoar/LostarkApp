package com.yhjsoaryhjsoarLostarkApp.Island;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.yhjsoaryhjsoarLostarkApp.Island.ExpandAdapterIsland;
import com.yhjsoaryhjsoarLostarkApp.Island.IslandChild;
import com.yhjsoaryhjsoarLostarkApp.Island.IslandGroup;
import com.yhjsoaryhjsoarLostarkApp.R;

import java.util.ArrayList;

public class IslandList extends AppCompatActivity {
    String[] gienaList;
    String[] procyonList;

    int[][] checked;

    int[] getTotal = new int[2];
    int[] total = new int[2];

    private ExpandableListView listView;
    ExpandAdapterIsland adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island_list);

        getData();
        final ArrayList<IslandGroup> DataList = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.islandList);
        IslandGroup gienaGroup = new IslandGroup("기에나의 바다", R.drawable.arrow_down, getTotal[0], total[0]);
        for(int i=0;i<gienaList.length;i++){
            gienaGroup.addChild(new IslandChild(gienaList[i], checked[0][i]));
        }
        IslandGroup procyonGroup = new IslandGroup("프로키온의 바다", R.drawable.arrow_down, getTotal[1], total[1]);
        for(int i=0;i<procyonList.length;i++){
            procyonGroup.addChild(new IslandChild(procyonList[i], checked[1][i]));
        }
        DataList.add(gienaGroup);
        DataList.add(procyonGroup);
        adapter = new ExpandAdapterIsland(getApplicationContext(), R.layout.island_parent, R.layout.island_child, DataList, this, checked);
        listView.setAdapter(adapter);
    }

    private void getData(){
        Resources res = getResources();
        gienaList = res.getStringArray(R.array.giena_island_list);
        procyonList = res.getStringArray(R.array.procyon_island_list);

        checked = new int[2][gienaList.length];
        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        for(int i=0;i<2;i++){
            for(int j=0;j<checked[i].length;j++){
                checked[i][j] = prefs.getInt("island_"+i+"_"+j, 0);
            }
        }
        total[0] = 0; getTotal[0] = 0;
        for(int i=0;i<gienaList.length;i++){
            getTotal[0]+=checked[0][i];
            total[0]++;
        }
        total[1] = 0; getTotal[1] = 0;
        for(int i=0;i<procyonList.length;i++){
            getTotal[1]+=checked[1][i];
            total[1]++;
        }
    }
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for(int i=0;i<2;i++){
            for(int j=0;j<checked[i].length;j++){
                editor.remove("island_"+i+"_"+j);
                editor.putInt("island_"+i+"_"+j, checked[i][j]);
            }
        }

        editor.commit();
    }
}
