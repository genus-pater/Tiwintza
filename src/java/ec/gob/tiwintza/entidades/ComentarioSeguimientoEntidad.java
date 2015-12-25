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
public class ComentarioSeguimientoEntidad {

    private long comentario_seguimiento_id;
    private SeguimientoEntidad seguimiento_fk;
    private String comentario_seguimiento_descripcion;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the comentario_seguimiento_id
     */
    public long getComentario_seguimiento_id() {
        return comentario_seguimiento_id;
    }

    /**
     * @param comentario_seguimiento_id the comentario_seguimiento_id to set
     */
    public void setComentario_seguimiento_id(long comentario_seguimiento_id) {
        this.comentario_seguimiento_id = comentario_seguimiento_id;
    }

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
     * @return the comentario_seguimiento_descripcion
     */
    public String getComentario_seguimiento_descripcion() {
        return comentario_seguimiento_descripcion;
    }

    /**
     * @param comentario_seguimiento_descripcion the
     * comentario_seguimiento_descripcion to set
     */
    public void setComentario_seguimiento_descripcion(String comentario_seguimiento_descripcion) {
        this.comentario_seguimiento_descripcion = comentario_seguimiento_descripcion;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public ComentarioSeguimientoEntidad(long comentario_seguimiento_id, SeguimientoEntidad seguimiento_fk, String comentario_seguimiento_descripcion) {
        this.comentario_seguimiento_id = comentario_seguimiento_id;
        this.seguimiento_fk = seguimiento_fk;
        this.comentario_seguimiento_descripcion = comentario_seguimiento_descripcion;
    }

    public ComentarioSeguimientoEntidad(SeguimientoEntidad seguimiento_fk, String comentario_seguimiento_descripcion) {
        this.seguimiento_fk = seguimiento_fk;
        this.comentario_seguimiento_descripcion = comentario_seguimiento_descripcion;
    }

    public ComentarioSeguimientoEntidad() {
    }

    //</editor-fold>
}
