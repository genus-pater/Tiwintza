 
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.entidades.Reporte_tramiteEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wmoina
 */


public class Reporte_tramiteModelo {
    

    public static ArrayList<Reporte_tramiteEntidad> obtenerReporte_tramite() throws Exception {
        ArrayList<Reporte_tramiteEntidad> arrLstReporte_tramite = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_demora_tramite(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstReporte_tramite = llenarReporte_tramite(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstReporte_tramite;
    }
    public static ArrayList<Reporte_tramiteEntidad> llenarReporte_tramite(ConjuntoResultado conResultado) throws Exception {
        ArrayList<Reporte_tramiteEntidad> arrLstReporte_tramite = new ArrayList<>();
        Reporte_tramiteEntidad objReporte_tramite;
        try {
            while (conResultado.next()) {
                objReporte_tramite = new Reporte_tramiteEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()), conResultado.getString(1), conResultado.getString(2),Long.parseLong(conResultado.getBigInteger(0).toString()));
                arrLstReporte_tramite.add(objReporte_tramite);
            }
        } catch (Exception e) {
            arrLstReporte_tramite.clear();
            throw e;
        }
        return arrLstReporte_tramite;
    }
}
