/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.gob.tiwintza.entidades.TramiteEntidad;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class TramiteConsultaControlador {

    private TramiteEntidad objTramite;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the objTramite
     */
    public TramiteEntidad getObjTramite() {
        return objTramite;
    }

    /**
     * @param objTramite the objTramite to set
     */
    public void setObjTramite(TramiteEntidad objTramite) {
        this.objTramite = objTramite;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Creates a new instance of TramiteConsultaControlador
     */
    public TramiteConsultaControlador() {
        objTramite = new TramiteEntidad();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void loginConsulta(){
        
    }
    //</editor-fold>
}
