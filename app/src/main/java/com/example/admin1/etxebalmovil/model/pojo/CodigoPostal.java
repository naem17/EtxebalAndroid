package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CodigoPostal implements DatabaseObject, Serializable {

    private int mCodigoPostal;

    public int getmCodigoPostal() {
        return mCodigoPostal;
    }

    public void setmCodigoPostal(int mCodigoPostal) {
        this.mCodigoPostal = mCodigoPostal;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setmCodigoPostal(json.getInt(JSONTag.Codigo_Postal.TAG_CODIGO_POSTAL));
        return this;
    }
}
