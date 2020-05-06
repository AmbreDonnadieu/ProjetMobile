package com.example.gathering;

public class ExampleItem {
    private int mImageResource;
    private int mid;
    private String mTitreEvent;
    private String mDateEvent;
    private String mHeureEvent;
    private String mLieuEvent;

    public ExampleItem(int imageResource, String titreEvent, String dateEvent, String heureEvent, String lieuEvent){
        mImageResource=imageResource;
        mTitreEvent = titreEvent;
        mDateEvent=dateEvent;
        mHeureEvent=heureEvent;
        mLieuEvent=lieuEvent;
    }
    public ExampleItem(int id,int imageResource, String titreEvent, String dateEvent, String heureEvent, String lieuEvent){
        mid=id;
        mImageResource=imageResource;
        mTitreEvent = titreEvent;
        mDateEvent=dateEvent;
        mHeureEvent=heureEvent;
        mLieuEvent=lieuEvent;
    }
    public ExampleItem(){
        mid=0;
        mImageResource=0;
        mTitreEvent = "theTitle";
        mDateEvent="01/01/0001";
        mHeureEvent="00:00";
        mLieuEvent="ThePlace";
    }

    public void changeEvent(String text){
        mTitreEvent = text;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getDateEvent(){
        return mDateEvent;
    }
    public String getHeureEvent(){
        return mHeureEvent;
    }
    public String getLieuEvent(){
        return mLieuEvent;
    }

    public String getTitre() {
        return mTitreEvent;
    }

    public void setDateEvent(String d){
        mDateEvent = d;
    }
    public void setHeureEvent(String h){
         mHeureEvent= h;
    }
    public void setLieuEvent(String l){
         mLieuEvent=l;
    }

    public void setTitre(String t) {
         mTitreEvent= t;
    }

    public int getId(){
        return mid;
    }

    public void setID(int i){
        mid=i;
    }
}
