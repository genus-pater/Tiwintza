/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class TramiteModelo {
    public static long insertarTramite(TramiteEntidad objTramiteIngresar) throws Exception {
        long lonRespuesta = 0;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_tramite(?,?,?,?,?,?,?)";
            arrayListParametros.add(new Parametro(1, objTramiteIngresar.getTrabajo_fk().getRol_usuario_fk().getRol_id().getRol_id()));
            arrayListParametros.add(new Parametro(2, objTramiteIngresar.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().getUsuario_id()));
            arrayListParametros.add(new Parametro(3, objTramiteIngresar.getTrabajo_fk().getDepartamento_fk().getDepartamento_id()));
            arrayListParametros.add(new Parametro(4, objTramiteIngresar.getTipo_fk().getTipo_id()));
            arrayListParametros.add(new Parametro(5, objTramiteIngresar.getPersona_fk().getPersona_id()));
            arrayListParametros.add(new Parametro(6, objTramiteIngresar.getFecha_subida()));
            arrayListParametros.add(new Parametro(7, objTramiteIngresar.getTramite_archivo()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                    lonRespuesta =Long.parseLong(conResultado.getBigInteger(0).toString());
            }
            conResultado = null;
        } catch (SQLException e) {
            System.err.println("error" + e.getMessage());
        } catch(Exception e){
            System.err.println("error" + e.getMessage());
        }
        return lonRespuesta;
    }
}
