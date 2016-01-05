/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

/**
 *
 * @author wmoina
 */
public class NotificacionEntidad {

    private long notificacion_id;
    private long notificacion_estado;
    private String notificacion_desctipcion;
    private String tramite_codigo;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">  

    public String getTramite_codigo() {
        return tramite_codigo;
    }

    public void setTramite_codigo(String tramite_codigo) {
        this.tramite_codigo = tramite_codigo;
    }
    
    
    public long getNotificacion_id() {
        return notificacion_id;
    }

    public void setNotificacion_id(long notificacion_id) {
        this.notificacion_id = notificacion_id;
    }

    public long getNotificacion_estado() {
        return notificacion_estado;
    }

    public void setNotificacion_estado(long notificacion_estado) {
        this.notificacion_estado = notificacion_estado;
    }

    public String getNotificacion_desctipcion() {
        return notificacion_desctipcion;
    }

    public void setNotificacion_desctipcion(String notificacion_desctipcion) {
        this.notificacion_desctipcion = notificacion_desctipcion;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public NotificacionEntidad(long notificacion_id, long notificacion_estado, String notificacion_desctipcion, String tramite_codigo) {
        this.notificacion_id = notificacion_id;
        this.notificacion_estado = notificacion_estado;
        this.notificacion_desctipcion = notificacion_desctipcion;
        this.tramite_codigo = tramite_codigo;
    }

    public NotificacionEntidad() {
    }

    public NotificacionEntidad(long notificacion_id) {
        this.notificacion_id = notificacion_id;
    }

    //</editor-fold>  
}
