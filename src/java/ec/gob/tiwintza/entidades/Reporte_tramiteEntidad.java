package ec.gob.tiwintza.entidades;

/**
 *
 * @author wmoina
 */
public class Reporte_tramiteEntidad {

    private long tramite_id;
    private String tramite_codigo;
    private String comentario_tramite;
    private long demora;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">  
    public long getTramite_id() {
        return tramite_id;
    }

    public void setTramite_id(long tramite_id) {
        this.tramite_id = tramite_id;
    }

    public String getTramite_codigo() {
        return tramite_codigo;
    }

    public void setTramite_codigo(String tramite_codigo) {
        this.tramite_codigo = tramite_codigo;
    }

    public String getComentario_tramite() {
        return comentario_tramite;
    }

    public void setComentario_tramite(String comentario_tramite) {
        this.comentario_tramite = comentario_tramite;
    }

    public long getDemora() {
        return demora;
    }

    public void setDemora(long demora) {
        this.demora = demora;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Reporte_tramiteEntidad(long tramite_id, String tramite_codigo, String comentario_tramite, long demora) {
        this.tramite_id = tramite_id;
        this.tramite_codigo = tramite_codigo;
        this.comentario_tramite = comentario_tramite;
        this.demora = demora;
    }

    public Reporte_tramiteEntidad() {
    }

//</editor-fold>  
}
