package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.json.JSONController;


public class FragmentoInicioSesion extends Fragment {

    private EditText mUsuarioEditText;
    private EditText mPasswordEditText;
    private Button mIniciarSesion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.iniciar_sesion_fragment_layout, container, false);

            mUsuarioEditText = v.findViewById(R.id.editTextPalabra);
            mPasswordEditText = v.findViewById(R.id.editTextPassword);
            mIniciarSesion = v.findViewById(R.id.btnLogIn);
            mIniciarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    byte errorCode = JSONController.logInUser(mUsuarioEditText.getText().toString().trim(), mPasswordEditText.getText().toString().trim());
                    switch  (errorCode) {
                        case JSONController.NO_ERROR: {
                            Toast.makeText(getContext(), "Bienvenido " + mUsuarioEditText.getText().toString().trim(), Toast.LENGTH_LONG).show();
                            JSONController.getData();
                            Intent intent=FragmentoListarActivity.newIntent(getContext());
                            startActivity(intent);
                            getActivity().finish();
                            //TODO preguntar si quiere log out


                        }
                            break;
                        case JSONController.INPUT_ERROR:
                            Toast.makeText(getContext(),    getString(R.string.errorUsuario), Toast.LENGTH_LONG).show();
                            break;
                        case JSONController.OTHER_ERROR:
                            Toast.makeText(getContext(), getString(R.string.errorOtro), Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });
            return v;
   }
}
