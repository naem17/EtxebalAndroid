package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Categoria implements DatabaseObject, Serializable {

    private int mCodigo;
    private String mCategoria;

    public int getmCodigo() {
        return mCodigo;
    }

    public void setmCodigo(int mCodigo) {
        this.mCodigo = mCodigo;
    }

    public String getmCategoria() {
        return mCategoria;
    }

    public void setmCategoria(String mCategoria) {
        this.mCategoria = mCategoria;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setmCodigo(json.getInt(JSONTag.Categoria.TAG_CODIGO));
        this.setmCategoria(json.getString(JSONTag.Categoria.TAG_CATEGORY));
        return this;
    }
}
