package com.example.admin1.etxebalmovil.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.admin1.etxebalmovil.model.json.JSONBuilder;
import com.example.admin1.etxebalmovil.model.json.JSONController;
import com.example.admin1.etxebalmovil.model.pojo.Filter;
import com.example.admin1.etxebalmovil.model.pojo.Lodging;
import com.example.admin1.etxebalmovil.model.pojo.PostCode;
import com.example.admin1.etxebalmovil.model.pojo.Reserve;
import com.example.admin1.etxebalmovil.model.pojo.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de almacenar los datos de la sesion.
 * Permite cambiar el usuario y modificar la lista de reservas.
 */
public class SessionDataController {
    private static SessionDataController ourInstance;
    private User mUser;
    private Util.Position mCurrentPos;
    private List<Lodging> mLodgings;
    private List<Reserve> mReserves;
    private List<Reserve> mHistory;
    private List<PostCode> mPostCodes;
    private List<String> mCitiesInBizkaia;
    private List<String> mCitiesInAraba;
    private List<String> mCitiesInGipuzkoa;

    public static SessionDataController getInstance() {
        if (null == ourInstance) {
            ourInstance = new SessionDataController();
        }
        return ourInstance;
    }

    private SessionDataController() {
        mLodgings = new ArrayList<>();
        mHistory = new ArrayList<>();
        mReserves = new ArrayList<>();
        mCitiesInBizkaia = new ArrayList<>();
        mCitiesInAraba = new ArrayList<>();
        mCitiesInGipuzkoa = new ArrayList<>();
        mPostCodes = new ArrayList<>();
        mUser = new User();
    }

    public Lodging getLodging(String id) {
        for (Lodging lodging : mLodgings) {
            if (lodging.getCode().equals(id))
                return lodging;
        }
        return null;
    }

    public List<Lodging> getLodgings() {
        return mLodgings;
    }

    public List<Lodging> getLodgings(Filter filter) {
        List<Lodging> lodgings = new ArrayList<>();

        for (Lodging lodging : mLodgings) {
            if (filter.check(lodging)) {
                lodgings.add(lodging);
            }
        }

        return lodgings;
    }

    public boolean signUpUser(User user) {
        byte error = JSONController.setData(JSONBuilder.build(user));
        if (error == JSONController.NO_ERROR) {
            this.mUser = user;
            return true;
        }
        return false;
    }

    public boolean addReserve(Reserve reserve) {
        if (JSONController.setData(JSONBuilder.build(JSONBuilder.INSERT, reserve)) == JSONController.OTHER_ERROR) {
            return false;
        }
        mReserves.add(reserve);
        return true;
    }

    public boolean removeReserve(Reserve reserve) {
        if (JSONController.setData(JSONBuilder.build(JSONBuilder.DELETE, reserve)) == JSONController.OTHER_ERROR) {
            return false;
        }
        mReserves.remove(reserve);
        return true;
    }

    public boolean isReserved(String id) {
        for (Reserve reserve : mReserves) {
            if (reserve.getLodgingCode().equals(id))
                return true;
        }
        return false;
    }

    public boolean isInHistory(String id) {
        for (Reserve reserve : mHistory) {
            if (reserve.getLodgingCode().equals(id))
                return true;
        }
        return false;
    }

    public Reserve getReserve(String lodgingId) {
        for (Reserve r : mReserves) {
            if (r.getLodgingCode().equals(lodgingId))
                return r;
        }
        return null;
    }

    public PostCode getPostCode(String postCode, String cityCode) {
        for (PostCode p : mPostCodes) {
            if (postCode.equals(p.getPostCode()) && cityCode.equals(p.getCityCode())) {
                return p;
            }
        }
        return null;
    }

    public void setReserves(List<Reserve> reserves) {
        Date today = new Date(new java.util.Date().getTime());

        for (Reserve reserve : reserves) {
            if (reserve.getFinishDate().before(today)) {
                mHistory.add(reserve);
            } else {
                mReserves.add(reserve);
            }
        }
    }

    public void setLodgings(List<Lodging> lodgings) {
        mLodgings = lodgings;
        mLodgings.sort(Lodging.COMPARE_BY_NAME);
    }

    public void setPostCodes(List<PostCode> postCodes) {
        mPostCodes = postCodes;

        String code, city;
        for (PostCode postCode : postCodes) {
            code = postCode.getCountyCode();
            city = postCode.getCityName();
            switch (code) {
                case PostCode.COUNTY_CODE_BIZ:
                    if (mCitiesInBizkaia.contains(city)) break;
                    mCitiesInBizkaia.add(city);
                    break;
                case PostCode.COUNTY_CODE_ARA:
                    if (mCitiesInAraba.contains(city)) break;
                    mCitiesInAraba.add(city);
                    break;
                case PostCode.COUNTY_CODE_GIP:
                    if (mCitiesInGipuzkoa.contains(city)) break;
                    mCitiesInGipuzkoa.add(city);
                    break;
                default:
                    break;
            }
        }
    }

    public void setCurrentPos(Context context) {
        if (context == null) {
            mCurrentPos = null;
            return;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> probiders = lm.getProviders(true);
        Location currentLocation = null;
        Util.Position currentPosition = new Util.Position();

        // Check permisions
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mCurrentPos = null;
            return;
        }
        for (String probider : probiders) {
            currentLocation = lm.getLastKnownLocation(probider);
            if (currentLocation != null)
                break;
        }
        if (currentLocation != null) {
            currentPosition.latitude = currentLocation.getLatitude();
            currentPosition.longitude = currentLocation.getLongitude();
        }

        mCurrentPos = currentPosition;
    }

    public Util.Position getCurrentPos() {
        return mCurrentPos;
    }

    public List<PostCode> getPostCodes() {
        return mPostCodes;
    }

    public List<Reserve> getReserves() {
        return mReserves;
    }

    public List<Reserve> getHistory() {
        return mHistory;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<String> getCitiesInBizkaia() {
        return mCitiesInBizkaia;
    }

    public List<String> getCitiesInAraba() {
        return mCitiesInAraba;
    }

    public List<String> getCitiesInGipuzkoa() {
        return mCitiesInGipuzkoa;
    }
}
