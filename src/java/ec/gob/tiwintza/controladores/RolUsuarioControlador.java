/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import ec.gob.tiwintza.modelos.RolModelo;
import ec.gob.tiwintza.modelos.RolUsuarioModelo;
import ec.gob.tiwintza.modelos.UsuarioModelo;
import ec.gob.tiwintza.sesiones.SesionUsuarioDataManager;
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
public class RolUsuarioControlador {

    private RolUsuarioEntidad objRolUsuario;
    private RolUsuarioEntidad objSelRolUsuario;
    private ArrayList<RolUsuarioEntidad> arrLisRolUsuario;
    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">

    /**
     * @return the objRolUsuario
     */
    public RolUsuarioEntidad getObjRolUsuario() {
        return objRolUsuario;
    }

    /**
     * @param objRolUsuario the objRolUsuario to set
     */
    public void setObjRolUsuario(RolUsuarioEntidad objRolUsuario) {
        this.objRolUsuario = objRolUsuario;
    }

    /**
     * @return the objSelRolUsuario
     */
    public RolUsuarioEntidad getObjSelRolUsuario() {
        return objSelRolUsuario;
    }

    /**
     * @param objSelRolUsuario the objSelRolUsuario to set
     */
    public void setObjSelRolUsuario(RolUsuarioEntidad objSelRolUsuario) {
        this.objSelRolUsuario = objSelRolUsuario;
    }

    /**
     * @return the arrLisRolUsuario
     */
    public ArrayList<RolUsuarioEntidad> getArrLisRolUsuario() {
        return arrLisRolUsuario;
    }

    /**
     * @param arrLisRolUsuario the arrLisRolUsuario to set
     */
    public void setArrLisRolUsuario(ArrayList<RolUsuarioEntidad> arrLisRolUsuario) {
        this.arrLisRolUsuario = arrLisRolUsuario;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    @PostConstruct
    public void reinit() {
        cargarRolUsuario();
    }

    public RolUsuarioControlador(RolUsuarioEntidad objRolUsuario, RolUsuarioEntidad objSelRolUsuario, ArrayList<RolUsuarioEntidad> arrLisRolUsuario) {
        this.objRolUsuario = objRolUsuario;
        this.objSelRolUsuario = objSelRolUsuario;
        this.arrLisRolUsuario = arrLisRolUsuario;
    }

    /**
     * Creates a new instance of RolUsuarioControlador
     */
    public RolUsuarioControlador() {
        objRolUsuario = new RolUsuarioEntidad();
        objSelRolUsuario = new RolUsuarioEntidad();
        arrLisRolUsuario = new ArrayList<>();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cargarRolUsuario() {
        try {
            this.arrLisRolUsuario = RolUsuarioModelo.obtenerRolUsuario();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void insertarRolUsuario() {
        try {
            if (RolUsuarioModelo.insertarRolUsuario(objRolUsuario)) {
                actualizarSesion();
                Util.addSuccessMessage("Se asignó un nuevo ROL");
            } else {
                Util.mostrarMensaje("Datos no Insertados");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        delete();
        cargarRolUsuario();
    }

    public void eliminarRolUsuario() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            long lonIdRolEliminar = Long.parseLong(params.get("prmIdRolEliminar"));
            long lonIdUsuarioEliminar = Long.parseLong(params.get("prmIdUsuarioEliminar"));
            if (RolUsuarioModelo.eliminarRolUsuario(lonIdRolEliminar, lonIdUsuarioEliminar) > 1) {
                actualizarSesion();
                Util.addSuccessMessage("Se ELIMINÓ correctamente la asignación del Rol");
            } else {
                Util.mostrarMensaje("No se pudo eliminar la asignación del Rol");
            }
            cargarRolUsuario();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizarRolUsuario() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            long lonIdUsuarioActualizar = Long.parseLong(params.get("prmIdUsuarioActualizar"));
            long lonIdRolRespaldo = Long.parseLong(params.get("idRolRespaldo"));
            if (RolUsuarioModelo.actualizarRolUsuario(new RolUsuarioEntidad(new UsuarioEntidad(lonIdUsuarioActualizar),
                    new RolEntidad(objSelRolUsuario.getRol_id().getRol_id())),
                    new RolUsuarioEntidad(new UsuarioEntidad(lonIdUsuarioActualizar),
                            new RolEntidad(lonIdRolRespaldo))) > 1) {
                actualizarSesion();
                Util.addSuccessMessage("Se ACTUALIZÓ correctamente la asignación del Rol");
            } else {
                Util.mostrarMensaje("No se pudo actualizar la asignación del Rol");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        delete();
        cargarRolUsuario();
    }

    public void actualizarSesion() throws Exception {
        SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
        String strQuery = "call bd_st.pr_select_rol_usuario_sesion(\"" + sesion.getSesionUsuarioActual().getUsuario_id() + "\")";;
        ArrayList<RolUsuarioEntidad> arrListAux = RolUsuarioModelo.obtenerRolUsuario(strQuery);
        sesion.setSesionRolUsuarioActual(arrListAux.get(0));
    }

    void delete() {
        objRolUsuario = null;
        objSelRolUsuario = null;
        arrLisRolUsuario = null;
    }
    //</editor-fold>

}
