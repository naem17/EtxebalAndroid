package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.json.JSONController;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNick;
    private EditText mNombre;
    private EditText mApellidos;
    private EditText mEmail;
    private EditText mTlf;
    private EditText mPassword;
    private Button mRegistrar;
    private Button mCancelar;
    private Usuario mUsuario;
    private SessionDataController mSessionDataController;

    // Errores //
    private ImageView mWarNick;
    private ImageView mWarName;
    private ImageView mWarLast;
    private ImageView mWarEmail;
    private ImageView mWarTlf;
    private ImageView mWarPass;

    private final int WARNING_AMARILLO = R.drawable.ic_warning_amarillo;
    private final int WARNING = R.drawable.ic_warning;
    private List<EditText> mErrores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mErrores = new ArrayList<>();

        mNick = findViewById(R.id.edtNick);
        mNick.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        mWarNick.setVisibility(View.INVISIBLE);
                        mErrores.remove(v);
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mNombre = findViewById(R.id.edtNombre);
        mNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        mWarName.setVisibility(View.INVISIBLE);
                        mErrores.remove(v);
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mApellidos = findViewById(R.id.edtLast);
        mApellidos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        mWarLast.setVisibility(View.INVISIBLE);
                        mErrores.remove(v);
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mEmail = findViewById(R.id.edtEmail);
        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        if (((EditText) v).getText().toString().matches("^([A-Za-z0-9]([._])?)+[A-Za-z0-9]@([A-Za-z])+\\.[a-z]{2,3}$")) {
                            mWarEmail.setVisibility(View.INVISIBLE);
                            mErrores.remove(v);
                            return;
                        } else {
                            mWarEmail.setVisibility(View.VISIBLE);
                            mWarEmail.setImageResource(WARNING_AMARILLO);
                            mWarEmail.setContentDescription(getString(R.string.formato));
                            setWarning(v);
                        }
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mTlf = findViewById(R.id.edvTelefono);
        mTlf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        if (((EditText) v).getText().toString().matches("\\d{9}")) {
                            mWarTlf.setVisibility(View.INVISIBLE);
                            mErrores.remove(v);
                            return;
                        } else {
                            mWarEmail.setVisibility(View.VISIBLE);
                            mWarEmail.setImageResource(WARNING_AMARILLO);
                            mWarEmail.setContentDescription(getString(R.string.formato));
                            setWarning(v);
                        }
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mPassword = findViewById(R.id.edtPass);
        mPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((EditText) v).getText().toString().length() > 0) {
                        mWarPass.setVisibility(View.INVISIBLE);
                        mErrores.remove(v);
                    }else{
                        mostrarErrores((EditText) v);
                    }
                }
            }
        });

        mWarNick = findViewById(R.id.warNick);
        mWarName = findViewById(R.id.warName);
        mWarLast = findViewById(R.id.warLast);
        mWarEmail = findViewById(R.id.warEmail);
        mWarTlf = findViewById(R.id.warTlf);
        mWarPass = findViewById(R.id.warPass);

        mRegistrar = findViewById(R.id.buttonRegistrar);
        mCancelar = findViewById(R.id.buttonCancelar);

        mRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mErrores.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, getString(R.string.alguno_vacio), Toast.LENGTH_SHORT).show();
                    return;
                }
                mUsuario = new Usuario();
                mUsuario.setNick(mNick.getText().toString());
                mUsuario.setName(mNombre.getText().toString());
                mUsuario.setLast(mApellidos.getText().toString());
                mUsuario.setEmail(mEmail.getText().toString());
                mUsuario.setTlf(mTlf.getText().toString());
                mUsuario.setPassword(mPassword.getText().toString());
                mSessionDataController = SessionDataController.getInstance();
                if (mSessionDataController.registarUsuario(mUsuario)) {
                    Toast.makeText(SignUpActivity.this, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
                    Intent intent = FragmentoInicioSesionActivity.newIntent(SignUpActivity.this);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Error al registrar Usuario", Toast.LENGTH_LONG).show();
                    Intent intent = FragmentoInicioSesionActivity.newIntent(SignUpActivity.this);
                    startActivity(intent);
                }
            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void mostrarErrores(EditText campo) {
        switch (campo.getId()) {
            case R.id.edtNick:
                mWarNick.setImageResource(WARNING);
                mWarNick.setVisibility(View.VISIBLE);
                break;
            case R.id.edtNombre:
                mWarName.setImageResource(WARNING);
                mWarName.setVisibility(View.VISIBLE);
                break;
            case R.id.edtLast:
                mWarLast.setImageResource(WARNING);
                mWarLast.setVisibility(View.VISIBLE);
                break;
            case R.id.edtEmail:
                mWarEmail.setImageResource(WARNING);
                mWarEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.edvTelefono:
                mWarTlf.setImageResource(WARNING);
                mWarTlf.setVisibility(View.VISIBLE);
                break;
            case R.id.edtPass:
                mWarPass.setImageResource(WARNING);
                mWarPass.setVisibility(View.VISIBLE);
                break;
        }
        setWarning(campo);
    }


    private void setWarning(View v) {
        mErrores.remove(v);
        mErrores.add((EditText) v);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}
