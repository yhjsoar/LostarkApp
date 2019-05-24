package com.yhjsoaryhjsoarLostarkApp.HeartNArt;

public class HeartNArtChild {
        private String number;
        private String group;
        private int isSelected;

        HeartNArtChild(String number, String group, int isSelected){
            this.number = number;
            this.group = group;
            this.isSelected = isSelected;
        }

        public void setIsSelected(int isSelected){
            this.isSelected = isSelected;
        }

        public int getIsSelected(){
            return isSelected;
        }

        public String getNumber(){return number;}

        public String getGroup(){ return group;}
    }
