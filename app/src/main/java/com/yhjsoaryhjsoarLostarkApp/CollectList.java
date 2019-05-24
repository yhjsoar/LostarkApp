package com.yhjsoaryhjsoarLostarkApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yhjsoaryhjsoarLostarkApp.HeartNArt.HeartNArtList;
import com.yhjsoaryhjsoarLostarkApp.Island.IslandList;
import com.yhjsoaryhjsoarLostarkApp.Mococo.MococoList;

public class CollectList extends AppCompatActivity {

    static final String[] LIST_MENU = {"섬의 마음 수집", "모코코 수집", "거인의 심장 및 위대한 미술품"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU) ;

        ListView listview = (ListView) findViewById(R.id.collectListView);
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if(position == 0){
                    intent = new Intent(CollectList.this, IslandList.class);
                } else if(position == 1){
                    intent = new Intent(CollectList.this, MococoList.class);
                } else{
                    intent = new Intent(CollectList.this, HeartNArtList.class);
                }
                startActivity(intent);
            }
        });
    }
}
