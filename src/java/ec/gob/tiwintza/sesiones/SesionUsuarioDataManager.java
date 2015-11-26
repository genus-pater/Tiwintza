/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.sesiones;

import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author eborja
 */
@ManagedBean
@SessionScoped
public class SesionUsuarioDataManager {

    private UsuarioEntidad sesionUsuarioActual;
    private RolUsuarioEntidad sesionRolUsuarioActual;
    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">

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
     * @param sesionUsuarioRolUsuarioActual the sesionUsuarioRolUsuarioActual to set
     */
    public void setSesionRolUsuarioActual(RolUsuarioEntidad sesionUsuarioRolUsuarioActual) {
        this.sesionRolUsuarioActual = sesionUsuarioRolUsuarioActual;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public SesionUsuarioDataManager() {
        sesionUsuarioActual=new UsuarioEntidad();
    }

    void Destroy() {
        this.sesionRolUsuarioActual = null;
        this.sesionUsuarioActual = null;
    }
    //</editor-fold>
}
