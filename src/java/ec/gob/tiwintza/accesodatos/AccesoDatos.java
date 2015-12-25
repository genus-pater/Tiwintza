/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.accesodatos;

import ec.gob.tiwintza.entidades.ArchivoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoArchivoEntidad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DWSYSTEMS-CIA
 */
public class AccesoDatos {

//<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar Procedimientos">
     /*
     metodo de que permite ejecutar una funcion con parametros
     */
    public static boolean ejecutaComando(String comando, ArrayList<Parametro> parametros) throws Exception {
        boolean respuesta = false;
        PreparedStatement prts = null;
        Connection con = null;

        try {
            Global global = new Global();
            //registro el driver
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                //preparo el comando 
                prts = con.prepareStatement(comando);
                //mando mis parametos a la funcion 
                for (Parametro parm : parametros) {
                    prts.setObject(parm.getPosicion(), parm.getValor());

                }
                //ejecutando la sentencia
                int i = prts.executeUpdate();

                if (i > 0) {
                    respuesta = true;
                }

                prts.close();
                prts = null;

            } catch (SQLException exCon) {
                throw exCon;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }
        return respuesta;
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar consulta con resultados sin parametros">
    public static ConjuntoResultado ejecutaQuery(String query) throws Exception {

        ConjuntoResultado conj = new ConjuntoResultado();
        //El ResultSet contiene dentro los registros leidos de la base de datos
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;

        try {
            Global global = new Global();
            //global.getProperties();
            //registro el driver
            Class.forName(global.getDRIVER());

            try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.*/

                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
        //Class.forName("org.postgresql.Driver");
                //try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.

                //  //con = DriverManager.getConnection(global.getURL(),global.getUSER(),global.getPASS());
                // con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hojadevida",
                //          "postgres","Admin123456");
                //Clase que representa una sentencia precompilada de SQL 
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;

            } catch (SQLDataException exSQL) {
                throw exSQL;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }

        return conj;

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Funcion para ejecutar consulta con resultados conparametros">
    public static ConjuntoResultado ejecutaQuery(String query, ArrayList<Parametro> parametros) throws Exception {

        ConjuntoResultado conj = new ConjuntoResultado();
        //El ResultSet contiene dentro los registros leidos de la base de datos
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;

        try {
            Global global = new Global();
            //registro el driver
            Class.forName(global.getDRIVER());

            //registro el driver
            try {
                //a clase DriverManager lleva seguimiento de los controladores disponibles y
                //maneja solicitudes de conexión entre controladores adecuados y bases de datos
                //o servidores de bases de datos. 
                //El parámetro url del método getConnection() es un URL de base de datos que especifica 
                //el subprotocolo (el mecanismo de conectividad de bases de datos), el identificador 
                //de la base de datos o del servidor de bases de datos y una lista de propiedades.

                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());

                pst = con.prepareStatement(query);
                for (Parametro param : parametros) {
                    pst.setObject(param.getPosicion(), param.getValor());
                }
                rs = pst.executeQuery();
                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;

            } catch (SQLDataException exSQL) {
                throw exSQL;
            } finally {
                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }

        return conj;

    }

//</editor-fold>  
    public static ArchivoEntidad ejecutaQueryArchivo(String strSql, ArrayList<Parametro> arrayListParametros) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;
        Global global = new Global();
        try {
            //registro el driver
            Class.forName(global.getDRIVER());
            con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
            pst = con.prepareStatement(strSql);
            for (Parametro param : arrayListParametros) {
                pst.setObject(param.getPosicion(), param.getValor());
            }
            rs = pst.executeQuery();
            ArchivoEntidad objAux = null;
            while (rs.next()) {
                objAux = new ArchivoEntidad(rs.getString("archivo_tipo"),
                        rs.getString("archivo_nombre"), rs.getBlob("archivo_blob"));
            }
            pst.close();
            pst = null;
            con.close();
            con = null;
            rs.close();
            rs = null;
            return objAux;
        } catch (SQLException e) {
            return null;
        }
    }

    public static SeguimientoArchivoEntidad ejecutaQuerySeguimientoArchivo(String strSql, ArrayList<Parametro> arrayListParametros) throws ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;
        Global global = new Global();
        try {
            //registro el driver
            Class.forName(global.getDRIVER());
            con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
            pst = con.prepareStatement(strSql);
            for (Parametro param : arrayListParametros) {
                pst.setObject(param.getPosicion(), param.getValor());
            }
            rs = pst.executeQuery();
            SeguimientoArchivoEntidad objAux = null;
            while (rs.next()) {
                objAux = new SeguimientoArchivoEntidad(rs.getBlob("seguimiento_archivo_blob"),
                        rs.getString("seguimiento_archivo_tipo"),
                        rs.getString("seguimiento_archivo_nombre"));
            }
            pst.close();
            pst = null;
            con.close();
            con = null;
            rs.close();
            rs = null;
            return objAux;
        } catch (SQLException e) {
            return null;
        }
    }
}
