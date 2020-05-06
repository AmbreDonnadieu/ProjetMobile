package com.example.gathering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gathering.ui.home.HomeFragment;
import com.example.gathering.ui.home.HomeViewModel;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    DatabaseHandler dbHandler;

    private int index;

    private ArrayList<ExampleItem> mExampleList;

    private TextView mTitre, mDate, mHeure, mLieu;

    private ImageView mSuppression;
    private ImageView mModify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent i = getIntent();
        dbHandler = new DatabaseHandler(this);

        mTitre=(TextView) findViewById(R.id.titre);
        mDate=(TextView) findViewById(R.id.date);
        mHeure=(TextView) findViewById(R.id.heure);
        mLieu=(TextView) findViewById(R.id.lieu);
        mSuppression = (ImageView) findViewById(R.id.image_delete);
        mModify = (ImageView) findViewById(R.id.image_write);

        mTitre.setText(i.getStringExtra("Titre"));
        mDate.setText(i.getStringExtra("Date"));
        mHeure.setText(i.getStringExtra("Heure"));
        mLieu.setText(i.getStringExtra("Lieu"));
        index = i.getIntExtra("index",0);

        mSuppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.removeEvent(index);
                Intent i2 = new Intent(CardActivity.this, MainActivity.class);
                startActivity(i2);
                finish();

            }
        });

        mModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(CardActivity.this, ModifCardContent.class);
                i3.putExtra("titre", mTitre.getText());
                i3.putExtra("date", mDate.getText());
                i3.putExtra("heure", mHeure.getText());
                i3.putExtra("lieu", mLieu.getText());
                i3.putExtra("index", index);
                startActivity(i3);
            }
        });

    }

    public void OnDeleteClick(){

    }
}
