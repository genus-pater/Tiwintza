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
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class TrabajoModelo {

    public static boolean insertarTrabajo(TrabajoEntidad objTrabajoIngresar) throws Exception {
        boolean boolRespuesta = false;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_trabajo(?,?,?)";
            arrayListParametros.add(new Parametro(1, objTrabajoIngresar.getRol_usuario_fk().getRol_id().getRol_id()));
            arrayListParametros.add(new Parametro(2, objTrabajoIngresar.getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrayListParametros.add(new Parametro(3, objTrabajoIngresar.getDepartamento_fk().getDepartamento_id()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                if (conResultado.getBoolean(0) == true) {
                    boolRespuesta = true;
                }
            }
            conResultado = null;
        } catch (SQLException e) {
            System.err.println("error" + e.getMessage());
        }
        return boolRespuesta;
    }

    public static int actualizarTrabajo(TrabajoEntidad objTrabajoRespaldo, TrabajoEntidad objTrabajoActualizar) throws Exception {
        int intRespuesta = 0;
        String strQuery = "select bd_st.fn_update_trabajo(?,?,?,?,?)";
        ArrayList<Parametro> listParametros = new ArrayList<>();
        listParametros.add(new Parametro(1, objTrabajoRespaldo.getRol_usuario_fk().getRol_id().getRol_id()));
        listParametros.add(new Parametro(2, objTrabajoRespaldo.getRol_usuario_fk().getUsuario_id().getUsuario_id()));
        listParametros.add(new Parametro(3, objTrabajoRespaldo.getDepartamento_fk().getDepartamento_id()));
        listParametros.add(new Parametro(4, objTrabajoActualizar.getDepartamento_fk().getDepartamento_id()));
        listParametros.add(new Parametro(5, objTrabajoActualizar.isTrabajo_estado()));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, listParametros);
        while (conResultado.next()) {
            intRespuesta = conResultado.getInt(0);
        }
        return intRespuesta;
    }

    public static ArrayList<TrabajoEntidad> obtenerTrabajo() throws Exception {
        ArrayList<TrabajoEntidad> arrLstTrabajo = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_trabajo(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstTrabajo = llenarTrabajo(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstTrabajo;
    }

    public static ArrayList<TrabajoEntidad> llenarTrabajo(ConjuntoResultado conResultado) throws Exception {
        ArrayList<TrabajoEntidad> arrLstTrabajo = new ArrayList<>();
        TrabajoEntidad objTrabajo;
        try {
            while (conResultado.next()) {
                objTrabajo = new TrabajoEntidad(new RolUsuarioEntidad(
                        new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(7).toString()), conResultado.getString(8),
                                conResultado.getString(9), conResultado.getString(10), conResultado.getString(11),
                                conResultado.getString(12), conResultado.getString(13)),
                        new RolEntidad(Long.parseLong(conResultado.getBigInteger(14).toString()),
                                conResultado.getString(15)), conResultado.getTimeStamp(6)),
                        new DepartamentoEntidad(Long.parseLong(conResultado.getBigInteger(16).toString()),
                                conResultado.getString(17), conResultado.getString(18)), conResultado.getBoolean(3));
                arrLstTrabajo.add(objTrabajo);
            }
        } catch (Exception e) {
            arrLstTrabajo.clear();
            throw e;
        }
        return arrLstTrabajo;
    }

    public static int eliminarTrabajo(long lonRolIdEliminar,long lonUsuarioIdEliminar,long lonDepartamentoIdEliminar) throws Exception {
        String strQuery = "select bd_st.fn_delete_trabajo(?,?,?)";
        int intResultado = 0;
        ArrayList<Parametro> lisParametros = new ArrayList<>();
        lisParametros.add(new Parametro(1, lonRolIdEliminar));
        lisParametros.add(new Parametro(2, lonUsuarioIdEliminar));
        lisParametros.add(new Parametro(3, lonDepartamentoIdEliminar));
        ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strQuery, lisParametros);
        while (conResultado.next()) {
            intResultado = conResultado.getInt(0);
        }
        return intResultado;
    }
}
