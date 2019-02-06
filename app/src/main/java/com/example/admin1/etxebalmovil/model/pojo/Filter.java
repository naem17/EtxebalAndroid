package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.Util;

import java.io.Serializable;

public class Filter implements Serializable {
    private int mCapacity;
    private String mPostCode;
    private String[] mCounty;
    private String[] mCity;
    private String mMark;
    private String[] mType;
    private String mSearchField;
    private int mRadius;

    public int getCapacity() {
        return mCapacity;
    }

    public String getPostCode() {
        return mPostCode;
    }

    public String[] getCounty() {
        return mCounty;
    }

    public String[] getCity() {
        return mCity;
    }

    public String getMark() {
        return mMark;
    }

    public String[] getType() {
        return mType;
    }

    public String getSearchField() {
        return mSearchField;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0)
            return;
        mCapacity = capacity;
    }

    public void setPostCode(String postCode) {
        mPostCode = postCode;
    }

    public void setCounty(String[] county) {
        mCounty = county;
    }

    public void setCity(String[] city) {
        mCity = city;
    }

    public void setMark(String mark) {
        mMark = mark;
    }

    public void setType(String[] type) {
        mType = type;
    }

    public void setSearchField(String searchField) {
        mSearchField = searchField;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public boolean check(Alojamiento alojamiento) {
        if (0 < mRadius) {
            Util.Position target = new Util.Position(alojamiento.getLatitude(), alojamiento.getLongitude());
            if (mRadius >= Util.calculateDistance(SessionDataController.getInstance().getCurrentPos(), target)) {
                return true;
            }
            return false;
        }

        if (mSearchField != null && !mSearchField.isEmpty()) {
            if (!lodging.getName().toLowerCase().contains(mSearchField.toLowerCase())
                    && !lodging.getDescription().toLowerCase().contains(mSearchField.toLowerCase())) {
                return false;
            }
        }
        if (lodging.getCapacity() < mCapacity) {
            return false;
        }
        if (mPostCode != null && mPostCode.length() > 0
                && !lodging.getPostCode().getPostCode().equals(mPostCode)) {
            return false;
        }
        if (mCounty != null && mCounty.length > 0
                && !arrayContains(mCounty, lodging.getPostCode().getCountyName())) {
            return false;
        }
        if (mCity != null && mCity.length > 0
                && !arrayContains(mCity, lodging.getPostCode().getCityName())) {
            return false;
        }
        if (mMark != null && !mMark.isEmpty()
                && !mMark.toLowerCase().contains(lodging.getMark().toLowerCase())) {
            return false;
        }
        if (mType != null && mType.length > 0
                && !arrayContains(mType, lodging.getType().toLowerCase())) {
            return false;
        }

        return true;
    }

    private boolean arrayContains(String[] array, String s) {
        for (String str : array) {
            if (str.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }

}
