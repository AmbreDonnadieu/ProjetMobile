package com.example.gathering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ModifCardContent extends AppCompatActivity {

    DatabaseHandler dbHandler;

    ExampleItem mEvent;

    EditText titre, date, heure, lieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_card_content);

        Intent i = getIntent();
        dbHandler = new DatabaseHandler(this);

        ImageView image = findViewById(R.id.modify_button);
        titre = findViewById(R.id.titre);
        date = findViewById(R.id.date);
        heure = findViewById(R.id.heure);
        lieu = findViewById(R.id.lieu);

        final int index = i.getIntExtra("index",0);
        titre.setText(i.getStringExtra("titre"));
        date.setText(i.getStringExtra("date"));
        heure.setText(i.getStringExtra("heure"));
        lieu.setText(i.getStringExtra("lieu"));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEvent = new ExampleItem();
                mEvent.setTitre(titre.getText().toString());
                mEvent.setDateEvent(date.getText().toString());
                mEvent.setHeureEvent(heure.getText().toString());
                mEvent.setLieuEvent(lieu.getText().toString());
                mEvent.setID(index);

                dbHandler.updateEvent(mEvent);
                finish();
                startActivity(new Intent(ModifCardContent.this, MainActivity.class));

            }
        });


    }
}
