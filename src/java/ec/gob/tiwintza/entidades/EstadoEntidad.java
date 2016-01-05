package ec.gob.tiwintza.entidades;

/**
 *
 * @author wmoina
 */
public class EstadoEntidad {

    private long estado_cadena;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">  
    public long getEstado_cadena() {
        return estado_cadena;
    }

    public void setEstado_cadena(long estado_cadena) {
        this.estado_cadena = estado_cadena;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores"> 
    public EstadoEntidad(long estado_cadena) {
        this.estado_cadena = estado_cadena;
    }
     public EstadoEntidad() {
    }
    
    //</editor-fold>   
}
