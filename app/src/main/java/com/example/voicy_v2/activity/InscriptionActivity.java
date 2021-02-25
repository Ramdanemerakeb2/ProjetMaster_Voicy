package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.voicy_v2.R;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        TextView sin = (TextView) findViewById(R.id.sin);

        //switch vers la connexion
        sin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                startActivity(it);
            }
        });
    }
}