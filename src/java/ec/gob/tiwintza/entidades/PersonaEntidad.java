/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

/**
 *
 * @author eborja
 */
public class PersonaEntidad {

    private long persona_id;
    private String persona_nombre;
    private String persona_apellido;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the persona_id
     */
    public long getPersona_id() {
        return persona_id;
    }

    /**
     * @param persona_id the persona_id to set
     */
    public void setPersona_id(long persona_id) {
        this.persona_id = persona_id;
    }

    /**
     * @return the persona_nombre
     */
    public String getPersona_nombre() {
        return persona_nombre;
    }

    /**
     * @param persona_nombre the persona_nombre to set
     */
    public void setPersona_nombre(String persona_nombre) {
        this.persona_nombre = persona_nombre;
    }

    /**
     * @return the persona_apellido
     */
    public String getPersona_apellido() {
        return persona_apellido;
    }

    /**
     * @param persona_apellido the persona_apellido to set
     */
    public void setPersona_apellido(String persona_apellido) {
        this.persona_apellido = persona_apellido;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public PersonaEntidad(long persona_id, String persona_nombre, String persona_apellido) {
        this.persona_id = persona_id;
        this.persona_nombre = persona_nombre;
        this.persona_apellido = persona_apellido;
    }

    public PersonaEntidad(String persona_nombre, String persona_apellido) {
        this.persona_nombre = persona_nombre;
        this.persona_apellido = persona_apellido;
    }

    public PersonaEntidad() {
    }

    //</editor-fold>
}
