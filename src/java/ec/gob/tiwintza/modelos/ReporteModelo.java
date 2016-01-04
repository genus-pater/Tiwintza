
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.entidades.ReporteEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wmoina
 */


public class ReporteModelo {

    public static ArrayList<ReporteEntidad> obtenerReporte() throws Exception {
        ArrayList<ReporteEntidad> arrLstReporte = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_reporte; ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstReporte = llenarReporte(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstReporte;
    }

    public static ArrayList<ReporteEntidad> llenarReporte(ConjuntoResultado conResultado) throws Exception {
        ArrayList<ReporteEntidad> arrLstReporte = new ArrayList<>();
        ReporteEntidad objReporte;
        try {
            while (conResultado.next()) {
                objReporte = new ReporteEntidad(conResultado.getString(0),conResultado.getString(1),conResultado.getString(2),conResultado.getString(3),conResultado.getString(4),conResultado.getString(5),conResultado.getString(6),conResultado.getTimeStamp(7),conResultado.getTimeStamp(8),conResultado.getBoolean(9),Long.parseLong(conResultado.getBigInteger(10).toString()),Long.parseLong(conResultado.getBigInteger(11).toString()),conResultado.getString(12));
                arrLstReporte.add(objReporte);
            }
        } catch (Exception e) {
            arrLstReporte.clear();
            throw e;
        }
        return arrLstReporte;
    }
}
