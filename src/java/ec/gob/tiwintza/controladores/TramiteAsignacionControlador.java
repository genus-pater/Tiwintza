/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ArchivoEntidad;
import ec.gob.tiwintza.entidades.DepartamentoEntidad;
import ec.gob.tiwintza.entidades.RolEntidad;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.entidades.TrabajoEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import ec.gob.tiwintza.modelos.ArchivoModelo;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class TramiteAsignacionControlador {

    private TramiteEntidad objSelTramite;
    private ArrayList<TramiteEntidad> arrLisTramite;
    private StreamedContent strFile;
    private String strRolUsuarioDepartamento;
    private Date datPlazo;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the datPlazo
     */
    public Date getDatPlazo() {
        return datPlazo;
    }

    /**
     * @param datPlazo the datPlazo to set
     */
    public void setDatPlazo(Date datPlazo) {
        this.datPlazo = datPlazo;
    }

    /**
     * @return the strRolUsuarioDepartamento
     */
    public String getStrRolUsuarioDepartamento() {
        return strRolUsuarioDepartamento;
    }

    /**
     * @param strRolUsuarioDepartamento the strRolUsuarioDepartamento to set
     */
    public void setStrRolUsuarioDepartamento(String strRolUsuarioDepartamento) {
        this.strRolUsuarioDepartamento = strRolUsuarioDepartamento;
    }

    /**
     * @return the strFile
     */
    public StreamedContent getStrFile() {
        try {
            ArchivoEntidad objArchivo = ArchivoModelo.obtenerArchivoBlob(objSelTramite.getTramite_id());
            strFile = new DefaultStreamedContent(objArchivo.getArchivo_blob().getBinaryStream(), objArchivo.getArchivo_tipo(), objArchivo.getArchivo_nombre());
            return strFile;
        } catch (Exception ex) {
            Logger.getLogger(TramiteAsignacionControlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * @param strFile the strFile to set
     */
    public void setStrFile(StreamedContent strFile) {
        this.strFile = strFile;
    }

    /**
     * @return the objSelTramite
     */
    public TramiteEntidad getObjSelTramite() {
        return objSelTramite;
    }

    /**
     * @param objSelTramite the objSelTramite to set
     */
    public void setObjSelTramite(TramiteEntidad objSelTramite) {
        this.objSelTramite = objSelTramite;
    }

    /**
     * @return the arrLisTramite
     */
    public ArrayList<TramiteEntidad> getArrLisTramite() {
        return arrLisTramite;
    }

    /**
     * @param arrLisTramite the arrLisTramite to set
     */
    public void setArrLisTramite(ArrayList<TramiteEntidad> arrLisTramite) {
        this.arrLisTramite = arrLisTramite;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    @PostConstruct
    public void reint() {
        cargarTramite();
    }

    /**
     * Creates a new instance of TramiteControlador
     */
    public TramiteAsignacionControlador() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tramiteControlador");
        arrLisTramite = new ArrayList<>();
        objSelTramite = new TramiteEntidad();
        strRolUsuarioDepartamento = "";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cargarTramite() {
        try {
            arrLisTramite = TramiteModelo.obtenerTramite();
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
            arrLisTramite.clear();
        }
    }

    public void eliminarTramite() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            long lonTramiteId = Long.parseLong(params.get("prmIdTramiteEliminar"));
            if (TramiteModelo.eliminarTramite(lonTramiteId)) {
                Util.addSuccessMessage("Se eliminó correctamente el trámite");
                cargarTramite();
            } else {
                Util.addErrorMessage("No se pudo eliminar correctamente el trámite");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void asignarTramite() {
        try {
            long lonTraId=Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idTramite"));
            String [] strIds=strRolUsuarioDepartamento.split("-");
            SeguimientoEntidad objAux=new SeguimientoEntidad(new TramiteEntidad(lonTraId),
            new TrabajoEntidad(new RolUsuarioEntidad(
                    new UsuarioEntidad(Long.parseLong(strIds[1])),new RolEntidad(Long.parseLong(strIds[0]))),
            new DepartamentoEntidad(Long.parseLong(strIds[2]))), null, new Timestamp(datPlazo.getTime()));
            if(SeguimientoModelo.insertarSeguimiento(objAux)>0){
                if(TramiteModelo.actualizarTramiteAsignacio(new TramiteEntidad(lonTraId))>0){
                    Util.addSuccessMessage("Se asignó correctamente el seguimiento del trámite");
                    RequestContext.getCurrentInstance().execute("{PF('diaAsignacionTramite').hide()}");
                    RequestContext.getCurrentInstance().update("frmAsignacionTramite");
                    cargarTramite();
                }else{
                    Util.addErrorMessage("No se pudo asignar el seguimiento del trámite");
                }
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    public void rowEnable(){
        if(objSelTramite.isTramite_asignacion()==false){
            RequestContext.getCurrentInstance().execute("{PF('diaAsignacionTramite').show()}");
        }
    }

    void Destroy() {
    }
    //</editor-fold>

}
