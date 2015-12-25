/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.DepartamentoEntidad;
import ec.gob.tiwintza.entidades.PersonaEntidad;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.TipoEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class TramiteModelo {

    public static boolean eliminarTramite(long lonIdTramiteEliminar) throws Exception {
        String strQuery = "select bd_st.fn_delete_tramite(?)";
        boolean booResultado = false;
        ArrayList<Parametro> lisParametros = new ArrayList<>();
        lisParametros.add(new Parametro(1, lonIdTramiteEliminar));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, lisParametros);
        while (conResultado.next()) {
            if (conResultado.getBoolean(0)) {
                booResultado = true;
            }
        }
        return booResultado;
    }

    public static long insertarTramite(TramiteEntidad objTramiteIngresar) throws Exception {
        long lonRespuesta = 0;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_tramite(?,?,?,?,?,?)";
            arrayListParametros.add(new Parametro(1, objTramiteIngresar.getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
            arrayListParametros.add(new Parametro(2, objTramiteIngresar.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrayListParametros.add(new Parametro(3, objTramiteIngresar.getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
            arrayListParametros.add(new Parametro(4, objTramiteIngresar.getTipo_fk().getTipo_id()));
            arrayListParametros.add(new Parametro(5, objTramiteIngresar.getPersona_fk().getPersona_id()));
            arrayListParametros.add(new Parametro(6, objTramiteIngresar.getFecha_subida()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                lonRespuesta = Long.parseLong(conResultado.getBigInteger(0).toString());
            }
            conResultado = null;
        } catch (SQLException e) {
            System.err.println("error" + e.getMessage());
        } catch (Exception e) {
            System.err.println("error" + e.getMessage());
        }
        return lonRespuesta;
    }

    public static boolean actualizarTramiteCodigo(TramiteEntidad objTramiteActualizar) throws Exception {
        boolean booRespuesta = false;
        String strQuery = "select bd_st.fn_update_tramite_codigo(?,?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objTramiteActualizar.getTramite_id()));
        listParametros.add(new Parametro(2, objTramiteActualizar.getTramite_codigo()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            if (conResultado.getBoolean(0) == true) {
                booRespuesta = true;
            }
        }
        return booRespuesta;

    }
    
    public static int actualizarTramiteAsignacio(TramiteEntidad objTramiteActualizar) throws Exception {
        int intRespuesta = 0;
        String strQuery = "select bd_st.fn_update_tramite_asignacion(?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objTramiteActualizar.getTramite_id()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            intRespuesta=conResultado.getInt(0);
        }
        return intRespuesta;

    }

    public static ArrayList<TramiteEntidad> obtenerTramite() throws Exception {
        ArrayList<TramiteEntidad> arrLstTramite = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_tramite(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstTramite = llenarTramite(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstTramite;
    }

    public static ArrayList<TramiteEntidad> llenarTramite(ConjuntoResultado conResultado) throws Exception {
        ArrayList<TramiteEntidad> arrLstTramite = new ArrayList<>();
        TramiteEntidad objTramite;
        try {
            while (conResultado.next()) {
                if (!conResultado.getBoolean(10)) {
                    objTramite = new TramiteEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                            new TrabajoEntidad(new RolUsuarioEntidad(
                                            new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(2).toString())),
                                            new RolEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()))),
                                    new DepartamentoEntidad(Long.parseLong(conResultado.getBigInteger(3).toString()))),
                            new TipoEntidad(Long.parseLong(conResultado.getBigInteger(4).toString())),
                            new PersonaEntidad(Long.parseLong(conResultado.getBigInteger(5).toString())),
                            conResultado.getTimeStamp(6), conResultado.getString(7), conResultado.getBoolean(8),
                            conResultado.getBoolean(9), conResultado.getBoolean(10));
                    arrLstTramite.add(objTramite);
                }
            }
        } catch (Exception e) {
            arrLstTramite.clear();
            throw e;
        }
        return arrLstTramite;
    }

}
