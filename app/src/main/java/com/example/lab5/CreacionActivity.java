package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class CreacionActivity extends AppCompatActivity {

    EditText txtnombrecrear, txtcedulacrear, txtcorreocrear, txtcontrasenacrear;
    RadioGroup tipologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion);

        IniciarControles();
    }

    private void IniciarControles(){

        txtnombrecrear = (EditText)findViewById(R.id.txtnombrecrear);
        txtcedulacrear = (EditText)findViewById(R.id.txtcedulacrear);
        txtcorreocrear = (EditText)findViewById(R.id.txtcorreocrear);
        txtcontrasenacrear = (EditText)findViewById(R.id.txtcontrasenacrear);
        tipologin = (RadioGroup)findViewById(R.id.rgRoles);

    }

    public void EnviarDatosCreados(View view){
        try {
            String nombre = txtnombrecrear.getText().toString();
            String cedula = txtcedulacrear.getText().toString();
            String correo = txtcorreocrear.getText().toString();
            String contrasena = txtcontrasenacrear.getText().toString();
            /*sacar el texto del radiobutton seleccionado*/
            int radioButtonId = tipologin.getCheckedRadioButtonId();
            View radioButton = tipologin.findViewById(radioButtonId);
            int indice = tipologin.indexOfChild(radioButton);
            RadioButton rb = (RadioButton)tipologin.getChildAt(indice);
            String Rol = rb.getText().toString();//texto
            switch (radioButtonId){
                case R.id.rbAdmin:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,Rol);
                    break;
                case R.id.rbRegis:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,Rol);
                    break;
                case R.id.rbUser:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,Rol);
                default:
                    Toast.makeText(this, "Seleccione una opcion!!", Toast.LENGTH_SHORT).show();
                    break;
            }
            Intent i = new Intent(this, ValidationActivity.class);
            i.putExtra("nombre",nombre);
            i.putExtra("cedula",cedula);
            startActivity(i);

            Intent in = new Intent(this, MainActivity.class);
            in.putExtra("correo",correo);
            in.putExtra("contrasena",contrasena);
            startActivity(in);

            Toast.makeText(this, "Usuario Creado Exitosamente", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }
    private void GuardarPorSharedPreferences(String nombre, String cedula, String correo, String pwd, String rol) {
        try{
            SharedPreferences prefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user",nombre);
            editor.putString("id",cedula);
            editor.putString("email",correo);
            editor.putString("pass",pwd);
            editor.putString("role", rol);
            editor.commit();
        }
        catch (Exception e){

        }
    }




}