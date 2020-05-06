package com.example.gathering.ui.home;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gathering.CardActivity;
import com.example.gathering.DatabaseHandler;
import com.example.gathering.ExampleAdapter;
import com.example.gathering.ExampleItem;
import com.example.gathering.R;
import com.example.gathering.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseHandler dbHandler;
    private TextView text;
    private FirebaseUser user;
    private String uid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        dbHandler = new DatabaseHandler(getActivity());
        if(dbHandler.getEventSize()==0){
            fillInList();
        }
        homeViewModel.setExampleList(dbHandler.getEventList());
        //ArrayList<ExampleItem> e = homeViewModel.getExampleList();
        buildRecyclerView(root);
        return root;
    }

    public void buildRecyclerView(final View root){
        homeViewModel.mRecyclerView = root.findViewById(R.id.recyclerview);
        homeViewModel.mLayoutManager = new LinearLayoutManager(getActivity());
        homeViewModel.mRecyclerView.setHasFixedSize(false);
        homeViewModel.mRecyclerView.setLayoutManager(homeViewModel.mLayoutManager);
        homeViewModel.mAdapter.mExampleList = homeViewModel.getExampleList();
        homeViewModel.mRecyclerView.setAdapter(homeViewModel.mAdapter);


        homeViewModel.mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //homeViewModel.changeItem(position,"Clicked");
                Intent i = new Intent(getActivity(), CardActivity.class);
                i.putExtra("Titre", homeViewModel.getItem(position).getTitre());
                i.putExtra("Date", homeViewModel.getItem(position).getDateEvent());
                i.putExtra("Heure", homeViewModel.getItem(position).getHeureEvent());
                i.putExtra("Lieu", homeViewModel.getItem(position).getLieuEvent());
                i.putExtra("index", homeViewModel.getItem(position).getId());
                startActivity(i);
            }

            @Override
            public void OnDeleteClick(int position) {
                dbHandler.removeEvent(homeViewModel.getItem(position).getId());
                homeViewModel.mRecyclerView.removeViewAt(position);
                homeViewModel.mAdapter.notifyItemRemoved(position);
                homeViewModel.setExampleList(dbHandler.getEventList());
                //homeViewModel.removeItem(position);
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        uid = user.getUid();
        //return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void fillInList(){
        ArrayList<ExampleItem> mexampleList = new ArrayList<>();
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

        //homeViewModel.setExampleList(mexampleList);

        for(int i=0; i<mexampleList.size();i++){
            dbHandler.addEvent(mexampleList.get(i));
        }
    }
}
