package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentoListarReservas extends Fragment {
    private RecyclerView listaFragmentosReservas;
    private ReservaAdapter adapterReservas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.alojamiento_recycler_view,container,false);
        listaFragmentosReservas= vista.findViewById(R.id.alojamientoRecyclerView);
        listaFragmentosReservas.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return vista;
    }

    private void updateUI()
    {
        ReservasLab reservasLab = ReservasLab.get(getActivity());
        ArrayList<Reservas> reservas = reservasLab.getReservas();
        if(adapterReservas ==null)
        {
            adapterReservas =new ReservaAdapter(reservas);
            listaFragmentosReservas.setAdapter(adapterReservas);
        }
        else
        {
            adapterReservas.notifyDataSetChanged();
        }
    }
    public class ReservaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombreReserva;
        private TextView nombreAlojamiento;
        private TextView cantidad;
        private Button cancelar;
        private TextView fechaInicio;
        private TextView fechaFin;

        private Reservas reserva;

        public  ReservaHolder(LayoutInflater inflater, ViewGroup parent, int ViewType)
        {
            super(inflater.inflate(ViewType, parent, false));
            itemView.setOnClickListener(this);

            //Inicializo todos los campos con los respectivos del Layout
            nombreReserva=itemView.findViewById(R.id.textViewNombreReserva);
            nombreAlojamiento= itemView.findViewById(R.id.textViewNombrealojamiento);
            cantidad=itemView.findViewById(R.id.editTextCantidadPersonas);
            cancelar=itemView.findViewById(R.id.buttonCancelar);
            fechaInicio=itemView.findViewById(R.id.editTextFechaInicio);
            fechaFin=itemView.findViewById(R.id.editTextFechaFin);

            nombreReserva.setEnabled(false);
            nombreAlojamiento.setEnabled(false);
            cantidad.setEnabled(false);
            fechaInicio.setEnabled(false);
            fechaFin.setEnabled(false);
        }

        @Override
        public void onClick(View v) {
            Intent intent=ReservasPagerActivity.newIntent(getActivity(), reserva.getMyID());
            startActivity(intent);
        }

        public void bind(final Reservas reserva)
        {
            this.reserva=reserva;

            nombreAlojamiento.setText(reserva.getFirmaAlojamiento());
            nombreReserva.setText(reserva.getNombreReserva());
            cantidad.setText(String.valueOf(reserva.getCantidad()));
            fechaInicio.setText(reserva.getFechaInicio().toString());
            fechaFin.setText(reserva.getFechaFin().toString());

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO borrar la reserva
                }
            });
        }
    }
    public class ReservaAdapter extends RecyclerView.Adapter<ReservaHolder>{
        private ArrayList<Reservas> reservasArrayList;

        public ReservaAdapter(ArrayList<Reservas> reservas)
        {
            this.reservasArrayList=reservas;
        }

        @NonNull
        @Override
        public ReservaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new ReservaHolder(layoutInflater, viewGroup, i);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservaHolder reservaHolder, int i) {
            Reservas reserva=reservasArrayList.get(i);
            reservaHolder.bind(reserva);
        }

        @Override
        public int getItemCount() {
            return reservasArrayList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.fragmento_reserva_layout;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
