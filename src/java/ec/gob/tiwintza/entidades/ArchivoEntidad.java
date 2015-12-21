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
public class ArchivoEntidad {

    private TramiteEntidad tramite_fk;
    private Blob archivo_blob = null;
    private String archivo_tipo;
    private String archivo_nombre;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
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
     * @return the archivo_blob
     */
    public Blob getArchivo_blob() {
        return archivo_blob;
    }

    /**
     * @param archivo_blob the archivo_blob to set
     */
    public void setArchivo_blob(Blob archivo_blob) {
        this.archivo_blob = archivo_blob;
    }

    /**
     * @return the archivo_tipo
     */
    public String getArchivo_tipo() {
        return archivo_tipo;
    }

    /**
     * @param archivo_tipo the archivo_tipo to set
     */
    public void setArchivo_tipo(String archivo_tipo) {
        this.archivo_tipo = archivo_tipo;
    }

    /**
     * @return the archivo_nombre
     */
    public String getArchivo_nombre() {
        return archivo_nombre;
    }

    /**
     * @param archivo_nombre the archivo_nombre to set
     */
    public void setArchivo_nombre(String archivo_nombre) {
        this.archivo_nombre = archivo_nombre;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public ArchivoEntidad() {
    }

    public ArchivoEntidad(TramiteEntidad tramite_fk, Blob archivo_blob, String archivo_tipo, String archivo_nombre) {
        this.tramite_fk = tramite_fk;
        this.archivo_tipo = archivo_tipo;
        this.archivo_nombre = archivo_nombre;
        this.archivo_blob = archivo_blob;
    }

    public ArchivoEntidad(String archivo_tipo, String archivo_nombre, Blob archivo_blob) {
        this.archivo_tipo = archivo_tipo;
        this.archivo_nombre = archivo_nombre;
        this.archivo_blob = archivo_blob;
    }

    //</editor-fold>
}
