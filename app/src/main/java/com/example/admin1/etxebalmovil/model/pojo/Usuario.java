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

    public String getNick() {
        return mNick;
    }

    public String getName() {
        return mName;
    }

    public String getLast() {
        return mLast;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPerfil() {
        return mPerfil;
    }

    public void setNick(String nick) {
        mNick = nick;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setLast(String last) {
        mLast = last;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void setPerfil(String perfil) {
        mPerfil = perfil;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setEmail(json.getString(JSONTag.User.TAG_EMAIL));
        this.setLast(json.getString(JSONTag.User.TAG_LASTNAME));
        this.setName(json.getString(JSONTag.User.TAG_NAME));
        this.setNick(json.getString(JSONTag.User.TAG_NICK));
        this.setPassword(json.getString(JSONTag.User.TAG_PASSWORD));
        this.setPerfil(json.getString(JSONTag.User.TAG_PERMISION));

        return this;
    }
}
