package com.example.gathering;

public class ExampleItem {
    private int mImageResource;
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
}
