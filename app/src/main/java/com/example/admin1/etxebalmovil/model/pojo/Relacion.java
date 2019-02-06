package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Relacion implements DatabaseObject, Serializable {

    private int id;
    private String codigoPostal;
    private String codigoProvincia;
    private String indiceMunicipio;

    public int getId() {
        return id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getIndiceMunicipio() {
        return indiceMunicipio;
    }

    public void setIndiceMunicipio(String indiceMunicipio) {
        this.indiceMunicipio = indiceMunicipio;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setCodigoPostal(json.getString(JSONTag.Relacion.TAG_CODIGO_POSTAL));
        this.setCodigoProvincia(json.getString(JSONTag.Relacion.TAG_CODIGO_PROVINCIA));
        this.setIndiceMunicipio(json.getString(JSONTag.Relacion.TAG_INDICE_MUNICIPIO));
        return this;
    }
}
