/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.NotificacionEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wmoina
 */


public class NotificacionModelo {
    
  

    public static int actualizarNotificacion(NotificacionEntidad objNotificacionActualizar) throws Exception {

        int intRespuesta = 0;
        String strQuery = "select bd_st.fn_update_notificacion(?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objNotificacionActualizar.getNotificacion_id()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            intRespuesta = conResultado.getInt(0);
        }
        return intRespuesta;

    }

    public static ArrayList<NotificacionEntidad> obtenerNotificacion(long longNotificacion_id) throws Exception {
        ArrayList<NotificacionEntidad> arrLstNotificacion = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_notificacion(?);";
            ArrayList<Parametro> lisParametros= new ArrayList<>();
            lisParametros.add(new Parametro(1, longNotificacion_id));  
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql,lisParametros);
            arrLstNotificacion = llenarNotificacion(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstNotificacion;
    }

    public static ArrayList<NotificacionEntidad> llenarNotificacion(ConjuntoResultado conResultado) throws Exception {
        ArrayList<NotificacionEntidad> arrLstNotificacion = new ArrayList<>();
        NotificacionEntidad objNotificacion;
        try {
            while (conResultado.next()) {
                objNotificacion = new NotificacionEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()), 
                        Long.parseLong(conResultado.getBigInteger(2).toString()), conResultado.getString(0),conResultado.getString(3));
                arrLstNotificacion.add(objNotificacion);
            }
        } catch (Exception e) {
            arrLstNotificacion.clear();
            throw e;
        }
        return arrLstNotificacion;
    }
    
    
}
