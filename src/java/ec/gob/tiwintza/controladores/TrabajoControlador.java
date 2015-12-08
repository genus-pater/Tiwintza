/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.DepartamentoEntidad;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import ec.gob.tiwintza.modelos.TrabajoModelo;
import java.util.ArrayList;
import java.util.Map;
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
public class TrabajoControlador {

    private TrabajoEntidad objTrabajo;
    private TrabajoEntidad objSelTrabajo;
    private ArrayList<TrabajoEntidad> arrLisTrabajo;
    private String strUsuarioIdTrabajo;
    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">

    /**
     * @return the strUsuarioIdTrabajo
     */
    public String getStrUsuarioIdTrabajo() {
        return strUsuarioIdTrabajo;
    }

    /**
     * @param strIdTrabajo the strUsuarioIdTrabajo to set
     */
    public void setStrUsuarioIdTrabajo(String strIdTrabajo) {
        this.strUsuarioIdTrabajo = strIdTrabajo;
    }

    /**
     * @return the objTrabajo
     */
    public TrabajoEntidad getObjTrabajo() {
        return objTrabajo;
    }

    /**
     * @param objTrabajo the objTrabajo to set
     */
    public void setObjTrabajo(TrabajoEntidad objTrabajo) {
        this.objTrabajo = objTrabajo;
    }

    /**
     * @return the objSelTrabajo
     */
    public TrabajoEntidad getObjSelTrabajo() {
        return objSelTrabajo;
    }

    /**
     * @param objSelTrabajo the objSelTrabajo to set
     */
    public void setObjSelTrabajo(TrabajoEntidad objSelTrabajo) {
        this.objSelTrabajo = objSelTrabajo;
    }

    /**
     * @return the arrLisTrabajo
     */
    public ArrayList<TrabajoEntidad> getArrLisTrabajo() {
        return arrLisTrabajo;
    }

    /**
     * @param arrLisTrabajo the arrLisTrabajo to set
     */
    public void setArrLisTrabajo(ArrayList<TrabajoEntidad> arrLisTrabajo) {
        this.arrLisTrabajo = arrLisTrabajo;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    @PostConstruct
    public void reinit() {
        cargarTrabajo();
    }

    public TrabajoControlador(TrabajoEntidad objTrabajo, TrabajoEntidad objSelTrabajo, ArrayList<TrabajoEntidad> arrLisTrabajo) {
        this.objTrabajo = objTrabajo;
        this.objSelTrabajo = objSelTrabajo;
        this.arrLisTrabajo = arrLisTrabajo;
    }

    /**
     * Creates a new instance of TrabajoControlador
     */
    public TrabajoControlador() {
        objTrabajo = new TrabajoEntidad();
        objSelTrabajo = new TrabajoEntidad();
        arrLisTrabajo = new ArrayList<>();
        strUsuarioIdTrabajo = "";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cargarTrabajo() {
        try {
            this.arrLisTrabajo = TrabajoModelo.obtenerTrabajo();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void insertarTrabajo() {
        try {
            String[] strId = strUsuarioIdTrabajo.split("-");
            objTrabajo.getRol_usuario_fk().getUsuario_id().setUsuario_id(Long.parseLong(strId[0]));
            objTrabajo.getRol_usuario_fk().getRol_id().setRol_id(Long.parseLong(strId[1]));
            if (TrabajoModelo.insertarTrabajo(objTrabajo)) {
                Util.addSuccessMessage("Se asignó un nuevo TRABAJO");
            } else {
                Util.mostrarMensaje("Datos no Insertados");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        delete();
        cargarTrabajo();
    }

    public void eliminarTrabajo() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            long lonIdRolEliminar = Long.parseLong(params.get("prmIdRolEliminar"));
            long lonIdUsuarioEliminar = Long.parseLong(params.get("prmIdUsuarioEliminar"));
            long lonIdDepartamentoEliminar = Long.parseLong(params.get("prmIdDepartamentoEliminar"));
            if (TrabajoModelo.eliminarTrabajo(lonIdRolEliminar, lonIdUsuarioEliminar, lonIdDepartamentoEliminar) > 1) {
                Util.addSuccessMessage("Se ELIMINÓ correctamente la asignación del Trabajo");
            } else {
                Util.mostrarMensaje("No se pudo eliminar la asignación del Trabajo");
            }
            cargarTrabajo();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizarTrabajo() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            long lonIdUsuarioActualizar = Long.parseLong(params.get("prmIdUsuarioActualizar"));
            long lonIdRolActualizar = Long.parseLong(params.get("prmIdRolActualizar"));
            long lonIdDepartamentoRespaldo = Long.parseLong(params.get("idDepartamentoRespaldo"));
            if (TrabajoModelo.actualizarTrabajo(new TrabajoEntidad(new RolUsuarioEntidad(
                    new UsuarioEntidad(lonIdUsuarioActualizar), new RolEntidad(lonIdRolActualizar)),
                    new DepartamentoEntidad(lonIdDepartamentoRespaldo)), new TrabajoEntidad(
                            new DepartamentoEntidad(objSelTrabajo.getDepartamento_fk().getDepartamento_id()),
                            objSelTrabajo.isTrabajo_estado())) > 1) {
                Util.addSuccessMessage("Se ACTUALIZÓ correctamente la asignación del Trabajo");
            } else {
                Util.mostrarMensaje("No se pudo actualizar la asignación del Trabajo");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        delete();
        cargarTrabajo();
    }

    void delete() {
        objTrabajo = null;
        objSelTrabajo = null;
        arrLisTrabajo = null;
        strUsuarioIdTrabajo = null;
    }
    //</editor-fold>
}
