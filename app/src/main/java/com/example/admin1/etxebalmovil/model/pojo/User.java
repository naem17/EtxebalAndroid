package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements DatabaseObject {
    private String mNick;
    private String mName;
    private String mLast;
    private String mEmail;
    private String mPassword;
    private int mPermision = 0;

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

    public int getPermision() {
        return mPermision;
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

    public void setPermision(int permision) {
        mPermision = permision;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setEmail(json.getString(JSONTag.User.TAG_EMAIL));
        this.setLast(json.getString(JSONTag.User.TAG_LASTNAME));
        this.setName(json.getString(JSONTag.User.TAG_NAME));
        this.setNick(json.getString(JSONTag.User.TAG_NICK));
        this.setPassword(json.getString(JSONTag.User.TAG_PASSWORD));
        this.setPermision(json.getInt(JSONTag.User.TAG_PERMISION));

        return this;
    }
}
