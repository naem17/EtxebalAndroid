package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TipoEuskera implements DatabaseObject, Serializable {

    private int mCodigo;
    private String mTipoEuskera;

    public int getCodigo() {
        return mCodigo;
    }

    public void setCodigo(int codigo) {
        mCodigo = codigo;
    }

    public String getTipoEuskera() {
        return mTipoEuskera;
    }

    public void setTipoEuskera(String tipoEuskera) {
        mTipoEuskera = tipoEuskera;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setCodigo(json.getInt(JSONTag.Tipo_Euskera.TAG_CODIGO));
        this.setTipoEuskera(json.getString(JSONTag.Tipo_Euskera.TAG_TYPE_EUSKERA));
        return this;
    }
}
