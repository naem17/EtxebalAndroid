package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class FragmentoListarAlojamientos extends Fragment {
    private RecyclerView listaFragmentosAlojamientos;
    private AlojamientoAdapter adapter;

    private boolean filtrados;

    private static final String EXTRA_ALOJAMIENTOS_FILTRADOS = FragmentoListarActivity.class.getName() + ".alojamiento_filtrado";

    public FragmentoListarAlojamientos(Boolean filtrados)
    {
        this.filtrados=filtrados;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!filtrados)
            filtrados=getActivity().getIntent().getBooleanExtra(EXTRA_ALOJAMIENTOS_FILTRADOS, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.alojamiento_recycler_view,container,false);
        listaFragmentosAlojamientos= vista.findViewById(R.id.alojamientoRecyclerView);
        listaFragmentosAlojamientos.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return vista;
    }

    private void updateUI()
    {
        AlojamientosLab alojamientosLab = AlojamientosLab.get(getActivity());
        ArrayList<Alojamientos> alojamientos = alojamientosLab.getAlojamientos();
        if(filtrados)
            alojamientos=alojamientosLab.getAlojamientosFiltrados();
        if(adapter==null)
        {
            adapter=new AlojamientoAdapter(alojamientos);
            listaFragmentosAlojamientos.setAdapter(adapter);
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }
    public class AlojamientoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombreAlojamiento;
        private TextView tipoAlojamiento;
        private ImageView imagenAlojamiento;
        private ImageButton mapa;
        private Button reserva;

        private Alojamientos alojamiento;

        public  AlojamientoHolder(LayoutInflater inflater, ViewGroup parent, int ViewType)
        {
            super(inflater.inflate(ViewType, parent, false));
            itemView.setOnClickListener(this);

            //Inicializo todos los campos con los respectivos del Layout
            nombreAlojamiento= itemView.findViewById(R.id.textViewAlojamientoNombre);
            tipoAlojamiento= itemView.findViewById(R.id.textViewAlojamientoTipo);
            imagenAlojamiento= itemView.findViewById(R.id.imageViewImagenAlojamiento);
            mapa=(ImageButton) itemView.findViewById(R.id.imagebUttonMapa);
            mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toas = Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT);

                    toas.show();
                    Intent mapita = new Intent(getActivity(), MapsActivity.class);
                    startActivity(mapita);

                }
            });
            reserva=itemView.findViewById(R.id.buttonReservar);




        }

        @Override
        public void onClick(View v) {

            Intent intent=AlojamientosPagerActivity.newIntent(getActivity(), alojamiento.getMyID());
            startActivity(intent);
        }

        public void bind(final Alojamientos alojamiento)
        {
            this.alojamiento=alojamiento;

            nombreAlojamiento.setText(alojamiento.getNombre());
            tipoAlojamiento.setText(alojamiento.getTipo());

            switch (alojamiento.getTipo().toLowerCase())
            {
                //TODO poner las imágenes según alojamiento
                case "albergues":
                    //  imagenAlojamiento.setImageDrawable();
                    break;
                case "campings":
                    break;
                case "agroturismos":
                    break;
                case "casas rurales":
                    break;
            }
            reserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=AlojamientosHacerReservaActivity.newIntent(getActivity(), alojamiento.getMyID());
                    startActivity(intent);
                }
            });
        }
    }
    public class AlojamientoAdapter extends RecyclerView.Adapter<AlojamientoHolder>{
        private ArrayList<Alojamientos> alojamientos;

        public AlojamientoAdapter(ArrayList<Alojamientos> alojamientos)
        {
            this.alojamientos=alojamientos;
        }

        @NonNull
        @Override
        public AlojamientoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new AlojamientoHolder(layoutInflater, viewGroup, i);
        }

        @Override
        public void onBindViewHolder(@NonNull AlojamientoHolder alojamientoHolder, int i) {
            Alojamientos alojamiento=alojamientos.get(i);
            alojamientoHolder.bind(alojamiento);
        }

        @Override
        public int getItemCount() {
            return alojamientos.size();
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.alojamiento_listable_layout;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
