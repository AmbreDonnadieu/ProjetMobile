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
        mexampleList.add(new ExampleItem(R.drawable.ic_android,"Event 1", "01/01/2020","01:00","Chicoutimi"));
        mexampleList.add(new ExampleItem(R.drawable.ic_sun,"Event 2", "02/01/2020","02:00","Québec"));
        mexampleList.add(new ExampleItem(R.drawable.ic_audio,"Event 3", "03/01/2020","03:00","Montreal"));
        mexampleList.add(new ExampleItem(R.drawable.ic_android,"Event 4", "04/01/2020","04:00","Chicoutimi"));
        mexampleList.add(new ExampleItem(R.drawable.ic_sun,"Event 5", "05/01/2020","05:00","Québec"));
        mexampleList.add(new ExampleItem(R.drawable.ic_audio,"Event 6", "06/01/2020","06:00","Montreal"));
        mexampleList.add(new ExampleItem(R.drawable.ic_android,"Event 7", "01/01/2020","07:00","Chicoutimi"));
        mexampleList.add(new ExampleItem(R.drawable.ic_sun,"Event 8", "02/01/2020","08:00","Québec"));
        mexampleList.add(new ExampleItem(R.drawable.ic_audio,"Event 9", "03/01/2020","09:00","Montreal"));
        mexampleList.add(new ExampleItem(R.drawable.ic_android,"Event 10", "04/01/2020","10:00","Chicoutimi"));
        mexampleList.add(new ExampleItem(R.drawable.ic_sun,"Event 11", "05/01/2020","11:00","Québec"));
        mexampleList.add(new ExampleItem(R.drawable.ic_audio,"Event 12", "06/01/2020","12:00","Montreal"));

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
}