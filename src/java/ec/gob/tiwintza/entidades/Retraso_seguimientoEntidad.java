package ec.gob.tiwintza.entidades;

/**
 *
 * @author wmoina
 */
public class Retraso_seguimientoEntidad {

    private long seguimiento_id;
    private String tramite_codigo;
    private String departamento_nombre;
    private String comentario_tramite_descripcion;
    private long dias_demora;
    private long dias_diferencia;
    private long retraso;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">  
    public long getSeguimiento_id() {
        return seguimiento_id;
    }

    public void setSeguimiento_id(long seguimiento_id) {
        this.seguimiento_id = seguimiento_id;
    }

    public String getTramite_codigo() {
        return tramite_codigo;
    }

    public void setTramite_codigo(String tramite_codigo) {
        this.tramite_codigo = tramite_codigo;
    }

    public String getDepartamento_nombre() {
        return departamento_nombre;
    }

    public void setDepartamento_nombre(String departamento_nombre) {
        this.departamento_nombre = departamento_nombre;
    }

    public String getComentario_tramite_descripcion() {
        return comentario_tramite_descripcion;
    }

    public void setComentario_tramite_descripcion(String comentario_tramite_descripcion) {
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
    }

    public long getDias_demora() {
        return dias_demora;
    }

    public void setDias_demora(long dias_demora) {
        this.dias_demora = dias_demora;
    }

    public long getDias_diferencia() {
        return dias_diferencia;
    }

    public void setDias_diferencia(long dias_diferencia) {
        this.dias_diferencia = dias_diferencia;
    }

    public long getRetraso() {
        return retraso;
    }

    public void setRetraso(long retraso) {
        this.retraso = retraso;
    }

       //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Retraso_seguimientoEntidad() {
    }

    public Retraso_seguimientoEntidad(long seguimiento_id) {
        this.seguimiento_id = seguimiento_id;
    }

    public Retraso_seguimientoEntidad(long seguimiento_id, String tramite_codigo, String departamento_nombre, String comentario_tramite_descripcion, long dias_demora, long dias_diferencia, long retraso) {
        this.seguimiento_id = seguimiento_id;
        this.tramite_codigo = tramite_codigo;
        this.departamento_nombre = departamento_nombre;
        this.comentario_tramite_descripcion = comentario_tramite_descripcion;
        this.dias_demora = dias_demora;
        this.dias_diferencia = dias_diferencia;
        this.retraso = retraso;
    }

//</editor-fold>
}
