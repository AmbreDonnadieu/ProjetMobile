package com.example.gathering.ui.home;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gathering.ExampleAdapter;
import com.example.gathering.ExampleItem;
import com.example.gathering.R;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    //private MutableLiveData<String> mText;

    public RecyclerView mRecyclerView;
    public ExampleAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ExampleItem> mexampleList;

    public HomeViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
        mexampleList = new ArrayList<>();

        //mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mexampleList);
    }

    public void changeItem(int position, String text){
        mexampleList.get(position).changeEvent(text);
        mAdapter.notifyItemChanged(position);
    }

    public void removeItem(int position){
        mexampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public ArrayList<ExampleItem> getExampleList(){
        return mexampleList;
    }
    public void setExampleList(ArrayList<ExampleItem> thelist){
         mexampleList = thelist;
    }

    public ExampleItem getItem(int position){
        return mexampleList.get(position);
    }
}