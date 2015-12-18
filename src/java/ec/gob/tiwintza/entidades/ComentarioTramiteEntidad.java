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
public class ComentarioTramiteEntidad {

    private long comentario_tramite_id;
    private TramiteEntidad tramite_fk;
    private String comentario_tramite_descripcion;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the comentario_tramite_id
     */
    public long getComentario_tramite_id() {
        return comentario_tramite_id;
    }

    /**
     * @param comentario_tramite_id the comentario_tramite_id to set
     */
    public void setComentario_tramite_id(long comentario_tramite_id) {
        this.comentario_tramite_id = comentario_tramite_id;
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
     * @return the comentario_tramite_descripcion
     */
    public String getComentario_tramite_descripcion() {
        return comentario_tramite_descripcion;
    }

    /**
     * @param comentario_tramite_descripcion the comentario_tramite_descripcion
     * to set
     */
    public void setComentario_tramite_descripcion(String comentario_tramite_descripcion) {
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public ComentarioTramiteEntidad() {
        tramite_fk=new TramiteEntidad();
    }

    public ComentarioTramiteEntidad(long comentario_tramite_id, TramiteEntidad tramite_fk, String comentario_tramite_descripcion) {
        this.comentario_tramite_id = comentario_tramite_id;
        this.tramite_fk = tramite_fk;
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
    }

    public ComentarioTramiteEntidad(TramiteEntidad tramite_fk, String comentario_tramite_descripcion) {
        this.tramite_fk = tramite_fk;
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
    }

    //</editor-fold> 
}
