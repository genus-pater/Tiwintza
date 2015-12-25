/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

import java.sql.Blob;

/**
 *
 * @author eborja
 */
public class SeguimientoArchivoEntidad {

    private SeguimientoEntidad seguimiento_fk;
    private Blob seguimiento_archivo_blob = null;
    private String seguimiento_archivo_tipo;
    private String seguimiento_archivo_nombre;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the seguimiento_fk
     */
    public SeguimientoEntidad getSeguimiento_fk() {
        return seguimiento_fk;
    }

    /**
     * @param seguimiento_fk the seguimiento_fk to set
     */
    public void setSeguimiento_fk(SeguimientoEntidad seguimiento_fk) {
        this.seguimiento_fk = seguimiento_fk;
    }

    /**
     * @return the seguimiento_archivo_blob
     */
    public Blob getSeguimiento_archivo_blob() {
        return seguimiento_archivo_blob;
    }

    /**
     * @param seguimiento_archivo_blob the seguimiento_archivo_blob to set
     */
    public void setSeguimiento_archivo_blob(Blob seguimiento_archivo_blob) {
        this.seguimiento_archivo_blob = seguimiento_archivo_blob;
    }

    /**
     * @return the seguimiento_archivo_tipo
     */
    public String getSeguimiento_archivo_tipo() {
        return seguimiento_archivo_tipo;
    }

    /**
     * @param seguimiento_archivo_tipo the seguimiento_archivo_tipo to set
     */
    public void setSeguimiento_archivo_tipo(String seguimiento_archivo_tipo) {
        this.seguimiento_archivo_tipo = seguimiento_archivo_tipo;
    }

    /**
     * @return the seguimiento_archivo_nombre
     */
    public String getSeguimiento_archivo_nombre() {
        return seguimiento_archivo_nombre;
    }

    /**
     * @param seguimiento_archivo_nombre the seguimiento_archivo_nombre to set
     */
    public void setSeguimiento_archivo_nombre(String seguimiento_archivo_nombre) {
        this.seguimiento_archivo_nombre = seguimiento_archivo_nombre;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public SeguimientoArchivoEntidad(Blob seguimiento_archivo_blob, String seguimiento_archivo_tipo, String seguimiento_archivo_nombre) {
        this.seguimiento_archivo_blob = seguimiento_archivo_blob;
        this.seguimiento_archivo_tipo = seguimiento_archivo_tipo;
        this.seguimiento_archivo_nombre = seguimiento_archivo_nombre;
    }

    public SeguimientoArchivoEntidad() {
    }

    public SeguimientoArchivoEntidad(SeguimientoEntidad seguimiento_fk, Blob seguimiento_archivo_blob, String seguimiento_archivo_tipo, String seguimiento_archivo_nombre) {
        this.seguimiento_fk = seguimiento_fk;
        this.seguimiento_archivo_blob = seguimiento_archivo_blob;
        this.seguimiento_archivo_tipo = seguimiento_archivo_tipo;
        this.seguimiento_archivo_nombre = seguimiento_archivo_nombre;
    }

    //</editor-fold>
}
