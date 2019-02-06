package com.example.admin1.etxebalmovil.model.json;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Alojamiento;
import com.example.admin1.etxebalmovil.model.pojo.Categoria;
import com.example.admin1.etxebalmovil.model.pojo.CodigoPostal;
import com.example.admin1.etxebalmovil.model.pojo.Municipio;
import com.example.admin1.etxebalmovil.model.pojo.Provincia;
import com.example.admin1.etxebalmovil.model.pojo.Relacion;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;
import com.example.admin1.etxebalmovil.model.pojo.Tipo;
import com.example.admin1.etxebalmovil.model.pojo.TipoEuskera;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.admin1.etxebalmovil.model.json.JSONTag.TAG_CURRENT_USER;
import static com.example.admin1.etxebalmovil.model.json.JSONTag.TAG_DATA;
import static com.example.admin1.etxebalmovil.model.json.JSONTag.TAG_ERROR;

public abstract class JSONController {
    /* ///////// START OF COMMON CONFIG ///////// */
    private static final String DB = "reto_gp2";
    /* *** Error Codes *** */
    public static final byte NO_ERROR = 0;
    public static final byte OTHER_ERROR = 1;
    public static final byte INPUT_ERROR = 2;

    /* ///////// START OF GET CONFIG ///////// */
    /* *** URL Constants *** */
    private static final String LINK = "&";
    private static final String DATA_TABLE = "data_table[]=";
    /* *** URL Configuration *** */
    private static final String HOST_GET = "https://kasserver.synology.me/etazi/get_json.php?";
    private static final String USER_FIELD = "NOMBRE_USUARIO";
    private static final String USER_TABLE = "USUARIOS";
    private static final String PASS_FIELD = "CONTRASENIA";
    private static final String HASH_MODE = "md5";
    /* *** Database Admin User *** */
    private static final String USERNAME = "AndroidDummy";
    private static final String PASSWORD = "dummy";
    /* *** Default URLs *** */
    private static final String BASE_URL_GET = HOST_GET + "db=" + DB
            + LINK + "users_table=" + USER_TABLE
            + LINK + "username_field=" + USER_FIELD
            + LINK + "password_field=" + PASS_FIELD;
    private static final String ADMIN_URL_GET = BASE_URL_GET
            + LINK + "username=" + USERNAME
            + LINK + "password=" + PASSWORD
            + LINK + "hash=" + HASH_MODE;
    private static String mUserURL;

    /* ///////// START OF SET CONFIG ///////// */
    private static final String BASE_URL_SET = "https://kasserver.synology.me/etazi/set_json.php";
    //    private static final String BASE_URL_SET = HOST_SET + "json=";
    private static final String DB_USER = "gp2";
    private static final String DB_PASSWORD = "NuG7FqwibR1ZAuKy";

    /* ///////// END OF CONFIGS ///////// */

