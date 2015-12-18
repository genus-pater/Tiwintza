/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.PersonaEntidad;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eborja
 */
public class PersonaModelo {

    public static ArrayList<PersonaEntidad> obtenerPersona(String strSql) throws Exception {
        ArrayList<PersonaEntidad> arrLstPersona = new ArrayList<>();
        try {
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstPersona = llenarPersonaAutomatico(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstPersona;
    }

    public static ArrayList<PersonaEntidad> llenarPersonaAutomatico(ConjuntoResultado conResultado) throws Exception {
        ArrayList<PersonaEntidad> arrLstSubcriterio = new ArrayList<>();
        PersonaEntidad objPersona;
        try {
            while (conResultado.next()) {
                objPersona = new PersonaEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                        conResultado.getString(1), conResultado.getString(2));
                arrLstSubcriterio.add(objPersona);
            }
        } catch (Exception e) {
            arrLstSubcriterio.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            // ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return arrLstSubcriterio;
    }
    
    public static long insertarPersonaAutomatico(PersonaEntidad objPersonaIngresar) throws Exception {
        long logRespuesta=0;
        try {
            ArrayList<Parametro> arrLisParametros = new ArrayList<>();
            String strSql = "select bd_st.fn_insert_persona(?,?)";
            arrLisParametros.add(new Parametro(1, objPersonaIngresar.getPersona_nombre()));
            arrLisParametros.add(new Parametro(2, objPersonaIngresar.getPersona_apellido()));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql, arrLisParametros);
            while (conResultado.next()) {
                    logRespuesta = Long.parseLong(conResultado.getBigInteger(0).toString());
            }
            conResultado = null;
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return logRespuesta;
    }
}
