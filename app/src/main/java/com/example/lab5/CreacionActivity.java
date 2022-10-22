package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.Toast;

//RegisterActivity
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

        txtnombrecrear = findViewById(R.id.txtnombrecrear);
        txtcedulacrear = findViewById(R.id.txtcedulacrear);
        txtcorreocrear = findViewById(R.id.txtcorreocrear);
        txtcontrasenacrear = findViewById(R.id.txtcontrasenacrear);
        tipologin = findViewById(R.id.rgRoles);

    }

    public void EnviarDatosCreados(View view){
        try {
            String nombre = txtnombrecrear.getText().toString();
            String cedula = txtcedulacrear.getText().toString();
            String correo = txtcorreocrear.getText().toString();
            String contrasena = txtcontrasenacrear.getText().toString();

            switch (tipologin.getCheckedRadioButtonId()){
                case R.id.rbAdmin:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,"Admnistrador");
                    break;
                case R.id.rbRegis:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,"Registrador");
                    break;
                case R.id.rbUser:
                    GuardarPorSharedPreferences(nombre, cedula, correo, contrasena,"Usuario Normal");
                    break;
                default:
                    Toast.makeText(this, "Seleccione una opcion!!", Toast.LENGTH_SHORT).show();
                    break;
            }

            /*Intent i = new Intent(this, ValidationActivity.class); //IMPRIME EN EL VALIDATION, cumple lo del LAB 5
            i.putExtra("user",nombre);
            i.putExtra("id",cedula);
            startActivity(i);*/

            Intent in = new Intent(this, MainActivity.class); //
            startActivity(in);

            Toast.makeText(this, "Usuario Creado Exitosamente", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Errorsito hehe" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}