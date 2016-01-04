
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.entidades.EstadoEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wmoina
 */


public class EstadoModelo {
    
    
    public static ArrayList<EstadoEntidad> obtenerEstado() throws Exception {
        ArrayList<EstadoEntidad> arrLstEstado = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_estados(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstEstado = llenarEstado(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstEstado;
    }

    public static ArrayList<EstadoEntidad> llenarEstado(ConjuntoResultado conResultado) throws Exception {
        ArrayList<EstadoEntidad> arrLstEstado = new ArrayList<>();
        EstadoEntidad objEstado;
        try {
            while (conResultado.next()) {
                objEstado = new EstadoEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()));
                arrLstEstado.add(objEstado);
            }
        } catch (Exception e) {
            arrLstEstado.clear();
            throw e;
        }
        return arrLstEstado;
    }
    
}
