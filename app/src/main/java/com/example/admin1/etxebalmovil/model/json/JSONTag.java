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
        public static final String TAG_CODIGO_TIPOS = "CODIGO_TIPOS";
        public static final String TAG_CODIGO_TIPOS_EUSKERA = "CODIGO_TIPOS_EUSKERA";
        public static final String TAG_CODIGO_CATEGORIAS = "CODIGO_CATEGORIAS";
        public static final String TAG_ID_RELACIONES = "ID_RELACIONES";
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

    public final class Provincia{
        public static final String TAG_PROVINCIA = "Provincia";

        public static final String TAG_CODIGO = "CODIGO";
        public static final String TAG_PROVINCUA = "PROVINCUA";
    }

    public final class Municipio{
        public static final String TAG_MUNICIPIO = "Municipio";

        public static final String TAG_CODIGO = "CODIGO";
        public static final String TAG_MUNICIPE = "MUNICIPIO";
        public static final String TAG_INDICE = "INDICE";
    }

    public final class Tipo_Euskera{
        public static final String TAG_TIPO_EUSKERA = "Tipo_Euskera";

        public static final String TAG_CODIGO = "CODIGO";
        public static final String TAG_TYPE_EUSKERA = "TIPO_EUSKERA";
    }

    public final class Codigo_Postal{
        public static final String TAG_POST_CODE = "Codigo_Postal";

        public static final String TAG_CODIGO_POSTAL = "CODIGO_POSTAL";
    }

    public final class Categoria{
        public static final String TAG_CATEGORIA = "Categoria";

        public static final String TAG_CODIGO = "CODIGO";
        public static final String TAG_CATEGORY = "CATEGORIA";
    }

    public final class Tipo {
        public static final String TAG_TIPO = "Tipo";

        public static final String TAG_CODIGO = "CODIGO";
        public static final String TAG_TYPE = "TIPO";
    }

    public final class Reserva {
        public static final String TAG_RESERVA = "Reservas";

        public static final String TAG_ID = "ID";
        public static final String TAG_NOMBRE_RESERVA = "NOMBRE_RESERVA";
        public static final String TAG_NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
        public static final String TAG_FIRMA_ALOJAMIENTO = "FIRMA_ALOJAMIENTO";
        public static final String TAG_FECHA_INICIO = "FECHA_INICIO";
        public static final String TAG_FECHA_FIN = "FECHA_FIN";
        public static final String TAG_CANTIDAD_PERSONAS = "CANTIDAD_PERSONAS";
    }
}
