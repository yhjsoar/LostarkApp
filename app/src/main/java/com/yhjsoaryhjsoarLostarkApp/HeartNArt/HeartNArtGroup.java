package com.yhjsoaryhjsoarLostarkApp.HeartNArt;

import android.widget.TextView;

import com.yhjsoaryhjsoarLostarkApp.Island.IslandChild;

import java.util.ArrayList;

public class HeartNArtGroup {
    private ArrayList<HeartNArtChild> child;
    private String kind;
    private String content;
    private int resId;
    private int get;
    private int total;

    HeartNArtGroup(String kind, int resId, int get, int total) {
        this.kind = kind;
        this.resId = resId;
        this.get = get;
        this.total = total;
        child = new ArrayList<HeartNArtChild>();
        getPtotal();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getKind() {
        return kind;
    }

    public String getContent() {
        return content;
    }

    public int getResId() {
        return resId;
    }

    public void addChild(HeartNArtChild child) {
        this.child.add(child);
    }

    public HeartNArtChild getChild(int position) {
        return child.get(position);
    }

    public ArrayList<HeartNArtChild> getObjectChild() {
        return child;
    }

    public int getGet() {
        return this.get;
    }

    public int getTotal() {
        return this.total;
    }

    public void setChild(int position, int selected) {
        child.get(position).setIsSelected(selected);
    }

    public void setGet(int get) {
        this.get = get;
        getPtotal();
    }

    public void setTotal(int total) {
        this.total = total;
        getPtotal();
    }

    public void getPtotal() {
        this.content = ("(" + Integer.toString(get) + "/" + Integer.toString(total) + ")");
    }
}
