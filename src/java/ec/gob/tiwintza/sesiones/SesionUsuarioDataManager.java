/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.sesiones;

import ec.gob.tiwintza.entidades.MenuEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eborja
 */
@ManagedBean
@SessionScoped
public class SesionUsuarioDataManager {

    private UsuarioEntidad sesionUsuarioActual;
    private RolUsuarioEntidad sesionRolUsuarioActual;
    private TrabajoEntidad sesionTrabajoUsuarioActual;
    private List<MenuEntidad> lisMenu;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the lisMenu
     */
    public List<MenuEntidad> getLisMenu() {
        return lisMenu;
    }

    /**
     * @param lisMenu the lisMenu to set
     */
    public void setLisMenu(List<MenuEntidad> lisMenu) {
        this.lisMenu = lisMenu;
    }

    /**
     * @return the sesionTrabajoUsuarioActual
     */
    public TrabajoEntidad getSesionTrabajoUsuarioActual() {
        return sesionTrabajoUsuarioActual;
    }

    /**
     * @param sesionDepartamentoUsuarioActual the sesionTrabajoUsuarioActual to
     * set
     */
    public void setSesionTrabajoUsuarioActual(TrabajoEntidad sesionDepartamentoUsuarioActual) {
        this.sesionTrabajoUsuarioActual = sesionDepartamentoUsuarioActual;
    }

    /**
     * @return the sesionUsuarioActual
     */
    public UsuarioEntidad getSesionUsuarioActual() {
        return sesionUsuarioActual;
    }

    /**
     * @param sesionUsuarioActual the sesionUsuarioActual to set
     */
    public void setSesionUsuarioActual(UsuarioEntidad sesionUsuarioActual) {
        this.sesionUsuarioActual = sesionUsuarioActual;
    }

    /**
     * @return the sesionUsuarioRolUsuarioActual
     */
    public RolUsuarioEntidad getSesionRolUsuarioActual() {
        return sesionRolUsuarioActual;
    }

    /**
     * @param sesionUsuarioRolUsuarioActual the sesionUsuarioRolUsuarioActual to
     * set
     */
    public void setSesionRolUsuarioActual(RolUsuarioEntidad sesionUsuarioRolUsuarioActual) {
        this.sesionRolUsuarioActual = sesionUsuarioRolUsuarioActual;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public SesionUsuarioDataManager() {
        sesionUsuarioActual = new UsuarioEntidad();
        sesionTrabajoUsuarioActual = new TrabajoEntidad();
        sesionRolUsuarioActual = new RolUsuarioEntidad();
    }

    void Destroy() {
        this.sesionRolUsuarioActual = null;
        this.sesionUsuarioActual = null;
        this.sesionTrabajoUsuarioActual = null;
        this.lisMenu=null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sesionUsuarioDataManager");
    }
    //</editor-fold>
}