    public static byte setData(String jsonString) {
        String result = doRequestPOST(jsonString);

        try {
            JSONObject json = new JSONObject(result);
            if (json.has(TAG_ERROR)) {
                return OTHER_ERROR;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return OTHER_ERROR;
        }
        return NO_ERROR;
    }

    public static byte getData() {
        String result = doRequestGET((ADMIN_URL_GET + LINK + DATA_TABLE + JSONTag.Alojamiento.TAG_ALOJAMIENTO
                + LINK + DATA_TABLE + JSONTag.Categoria.TAG_CATEGORIA
                + LINK + DATA_TABLE + JSONTag.Codigo_Postal.TAG_POST_CODE
                + LINK + DATA_TABLE + JSONTag.Municipio.TAG_MUNICIPIO
                + LINK + DATA_TABLE + JSONTag.Provincia.TAG_PROVINCIA
                + LINK + DATA_TABLE + JSONTag.Relacion.TAG_RELACION
                + LINK + DATA_TABLE + JSONTag.Reserva.TAG_RESERVA
                + LINK + DATA_TABLE + JSONTag.Tipo.TAG_TIPO
                + LINK + DATA_TABLE + JSONTag.Tipo_Euskera.TAG_TIPO_EUSKERA
        ));

        try {
            SessionDataController controller = SessionDataController.getInstance();
            HashMap<String, List<DatabaseObject>> data;

            // Se meten las relaciones
            data = parseJSON(result, JSONTag.Relacion.TAG_RELACION);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Relacion> relaciones = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Relacion.TAG_RELACION)) {
                relaciones.add((Relacion) o);
            }

            controller.setRelaciones(relaciones);

            //Se meten las provincias
            data = parseJSON(result, JSONTag.Provincia.TAG_PROVINCIA);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Provincia> provincias = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Provincia.TAG_PROVINCIA)) {
                provincias.add((Provincia) o);
            }

            controller.setProvincias(provincias);

            //Se meten los municipios
            data = parseJSON(result, JSONTag.Municipio.TAG_MUNICIPIO);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Municipio> municipios = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Municipio.TAG_MUNICIPIO)) {
                municipios.add((Municipio) o);
            }

            controller.setMunicipios(municipios);

            //Se meten los codigos postales
            data = parseJSON(result, JSONTag.Codigo_Postal.TAG_POST_CODE);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<CodigoPostal> codigosPostales = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Codigo_Postal.TAG_POST_CODE)) {
                codigosPostales.add((CodigoPostal) o);
            }

            controller.setCodigosPostales(codigosPostales);

            //Se meten las categorias
            data = parseJSON(result, JSONTag.Categoria.TAG_CATEGORIA);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Categoria> categorias = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Categoria.TAG_CATEGORIA)) {
                categorias.add((Categoria) o);
            }

            controller.setCategorias(categorias);

            //Se meten los tipos
            data = parseJSON(result, JSONTag.Tipo.TAG_TIPO);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Tipo> tipos = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Tipo.TAG_TIPO)) {
                tipos.add((Tipo) o);
            }

            controller.setTipos(tipos);

            //Se meten los tipos euskera
            data = parseJSON(result, JSONTag.Tipo_Euskera.TAG_TIPO_EUSKERA);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<TipoEuskera> tiposEuskera = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Tipo_Euskera.TAG_TIPO_EUSKERA)) {
                tiposEuskera.add((TipoEuskera) o);
            }

            controller.setTiposEuskera(tiposEuskera);

            // Se meten los alojamientos
            data = parseJSON(result, JSONTag.Alojamiento.TAG_ALOJAMIENTO);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Alojamiento> alojamientos = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Alojamiento.TAG_ALOJAMIENTO)) {
                alojamientos.add((Alojamiento) o);
            }

            controller.setAlojamientos(alojamientos);
        } catch (JSONException e) {
            e.printStackTrace();
            return OTHER_ERROR;
        }
        return NO_ERROR;
    }

    public static byte logInUser(String username, String password) {
        mUserURL = BASE_URL_GET + LINK + "username=" + username
                + LINK + "password=" + password
                + LINK + "hash=" + HASH_MODE;
        /*https://kasserver.synology.me/etazi/get_json.php?db=reto_gp2&users_table=USUARIOS&username_field
        =NOMBRE_USUARIO&password_field=CONTRASENIA&username=a&password=a&hash=md5&data_table[]]=DUAL&get_user=true*/

        String result = doRequestGET((mUserURL + LINK + DATA_TABLE + JSONTag.TAG_DUAL
                + LINK + "get_user=true"));

        try {
            SessionDataController controller = SessionDataController.getInstance();
            HashMap<String, List<DatabaseObject>> data = parseJSON(result);

            // Check for errors
            if (data.containsKey(TAG_ERROR)) {
                return INPUT_ERROR;
            }
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            // Insert current user
            Usuario usuario = (Usuario) data.get(TAG_CURRENT_USER).get(0);
            controller.setUsuario(usuario);

            // Insert reserves
            /*if (loadReserves() == OTHER_ERROR) {
                return OTHER_ERROR;
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
            return OTHER_ERROR;
        }
        return NO_ERROR;
    }

    private static byte loadReserves() {
        String result = doRequestGET(mUserURL + LINK + DATA_TABLE + JSONTag.Reserva.TAG_RESERVA);

        try {
            SessionDataController controller = SessionDataController.getInstance();
            HashMap<String, List<DatabaseObject>> data = parseJSON(result, JSONTag.Reserva.TAG_RESERVA);
            List<Reserva> reservas = new ArrayList<>();
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            // Insert reserves
            // Select only current user's reserves
            List<DatabaseObject> list = data.get(JSONTag.Reserva.TAG_RESERVA);
            list.sort(Reserva.COMPARE_BY_USER); // Sort by user
            String user = controller.getUsuario().getNick();
            boolean userFound = false;
            for (int i = 0; i < list.size(); i++) {
                if (((Reserva) list.get(i)).getmNombreCliente().equals(user)) {
                    reservas.add((Reserva) list.get(i));
                    userFound = true;
                } else if (userFound) { // User already found. No more reserves for the current user
                    break;
                }
            }
/*            for (DatabaseObject r : data.get(JSONTag.Reserve.TAG_RESERVE)) {
                if (((Reserve) r).getUserNick().equals(controller.getUser().getNick())) {
                    reserves.add((Reserve) r);
                }
            }*/

            controller.setReservas(reservas);
        } catch (JSONException e) {
            e.printStackTrace();
            return OTHER_ERROR;
        }
        return NO_ERROR;
    }

    private static String doRequestGET(String s) {
        String response = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(s).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();

            String buffer;
            while ((buffer = br.readLine()) != null) {
                builder.append(buffer);
            }

            response = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static String doRequestPOST(String s) {
        String response = null;
        s = "json=".concat(s);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(BASE_URL_SET).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();

            String buffer;
            while ((buffer = br.readLine()) != null) {
                builder.append(buffer);
            }

            response = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static HashMap<String, List<DatabaseObject>> parseJSON(String jsonString, String... dataTables) throws JSONException {
        HashMap<String, List<DatabaseObject>> fullData = new HashMap<>();
        List<DatabaseObject> data;
        DatabaseObject object;

        JSONObject json = new JSONObject(jsonString);
        JSONArray jsonData;

        byte errorCode = handleError(json);
        if (errorCode == NO_ERROR) { // If there has been no errors in the doRequest
            for (String tag : dataTables) { // Each data table requested
                boolean validTag = true;
                jsonData = json.getJSONObject(TAG_DATA).getJSONArray(tag);

                // Each object inside current tag
                data = new ArrayList<>();
                for (int i = 0; i < jsonData.length() && validTag; i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    switch (tag) {
                        case JSONTag.Alojamiento.TAG_ALOJAMIENTO:
                            object = new Alojamiento();
                            break;
                        case JSONTag.Reserva.TAG_RESERVA:
                            object = new Reserva();
                            break;
                        case JSONTag.Codigo_Postal.TAG_POST_CODE:
                            object = new CodigoPostal();
                            break;
                        case JSONTag.Usuario.TAG_USER:
                            object = new Usuario();
                            break;
                        case JSONTag.Categoria.TAG_CATEGORIA:
                            object = new Categoria();
                            break;
                        case JSONTag.Municipio.TAG_MUNICIPIO:
                            object = new Municipio();
                            break;
                        case JSONTag.Provincia.TAG_PROVINCIA:
                            object = new Provincia();
                            break;
                        case JSONTag.Relacion.TAG_RELACION:
                            object = new Relacion();
                            break;
                        case JSONTag.Tipo.TAG_TIPO:
                            object = new Tipo();
                            break;
                        case JSONTag.Tipo_Euskera.TAG_TIPO_EUSKERA:
                            object = new TipoEuskera();
                            break;
                        default:
                            validTag = false;
                            continue;
                    }
                    data.add(object.fromJSON(jsonObject));
                }
                if (!data.isEmpty()) {
                    fullData.put(tag, data);
                }
            }

            // Check if there is get_user in doRequest
            if (json.has(TAG_CURRENT_USER)) {
                JSONObject jsonObject = json.getJSONObject(TAG_CURRENT_USER);
                object = new Usuario();
                data = new ArrayList<>();
                data.add(object.fromJSON(jsonObject));
                fullData.put(TAG_CURRENT_USER, data);
            }

        } else if (errorCode == INPUT_ERROR) { // User input error
            return fullData;
        }

        return fullData;
    }

    private static byte handleError(JSONObject json) throws JSONException {
        if (!json.has(TAG_ERROR)) {
            return NO_ERROR;
        }

        final String USER_ERROR = "Invalid username or password";
        String error = (String) json.get(TAG_ERROR);
        System.out.println(error);

        if (USER_ERROR.equalsIgnoreCase(error)) {
            return INPUT_ERROR;
        }

        return OTHER_ERROR;
    }

/*    public static void test() {
        try {
            String url = BASE_URL_GET + "&username=c&password=a&hash=md5&data_table[]=PostaKodeak";
            JSONObject json = new JSONObject(doRequest(url));
            JSONArray data;
            List<PostCode> objects = new ArrayList<>();

            if (!json.has(TAG_ERROR)) {
                data = json.getJSONObject(TAG_DATA).getJSONArray("PostaKodeak");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject object = data.getJSONObject(i);
                    PostCode postCode = new PostCode();
                    objects.add((PostCode) postCode.fromJSON(object));
                }

            }

            SessionDataController.getInstance().setPostCodes(objects);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public static String getDB() {
        return DB;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}
