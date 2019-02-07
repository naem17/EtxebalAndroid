package com.example.admin1.etxebalmovil;

import android.content.Context;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.json.JSONController;
import com.example.admin1.etxebalmovil.model.pojo.Alojamiento;
import com.example.admin1.etxebalmovil.model.pojo.Municipio;
import com.example.admin1.etxebalmovil.model.pojo.Provincia;
import com.example.admin1.etxebalmovil.model.pojo.Relacion;
import com.example.admin1.etxebalmovil.model.pojo.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlojamientosLab {

    private static AlojamientosLab alojamientosLab;
    private ArrayList<Alojamientos> alojamientos;
    private static ArrayList<Alojamientos> filtrados;

    private AlojamientosLab(Context context)
    {
            List<Alojamiento> alojamiento= SessionDataController.getInstance().getAlojamientos();
            alojamientos=new ArrayList<>();
        for (Alojamiento alo : alojamiento) {
            alojamientos.add(new Alojamientos());
            int posicionAlojamiento=alojamientos.size()-1;

            alojamientos.get(posicionAlojamiento).setFirma(alo.getFirma());
            alojamientos.get(posicionAlojamiento).setNombre(alo.getNombre());
            alojamientos.get(posicionAlojamiento).setDescripcion(alo.getDescripcion());
            alojamientos.get(posicionAlojamiento).setDescripcionEuskera(alo.getDescripcionEuskera());
            alojamientos.get(posicionAlojamiento).setTelefono(alo.getTelefono());
            alojamientos.get(posicionAlojamiento).setDireccion(alo.getDireccion());
            alojamientos.get(posicionAlojamiento).setEmail(alo.getEmail());
            alojamientos.get(posicionAlojamiento).setWeb(alo.getWeb());
            alojamientos.get(posicionAlojamiento).setClub(alo.isClub());
            alojamientos.get(posicionAlojamiento).setRestaurante(alo.isRestaurante());
            alojamientos.get(posicionAlojamiento).setAutocaravana(alo.isAutocaravana());
            alojamientos.get(posicionAlojamiento).setTienda(alo.isTienda());
            alojamientos.get(posicionAlojamiento).setCapacidad(alo.getCapacidad());
            alojamientos.get(posicionAlojamiento).setGastronomico(alo.isGastronomico());
            alojamientos.get(posicionAlojamiento).setSufring(alo.isSurf());
            alojamientos.get(posicionAlojamiento).setCoordenadas(alo.getCoordenadas());
            alojamientos.get(posicionAlojamiento).setTipoEuskera(alo.getTipoEuskera());

            if(alo.getTipo().compareToIgnoreCase("null")!=0) {
                List<Tipo> tipos = SessionDataController.getInstance().getTipos();
                int codigoTipo = 0;
                while (codigoTipo < tipos.size() && Integer.valueOf(alo.getTipo()) != tipos.get(codigoTipo).getCodigo())
                    codigoTipo++;
                alojamientos.get(posicionAlojamiento).setTipo(tipos.get(codigoTipo).getTipo());
            } else
                alojamientos.get(posicionAlojamiento).setTipo("Indefinido");
            List<Relacion> relacions=SessionDataController.getInstance().getRelaciones();
            int indiceRelacion=0;
            while (indiceRelacion<relacions.size() && relacions.get(indiceRelacion).getId()!=alo.getIdRelaciones())
                indiceRelacion++;
            List<Provincia> provincias=SessionDataController.getInstance().getProvincias();

            int codigo=0;
            while (codigo<provincias.size() && provincias.get(codigo).getmCodigo()!=Integer.valueOf(relacions.get(indiceRelacion).getCodigoProvincia()))
                codigo++;
            alojamientos.get(posicionAlojamiento).setProvincia(provincias.get(codigo).getmProvincua());

            List<Municipio> municipios=SessionDataController.getInstance().getMunicipios();
            codigo=0;
            while (codigo<municipios.size() && municipios.get(codigo).getmIndice()!=Integer.valueOf(relacions.get(indiceRelacion).getIndiceMunicipio()))
                codigo++;
            alojamientos.get(posicionAlojamiento).setMunicipio(municipios.get(codigo).getmMunicipio());

            alojamientos.get(posicionAlojamiento).setCp(Integer.valueOf(relacions.get(indiceRelacion).getCodigoPostal()));


        }
    }
    public static AlojamientosLab get(Context context)
    {
        if(alojamientosLab==null)
            alojamientosLab=new AlojamientosLab(context);
        return alojamientosLab;
    }

    public ArrayList<Alojamientos> getAlojamientos() {
        return alojamientos;
    }
    public ArrayList<Alojamientos> getAlojamientosFiltrados() {
        return filtrados;
    }
    public void setAlojamientosFiltrados(ArrayList<Alojamientos> filtrados){this.filtrados=filtrados;}
    public Alojamientos getAlojamiento(UUID id)
    {
        int i=0;
        while(i<alojamientos.size() && !alojamientos.get(i).getMyID().equals(id))
            i++;
        return i>=alojamientos.size()?null:alojamientos.get(i);
    }
    public int getPosicion(UUID id)
    {
        int i=0;
        while(i<alojamientos.size() && !alojamientos.get(i).getMyID().equals(id))
            i++;
        return i>=alojamientos.size()?-1:i;
    }
    public Alojamientos getAlojamiento(String firma)
    {
        int i=0;
        while (i<alojamientos.size() && alojamientos.get(i).getFirma().compareToIgnoreCase(firma)!=0)
        {
            i++;
        }
        return i>=alojamientos.size()?null:alojamientos.get(i);
    }
    public Alojamientos getAlojamientoFiltrado(UUID id)
    {
        int i=0;
        while(i<filtrados.size() && !filtrados.get(i).getMyID().equals(id))
            i++;
        return i>=filtrados.size()?null:filtrados.get(i);
    }
    public int getPosicionFiltrados(UUID id)
    {
        int i=0;
        while(i<filtrados.size() && !filtrados.get(i).getMyID().equals(id))
            i++;
        return i>=filtrados.size()?-1:i;
    }
    public Alojamientos getAlojamientoFiltrado(String firma)
    {
        int i=0;
        while (i<filtrados.size() && filtrados.get(i).getFirma().compareToIgnoreCase(firma)!=0)
        {
            i++;
        }
        return i>=filtrados.size()?null:filtrados.get(i);
    }

}
