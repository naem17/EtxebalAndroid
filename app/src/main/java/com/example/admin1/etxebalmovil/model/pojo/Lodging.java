package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Comparator;

public class Lodging implements DatabaseObject, Serializable {
    private String mCode;
    private String mName;
    private String mDescription;
    private String mAddres;
    private String mMark;
    private String mEmail;
    private String mWeb;
    private String mPhone;
    private int mCapacity;
    private PostCode mPostCode;
    private double mLatitude;
    private double mLongitude;
    private String mType;
    private String mFriendlyURL;
    private String mZipFile;

    public static final String TYPE_LODGING = "Aterpetxeak";
    public static final String TYPE_CAMPING = "Kanpinak";
    public static final String TYPE_RURAL = "Landetxeak";
    public static final String TYPE_AGROTURISM = "Nekazaritza-turismoak";

    public static Comparator<Lodging> COMPARE_BY_NAME = new Comparator<Lodging>() {
        @Override
        public int compare(Lodging o1, Lodging o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAddres() {
        return mAddres;
    }

    public void setAddres(String addres) {
        mAddres = addres;
    }

    public String getMark() {
        return mMark;
    }

    public void setMark(String mark) {
        mMark = mark;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getWeb() {
        return mWeb;
    }

    public void setWeb(String web) {
        mWeb = web;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public void setCapacity(int capacity) {
        mCapacity = capacity;
    }

    public PostCode getPostCode() {
        return mPostCode;
    }

    public void setPostCode(PostCode postCode) {
        mPostCode = postCode;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getFriendlyURL() {
        return mFriendlyURL;
    }

    public void setFriendlyURL(String friendlyURL) {
        mFriendlyURL = friendlyURL;
    }

    public String getZipFile() {
        return mZipFile;
    }

    public void setZipFile(String zipFile) {
        mZipFile = zipFile;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setAddres(json.getString(JSONTag.Lodging.TAG_ADDRES));
        this.setCapacity(json.getInt(JSONTag.Lodging.TAG_CAPACITY));
        this.setCode(json.getString(JSONTag.Lodging.TAG_ID));
        this.setDescription(json.getString(JSONTag.Lodging.TAG_DESCRIPTION));
        this.setEmail(json.getString(JSONTag.Lodging.TAG_EMAIL));
        this.setFriendlyURL(json.getString(JSONTag.Lodging.TAG_FRIENDLY_URL));
        this.setLatitude(json.getDouble(JSONTag.Lodging.TAG_LATITUDE));
        this.setLongitude(json.getDouble(JSONTag.Lodging.TAG_LONGITUDE));
        this.setMark(json.getString(JSONTag.Lodging.TAG_MARK));
        this.setName(json.getString(JSONTag.Lodging.TAG_NAME));
        this.setPhone(json.getString(JSONTag.Lodging.TAG_PHONE));
        this.setWeb(json.getString(JSONTag.Lodging.TAG_WEB));
        this.setZipFile(json.getString(JSONTag.Lodging.TAG_ZIPFILE));
        this.setType(json.getString(JSONTag.Lodging.TAG_TYPE));
        String postCode = json.getString(JSONTag.Lodging.TAG_POST_CODE);
        String cityCode = json.getString(JSONTag.Lodging.TAG_CITY_CODE);
        PostCode p = SessionDataController.getInstance().getPostCode(postCode, cityCode);
        this.setPostCode(p);

        return this;
    }
}