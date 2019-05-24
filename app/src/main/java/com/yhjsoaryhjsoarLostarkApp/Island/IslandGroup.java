package com.yhjsoaryhjsoarLostarkApp.Island;

import android.widget.TextView;

import com.yhjsoaryhjsoarLostarkApp.Island.IslandChild;

import java.util.ArrayList;

public class IslandGroup {
    private ArrayList<IslandChild> child;
    private String title;
    private String content;
    private int resId;
    private int get;
    private int total;

    IslandGroup(String title, int resId, int get, int total){
        this.title = title;
        this.resId = resId;
        child = new ArrayList<IslandChild>();
        this.get = get;
        this.total = total;
        getPtotal();
    }

    public void setContent(String content){
        this.content = content;
    }
    public void setResId(int resId){
        this.resId = resId;
    }

    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public int getResId(){
        return resId;
    }

    public void addChild(IslandChild child){
        this.child.add(child);
    }
    public IslandChild getChild(int position){
        return child.get(position);
    }
    public ArrayList<IslandChild> getObjectChild(){
        return child;
    }
    public int getGet(){ return this.get;}
    public int getTotal(){ return this.total;}
    public void setGet(int get) {
        this.get = get;
        getPtotal();
    }
    public void getPtotal(){
        this.content = ("("+Integer.toString(get)+"/"+Integer.toString(total)+")");
    }
}
