package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.json.JSONController;

import java.util.UUID;

public class FragmentoInicioSesionActivity extends AppCompatActivity {

    //TODO que sea main activity y loguee

    private EditText mUsuarioEditText;
    private EditText mPasswordEditText;
    private Button mIniciarSesion;

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect, FragmentoInicioSesionActivity.class);
        return intent;
    }

    protected Fragment createFragment() {
        return new FragmentoInicioSesion();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion_fragment_layout);

        mUsuarioEditText = findViewById(R.id.editTextPalabra);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mIniciarSesion = findViewById(R.id.btnLogIn);
        mIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte errorCode = JSONController.logInUser(mUsuarioEditText.getText().toString().trim(), mPasswordEditText.getText().toString().trim());
                switch (errorCode) {
                    case JSONController.NO_ERROR: {
                        Toast.makeText(FragmentoInicioSesionActivity.this, "Bienvenido " + mUsuarioEditText.getText().toString().trim(), Toast.LENGTH_LONG).show();
                        JSONController.getData();
                        Intent intent = FragmentoListarActivity.newIntent(FragmentoInicioSesionActivity.this);
                        startActivity(intent);
                        //TODO preguntar si quiere log out


                    }
                    break;
                    case JSONController.INPUT_ERROR:
                        Toast.makeText(FragmentoInicioSesionActivity.this, getString(R.string.errorUsuario), Toast.LENGTH_LONG).show();
                        break;
                    case JSONController.OTHER_ERROR:
                        Toast.makeText(FragmentoInicioSesionActivity.this, getString(R.string.errorOtro), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
