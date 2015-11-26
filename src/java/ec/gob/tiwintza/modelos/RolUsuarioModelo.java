/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class RolUsuarioModelo {
    
    public static ArrayList<RolUsuarioEntidad> obtenerRolUsuario(String strSql) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstRolUsuario = new ArrayList<>();
        try {
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstRolUsuario = llenarRolUsuarioSesion(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstRolUsuario;
    }
    
    public static ArrayList<RolUsuarioEntidad> llenarRolUsuarioSesion(ConjuntoResultado conResultado) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstSubcriterio = new ArrayList<>();
        RolUsuarioEntidad objRolUsuario;
        try {
            while (conResultado.next()) {
                objRolUsuario = new RolUsuarioEntidad(new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(0).toString())),
                new RolEntidad(Long.parseLong(conResultado.getBigInteger(1).toString()),conResultado.getString(4)), conResultado.getTimeStamp(2));
                arrLstSubcriterio.add(objRolUsuario);
            }
        } catch (Exception e) {
            arrLstSubcriterio.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return arrLstSubcriterio;
    }
    
    public static ArrayList<RolUsuarioEntidad> llenarRolUsuario(ConjuntoResultado conResultado) throws Exception {
        ArrayList<RolUsuarioEntidad> arrLstSubcriterio = new ArrayList<>();
        RolUsuarioEntidad objRolUsuario;
        try {
            while (conResultado.next()) {
                objRolUsuario = new RolUsuarioEntidad(new UsuarioEntidad(Long.parseLong(conResultado.getBigInteger(0).toString())),
                new RolEntidad(Long.parseLong(conResultado.getBigInteger(1).toString())), conResultado.getTimeStamp(2));
                arrLstSubcriterio.add(objRolUsuario);
            }
        } catch (Exception e) {
            arrLstSubcriterio.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return arrLstSubcriterio;
    }
}
