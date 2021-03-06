/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.ComentarioSeguimientoEntidad;
import ec.gob.tiwintza.entidades.DepartamentoEntidad;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class SeguimientoModelo {

    public static boolean eliminarSeguimiento(long lonIdSeguimientoEliminar) throws Exception {
        String strQuery = "select bd_st.fn_delete_tramite(?)";
        boolean booResultado = false;
        ArrayList<Parametro> lisParametros = new ArrayList<>();
        lisParametros.add(new Parametro(1, lonIdSeguimientoEliminar));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, lisParametros);
        while (conResultado.next()) {
            if (conResultado.getBoolean(0)) {
                booResultado = true;
            }
        }
        return booResultado;
    }

    public static int insertarSeguimiento(SeguimientoEntidad objSeguimientoIngresar) throws Exception {
        int intRespuesta = 0;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_seguimiento(?,?,?,?,?,?)";
            arrayListParametros.add(new Parametro(1, objSeguimientoIngresar.getTramite_fk().getTramite_id()));
            arrayListParametros.add(new Parametro(2, objSeguimientoIngresar.getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
            arrayListParametros.add(new Parametro(3, objSeguimientoIngresar.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrayListParametros.add(new Parametro(4, objSeguimientoIngresar.getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
            arrayListParametros.add(new Parametro(5, objSeguimientoIngresar.getSeguimiento_fecha_subida()));
            arrayListParametros.add(new Parametro(6, objSeguimientoIngresar.getSeguimiento_fecha_lapso()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                intRespuesta = conResultado.getInt(0);
            }
            conResultado = null;
        } catch (SQLException e) {
            System.err.println("error" + e.getMessage());
        } catch (Exception e) {
            System.err.println("error" + e.getMessage());
        }
        return intRespuesta;
    }

    public static ArrayList<SeguimientoEntidad> obtenerSeguimiento() throws Exception {
        ArrayList<SeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_seguimiento(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstSeguimiento = llenarSeguimiento(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstSeguimiento;
    }

    public static ArrayList<ComentarioSeguimientoEntidad> obtenerSeguimientoConsulta(String strCod) throws Exception {
        ArrayList<ComentarioSeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        try {
            ArrayList<Parametro> arrLisPar = new ArrayList<>();
            String strSql = "call bd_st.pr_select_seguimiento_consulta(?); ";
            arrLisPar.add(new Parametro(1, strCod));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrLisPar);
            arrLstSeguimiento = llenarSeguimientoConsulta(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstSeguimiento;
    }

    public static ArrayList<SeguimientoEntidad> obtenerSeguimientoSesion(long lonRol, long lonUsu, long lonDep) throws Exception {
        ArrayList<SeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "call bd_st.pr_select_seguimiento_sesion(?,?,?); ";
            arrayListParametros.add(new Parametro(1, lonRol));
            arrayListParametros.add(new Parametro(2, lonUsu));
            arrayListParametros.add(new Parametro(3, lonDep));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            arrLstSeguimiento = llenarSeguimiento(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstSeguimiento;
    }

    public static ArrayList<SeguimientoEntidad> obtenerSeguimientoTree(long lonTraId) throws Exception {
        ArrayList<SeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "call bd_st.pr_select_tree(?); ";
            arrayListParametros.add(new Parametro(1, lonTraId));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            arrLstSeguimiento = llenarSeguimientoTree(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstSeguimiento;
    }

    public static String obtenerSeguimientoNodo(long lonTraId, long lonSegId) throws Exception {
        String strNodo = "";
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "call bd_st.pr_select_nodo(?,?); ";
            arrayListParametros.add(new Parametro(1, lonTraId));
            arrayListParametros.add(new Parametro(2, lonSegId));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                strNodo = conResultado.getString(0) + ": " + conResultado.getString(1) + " " + conResultado.getString(2);
            }
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return strNodo;
    }

    public static ArrayList<SeguimientoEntidad> llenarSeguimiento(ConjuntoResultado conResultado) throws Exception {
        ArrayList<SeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        SeguimientoEntidad objSeguimiento;
        try {
            while (conResultado.next()) {
                objSeguimiento = new SeguimientoEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                        new TramiteEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()), conResultado.getString(18)),
                        new TrabajoEntidad(new RolUsuarioEntidad(
                                        new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(3).toString())),
                                        new RolEntidad(Long.parseLong(conResultado.getBigInteger(2).toString()))),
                                new DepartamentoEntidad(Long.parseLong(conResultado.getBigInteger(4).toString()))),
                        conResultado.getTimeStamp(5), conResultado.getTimeStamp(6));
                arrLstSeguimiento.add(objSeguimiento);
            }
        } catch (Exception e) {
            arrLstSeguimiento.clear();
            throw e;
        }
        return arrLstSeguimiento;
    }

    public static ArrayList<ComentarioSeguimientoEntidad> llenarSeguimientoConsulta(ConjuntoResultado conResultado) throws Exception {
        ArrayList<ComentarioSeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        ComentarioSeguimientoEntidad objSeguimiento;
        try {
            while (conResultado.next()) {
                objSeguimiento = new ComentarioSeguimientoEntidad(new SeguimientoEntidad(
                        new TrabajoEntidad(
                                new DepartamentoEntidad(conResultado.getString(0)))),
                        conResultado.getString(1));
                arrLstSeguimiento.add(objSeguimiento);
            }
        } catch (Exception e) {
            arrLstSeguimiento.clear();
            throw e;
        }
        return arrLstSeguimiento;
    }

    public static ArrayList<SeguimientoEntidad> llenarSeguimientoTree(ConjuntoResultado conResultado) throws Exception {
        ArrayList<SeguimientoEntidad> arrLstSeguimiento = new ArrayList<>();
        SeguimientoEntidad objSeguimiento;
        try {
            while (conResultado.next()) {
                objSeguimiento = new SeguimientoEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                        new TramiteEntidad(Long.parseLong(conResultado.getBigInteger(1).toString())),
                        new TrabajoEntidad(new RolUsuarioEntidad(
                                        new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(3).toString())),
                                        new RolEntidad(Long.parseLong(conResultado.getBigInteger(2).toString()))),
                                new DepartamentoEntidad(Long.parseLong(conResultado.getBigInteger(4).toString()))),
                        conResultado.getTimeStamp(5), conResultado.getTimeStamp(6));
                arrLstSeguimiento.add(objSeguimiento);
            }
        } catch (Exception e) {
            arrLstSeguimiento.clear();
            throw e;
        }
        return arrLstSeguimiento;
    }

    public static int actualizarSeguimientoAsignacion(SeguimientoEntidad objSeguimientoActualizar) throws Exception {
        int intRespuesta = 0;
        String strQuery = "select bd_st.fn_update_seguimiento_asignacion(?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objSeguimientoActualizar.getSeguimiento_id()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            intRespuesta = conResultado.getInt(0);
        }
        return intRespuesta;
    }
    
    public static int actualizarSeguimientoFechaFin(SeguimientoEntidad objSeguimientoActualizar) throws Exception {
        int intRespuesta = 0;
        String strQuery = "select bd_st.fn_update_seguimiento_fecha_fin(?,?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objSeguimientoActualizar.getSeguimiento_id()));
        listParametros.add(new Parametro(2, objSeguimientoActualizar.getSeguimiento_fecha_fin()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            intRespuesta = conResultado.getInt(0);
        }
        return intRespuesta;
    }

}
