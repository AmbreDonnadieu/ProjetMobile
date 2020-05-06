package com.example.gathering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCard extends AppCompatActivity {

    ImageView mAddButton;
    ExampleItem mEvent;
    DatabaseHandler dbHandler;

    EditText titre, date, heure, lieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        dbHandler= new DatabaseHandler(this);

        mAddButton = (ImageView)findViewById(R.id.add_button);
        mEvent= new ExampleItem();


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titre = (EditText)findViewById(R.id.titre);
                mEvent.setTitre(titre.getText().toString());

                date = (EditText)findViewById(R.id.date);
                mEvent.setDateEvent(date.getText().toString());

                heure = (EditText)findViewById(R.id.heure);
                mEvent.setHeureEvent(heure.getText().toString());

                lieu = (EditText)findViewById(R.id.lieu);
                mEvent.setLieuEvent(lieu.getText().toString());

                dbHandler.addEvent(mEvent);
                finish();
                startActivity(new Intent(AddCard.this, MainActivity.class));
            }
        });
    }
}
