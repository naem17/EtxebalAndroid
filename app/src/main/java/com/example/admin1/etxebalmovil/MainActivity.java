package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layaout_general);
        FragmentoInicioSesion fragmentoInicioSesion=new FragmentoInicioSesion();
        fragmentoInicioSesion.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout,fragmentoInicioSesion).commit();

        mLogInButton = (ImageButton) findViewById(R.id.imageButtonSesion);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
