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
public class SeguimientoEntidad {

    private long seguimiento_id;
    private TramiteEntidad tramite_fk;
    private TrabajoEntidad trabajo_fk;
    private Timestamp seguimiento_fecha_subida;
    private Timestamp seguimiento_fecha_lapso;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the seguimiento_id
     */
    public long getSeguimiento_id() {
        return seguimiento_id;
    }

    /**
     * @param seguimiento_id the seguimiento_id to set
     */
    public void setSeguimiento_id(long seguimiento_id) {
        this.seguimiento_id = seguimiento_id;
    }

    /**
     * @return the tramite_fk
     */
    public TramiteEntidad getTramite_fk() {
        return tramite_fk;
    }

    /**
     * @param tramite_fk the tramite_fk to set
     */
    public void setTramite_fk(TramiteEntidad tramite_fk) {
        this.tramite_fk = tramite_fk;
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
     * @return the seguimiento_fecha_subida
     */
    public Timestamp getSeguimiento_fecha_subida() {
        return seguimiento_fecha_subida;
    }

    /**
     * @param seguimiento_fecha_subida the seguimiento_fecha_subida to set
     */
    public void setSeguimiento_fecha_subida(Timestamp seguimiento_fecha_subida) {
        this.seguimiento_fecha_subida = seguimiento_fecha_subida;
    }

    /**
     * @return the seguimiento_fecha_lapso
     */
    public Timestamp getSeguimiento_fecha_lapso() {
        return seguimiento_fecha_lapso;
    }

    /**
     * @param seguimiento_fecha_lapso the seguimiento_fecha_lapso to set
     */
    public void setSeguimiento_fecha_lapso(Timestamp seguimiento_fecha_lapso) {
        this.seguimiento_fecha_lapso = seguimiento_fecha_lapso;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public SeguimientoEntidad(TramiteEntidad tramite_fk, TrabajoEntidad trabajo_fk, Timestamp seguimiento_fecha_subida, Timestamp seguimiento_fecha_lapso) {
        this.tramite_fk = tramite_fk;
        this.trabajo_fk = trabajo_fk;
        this.seguimiento_fecha_subida = seguimiento_fecha_subida;
        this.seguimiento_fecha_lapso = seguimiento_fecha_lapso;
    }

    public SeguimientoEntidad() {
    }

    public SeguimientoEntidad(long seguimiento_id, TramiteEntidad tramite_fk, TrabajoEntidad trabajo_fk, Timestamp seguimiento_fecha_subida, Timestamp seguimiento_fecha_lapso) {
        this.seguimiento_id = seguimiento_id;
        this.tramite_fk = tramite_fk;
        this.trabajo_fk = trabajo_fk;
        this.seguimiento_fecha_subida = seguimiento_fecha_subida;
        this.seguimiento_fecha_lapso = seguimiento_fecha_lapso;
    }

    //</editor-fold>
}
