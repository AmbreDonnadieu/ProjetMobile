package com.example.gathering.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gathering.CardActivity;
import com.example.gathering.ExampleAdapter;
import com.example.gathering.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        buildRecyclerView(root);

        homeViewModel.getExampleList();
        return root;
    }

    public void buildRecyclerView(View root){
        homeViewModel.mRecyclerView = root.findViewById(R.id.recyclerview);
        homeViewModel.mLayoutManager = new LinearLayoutManager(getActivity());
        homeViewModel.mRecyclerView.setHasFixedSize(true);
        homeViewModel.mRecyclerView.setLayoutManager(homeViewModel.mLayoutManager);
        homeViewModel.mRecyclerView.setAdapter(homeViewModel.mAdapter);


        homeViewModel.mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //homeViewModel.changeItem(position,"Clicked");
                Intent i = new Intent(getActivity(), CardActivity.class);
                startActivity(i);
            }

            @Override
            public void OnDeleteClick(int position) {
                homeViewModel.removeItem(position);
            }
        });

    }
}
