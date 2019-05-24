package com.yhjsoaryhjsoarLostarkApp.HeartNArt;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.yhjsoaryhjsoarLostarkApp.R;

import java.util.ArrayList;

public class HeartNArtList extends AppCompatActivity {
    private ExpandableListView listView;
    ExpandAdapterHeatNArt adapter;

    int[][] checked;

    String[] heartList;
    String[] heartGroupList;

    String[] artList;
    String[] artGroupList;

    int[] getTotal = new int[2];
    int[] total = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_nart_list);

        getData();
        final ArrayList<HeartNArtGroup> DataList = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.heart_art_list);
        HeartNArtGroup heartGroup = new HeartNArtGroup("거인의 심장", R.drawable.arrow_down, getTotal[0], total[0]);
        for(int i=0;i<heartList.length;i++){
            heartGroup.addChild(new HeartNArtChild(heartList[i], heartGroupList[i], checked[0][i]));
        }
        HeartNArtGroup artGroup = new HeartNArtGroup("위대한 미술품", R.drawable.arrow_down, getTotal[1], total[1]);
        for(int i=0;i<artList.length;i++){
            artGroup.addChild(new HeartNArtChild(artList[i], artGroupList[i], checked[1][i]));
        }
        DataList.add(heartGroup);
        DataList.add(artGroup);
        adapter = new ExpandAdapterHeatNArt(getApplicationContext(), R.layout.heart_n_art_parent, R.layout.heart_n_art_child, DataList, this, checked);
        listView.setAdapter(adapter);
    }

    private void getData(){
        Resources res = getResources();
        heartGroupList = res.getStringArray(R.array.heartGroup);
        artGroupList = res.getStringArray(R.array.artGroup);

        heartList = new String[heartGroupList.length];
        for(int i=0;i<heartGroupList.length;i++){
            heartList[i] = Integer.toString(i)+"번";
        }
        artList = new String[artGroupList.length];
        for(int i=0;i<artGroupList.length;i++){
            artList[i] = Integer.toString(i)+"번";
        }

        checked = new int[2][artList.length];

        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        for(int i=0;i<2;i++){
            for(int j=0;j<checked[i].length;j++){
                checked[i][j] = prefs.getInt("heart_"+i+"_"+j, 0);
            }
        }

        total[0] = 0; getTotal[0] = 0;
        for(int i=0;i<heartList.length;i++){
            getTotal[0]+=checked[0][i];
            total[0]++;
        }
        for(int i=0;i<artList.length;i++){
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
                editor.remove("heart_"+i+"_"+j);
                editor.putInt("heart_"+i+"_"+j, checked[i][j]);
            }
        }

        editor.commit();
    }
}
