package com.example.admin1.etxebalmovil.model.json;

import com.example.admin1.etxebalmovil.model.Util;
import com.example.admin1.etxebalmovil.model.pojo.Reserve;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JSONBuilder {
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static String build(String action, Reserve reserve) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> jsonmap = new HashMap<>();
            final Map<String, Object> table;

            jsonmap.put("db", JSONController.getDB());
            jsonmap.put("user", JSONController.getDbUser());
            jsonmap.put("password", JSONController.getDbPassword());

            switch (action) {
                case INSERT:
                    table = buildInsert(reserve);
                    break;
                case UPDATE:
                    table = buildUpdate(reserve);
                    break;
                case DELETE:
                    table = buildDelete(reserve);
                    break;
                default:
                    return null;
            }
            jsonmap.put("tables", new HashMap<String, Object>(){{put(JSONTag.Reserva.TAG_RESERVA, table);}});
            // convert map to JSON string
            json = mapper.writeValueAsString(jsonmap);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static String build(Usuario usuario) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> jsonmap = new HashMap<>();
            final Map<String, Object> table;

            jsonmap.put("db", JSONController.getDB());
            jsonmap.put("user", JSONController.getDbUser());
            jsonmap.put("password", JSONController.getDbPassword());

            table = buildInsert(usuario);

            jsonmap.put("tables", new HashMap<String, Object>(){{put(JSONTag.Usuario.TAG_USER, table);}});
            // convert map to JSON string
            json = mapper.writeValueAsString(jsonmap);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private static Map<String, Object> buildInsert(Usuario usuario) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> value = new HashMap<>();
        List<Object> values = new ArrayList<>();

        value.put("NOMBRE_USUARIO", usuario.getNick());
        value.put("CONTRASENIA", Util.md5(usuario.getPassword()));
        value.put("NOMBRE", usuario.getName());
        value.put("APELLIDOS", usuario.getLast());
        value.put("EMAIL", usuario.getEmail());
        value.put("TELEFONO", usuario.getTlf());
        value.put("PERFIL", usuario.getPerfil());
        values.add(value);

        table.put("action", INSERT);
        table.put("values", values);

        return table;
    }

    private static Map<String, Object> buildInsert(Reserve reserve) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> value = new HashMap<>();
        List<Object> values = new ArrayList<>();

        value.put("id_kodea", reserve.getLodgingCode());
        value.put("Nick", reserve.getUserNick());
        value.put("hasiera_data", reserve.getStartDate());
        value.put("amaiera_data", reserve.getFinishDate());
        values.add(value);

        table.put("action", INSERT);
        table.put("values", values);

        return table;
    }

    private static Map<String, Object> buildDelete(Reserve reserve) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> where = new HashMap<>();
        List<Object> wheres = new ArrayList<>();

        where.put("field", JSONTag.Reserva.TAG_NOMBRE_CLIENTE);
        where.put("value", reserve.getUserNick());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO);
        where.put("value", reserve.getLodgingCode());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FECHA_INICIO);
        where.put("value", reserve.getStartDate().toString());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FECHA_FIN);
        where.put("value", reserve.getFinishDate().toString());
        wheres.add(where);

        table.put("action", DELETE);
        table.put("where", wheres);

        return table;
    }

    private static Map<String, Object> buildUpdate(Reserve reserve) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();

        table.put("action", UPDATE);
        // Add value
        map.put(JSONTag.Reserva.TAG_FECHA_INICIO, reserve.getStartDate());
        map.put(JSONTag.Reserva.TAG_FECHA_FIN, reserve.getFinishDate());
        list.add(map);

        table.put("values", list);
        // Add where
        map.put("field", JSONTag.Reserva.TAG_NOMBRE_CLIENTE);
        map.put("value", reserve.getUserNick());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO);
        map.put("value", reserve.getLodgingCode());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_FECHA_INICIO);
        map.put("value", reserve.getStartDate().toString());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_FECHA_FIN);
        map.put("value", reserve.getFinishDate().toString());
        list.add(map);

        table.put("where", list);

        return table;
    }

}