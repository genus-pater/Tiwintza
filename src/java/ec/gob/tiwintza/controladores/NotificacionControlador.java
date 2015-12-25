package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.NotificacionEntidad;
import ec.gob.tiwintza.modelos.NotificacionModelo;
import ec.gob.tiwintza.sesiones.SesionUsuarioDataManager;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wmoina
 */
@ManagedBean
@RequestScoped

public class NotificacionControlador {

    private NotificacionEntidad objNotificacion;
    private ArrayList<NotificacionEntidad> arrLisNotificacion;
    private Integer numeroArray = 0;

    SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets"> 
    public NotificacionEntidad getObjNotificacion() {
        return objNotificacion;
    }

    public void setObjNotificacion(NotificacionEntidad objNotificacion) {
        this.objNotificacion = objNotificacion;
    }

    public ArrayList<NotificacionEntidad> getArrLisNotificacion() {
        return arrLisNotificacion;
    }

    public void setArrLisNotificacion(ArrayList<NotificacionEntidad> arrLisNotificacion) {
        this.arrLisNotificacion = arrLisNotificacion;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    @PostConstruct
    public void reint() {
        cargarNotificacion();

    }

    public NotificacionControlador() {
        objNotificacion = new NotificacionEntidad();
        arrLisNotificacion = new ArrayList<>();
    }

    public NotificacionControlador(NotificacionEntidad objNotificacion, ArrayList<NotificacionEntidad> arrLisNotificacion) {
        this.objNotificacion = objNotificacion;
        this.arrLisNotificacion = arrLisNotificacion;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cargarNotificacion() {
        try {
            this.arrLisNotificacion = NotificacionModelo.obtenerNotificacion(sesion.getSesionUsuarioActual().getUsuario_id());
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void actualizarNotificacion(long id) throws Exception {

        long lonIdNotificacion = id;
        NotificacionModelo.actualizarNotificacion(new NotificacionEntidad(lonIdNotificacion));

        delete();
    }

    void delete() {
        objNotificacion = null;
        arrLisNotificacion = null;
    }

    //</editor-fold>
}
