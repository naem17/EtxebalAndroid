package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Municipio implements DatabaseObject, Serializable {

    private int mCodigo;
    private String mMunicipio;
    private int mIndice;

    public int getmCodigo() {
        return mCodigo;
    }

    public void setmCodigo(int mCodigo) {
        this.mCodigo = mCodigo;
    }

    public String getmMunicipio() {
        return mMunicipio;
    }

    public void setmMunicipio(String mMunicipio) {
        this.mMunicipio = mMunicipio;
    }

    public int getmIndice() {
        return mIndice;
    }

    public void setmIndice(int mIndice) {
        this.mIndice = mIndice;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setmCodigo(json.getInt(JSONTag.Municipio.TAG_CODIGO));
        this.setmMunicipio(json.getString(JSONTag.Municipio.TAG_MUNICIPE));
        this.setmIndice(json.getInt(JSONTag.Municipio.TAG_INDICE));
        return this;
    }
}
