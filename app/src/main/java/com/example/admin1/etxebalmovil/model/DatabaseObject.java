package com.example.admin1.etxebalmovil.model;

import org.json.JSONException;
import org.json.JSONObject;

public interface DatabaseObject {
    DatabaseObject fromJSON(JSONObject json) throws JSONException;
}
