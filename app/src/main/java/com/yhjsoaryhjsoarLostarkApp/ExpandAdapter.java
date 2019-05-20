package com.yhjsoaryhjsoarLostarkApp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.text.method.DateKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<mococoGroup> DataList;
    private LayoutInflater myinf = null;

    MococoList mococoList;

    //ExpandAdapter adapter;

    public ExpandAdapter(Context context, int groupLay, int chlidLay, ArrayList<mococoGroup> DataList, MococoList mococoList){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mococoList = mococoList;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView textView1 = (TextView)convertView.findViewById(R.id.location);
        TextView textView2 = (TextView)convertView.findViewById(R.id.total) ;
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);

        if(isExpanded){
            DataList.get(groupPosition).setResId(R.drawable.arrow_up);
        } else{
            DataList.get(groupPosition).setResId(R.drawable.arrow_down);
        }
        textView1.setText(DataList.get(groupPosition).getTitle());
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


        DataList.get(groupPosition).setTextView(textView2);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        TextView textView1 = (TextView)convertView.findViewById(R.id.locChild);
        final TextView textView2 = (TextView)convertView.findViewById(R.id.mococoGet);
        textView1.setText(DataList.get(groupPosition).getChild(childPosition).getLocation());
        textView2.setText(DataList.get(groupPosition).getChild(childPosition).getPtotal());
        if(DataList.get(groupPosition).getChild(childPosition).getTotal()==DataList.get(groupPosition).getChild(childPosition).getGet()){
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
        Button upButton = (Button)convertView.findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DataList.get(groupPosition).getChild(childPosition).getTotal()==DataList.get(groupPosition).getChild(childPosition).getGet())    return;
                    mococoList.mococo_get[groupPosition][childPosition]++;
                    DataList.get(groupPosition).setChild(childPosition, mococoList.mococo_get[groupPosition][childPosition]);
                    mococoList.total_get[groupPosition]++;
                    DataList.get(groupPosition).setGet(mococoList.total_get[groupPosition]);
                    mococoList.adapter.notifyDataSetChanged();
                }
        });

        Button downButton = (Button)convertView.findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataList.get(groupPosition).getChild(childPosition).getGet()==0) return;
                mococoList.mococo_get[groupPosition][childPosition]--;
                DataList.get(groupPosition).setChild(childPosition, mococoList.mococo_get[groupPosition][childPosition]);
                mococoList.total_get[groupPosition]--;
                DataList.get(groupPosition).setGet(mococoList.total_get[groupPosition]);
                mococoList.adapter.notifyDataSetChanged();
            }
        });

        Button maxButton = (Button)convertView.findViewById(R.id.maxButton);
        maxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int origin = DataList.get(groupPosition).getChild(childPosition).getGet();
                mococoList.mococo_get[groupPosition][childPosition]= DataList.get(groupPosition).getChild(childPosition).getTotal();
                DataList.get(groupPosition).setChild(childPosition, mococoList.mococo_get[groupPosition][childPosition]);
                int dif = DataList.get(groupPosition).getChild(childPosition).getGet() - origin;
                mococoList.total_get[groupPosition] += dif;
                DataList.get(groupPosition).setGet(mococoList.total_get[groupPosition]);
                mococoList.adapter.notifyDataSetChanged();
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
    public mococoGroup getGroup(int groupPosition) {
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
