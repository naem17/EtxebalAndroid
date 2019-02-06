package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Tipo implements DatabaseObject, Serializable {

    private int mCodigo;
    private String mTipo;

    public int getCodigo() {
        return mCodigo;
    }

    public void setCodigo(int codigo) {
        mCodigo = codigo;
    }

    public String getTipo() {
        return mTipo;
    }

    public void setTipo(String tipo) {
        mTipo = tipo;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setCodigo(json.getInt(JSONTag.Tipo.TAG_CODIGO));
        this.setTipo(json.getString(JSONTag.Tipo.TAG_TYPE));
        return this;
    }
}
