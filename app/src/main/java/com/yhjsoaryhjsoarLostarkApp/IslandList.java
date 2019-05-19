package com.yhjsoaryhjsoarLostarkApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IslandList extends AppCompatActivity {
    String gienaContent;
    String procContent;

    TextView gienaText;
    TextView procText;

    ListView gienaListView;
    ListView procListView;

    ArrayList<String> gienaArrayList = new ArrayList<String>();
    ArrayList<String> procArrayList = new ArrayList<String>();

    private CustomAdapter adapterCustomGiena = null;
    private CustomAdapter adapterCustomProc = null;

    int gienaChecked = 0;
    int procChecked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island_list);

        ImageView imageView = (ImageView)findViewById(R.id.arrow_1);
        ImageView imageView2 = (ImageView)findViewById(R.id.arrow_2);
        gienaText = (TextView)findViewById(R.id.gienaNum);
        procText = (TextView)findViewById(R.id.procNum);
        gienaContent=contentString(gienaChecked, 0);
        procContent=contentString(procChecked, 1);
        final String[] islandListGiena = getResources().getStringArray(R.array.giena_list);
        final String[] islandListProcyon = getResources().getStringArray(R.array.proc_list);
        for(int i=0;i<islandListGiena.length;i++){
            gienaArrayList.add(islandListGiena[i]);
        }
        for(int i=0;i<islandListProcyon.length;i++){
            procArrayList.add(islandListProcyon[i]);
        }
        adapterCustomGiena = new CustomAdapter(IslandList.this, gienaArrayList);
        adapterCustomProc = new CustomAdapter(IslandList.this, procArrayList);

        //imageView setting
        imageView.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
        imageView2.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);

        //textView setting
        gienaText.setText(gienaContent);
        procText.setText(procContent);

        gienaListView = (ListView)findViewById(R.id.gienaList);
        gienaListView.setAdapter(adapterCustomGiena);
        gienaListView.setOnItemClickListener(gienaItemClickListener);

        procListView = (ListView)findViewById(R.id.procList);
        procListView.setAdapter(adapterCustomProc);
        procListView.setOnItemClickListener(procClickListener);

        ArrayList<Integer> savedGiena = new ArrayList<Integer>();
        ArrayList<Integer> savedProc = new ArrayList<Integer>();
        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        int sizeGiena = prefs.getInt("Status_Giena_size", 0);
        for(int i=0;i<sizeGiena;i++) {
            savedGiena.add(prefs.getInt("Status_Giena_" + i, 0));
        }
        int sizeProc = prefs.getInt("Status_Proc_size", 0);
        for(int i=0;i<sizeProc;i++){
            savedProc.add(prefs.getInt("Status_Proc_"+i, 0));
        }
        if(sizeGiena!=0) {
            adapterCustomGiena.recallChecked(savedGiena);
            adapterCustomGiena.notifyDataSetChanged();
            gienaChecked = adapterCustomGiena.getChecked().size();
            gienaContent = contentString(gienaChecked, 0);
            gienaText.setText(gienaContent);
        }
        if(sizeProc!=0) {
            adapterCustomProc.recallChecked(savedProc);
            adapterCustomProc.notifyDataSetChanged();
            procChecked = adapterCustomProc.getChecked().size();
            procContent = contentString(procChecked, 0);
            procText.setText(procContent);
        }


        setListViewHeightBasedOnChildren(gienaListView);
        setListViewHeightBasedOnChildren(procListView);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public String contentString(int get, int list){
        int all;
        if(list==0){
            all = getResources().getStringArray(R.array.giena_list).length;
        } else{
            all = getResources().getStringArray(R.array.proc_list).length;
        }
        return ("("+Integer.toString(get)+"/"+Integer.toString(all)+")");
    }

    class CustomAdapter extends BaseAdapter{
        private ViewHolder viewHolder = null;
        private LayoutInflater inflater = null;
        private ArrayList<String> sArrayList = new ArrayList<String>();
        private boolean[] isCheckedConfirm;

        public CustomAdapter(Context c, ArrayList<String> mList){
            inflater = LayoutInflater.from(c);
            this.sArrayList = mList;
            this.isCheckedConfirm = new boolean[sArrayList.size()];
        }

        public void setAllChecked(boolean ischecked){
            int tempSize = isCheckedConfirm.length;
            for(int i=0;i<tempSize;i++){
                isCheckedConfirm[i] = ischecked;
            }
        }

        public void setChecked(int position){
            isCheckedConfirm[position] = !isCheckedConfirm[position];
        }

        public void recallChecked(ArrayList<Integer> savedList){
            int tempSize = savedList.size();
            for(int i=0;i<tempSize;i++){
                isCheckedConfirm[savedList.get(i)] = true;
            }
        }

        public ArrayList<Integer> getChecked(){
            int tempSize = isCheckedConfirm.length;
            ArrayList<Integer> mArrayList = new ArrayList<Integer>();
            for(int i=0;i<tempSize;i++){
                if(isCheckedConfirm[i]){
                    mArrayList.add(i);
                }
            }
            return mArrayList;
        }
        @Override
        public int getCount() {
            return sArrayList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View v = convertView;
            if(v==null){
                viewHolder = new ViewHolder();
                v = inflater.inflate(R.layout.listview_item, null);
                viewHolder.cBox = (CheckBox)v.findViewById(R.id.checkbox);
                viewHolder.tView = (TextView)v.findViewById(R.id.textView1);
                v.setTag(viewHolder);
            } else{
                viewHolder = (ViewHolder)v.getTag();
            }

            viewHolder.cBox.setClickable(false);
            viewHolder.cBox.setFocusable(false);

            viewHolder.tView.setText(sArrayList.get(position));
            viewHolder.cBox.setChecked(isCheckedConfirm[position]);

            if(isCheckedConfirm[position]){
                viewHolder.tView.setPaintFlags(viewHolder.tView.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.tView.setTextColor(Color.GRAY);
            } else{
                viewHolder.tView.setPaintFlags(0);
                viewHolder.tView.setTextColor(Color.BLACK);
            }

            return v;
        }
    }

    class ViewHolder {
        // 새로운 Row에 들어갈 CheckBox
        private CheckBox cBox = null;
        private TextView tView = null;
    }

    private AdapterView.OnItemClickListener gienaItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            adapterCustomGiena.setChecked(position);
            adapterCustomGiena.notifyDataSetChanged();
            gienaChecked = adapterCustomGiena.getChecked().size();
            gienaContent = contentString(gienaChecked, 0);
            gienaText.setText(gienaContent);
        }
    };

    private AdapterView.OnItemClickListener procClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            adapterCustomProc.setChecked(position);
            adapterCustomProc.notifyDataSetChanged();
            procChecked = adapterCustomProc.getChecked().size();
            procContent = contentString(procChecked, 1);
            procText.setText(procContent);
        }
    };

    protected void onStop() {
        super.onStop();
        ArrayList<Integer> savingListGiena = adapterCustomGiena.getChecked();
        ArrayList<Integer> savingListProc = adapterCustomProc.getChecked();

        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Status_Giena_size", savingListGiena.size());
        for(int i=0;i<savingListGiena.size();i++){
            editor.remove("Status_Giena_"+i);
            editor.putInt("Status_Giena_"+i, savingListGiena.get(i));
        }
        editor.putInt("Status_Proc_size", savingListProc.size());
        for(int i=0;i<savingListProc.size();i++){
            editor.remove("Status_Proc_"+i);
            editor.putInt("Status_Proc_"+i, savingListProc.get(i));
        }

        editor.commit();
    }
}
