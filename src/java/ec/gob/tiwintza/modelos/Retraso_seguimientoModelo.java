
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.entidades.Retraso_seguimientoEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wmoina
 */


public class Retraso_seguimientoModelo {
    
    public static ArrayList<Retraso_seguimientoEntidad> obtenerRetraso_seguimiento() throws Exception {
        ArrayList<Retraso_seguimientoEntidad> arrLstRetraso_seguimiento = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_demora_seguimiento(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstRetraso_seguimiento = llenarRetraso_seguimiento(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstRetraso_seguimiento;
    }

    public static ArrayList<Retraso_seguimientoEntidad> llenarRetraso_seguimiento(ConjuntoResultado conResultado) throws Exception {
        ArrayList<Retraso_seguimientoEntidad> arrLstRetraso_seguimiento = new ArrayList<>();
        Retraso_seguimientoEntidad objRetraso_seguimiento;
        try {
            while (conResultado.next()) {
                objRetraso_seguimiento = new Retraso_seguimientoEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()), conResultado.getString(1), conResultado.getString(2),conResultado.getString(3),Long.parseLong(conResultado.getBigInteger(4).toString()),Long.parseLong(conResultado.getBigInteger(5).toString()),Long.parseLong(conResultado.getBigInteger(6).toString()));
                arrLstRetraso_seguimiento.add(objRetraso_seguimiento);
            }
        } catch (Exception e) {
            arrLstRetraso_seguimiento.clear();
            throw e;
        }
        return arrLstRetraso_seguimiento;
    }
    
}
