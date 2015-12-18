/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.ComentarioTramiteEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class ComentarioTramiteModelo {
    
    public static boolean insertarComentarioTramite(ComentarioTramiteEntidad objComentarioTramiteIngresar) throws Exception {
        boolean booRespuesta = false;
        try {
            ArrayList<Parametro> arrLisParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_comentario_tramite(?,?)";
            arrLisParametros.add(new Parametro(1, objComentarioTramiteIngresar.getTramite_fk().getTramite_id()));
            arrLisParametros.add(new Parametro(2, objComentarioTramiteIngresar.getComentario_tramite_descripcion()));
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
