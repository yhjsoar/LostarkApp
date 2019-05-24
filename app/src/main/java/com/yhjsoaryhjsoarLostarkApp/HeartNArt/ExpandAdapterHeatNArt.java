package com.yhjsoaryhjsoarLostarkApp.HeartNArt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhjsoaryhjsoarLostarkApp.Island.IslandGroup;
import com.yhjsoaryhjsoarLostarkApp.R;

import java.security.acl.Group;
import java.util.ArrayList;

public class ExpandAdapterHeatNArt extends BaseExpandableListAdapter {
    private ArrayList<HeartNArtGroup> DataList;
    private LayoutInflater inflater = null;
    private Context context;
    private int groupLayout = 0;
    private int childLayout = 0;

    private int[][] isChecked;
    int[] total = new int[2];

    HeartNArtList heartNArtList;

    public ExpandAdapterHeatNArt(Context context, int groupLay, int childLay, ArrayList<HeartNArtGroup> DataList, HeartNArtList heartNArtList, int[][] isChecked){
        this.context = context;
        this.groupLayout = groupLay;
        this.childLayout = childLay;
        this.DataList = DataList;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.heartNArtList = heartNArtList;
        this.isChecked = isChecked;
        total[0] = 0; total[1] = 0;
        for(int i=0;i<heartNArtList.heartGroupList.length;i++){
            total[0]+=isChecked[0][i];
        }
        for(int i=0;i<heartNArtList.artList.length;i++){
            total[1]+=isChecked[1][i];
        }
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = inflater.inflate(this.groupLayout, parent, false);
        }
        TextView textView1 = (TextView)convertView.findViewById(R.id.kind);
        TextView textView2 = (TextView)convertView.findViewById(R.id.total3);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView3);

        DataList.get(groupPosition).setGet(total[groupPosition]);

        if(isExpanded){
            DataList.get(groupPosition).setResId(R.drawable.arrow_up);
        } else{
            DataList.get(groupPosition).setResId(R.drawable.arrow_down);
        }
        textView1.setText(DataList.get(groupPosition).getKind());
        textView2.setText(DataList.get(groupPosition).getContent());
        imageView.setImageResource(DataList.get(groupPosition).getResId());

        if(DataList.get(groupPosition).getGet()==DataList.get(groupPosition).getTotal()){
            textView1.setPaintFlags(textView1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            textView2.setPaintFlags(textView2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            textView1.setTextColor(Color.GRAY);
            textView2.setTextColor(Color.GRAY);
        } else{
            textView1.setPaintFlags(0);
            textView2.setPaintFlags(0);
            textView1.setTextColor(Color.BLACK);
            textView2.setTextColor(Color.BLACK);
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(this.childLayout, parent, false);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.number);
        TextView textView2 = (TextView)convertView.findViewById(R.id.group);

        final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox2);

        textView.setText(DataList.get(groupPosition).getChild(childPosition).getNumber());
        textView2.setText(DataList.get(groupPosition).getChild(childPosition).getGroup());

        if(isChecked[groupPosition][childPosition]==1){
            checkBox.setChecked(true);
            textView.setPaintFlags(textView.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setTextColor(Color.GRAY);
            textView2.setPaintFlags(textView.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
            textView2.setTextColor(Color.GRAY);
        } else{
            checkBox.setChecked(false);
            textView.setPaintFlags(0);
            textView.setTextColor(Color.BLACK);
            textView2.setPaintFlags(0);
            textView2.setTextColor(Color.BLACK);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChecked[groupPosition][childPosition]==1){
                    isChecked[groupPosition][childPosition]=0;
                } else{
                    isChecked[groupPosition][childPosition]=1;
                }
                total[groupPosition] = 0;
                for(int i=0;i<isChecked[groupPosition].length;i++){
                    total[groupPosition]+=isChecked[groupPosition][i];
                }
                heartNArtList.adapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).getObjectChild().size();
    }

    @Override
    public HeartNArtGroup getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return DataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }
}
