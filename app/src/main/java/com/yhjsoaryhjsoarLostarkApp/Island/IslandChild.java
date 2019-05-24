package com.yhjsoaryhjsoarLostarkApp.Island;

public class IslandChild {
    private String island;
    private int isSelected;

    IslandChild(String island, int isSelected){
        this.island = island;
        this.isSelected = isSelected;
    }

    public void setIsSelected(int isSelected){
        this.isSelected = isSelected;
    }

    public int getIsSelected(){
        return isSelected;
    }

    public String getIsland(){
        return island;
    }
}
