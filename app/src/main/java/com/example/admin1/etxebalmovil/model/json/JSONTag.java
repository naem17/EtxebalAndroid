package com.example.admin1.etxebalmovil.model.json;

public final class JSONTag {
    public static final String TAG_DATA = "data";
    public static final String TAG_CURRENT_USER = "user";
    public static final String TAG_ERROR = "error";
    public static final String TAG_DUAL = "Dual";

    public final class Lodging {
        public static final String TAG_LODGING = "Alojamendua";

        public static final String TAG_NAME = "Alojamendu_izena";
        public static final String TAG_DESCRIPTION = "Deskribapena";
        public static final String TAG_ADDRES = "Helbidea";
        public static final String TAG_MARK = "Marka";
        public static final String TAG_EMAIL = "Email";
        public static final String TAG_WEB = "Web";
        public static final String TAG_PHONE = "Telefonoa";
        public static final String TAG_ID = "id_kodea";
        public static final String TAG_CAPACITY = "Capacity";
        public static final String TAG_POST_CODE = "Posta_Kodea";
        public static final String TAG_CITY_CODE = "Herri_kodea";
        public static final String TAG_LATITUDE = "Latitude";
        public static final String TAG_LONGITUDE = "Longitude";
        public static final String TAG_TYPE = "Mota";
        public static final String TAG_FRIENDLY_URL = "friendlyUrl";
        public static final String TAG_ZIPFILE = "ZipFile";
    }

    public final class User {
        public static final String TAG_USER = "Erabiltzaileak";

        public static final String TAG_PERMISION = "Baimenak";
        public static final String TAG_EMAIL = "Email";
        public static final String TAG_NAME = "Izena";
        public static final String TAG_PASSWORD = "Pasahitza";
        public static final String TAG_LASTNAME = "Abizenak";
        public static final String TAG_NICK = "Nick";
    }

    public final class PostCode {
        public static final String TAG_POSTCODE = "PostaKodeak";

        public static final String TAG_COUNTY_CODE = "Lurralde_kodea";
        public static final String TAG_COUNTY_NAME = "Lurralde_izena";
        public static final String TAG_CITY_NAME = "Herri_izena";
        public static final String TAG_CITY_CODE = "Herri_kodea";
        public static final String TAG_POST_CODE = "Posta_Kodea";
    }

    public final class Reserve {
        public static final String TAG_RESERVE = "Erreserbak";

        public static final String TAG_LODGING = "id_kodea";
        public static final String TAG_USER = "Nick";
        public static final String TAG_START = "hasiera_data";
        public static final String TAG_END = "amaiera_data";
    }
}