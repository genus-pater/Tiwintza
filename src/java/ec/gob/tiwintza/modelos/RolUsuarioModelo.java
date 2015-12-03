/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class RolUsuarioModelo {

    public static int eliminarRolUsuario(long lonIdRolEliminar, long lonIdUsuarioEliminar) throws Exception {
        String strQuery = "select bd_st.fn_delete_rol_usuario(?,?)";
        int intResultado = 0;
        ArrayList<Parametro> lisParametros = new ArrayList<>();
        lisParametros.add(new Parametro(1, lonIdUsuarioEliminar));
        lisParametros.add(new Parametro(2, lonIdRolEliminar));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, lisParametros);
        while (conResultado.next()) {
            intResultado = conResultado.getInt(0);
        }
        return intResultado;
    }

    public static boolean insertarRolUsuario(RolUsuarioEntidad objRolUsuarioIngresar) throws Exception {
        boolean booRespuesta = false;
        try {
            ArrayList<Parametro> arrLisParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_rol_usuario(?,?,?)";
            arrLisParametros.add(new Parametro(1, objRolUsuarioIngresar.getUsuario_id().getUsuario_id()));
            arrLisParametros.add(new Parametro(2, objRolUsuarioIngresar.getRol_id().getRol_id()));
            arrLisParametros.add(new Parametro(3, objRolUsuarioIngresar.getRol_usuario_fecha_crecion()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrLisParametros);
            while (conResultado.next()) {
                if (conResultado.getBoolean(0) == true) {
                    booRespuesta = true;
                }
            }
            conResultado = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return booRespuesta;
    }

    public static int actualizarRolUsuario(RolUsuarioEntidad objRolUsuarioActualizar,
            RolUsuarioEntidad objRolUsuarioRespaldo) throws Exception {
        int intResultado = 0;
        String strQuery = "select bd_st.fn_update_rol_usuario(?,?,?)";
        ArrayList<Parametro> arrLisParametros = new ArrayList<>();
        arrLisParametros.add(new Parametro(1, objRolUsuarioRespaldo.getUsuario_id().getUsuario_id()));
        arrLisParametros.add(new Parametro(2, objRolUsuarioRespaldo.getRol_id().getRol_id()));
        arrLisParametros.add(new Parametro(3, objRolUsuarioActualizar.getRol_id().getRol_id()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, arrLisParametros);
        while (conResultado.next()) {
            intResultado = conResultado.getInt(0);
        }
        return intResultado;
    }

    public static ArrayList<RolUsuarioEntidad> obtenerRolUsuario() throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstRolUsuario = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_rol_usuario();";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstRolUsuario = llenarRolUsuario(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstRolUsuario;
    }

    public static ArrayList<RolUsuarioEntidad> obtenerRolUsuario(String strSql) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstRolUsuario = new ArrayList<>();
        try {
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstRolUsuario = llenarRolUsuarioSesion(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstRolUsuario;
    }

    public static ArrayList<RolUsuarioEntidad> llenarRolUsuarioSesion(ConjuntoResultado conResultado) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstSubcriterio = new ArrayList<>();
        RolUsuarioEntidad objRolUsuario;
        try {
            while (conResultado.next()) {
                objRolUsuario = new RolUsuarioEntidad(new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(0).toString())),
                        new RolEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()), conResultado.getString(4)), conResultado.getTimeStamp(2));
                arrLstSubcriterio.add(objRolUsuario);
            }
        } catch (Exception e) {
            arrLstSubcriterio.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return arrLstSubcriterio;
    }

    public static ArrayList<RolUsuarioEntidad> llenarRolUsuario(ConjuntoResultado conResultado) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstSubcriterio = new ArrayList<>();
        RolUsuarioEntidad objRolUsuario;
        try {
            while (conResultado.next()) {
                objRolUsuario = new RolUsuarioEntidad(new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                        conResultado.getString(4), conResultado.getString(5), conResultado.getString(6), conResultado.getString(7),
                        conResultado.getString(8), conResultado.getString(9)), new RolEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()),
                                conResultado.getString(11)), conResultado.getTimeStamp(2));
                arrLstSubcriterio.add(objRolUsuario);
            }
        } catch (Exception e) {
            arrLstSubcriterio.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return arrLstSubcriterio;
    }
}
