/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.SeguimientoArchivoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class SeguimientoArchivoModelo {
    
    public static boolean insertarSeguimientoArchivo(SeguimientoArchivoEntidad objSeguimientoArchivoIngresar) throws Exception {
        boolean booRespuesta = false;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_seguimiento_archivo(?,?,?,?,?,?,?,?)";
            arrayListParametros.add(new Parametro(1, objSeguimientoArchivoIngresar.getSeguimiento_fk().getSeguimiento_id()));
            arrayListParametros.add(new Parametro(2, objSeguimientoArchivoIngresar.getSeguimiento_fk().getTramite_fk().getTramite_id()));
            arrayListParametros.add(new Parametro(3, objSeguimientoArchivoIngresar.getSeguimiento_fk().getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
            arrayListParametros.add(new Parametro(4, objSeguimientoArchivoIngresar.getSeguimiento_fk().getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrayListParametros.add(new Parametro(5, objSeguimientoArchivoIngresar.getSeguimiento_fk().getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
            arrayListParametros.add(new Parametro(6, objSeguimientoArchivoIngresar.getSeguimiento_archivo_blob()));
            arrayListParametros.add(new Parametro(7, objSeguimientoArchivoIngresar.getSeguimiento_archivo_tipo()));
            arrayListParametros.add(new Parametro(8, objSeguimientoArchivoIngresar.getSeguimiento_archivo_nombre()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                booRespuesta = conResultado.getBoolean(0);
            }
            conResultado = null;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return booRespuesta;
    }

    public static SeguimientoArchivoEntidad obtenerSeguimientoArchivoBlob(SeguimientoEntidad objSeguimiento) throws Exception {
        ArrayList<Parametro> arrayListParametros = new ArrayList<>();
        String strSql = "call bd_st.pr_select_seguimiento_archivo(?,?,?,?,?)";
        arrayListParametros.add(new Parametro(1, objSeguimiento.getSeguimiento_id()));
        arrayListParametros.add(new Parametro(2, objSeguimiento.getTramite_fk().getTramite_id()));
        arrayListParametros.add(new Parametro(3, objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
        arrayListParametros.add(new Parametro(4, objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
        arrayListParametros.add(new Parametro(5, objSeguimiento.getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
        SeguimientoArchivoEntidad objResultado = AccesoDatos.ejecutaQuerySeguimientoArchivo(strSql, arrayListParametros);
        return objResultado;
    }
    
}
