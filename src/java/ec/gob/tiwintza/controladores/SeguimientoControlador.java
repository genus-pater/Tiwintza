package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author wmoina
 */
@ManagedBean
@RequestScoped

public class SeguimientoControlador {

    private SeguimientoEntidad objSeguimiento;
    private SeguimientoEntidad objSelSeguimiento;
    private ArrayList<SeguimientoEntidad> arrLisSeguimiento;

//<editor-fold defaultstate="collapsed" desc="Sets y Gets"> 
    public SeguimientoEntidad getObjSeguimiento() {
        return objSeguimiento;
    }

    public void setObjSeguimiento(SeguimientoEntidad objSeguimiento) {
        this.objSeguimiento = objSeguimiento;
    }

    public SeguimientoEntidad getObjSelSeguimiento() {
        return objSelSeguimiento;
    }

    public void setObjSelSeguimiento(SeguimientoEntidad objSelSeguimiento) {
        this.objSelSeguimiento = objSelSeguimiento;
    }

    public ArrayList<SeguimientoEntidad> getArrLisSeguimiento() {
        return arrLisSeguimiento;
    }

    public void setArrLisSeguimiento(ArrayList<SeguimientoEntidad> arrLisSeguimiento) {
        this.arrLisSeguimiento = arrLisSeguimiento;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reint() {
        cargarSeguimiento();
    }

    public SeguimientoControlador(SeguimientoEntidad objSeguimiento, SeguimientoEntidad objSelSeguimiento, ArrayList<SeguimientoEntidad> arrLisSeguimiento) {
        this.objSeguimiento = objSeguimiento;
        this.objSelSeguimiento = objSelSeguimiento;
        this.arrLisSeguimiento = arrLisSeguimiento;
    }

    public SeguimientoControlador() {
        objSeguimiento = new SeguimientoEntidad();
        objSelSeguimiento = new SeguimientoEntidad();
        arrLisSeguimiento = new ArrayList<>();
    }
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Funciones">

    public void cargarSeguimiento() {
        try {
            this.arrLisSeguimiento = SeguimientoModelo.obtenerSeguimiento();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void eliminarSeguimiento() {
        objSeguimiento = null;
        objSelSeguimiento = null;
        arrLisSeguimiento = null;
    }
//</editor-fold>

}
