package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ValidationActivity extends AppCompatActivity {

    TextView lblnombre,lblcedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        IniciarControles();

        Intent i = getIntent();
        if(i != null){
            PopularDatos(i);
        }

    }

    private void PopularDatos(Intent i){
        lblnombre.setText(i.getStringExtra("user"));
        lblcedula.setText(i.getStringExtra("id"));

    }


    private void IniciarControles(){
        lblnombre = (TextView)findViewById(R.id.lblnombre);
        lblcedula = (TextView)findViewById(R.id.lblcedula);

    }



}