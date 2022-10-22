package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
//LoginActivity
public class MainActivity extends AppCompatActivity {
    EditText usuario,password;
    RadioGroup tipologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValidarSession();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu:

                Intent crear = new Intent(this, CreacionActivity.class);
                startActivity(crear);

                Toast.makeText(MainActivity.this, "Creando Usuario", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void ValidarSession() {
        SharedPreferences login = getSharedPreferences("Login",Context.MODE_PRIVATE);
        int estado = login.getInt("estado",0);
        if (estado == 1){
            startActivity(new Intent(this,MainActivity.class));
        }

    }
    public void Ingresar(View view){
        try {
            String user = usuario.getText().toString();
            String contra = password.getText().toString();

            SharedPreferences Login = getSharedPreferences("Login",Context.MODE_PRIVATE);
            String usuarioRegistrado = Login.getString("correo",user);
            String passwordRegistrado = Login.getString("contrasena",contra);

            if (user.equals(usuarioRegistrado) && contra.equals(passwordRegistrado)) {
                switch (tipologin.getCheckedRadioButtonId()) {
                    case R.id.rbAdmin:
                        Intent iAdmin = new Intent(this, AdministradorActivity.class); //crear pantalla de Admin
                        startActivity(iAdmin);
                        break;

                    case R.id.rbRegis:
                        Intent iRegis = new Intent(this, RegistradorActivity.class); //Crear pantalla de registrador
                        startActivity(iRegis);
                        break;

                    case R.id.rbUser:
                        Intent iUser = new Intent(this, ValidationActivity.class); //esta si va a la de validation
                        startActivity(iUser);
                        break;
                }

            }


            }catch (Exception e){
            Toast.makeText(this,"Ups! Un error "+ e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    public void Logout(View v){
        SharedPreferences login = getSharedPreferences("Login", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = login.edit();
        editor.putInt("estado",0);
        editor.commit();

        startActivity(new Intent(this,MainActivity.class));
    }

}