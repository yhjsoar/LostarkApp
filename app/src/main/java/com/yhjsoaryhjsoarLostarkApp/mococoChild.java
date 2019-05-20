package com.yhjsoaryhjsoarLostarkApp;

public class mococoChild {
    private String location;
    private int get;
    private int total;

    mococoChild(){
        location="";
        get=0;
        total=0;
    }

    mococoChild(String loc, int get, int total){
        this.location = loc;
        this.get = get;
        this.total = total;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public void setGet(int get){
        this.get = get;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public String getLocation(){
        return location;
    }
    public int getGet(){
        return get;
    }
    public int getTotal(){
        return total;
    }
    public String getPtotal(){
        return ("("+Integer.toString(get)+"/"+Integer.toString(total)+")");
    }
}
