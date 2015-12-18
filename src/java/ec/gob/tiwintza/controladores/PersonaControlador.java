/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.gob.tiwintza.entidades.PersonaEntidad;
import ec.gob.tiwintza.modelos.PersonaModelo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class PersonaControlador {

    private PersonaEntidad objPersona;
    private PersonaEntidad objSelPersona;
    private ArrayList<PersonaEntidad> arrLisPersona;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the objPersona
     */
    public PersonaEntidad getObjPersona() {
        return objPersona;
    }

    /**
     * @param objPersona the objPersona to set
     */
    public void setObjPersona(PersonaEntidad objPersona) {
        this.objPersona = objPersona;
    }

    /**
     * @return the objSelPersona
     */
    public PersonaEntidad getObjSelPersona() {
        return objSelPersona;
    }

    /**
     * @param objSelPersona the objSelPersona to set
     */
    public void setObjSelPersona(PersonaEntidad objSelPersona) {
        this.objSelPersona = objSelPersona;
    }

    /**
     * @return the arrLisPersona
     */
    public ArrayList<PersonaEntidad> getArrLisPersona() {
        return arrLisPersona;
    }

    /**
     * @param arrLisPersona the arrLisPersona to set
     */
    public void setArrLisPersona(ArrayList<PersonaEntidad> arrLisPersona) {
        this.arrLisPersona = arrLisPersona;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    @PostConstruct
    public void reinit() {
        //cargarPersona();
    }

    public PersonaControlador(PersonaEntidad objPersona, PersonaEntidad objSelPersona, ArrayList<PersonaEntidad> arrLisPersona) {
        this.objPersona = objPersona;
        this.objSelPersona = objSelPersona;
        this.arrLisPersona = arrLisPersona;
    }

    /**
     * Creates a new instance of PersonaControlador
     */
    public PersonaControlador() {
        objPersona = new PersonaEntidad();
        objSelPersona = new PersonaEntidad();
        arrLisPersona = new ArrayList<>();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public ArrayList<PersonaEntidad> cargarPersona(String strBusqueda) {
        try {
            String strSql = "call bd_st.pr_select_nombre_automatico('" + strBusqueda + "')";
            this.arrLisPersona = PersonaModelo.obtenerPersona(strSql);
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
        return arrLisPersona;
    }

    void delete() {
        objPersona = null;
        objSelPersona = null;
        arrLisPersona = null;
    }
    //</editor-fold>

}
