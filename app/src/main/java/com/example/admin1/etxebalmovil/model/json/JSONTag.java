package com.example.admin1.etxebalmovil.model.json;

public final class JSONTag {
    public static final String TAG_DATA = "data";
    public static final String TAG_CURRENT_USER = "user";
    public static final String TAG_ERROR = "error";
    public static final String TAG_DUAL = "DUAL";

    public final class Alojamiento {
        public static final String TAG_ALOJAMIENTO = "Alojamiento";

        public static final String TAG_FIRMA = "FIRMA";
        public static final String TAG_NAME = "NOMBRE";
        public static final String TAG_DESCRPCION_ABREVIADA = "DESCRIPCION_ABREVIADA";
        public static final String TAG_DESCRPCION_ABREVIADA_EUSKERA = "DESCRIPCION_ABREVIADA_EUSKERA";
        public static final String TAG_DESCRIPCION = "DESCRIPCION";
        public static final String TAG_DESCRIPCION_EUSKERA = "DESCRIPCION_EUSKERA";
        public static final String TAG_TELEFONO = "TELEFONO";
        public static final String TAG_DIRECCION = "DIRECCION";
        public static final String TAG_CALIDAD = "CALIDAD_ASEGURADA";
        public static final String TAG_EMAIL = "EMAIL";
        public static final String TAG_CLUB = "CLUB";
        public static final String TAG_RESTAURANTE = "RESTAURANTE";
        public static final String TAG_AUROCARAVANA = "AUTOCARAVANA";
        public static final String TAG_TIENDA = "TIENDA";
        public static final String TAG_CAPACIDAD = "CAPACIDAD";
        public static final String TAG_GASTRONOMICO = "TAG_GASTRONOMICO";
        public static final String TAG_SURFING = "SURFING";
        public static final String TAG_COORDENADAS = "COORDENADAS";
        public static final String TAG_ = "SURFING";
        public static final String TAG_SURFING = "SURFING";
        public static final String TAG_SURFING = "SURFING";
    }

    public final class Usuario {
        public static final String TAG_USER = "Usuarios";

        public static final String TAG_PERFIL = "PERFIL";
        public static final String TAG_EMAIL = "EMAIL";
        public static final String TAG_NAME = "NOMBRE";
        public static final String TAG_PASSWORD = "CONTRASENIA";
        public static final String TAG_LASTNAME = "APELLIDOS";
        public static final String TAG_NICK = "NOMBRE_USUARIO";
        public static final String TAG_TELEFONO = "TELEFONO";
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
