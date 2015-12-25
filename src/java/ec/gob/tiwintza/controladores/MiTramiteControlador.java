/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import ec.gob.tiwintza.sesiones.SesionUsuarioDataManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class MiTramiteControlador {

    private ArrayList<SeguimientoEntidad> arrLisSeguimiento;
    private Date datCurrentDay;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the arrLisSeguimiento
     */
    public ArrayList<SeguimientoEntidad> getArrLisSeguimiento() {
        return arrLisSeguimiento;
    }

    /**
     * @param arrLisSeguimiento the arrLisSeguimiento to set
     */
    public void setArrLisSeguimiento(ArrayList<SeguimientoEntidad> arrLisSeguimiento) {
        this.arrLisSeguimiento = arrLisSeguimiento;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reinit() {
        cargarSeguimiento();
    }

    /**
     * Creates a new instance of MiTramiteControlador
     */
    public MiTramiteControlador() {
        arrLisSeguimiento = new ArrayList<>();
        datCurrentDay=new Date();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cargarSeguimiento() {
        try {
            SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
            arrLisSeguimiento = SeguimientoModelo.obtenerSeguimientoSesion(
                    sesion.getSesionRolUsuarioActual().getRol_id().getRol_id(),
                    sesion.getSesionUsuarioActual().getUsuario_id(),
                    sesion.getSesionTrabajoUsuarioActual().getDepartamento_fk().getDepartamento_id());
        } catch (Exception e) {
        }
    }

    public String plazo(Date datPlazo) throws ParseException {
        String strClass="";
        Date datCurrentDate = new Date();
        SimpleDateFormat simAux = new SimpleDateFormat("dd/MM/yyyy");
        String strAux=simAux.format(datPlazo);
        datPlazo=simAux.parse(strAux);
        strAux=simAux.format(datCurrentDate);
        datCurrentDate=simAux.parse(strAux);
        if (datPlazo.compareTo(datCurrentDate)>0) {
            strClass="greenTab";
        }
        if(datPlazo.compareTo(datCurrentDate)<0){
            strClass="redTab";
        }
        if(datPlazo.compareTo(datCurrentDate)==0){
            strClass="orangeTab";
        }
        return strClass;
    }
    //</editor-fold>

    /**
     * @return the datCurrentDay
     */
    public Date getDatCurrentDay() {
        return datCurrentDay;
    }

    /**
     * @param datCurrentDay the datCurrentDay to set
     */
    public void setDatCurrentDay(Date datCurrentDay) {
        this.datCurrentDay = datCurrentDay;
    }
}
