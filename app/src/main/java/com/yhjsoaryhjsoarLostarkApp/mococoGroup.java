package com.yhjsoaryhjsoarLostarkApp;

import android.widget.TextView;

import java.util.ArrayList;

public class mococoGroup {
    private ArrayList<mococoChild> child;
    private String title;
    private String content;
    private int resId;
    private TextView textView;
    private int get;
    private int total;


    mococoGroup(String title, int resId, int get, int total){
        this.title = title;
        this.resId = resId;
        child = new ArrayList<mococoChild>();
        this.get = get;
        this.total = total;
        getPtotal();
    }

    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setResId(int resId){
        this.resId = resId;
    }
    public void setTextView(TextView textView){ this.textView = textView; }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public int getResId(){
        return resId;
    }
    public TextView getTextView(){ return textView; }
    public void addChild(mococoChild child){
        this.child.add(child);
    }
    public mococoChild getChild(int position){
        return child.get(position);
    }
    public ArrayList<mococoChild> getObjectChild(){
        return child;
    }
    public void setChild(int position, int get){
        mococoChild child2 = child.get(position);
        child2.setGet(get);
        child.set(position, child2);
    }
    public int getGet(){ return this.get;}
    public int getTotal(){ return this.total;}
    public void setGet(int get){
        this.get = get;
        getPtotal();
    }
    public void setTotal(int total) {
        this.total = total;
        getPtotal();
    }
    public void getPtotal(){
        this.content = ("("+Integer.toString(get)+"/"+Integer.toString(total)+")");
    }
}
