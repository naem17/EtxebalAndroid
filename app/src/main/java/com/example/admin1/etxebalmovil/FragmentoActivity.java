package com.example.admin1.etxebalmovil;

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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public abstract class FragmentoActivity extends AppCompatActivity {
    private TextView[] opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;
    public static final int CANTIDAD=5;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prueba_layout);

        //FragmentManager para gestionar los fragmentos
        FragmentManager fragmentManager=getSupportFragmentManager();


        Fragment fragment=fragmentManager.findFragmentById(R.id.prueba);

        if(fragment==null)
        {
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragment=createFragment();
            fragmentTransaction.add(R.id.prueba, fragment);
            fragmentTransaction.commit();
        }



        drawerLayout=findViewById(R.id.drawer_layout);
        listView=findViewById(R.id.left_drawer);
   /*     opciones=new TextView[5];
        opciones[0]=(TextView) findViewById(R.id.textViewInicioSesion);
        opciones[1]=(TextView) findViewById(R.id.textViewMisReservas);
        opciones[2]=(TextView) findViewById(R.id.textViewBuscar);
        opciones[3]=(TextView) findViewById(R.id.textViewCercaDeMi);
        opciones[4]=(TextView) findViewById(R.id.textViewAboutUs);*/
        String[] menu=new String[]{"a","b","c","d","e"};

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.elemento_lista_navegacion,menu));
        // listView.setOnItemClickListener(new DrawerIemClickListener());
        //TODO Hacer un array adapter propio para poder mostrar con imágenes
    }
}
