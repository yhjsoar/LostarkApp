package com.yhjsoaryhjsoarLostarkApp;

import android.Manifest;
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

public class MainActivity extends AppCompatActivity {

    static final String[] LIST_MENU = {"수집품"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU) ;

        ListView listview = (ListView) findViewById(R.id.listView) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(MainActivity.this, CollectList.class);
                startActivity(intent);
            }
        });
    }
}
