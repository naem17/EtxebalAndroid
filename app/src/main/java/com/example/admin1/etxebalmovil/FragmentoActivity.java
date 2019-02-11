package com.example.admin1.etxebalmovil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.json.JSONBuilder;
import com.example.admin1.etxebalmovil.model.json.JSONController;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class FragmentoActivity extends AppCompatActivity {
    private ArrayList<String> opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;
    public static final int CANTIDAD=5;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prueba_layout);

        //FragmentManager para gestionar los fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();


        Fragment fragment = fragmentManager.findFragmentById(R.id.prueba);

        if (fragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = createFragment();
            fragmentTransaction.add(R.id.prueba, fragment);
            fragmentTransaction.commit();
        }
        drawerLayout=findViewById(R.id.drawer_layout);
        listView=findViewById(R.id.left_drawer);
        opciones=new ArrayList<>();
        opciones.add("perfil");
        opciones.add("reservas");
        opciones.add("buscar");
        opciones.add("cerca");
        opciones.add("alojamientos");

        listView.setAdapter(new TextviewArrayAdapter(this, R.layout.elemento_lista_navegacion_inicio,opciones));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 1:
                    {
                            Intent intent=FragmentoListarReservasActivity.newIntent(getBaseContext());
                            startActivity(intent);
                            finish();
                    }
                    break;
                    case 2:
                    {
                        Intent intent=AlojamientoBuscarActivity.newIntent(getBaseContext());
                        startActivity(intent);
                        finish();
                    }
                    break;
                    case 3:
                    {
                        Toast toas = Toast.makeText(getBaseContext(),"Bienvenido al mapita",Toast.LENGTH_SHORT);
                        toas.show();
                        Intent todoMapita= (Intent) MapsActivityViewALL.newIntent(getBaseContext());
                        startActivity(todoMapita);
                        finish();
                    }
                    break;
                    case 4:
                    {
                        Intent intent=FragmentoListarActivity.newIntent(getBaseContext());
                        startActivity(intent);
                        finish();
                    }
                    break;
                }


            }
        });
    }

    public class TextviewArrayAdapter extends ArrayAdapter<String>{
        public TextviewArrayAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String opcion=getItem(position);
            switch (opcion)
            {
                case "perfil":
                {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_navegacion_inicio, parent, false);
                    }
                }break;
                case "reservas":
                {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_navegacion_reservas, parent, false);
                    }
                }break;
                case "buscar":
                {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_navegacion_buscar, parent, false);
                    }
                }break;
                case "cerca":
                {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_navegacion_cerca_de_mi, parent, false);
                    }
                }break;
                case "alojamientos":
                {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_alojamientos, parent, false);
                    }
                }break;
            }


            return convertView;
        }

    }
}

