/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

import java.sql.Timestamp;

/**
 *
 * @author eborja
 */
public class TramiteEntidad {

    private long tramite_id;
    private TrabajoEntidad trabajo_fk;
    private TipoEntidad tipo_fk;
    private PersonaEntidad persona_fk;
    private Timestamp fecha_subida;
    private byte[] tramite_archivo;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the tramite_id
     */
    public long getTramite_id() {
        return tramite_id;
    }

    /**
     * @param tramite_id the tramite_id to set
     */
    public void setTramite_id(long tramite_id) {
        this.tramite_id = tramite_id;
    }

    /**
     * @return the trabajo_fk
     */
    public TrabajoEntidad getTrabajo_fk() {
        return trabajo_fk;
    }

    /**
     * @param trabajo_fk the trabajo_fk to set
     */
    public void setTrabajo_fk(TrabajoEntidad trabajo_fk) {
        this.trabajo_fk = trabajo_fk;
    }

    /**
     * @return the tipo_fk
     */
    public TipoEntidad getTipo_fk() {
        return tipo_fk;
    }

    /**
     * @param tipo_fk the tipo_fk to set
     */
    public void setTipo_fk(TipoEntidad tipo_fk) {
        this.tipo_fk = tipo_fk;
    }

    /**
     * @return the persona_fk
     */
    public PersonaEntidad getPersona_fk() {
        return persona_fk;
    }

    /**
     * @param persona_fk the persona_fk to set
     */
    public void setPersona_fk(PersonaEntidad persona_fk) {
        this.persona_fk = persona_fk;
    }

    /**
     * @return the fecha_subida
     */
    public Timestamp getFecha_subida() {
        return fecha_subida;
    }

    /**
     * @param fecha_subida the fecha_subida to set
     */
    public void setFecha_subida(Timestamp fecha_subida) {
        this.fecha_subida = fecha_subida;
    }

    /**
     * @return the tramite_archivo
     */
    public byte[] getTramite_archivo() {
        return tramite_archivo;
    }

    /**
     * @param tramite_archivo the tramite_archivo to set
     */
    public void setTramite_archivo(byte[] tramite_archivo) {
        this.tramite_archivo = tramite_archivo;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public TramiteEntidad() {
        trabajo_fk=new TrabajoEntidad();
        tipo_fk=new TipoEntidad();
        persona_fk=new PersonaEntidad();
    }

    public TramiteEntidad(TrabajoEntidad trabajo_fk, TipoEntidad tipo_fk, PersonaEntidad persona_fk, Timestamp fecha_subida, byte[] tramite_archivo) {
        this.trabajo_fk = trabajo_fk;
        this.tipo_fk = tipo_fk;
        this.persona_fk = persona_fk;
        this.fecha_subida = fecha_subida;
        this.tramite_archivo = tramite_archivo;
    }

    public TramiteEntidad(long tramite_id, TrabajoEntidad trabajo_fk, TipoEntidad tipo_fk, PersonaEntidad persona_fk, Timestamp fecha_subida, byte[] tramite_archivo) {
        this.tramite_id = tramite_id;
        this.trabajo_fk = trabajo_fk;
        this.tipo_fk = tipo_fk;
        this.persona_fk = persona_fk;
        this.fecha_subida = fecha_subida;
        this.tramite_archivo = tramite_archivo;
    }

    //</editor-fold>
}
