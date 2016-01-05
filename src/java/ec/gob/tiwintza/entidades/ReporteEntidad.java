
package ec.gob.tiwintza.entidades;

import java.sql.Timestamp;

/**
 *
 * @author wmoina
 */
public class ReporteEntidad {

    private String comentario_tramite_descripcion;
    private String rol_descripcion;
    private String usuario_nombre;
    private String usuario_apellido;
    private String usuario_email;
    private String deparftamento_nombre;
    private String seguimiento_descripcion;
    private Timestamp fecha_subida;
    private Timestamp fecha_lapso;
    private boolean tramite_estado;
    private long seguimiento_id;
    private long tramite_id;
    private String tramite_codigo;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">

    public String getTramite_codigo() {
        return tramite_codigo;
    }

    public void setTramite_codigo(String tramite_codigo) {
        this.tramite_codigo = tramite_codigo;
    }
    public long getTramite_id() {
        return tramite_id;
    }

    public void setTramite_id(long tramite_id) {
        this.tramite_id = tramite_id;
    }
    
    public long getSeguimiento_id() {
        return seguimiento_id;
    }

    public void setSeguimiento_id(long seguimiento_id) {
        this.seguimiento_id = seguimiento_id;
    }

    public String getComentario_tramite_descripcion() {
        return comentario_tramite_descripcion;
    }

    public void setComentario_tramite_descripcion(String comentario_tramite_descripcion) {
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
    }

    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_apellido() {
        return usuario_apellido;
    }

    public void setUsuario_apellido(String usuario_apellido) {
        this.usuario_apellido = usuario_apellido;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getDeparftamento_nombre() {
        return deparftamento_nombre;
    }

    public void setDeparftamento_nombre(String deparftamento_nombre) {
        this.deparftamento_nombre = deparftamento_nombre;
    }

    public String getSeguimiento_descripcion() {
        return seguimiento_descripcion;
    }

    public void setSeguimiento_descripcion(String seguimiento_descripcion) {
        this.seguimiento_descripcion = seguimiento_descripcion;
    }

    public Timestamp getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(Timestamp fecha_subida) {
        this.fecha_subida = fecha_subida;
    }

    public Timestamp getFecha_lapso() {
        return fecha_lapso;
    }

    public void setFecha_lapso(Timestamp fecha_lapso) {
        this.fecha_lapso = fecha_lapso;
    }

    public boolean isTramite_estado() {
        return tramite_estado;
    }

    public void setTramite_estado(boolean tramite_estado) {
        this.tramite_estado = tramite_estado;
    }

    //</editor-fold>  
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public ReporteEntidad(String comentario_tramite_descripcion, String rol_descripcion, String usuario_nombre, String usuario_apellido, String usuario_email, String deparftamento_nombre, String seguimiento_descripcion, Timestamp fecha_subida, Timestamp fecha_lapso, boolean tramite_estado, long seguimiento_id, long tramite_id, String tramite_codigo) {
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
        this.rol_descripcion = rol_descripcion;
        this.usuario_nombre = usuario_nombre;
        this.usuario_apellido = usuario_apellido;
        this.usuario_email = usuario_email;
        this.deparftamento_nombre = deparftamento_nombre;
        this.seguimiento_descripcion = seguimiento_descripcion;
        this.fecha_subida = fecha_subida;
        this.fecha_lapso = fecha_lapso;
        this.tramite_estado = tramite_estado;
        this.seguimiento_id = seguimiento_id;
        this.tramite_id = tramite_id;
        this.tramite_codigo = tramite_codigo;
    }
    public ReporteEntidad() {
    }
    public ReporteEntidad(long seguimiento_id) {
        this.seguimiento_id = seguimiento_id;
    }
    //</editor-fold>

    

}
