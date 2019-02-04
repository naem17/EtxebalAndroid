package com.example.admin1.etxebalmovil.model.json;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Lodging;
import com.example.admin1.etxebalmovil.model.pojo.PostCode;
import com.example.admin1.etxebalmovil.model.pojo.Reserve;
import com.example.admin1.etxebalmovil.model.pojo.User;

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
    private static final String DB = "reto_gp1";
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
    private static final String USER_FIELD = "Nick";
    private static final String USER_TABLE = "Erabiltzaileak";
    private static final String PASS_FIELD = "Pasahitza";
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
    private static final String DB_USER = "gp1";
    private static final String DB_PASSWORD = "ZBlrkPWaSdVs5F3l";

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
        String result = doRequestGET((ADMIN_URL_GET + LINK + DATA_TABLE + JSONTag.Lodging.TAG_LODGING
                + LINK + DATA_TABLE + JSONTag.PostCode.TAG_POSTCODE));

        try {
            SessionDataController controller = SessionDataController.getInstance();
            HashMap<String, List<DatabaseObject>> data;

            // Insert postcodes first to be able to create lodgings
            data = parseJSON(result, JSONTag.PostCode.TAG_POSTCODE);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<PostCode> postCodes = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.PostCode.TAG_POSTCODE)) {
                postCodes.add((PostCode) o);
            }

            controller.setPostCodes(postCodes);
            // Insert lodgings
            data = parseJSON(result, JSONTag.Lodging.TAG_LODGING);
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            List<Lodging> lodgings = new ArrayList<>();
            for (DatabaseObject o : data.get(JSONTag.Lodging.TAG_LODGING)) {
                lodgings.add((Lodging) o);
            }

            controller.setLodgings(lodgings);
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
            User user = (User) data.get(TAG_CURRENT_USER).get(0);
            if (user.getPermision() > 0) {
                return INPUT_ERROR;
            }
            controller.setUser(user);

            // Insert reserves
            if (loadReserves() == OTHER_ERROR) {
                return OTHER_ERROR;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return OTHER_ERROR;
        }
        return NO_ERROR;
    }

    private static byte loadReserves() {
        String result = doRequestGET(mUserURL + LINK + DATA_TABLE + JSONTag.Reserve.TAG_RESERVE);

        try {
            SessionDataController controller = SessionDataController.getInstance();
            HashMap<String, List<DatabaseObject>> data = parseJSON(result, JSONTag.Reserve.TAG_RESERVE);
            List<Reserve> reserves = new ArrayList<>();
            // Check for errors
            if (data.isEmpty()) {
                return OTHER_ERROR;
            }

            // Insert reserves
            // Select only current user's reserves
            List<DatabaseObject> list = data.get(JSONTag.Reserve.TAG_RESERVE);
            list.sort(Reserve.COMPARE_BY_USER); // Sort by user
            String user = controller.getUser().getNick();
            boolean userFound = false;
            for (int i = 0; i < list.size(); i++) {
                if (((Reserve) list.get(i)).getUserNick().equals(user)) {
                    reserves.add((Reserve) list.get(i));
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

            controller.setReserves(reserves);
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
                        case JSONTag.Lodging.TAG_LODGING:
                            object = new Lodging();
                            break;
                        case JSONTag.Reserve.TAG_RESERVE:
                            object = new Reserve();
                            break;
                        case JSONTag.PostCode.TAG_POSTCODE:
                            object = new PostCode();
                            break;
                        case JSONTag.User.TAG_USER:
                            object = new User();
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
                object = new User();
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