package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNick;
    private EditText mNombre;
    private EditText mApellidos;
    private EditText mEmail;
    private EditText mTlf;
    private EditText mPassword;
    private Button mRegistrar;
    private Usuario mUsuario;
    private SessionDataController mSessionDataController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNick = findViewById(R.id.edtNick);
        mNombre = findViewById(R.id.edtNombre);
        mApellidos = findViewById(R.id.edtLast);
        mEmail = findViewById(R.id.edtEmail);
        mTlf = findViewById(R.id.edvTelefono);
        mPassword = findViewById(R.id.edtPass);
        mRegistrar = findViewById(R.id.buttonRegistrar);

        mRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsuario = new Usuario();
                mUsuario.setNick(mNick.getText().toString());
                mUsuario.setName(mNombre.getText().toString());
                mUsuario.setLast(mApellidos.getText().toString());
                mUsuario.setEmail(mEmail.getText().toString());
                mUsuario.setTlf(mTlf.getText().toString());
                mUsuario.setPassword(mPassword.getText().toString());
                mSessionDataController = SessionDataController.getInstance();
                if(mSessionDataController.registarUsuario(mUsuario)){
                    Toast.makeText(SignUpActivity.this, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
                    Intent intent = FragmentoListarActivity.newIntent(SignUpActivity.this);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpActivity.this, "Error al registrar Usuario", Toast.LENGTH_LONG).show();
                    Intent intent = FragmentoInicioSesionActivity.newIntent(SignUpActivity.this);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}
