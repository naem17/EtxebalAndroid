package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Date;
import java.util.Comparator;

public class Reserve implements Serializable, DatabaseObject {
    private String mLodgingCode;
    private String mUserNick;
    private Date mStartDate;
    private Date mFinishDate;

    public static Comparator<DatabaseObject> COMPARE_BY_USER = new Comparator<DatabaseObject>() {
        @Override
        public int compare(DatabaseObject o1, DatabaseObject o2) {
            return ((Reserve)o1).getUserNick().compareTo(((Reserve)o2).getUserNick());
        }
    };

    public String getLodgingCode() {
        return mLodgingCode;
    }

    public void setLodgingCode(String lodgingCode) {
        mLodgingCode = lodgingCode;
    }

    public String getUserNick() {
        return mUserNick;
    }

    public void setUserNick(String userNick) {
        mUserNick = userNick;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getFinishDate() {
        return mFinishDate;
    }

    public void setFinishDate(Date finishDate) {
        mFinishDate = finishDate;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setFinishDate(Date.valueOf(json.getString(JSONTag.Reserve.TAG_END)));
        this.setStartDate(Date.valueOf(json.getString(JSONTag.Reserve.TAG_START)));
        this.setUserNick(json.getString(JSONTag.Reserve.TAG_USER));
        this.setLodgingCode(json.getString(JSONTag.Reserve.TAG_LODGING));

        return this;
    }

}
