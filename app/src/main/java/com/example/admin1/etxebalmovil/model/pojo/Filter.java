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
            Util.Position target = new Util.Position(alojamiento.getCoordenadas());
            if (mRadius >= Util.calculateDistance(SessionDataController.getInstance().getPosicionActual(), target)) {
                return true;
            }
            return false;
        }

        if (mSearchField != null && !mSearchField.isEmpty()) {
            if (!alojamiento.getNombre().toLowerCase().contains(mSearchField.toLowerCase())
                    && !alojamiento.getDescripcion().toLowerCase().contains(mSearchField.toLowerCase())) {
                return false;
            }
        }
        if (alojamiento.getCapacidad() < mCapacity) {
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
