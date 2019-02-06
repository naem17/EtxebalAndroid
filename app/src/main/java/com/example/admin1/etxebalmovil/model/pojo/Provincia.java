package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Provincia implements DatabaseObject, Serializable {

    private int mCodigo;
    private String mProvincua;

    public int getmCodigo() {
        return mCodigo;
    }

    public void setmCodigo(int mCodigo) {
        this.mCodigo = mCodigo;
    }

    public String getmProvincua() {
        return mProvincua;
    }

    public void setmProvincua(String mProvincua) {
        this.mProvincua = mProvincua;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setmCodigo(json.getInt(JSONTag.Provincia.TAG_CODIGO));
        this.setmProvincua(json.getString(JSONTag.Provincia.TAG_PROVINCUA));
        return this;
    }
}
