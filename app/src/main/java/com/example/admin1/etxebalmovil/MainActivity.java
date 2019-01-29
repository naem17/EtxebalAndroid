package com.example.admin1.etxebalmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layaout_general);
        FragmentoInicioSesion fragmentoInicioSesion=new FragmentoInicioSesion();
        fragmentoInicioSesion.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout,fragmentoInicioSesion).commit();
    }
}
