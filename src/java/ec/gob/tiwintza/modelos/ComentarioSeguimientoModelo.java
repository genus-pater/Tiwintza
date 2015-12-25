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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class ComentarioSeguimientoModelo {
    
    public static boolean insertarComentarioSeguimiento(ComentarioSeguimientoEntidad objComentarioSeguimientoIngresar) throws Exception {
        boolean booRespuesta = false;
        try {
            ArrayList<Parametro> arrLisParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_comentario_seguimiento(?,?,?,?,?,?)";
            arrLisParametros.add(new Parametro(1, objComentarioSeguimientoIngresar.getSeguimiento_fk().getSeguimiento_id()));
            arrLisParametros.add(new Parametro(2, objComentarioSeguimientoIngresar.getSeguimiento_fk().getTramite_fk().getTramite_id()));
            arrLisParametros.add(new Parametro(3, objComentarioSeguimientoIngresar.getSeguimiento_fk().getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
            arrLisParametros.add(new Parametro(4, objComentarioSeguimientoIngresar.getSeguimiento_fk().getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrLisParametros.add(new Parametro(5, objComentarioSeguimientoIngresar.getSeguimiento_fk().getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
            arrLisParametros.add(new Parametro(6, objComentarioSeguimientoIngresar.getComentario_seguimiento_descripcion()));
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
    
}
