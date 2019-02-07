package com.example.admin1.etxebalmovil.model.json;

import com.example.admin1.etxebalmovil.model.Util;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;
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

    public static String build(String action, Reserva reserva) {
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
                    table = buildInsert(reserva);
                    break;
                case UPDATE:
                    table = buildUpdate(reserva);
                    break;
                case DELETE:
                    table = buildDelete(reserva);
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

        value.put(JSONTag.Usuario.TAG_NICK, usuario.getNick());
        value.put(JSONTag.Usuario.TAG_PASSWORD, Util.md5(usuario.getPassword()));
        value.put(JSONTag.Usuario.TAG_NAME, usuario.getName());
        value.put(JSONTag.Usuario.TAG_LASTNAME, usuario.getLast());
        value.put(JSONTag.Usuario.TAG_EMAIL, usuario.getEmail());
        value.put(JSONTag.Usuario.TAG_TELEFONO, usuario.getTlf());
        value.put(JSONTag.Usuario.TAG_PERFIL, usuario.getPerfil());
        values.add(value);

        table.put("action", INSERT);
        table.put("values", values);

        return table;
    }

    private static Map<String, Object> buildInsert(Reserva reserva) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> value = new HashMap<>();
        List<Object> values = new ArrayList<>();

        value.put(JSONTag.Reserva.TAG_NOMBRE_RESERVA, reserva.getmNombreReserva());
        value.put(JSONTag.Reserva.TAG_NOMBRE_CLIENTE, reserva.getmNombreCliente());
        value.put(JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO, reserva.getmFirmaAlojamiento());
        value.put(JSONTag.Reserva.TAG_FECHA_INICIO, reserva.getmFechaInicio());
        value.put(JSONTag.Reserva.TAG_FECHA_FIN, reserva.getmFechaFin());
        value.put(JSONTag.Reserva.TAG_CANTIDAD_PERSONAS, reserva.getmCantidadPersonas());
        values.add(value);

        table.put("action", INSERT);
        table.put("values", values);

        return table;
    }

    private static Map<String, Object> buildDelete(Reserva reserva) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> where = new HashMap<>();
        List<Object> wheres = new ArrayList<>();

        where.put("field", JSONTag.Reserva.TAG_NOMBRE_CLIENTE);
        where.put("value", reserva.getmNombreCliente());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO);
        where.put("value", reserva.getmFirmaAlojamiento());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FECHA_INICIO);
        where.put("value", reserva.getmFechaInicio().toString());
        wheres.add(where);
        where.clear();
        where.put("field", JSONTag.Reserva.TAG_FECHA_FIN);
        where.put("value", reserva.getmFechaFin().toString());
        wheres.add(where);

        table.put("action", DELETE);
        table.put("where", wheres);

        return table;
    }

    private static Map<String, Object> buildUpdate(Reserva reserva) {
        Map<String, Object> table = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();

        table.put("action", UPDATE);
        // Add value
        map.put(JSONTag.Reserva.TAG_FECHA_INICIO, reserva.getmFechaInicio());
        map.put(JSONTag.Reserva.TAG_FECHA_FIN, reserva.getmFechaFin());
        map.put(JSONTag.Reserva.TAG_CANTIDAD_PERSONAS, reserva.getmCantidadPersonas());
        list.add(map);

        table.put("values", list);
        // Add where
        map.put("field", JSONTag.Reserva.TAG_NOMBRE_CLIENTE);
        map.put("value", reserva.getmNombreCliente());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO);
        map.put("value", reserva.getmFirmaAlojamiento());
        list.add(map);
        map.clear();
        //TODO añadir fechas
        map.put("field", JSONTag.Reserva.TAG_FECHA_INICIO);
        map.put("value", reserva.getmFechaInicio()==null? null:reserva.getmFechaInicio().toString());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_FECHA_FIN);
        map.put("value", reserva.getmFechaFin()==null?null:reserva.getmFechaFin().toString());
        list.add(map);
        map.clear();
        map.put("field", JSONTag.Reserva.TAG_CANTIDAD_PERSONAS);
        map.put("value", reserva.getmCantidadPersonas());
        list.add(map);

        table.put("where", list);

        return table;
    }

}
