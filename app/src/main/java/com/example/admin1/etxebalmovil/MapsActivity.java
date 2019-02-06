package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.UUID;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
private static final String EXTRA_ALOJAMIENTOS=AlojamientosLab.class.getName() + ".alojamiento_id";
    private GoogleMap mMap;
    private Alojamientos mAlojamientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        UUID myID=(UUID) getIntent().getSerializableExtra(EXTRA_ALOJAMIENTOS);
        mAlojamientos=AlojamientosLab.get(getBaseContext()).getAlojamiento(myID);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private String createFragment(UUID alojamientos){
        Fragment Alojamientos=new Fragment();
        return  AlojamientosLab.class.getName()+alojamientos;
    }


    public static Intent newIntent(Context context, UUID alojamientos) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTOS, alojamientos);
        return intent;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] coordenadas = new String[2];

        coordenadas=mAlojamientos.getCoordenadas().split(",");
        Log.d("MapaActivity",coordenadas[0]+ coordenadas[1]);
        LatLng target = new LatLng(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));

        MarkerOptions options = new MarkerOptions();
        options.position(target).title(mAlojamientos.getNombre());
        mMap.addMarker(options);
        //Poner el Zoom en la marca
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 20f));
    }
}
