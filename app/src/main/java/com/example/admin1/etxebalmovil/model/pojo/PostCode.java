package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class PostCode implements DatabaseObject, Serializable {
    private String mCountyCode;
    private String mCityCode;
    private String mPostCode;
    private String mCountyName;
    private String mCityName;

    public static final String COUNTY_BIZ = "Bizkaia";
    public static final String COUNTY_CODE_BIZ = "48";
    public static final String COUNTY_ARA = "Araba";
    public static final String COUNTY_CODE_ARA = "01";
    public static final String COUNTY_GIP = "Gipuzkoa";
    public static final String COUNTY_CODE_GIP = "20";

    public String getCountyCode() {
        return mCountyCode;
    }

    public void setCountyCode(String countyCode) {
        mCountyCode = countyCode;
    }

    public String getCityCode() {
        return mCityCode;
    }

    public void setCityCode(String cityCode) {
        mCityCode = cityCode;
    }

    public String getPostCode() {
        return mPostCode;
    }

    public void setPostCode(String postCode) {
        mPostCode = postCode;
    }

    public String getCountyName() {
        return mCountyName;
    }

    public void setCountyName(String countyName) {
        mCountyName = countyName;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    @Override
    public PostCode fromJSON(JSONObject json) throws JSONException {
        this.setCityCode(json.getString(JSONTag.PostCode.TAG_CITY_CODE));
        this.setCityName(json.getString(JSONTag.PostCode.TAG_CITY_NAME));
        this.setCountyCode(json.getString(JSONTag.PostCode.TAG_COUNTY_CODE));
        this.setCountyName(json.getString(JSONTag.PostCode.TAG_COUNTY_NAME));
        this.setPostCode(json.getString(JSONTag.PostCode.TAG_POST_CODE));

        return this;
    }
}
