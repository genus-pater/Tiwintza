/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import com.sun.rowset.WebRowSetImpl;
import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.ArchivoEntidad;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class ArchivoModelo {

    public static long insertarArchivo(ArchivoEntidad objArchivoIngresar) throws Exception {
        long lonRespuesta = 0;
        try {
            ArrayList<Parametro> arrayListParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_archivo(?,?,?,?)";
            arrayListParametros.add(new Parametro(1, objArchivoIngresar.getTramite_fk().getTramite_id()));
            arrayListParametros.add(new Parametro(2, objArchivoIngresar.getArchivo_blob()));
            arrayListParametros.add(new Parametro(3, objArchivoIngresar.getArchivo_tipo()));
            arrayListParametros.add(new Parametro(4, objArchivoIngresar.getArchivo_nombre()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrayListParametros);
            while (conResultado.next()) {
                lonRespuesta = Long.parseLong(conResultado.getBigInteger(0).toString());
            }
            conResultado = null;
        } catch (SQLException e) {
            System.err.println("error" + e.getMessage());
        } catch (Exception e) {
            System.err.println("error" + e.getMessage());
        }
        return lonRespuesta;
    }

    public static ArchivoEntidad obtenerArchivoBlob(long lonIdTramite) throws Exception {
        ArrayList<Parametro> arrayListParametros = new ArrayList<>();
        String strSql = "call bd_st.pr_select_archivo(?)";
        arrayListParametros.add(new Parametro(1, lonIdTramite));
        ArchivoEntidad objResultado = AccesoDatos.ejecutaQueryArchivo(strSql, arrayListParametros);
        return objResultado;
    }
}
