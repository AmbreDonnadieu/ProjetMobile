package com.example.gathering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewholder> {

    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
        void OnDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewholder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTitre;
        public TextView mDate;
        public TextView mHeure;
        public TextView mLieu;

        public ImageView mDeleteImage;

        public ExampleViewholder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTitre = itemView.findViewById(R.id.titre);
            mDate = itemView.findViewById(R.id.date);
            mHeure = itemView.findViewById(R.id.heure);
            mLieu = itemView.findViewById(R.id.lieu);
            mDeleteImage = itemView.findViewById(R.id.image_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewholder evh = new ExampleViewholder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewholder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTitre.setText(currentItem.getTitre());
        holder.mDate.setText(currentItem.getDateEvent());
        holder.mHeure.setText(currentItem.getHeureEvent());
        holder.mLieu.setText(currentItem.getLieuEvent());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
