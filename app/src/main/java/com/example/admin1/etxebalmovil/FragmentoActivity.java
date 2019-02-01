package com.example.admin1.etxebalmovil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class FragmentoActivity extends AppCompatActivity {
    private ArrayList<TextView> opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prueba_layout);
        drawerLayout=findViewById(R.id.drawer_layout);
        listView=findViewById(R.id.left_drawer);
        opciones.add((TextView) findViewById(R.id.textViewInicioSesion));
        opciones.add((TextView) findViewById(R.id.textViewLblMisReservas));
        opciones.add((TextView) findViewById(R.id.textViewBuscar));
        opciones.add((TextView) findViewById(R.id.textViewCercaDeMi));
        opciones.add((TextView) findViewById(R.id.textViewAboutUs));

        listView.setAdapter(new ArrayAdapter<TextView>(this, R.layout.menu_rapido,opciones));
       // listView.setOnItemClickListener(new DrawerIemClickListener());
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

    }
}
