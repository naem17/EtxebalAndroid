package com.example.admin1.etxebalmovil.model.json;

public final class JSONTag {
    public static final String TAG_DATA = "data";
    public static final String TAG_CURRENT_USER = "user";
    public static final String TAG_ERROR = "error";
    public static final String TAG_DUAL = "DUAL";

    public final class Lodging {
        public static final String TAG_LODGING = "Alojamiento";

        public static final String TAG_FIRMA = "Firma";
        public static final String TAG_NAME = "Nombre";
        public static final String TAG_DESCRIPTION = "Descripcion";
        public static final String TAG_ADDRES = "Direccion";
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

    public final class Usuario {
        public static final String TAG_USER = "Usuarios";

        public static final String TAG_PERFIL = "PERFIL";
        public static final String TAG_EMAIL = "EMAIL";
        public static final String TAG_NAME = "NOMBRE";
        public static final String TAG_PASSWORD = "CONTRASENIA";
        public static final String TAG_LASTNAME = "APELLIDOS";
        public static final String TAG_NICK = "NOMBRE_USUARIO";
    }

    public final class PostCode {
        public static final String TAG_POSTCODE = "CodigoPostal";

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
