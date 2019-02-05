package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario implements DatabaseObject {
    private int mId;
    private String mNick;
    private String mPassword;
    private String mName;
    private String mLast;
    private String mEmail;
    private String mTlf;
    private String mPerfil = "";

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNick() {
        return mNick;
    }

    public void setNick(String nick) {
        mNick = nick;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLast() {
        return mLast;
    }

    public void setLast(String last) {
        mLast = last;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getTlf() {
        return mTlf;
    }

    public void setTlf(String tlf) {
        mTlf = tlf;
    }

    public String getPerfil() {
        return mPerfil;
    }

    public void setPerfil(String perfil) {
        mPerfil = perfil;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setEmail(json.getString(JSONTag.Usuario.TAG_EMAIL));
        this.setLast(json.getString(JSONTag.Usuario.TAG_LASTNAME));
        this.setName(json.getString(JSONTag.Usuario.TAG_NAME));
        this.setNick(json.getString(JSONTag.Usuario.TAG_NICK));
        this.setPassword(json.getString(JSONTag.Usuario.TAG_PASSWORD));
        this.setPerfil(json.getString(JSONTag.Usuario.TAG_PERFIL));

        return this;
    }
}
