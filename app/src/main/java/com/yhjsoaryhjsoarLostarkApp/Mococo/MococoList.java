package com.yhjsoaryhjsoarLostarkApp.Mococo;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.yhjsoaryhjsoarLostarkApp.R;

import java.util.ArrayList;

public class MococoList extends AppCompatActivity {
    int[] numList = new int[11];

    String[] location;
    String[][] location_list = new String[11][60];

    int[][] mococo_total = new int[11][60];
    int[] total;

    public int[][] mococo_get = new int[11][60];
    public int[] total_get = new int[11];

    private ExpandableListView listView;
    ExpandAdapterMococo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mococo_list);
//
//        init();
        getData();

        final ArrayList<mococoGroup> DataList = new ArrayList<mococoGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);
        for(int i=0;i<11;i++){
            mococoGroup mococogroup = new mococoGroup(location[i], R.drawable.arrow_down, total_get[i], total[i]);
            for(int j=0;j<numList[i];j++){
                mococogroup.addChild(new mococoChild(location_list[i][j], mococo_get[i][j], mococo_total[i][j]));
            }
            DataList.add(mococogroup);
        }

        adapter = new ExpandAdapterMococo(getApplicationContext(), R.layout.mococo_parent, R.layout.mococo_child, DataList, this);
        listView.setAdapter(adapter);
    }

    private void getData(){
        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        for(int i=0;i<11;i++){
            for(int j=0;j<60;j++){
                mococo_get[i][j] = prefs.getInt("Mococo_"+i+"_"+j, 0);
            }
            total_get[i] = prefs.getInt("get_"+i, 0);
        }
        Resources res = getResources();
        location = res.getStringArray(R.array.location);
        int[] artemis = res.getIntArray(R.array.artemis_mococo);
        String[] artemisLoc = res.getStringArray(R.array.artemis);
        int[] yudia = res.getIntArray(R.array.yudia_mococo);
        String[]yudiaLoc = res.getStringArray(R.array.yudia);
        int[] luteranWest = res.getIntArray(R.array.luteranWest_mococo);
        String[] luteranWestLoc = res.getStringArray(R.array.luteranWest);
        int[] luteranEast = res.getIntArray(R.array.luteranEast_mococo);
        String[] luteranEastLoc = res.getStringArray(R.array.luteranEast);
        int[] enich = res.getIntArray(R.array.enich_mococo);
        String[] enichLoc = res.getStringArray(R.array.enich);
        int[] ardetain = res.getIntArray(R.array.ardetain_mococo);
        String[] ardetainLoc = res.getStringArray(R.array.ardetain);
        int[] baereun = res.getIntArray(R.array.baereun_moccoo);
        String[] baereunLoc = res.getStringArray(R.array.baereun);
        int[] shushire = res.getIntArray(R.array.shushire_mococo);
        String[] shushireLoc = res.getStringArray(R.array.shushire);
        int[] rohendel = res.getIntArray(R.array.rohendel_mococo);
        String[] rohendelLoc = res.getStringArray(R.array.rohendel);
        int[] giena = res.getIntArray(R.array.giena_mococo);
        String[] gienaLoc = res.getStringArray(R.array.giena_island);
        int[] procyon = res.getIntArray(R.array.procyon_mococo);
        String[] procyonLoc = res.getStringArray(R.array.procyon_island);

        numList[0] = artemis.length;
        for(int i=0;i<numList[0];i++){
            location_list[0][i] = artemisLoc[i];
            mococo_total[0][i] = artemis[i];
        }
        numList[1] = yudia.length;
        for(int i=0;i<numList[1];i++){
            location_list[1][i] = yudiaLoc[i];
            mococo_total[1][i] = yudia[i];
        }
        numList[2] = luteranWest.length;
        for(int i=0;i<numList[2];i++){
            location_list[2][i] = luteranWestLoc[i];
            mococo_total[2][i] = luteranWest[i];
        }
        numList[3] = luteranEast.length;
        for(int i=0;i<numList[3];i++){
            location_list[3][i] = luteranEastLoc[i];
            mococo_total[3][i] = luteranEast[i];
        }
        numList[4] = enich.length;
        for(int i=0;i<numList[4];i++){
            location_list[4][i] = enichLoc[i];
            mococo_total[4][i] = enich[i];
        }
        numList[5] = ardetain.length;
        for(int i=0;i<numList[5];i++){
            location_list[5][i] = ardetainLoc[i];
            mococo_total[5][i] = ardetain[i];
        }
        numList[6] = baereun.length;
        for(int i=0;i<numList[6];i++){
            location_list[6][i] = baereunLoc[i];
            mococo_total[6][i] = baereun[i];
        }
        numList[7] = shushire.length;
        for(int i=0;i<numList[7];i++){
            location_list[7][i] = shushireLoc[i];
            mococo_total[7][i] = shushire[i];
        }
        numList[8] = rohendel.length;
        for(int i=0;i<numList[8];i++){
            location_list[8][i] = rohendelLoc[i];
            mococo_total[8][i] = rohendel[i];
        }
        numList[9] = giena.length;
        for(int i=0;i<numList[9];i++){
            location_list[9][i] = gienaLoc[i];
            mococo_total[9][i] = giena[i];
        }
        numList[10] = procyon.length;
        for(int i=0;i<numList[10];i++){
            location_list[10][i] = procyonLoc[i];
            mococo_total[10][i] = procyon[i];
        }

        total = new int[11];
//        for(int i=0;i<total.length;i++)
        for(int i=0;i<total.length;i++){
            total[i] = 0;
            for(int j=0;j<numList[i];j++){
                total[i]+=mococo_total[i][j];
            }
        }

        for(int i=0;i<11;i++){
            total_get[i] = 0;
            for(int j=0;j<numList[i];j++){
                total_get[i]+=mococo_get[i][j];
            }
        }
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for(int i=0;i<11;i++){
            for(int j=0;j<60;j++){
                editor.putInt("Mococo_"+i+"_"+j, mococo_get[i][j]);
            }
        }

        editor.commit();
    }
}
