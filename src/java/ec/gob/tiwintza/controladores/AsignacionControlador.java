/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ComentarioSeguimientoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoArchivoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.modelos.ComentarioSeguimientoModelo;
import ec.gob.tiwintza.modelos.SeguimientoArchivoModelo;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eborja
 */
@ManagedBean
@SessionScoped

public class AsignacionControlador {

    private ComentarioSeguimientoEntidad objComentario;
    private String strRolUsuarioDepartamento;
    private Date datPlazoAsignacion;
    private UploadedFile uplTramite;
    private SeguimientoEntidad objSeguimiento;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    public SeguimientoEntidad getObjSeguimiento() {
        return objSeguimiento;
    }

    /**
     * @param objSeguimiento the objSeguimiento to set
     */
    public void setObjSeguimiento(SeguimientoEntidad objSeguimiento) {
        this.objSeguimiento = objSeguimiento;
    }

    /**
     * @return the uplTramite
     */
    public UploadedFile getUplTramite() {
        return uplTramite;
    }

    /**
     * @param uplTramite the uplTramite to set
     */
    public void setUplTramite(UploadedFile uplTramite) {
        this.uplTramite = uplTramite;
    }

    /**
     * @return the datPlazoAsignacion
     */
    public Date getDatPlazoAsignacion() {
        return datPlazoAsignacion;
    }

    /**
     * @param datPlazoAsignacion the datPlazoAsignacion to set
     */
    public void setDatPlazoAsignacion(Date datPlazoAsignacion) {
        this.datPlazoAsignacion = datPlazoAsignacion;
    }

    /**
     * @return the objComentario
     */
    public ComentarioSeguimientoEntidad getObjComentario() {
        return objComentario;
    }

    /**
     * @param objComentario the objComentario to set
     */
    public void setObjComentario(ComentarioSeguimientoEntidad objComentario) {
        this.objComentario = objComentario;
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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reinit() {

    }

    /**
     * Creates a new instance of AsignacionControlador
     */
    public AsignacionControlador() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tramiteControlador");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("asignacionControlador");
        objComentario = new ComentarioSeguimientoEntidad();
        objSeguimiento = new SeguimientoEntidad();
        strRolUsuarioDepartamento = "";
        uplTramite = null;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void fileUploadListener(FileUploadEvent event) throws IOException {
        this.uplTramite = event.getFile();
        RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmMiAsignacion:outNombreArchivo\").innerHTML='" + uplTramite.getFileName() + "'}");
    }

    public void fileUploadListenerTerminar(FileUploadEvent event) throws IOException {
        this.uplTramite = event.getFile();
        RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmTerminar:outNombreArchivo\").innerHTML='" + uplTramite.getFileName() + "'}");
    }

    public void terminar() throws SQLException, IOException, Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        objSeguimiento.setSeguimiento_id(Long.parseLong(params.get("idSeguimiento")));
        objSeguimiento.getTramite_fk().setTramite_id(Long.parseLong(params.get("idTramite")));
        objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getRol_id().setRol_id(Long.parseLong(params.get("idRol")));
        objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().setUsuario_id(Long.parseLong(params.get("idUsuario")));
        objSeguimiento.getTrabajo_fk().getDepartamento_fk().setDepartamento_id(Long.parseLong(params.get("idDepartamento")));
        objSeguimiento.getTramite_fk().setFecha_fin(new Timestamp(new Date().getTime()));
        objSeguimiento.setSeguimiento_fecha_fin(new Timestamp(new Date().getTime()));
        objComentario.setSeguimiento_fk(objSeguimiento);
        if (uplTramite != null) {
            InputStream inpStream = uplTramite.getInputstream();
            byte[] bytTramite = IOUtils.toByteArray(inpStream);
            Blob bloAux = null;
            bloAux = new javax.sql.rowset.serial.SerialBlob(bytTramite);
            String strNombre = uplTramite.getFileName();
            String strTipo = uplTramite.getContentType();
            if (ComentarioSeguimientoModelo.insertarComentarioSeguimiento(objComentario)) {
                if (SeguimientoArchivoModelo.insertarSeguimientoArchivo(new SeguimientoArchivoEntidad(objSeguimiento,
                        bloAux, strTipo, strNombre))) {
                    if (SeguimientoModelo.actualizarSeguimientoFechaFin(objSeguimiento) > 0) {
                        if (TramiteModelo.actualizarTramiteTerminar(objSeguimiento.getTramite_fk()) > 0) {
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("asignacionControlador");
                            RequestContext.getCurrentInstance().update("frmMiAsignacionTramite:pnlMiTramite");
                            Util.addSuccessMessage("Se concluyó con éxito el seguimiento del tramite.");
                            this.destroy();
                            RequestContext.getCurrentInstance().execute("{PF('wgTerminar').hide()}");
                        } else {
                            Util.addErrorMessage("No se pudo terminar el Trámite.");
                        }
                    } else {
                        Util.addErrorMessage("No se pudo terminar el Trámite.");
                    }
                } else {
                    Util.addErrorMessage("No se pudo terminar el Trámite.");
                }
            } else {
                Util.addErrorMessage("No se pudo terminar el Trámite.");
            }
        } else {
            if (ComentarioSeguimientoModelo.insertarComentarioSeguimiento(objComentario)) {
                if (SeguimientoModelo.actualizarSeguimientoFechaFin(objSeguimiento) > 0) {
                    if (TramiteModelo.actualizarTramiteTerminar(objSeguimiento.getTramite_fk()) > 0) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("asignacionControlador");
                        RequestContext.getCurrentInstance().update("frmMiAsignacionTramite:pnlMiTramite");
                        Util.addSuccessMessage("Se concluyó con éxito el seguimiento del tramite.");
                        this.destroy();
                        RequestContext.getCurrentInstance().execute("{PF('wgTerminar').hide()}");
                    } else {
                        Util.addErrorMessage("No se pudo terminar el Trámite.");
                    }
                } else {
                    Util.addErrorMessage("No se pudo terminar el Trámite.");
                }
            } else {
                Util.addErrorMessage("No se pudo terminar el Trámite.");
            }
        }
    }

    public void ingresoAsignacion() {
        try {
            String[] strIds = strRolUsuarioDepartamento.split("-");
            if (uplTramite != null) {
                InputStream inpStream = uplTramite.getInputstream();
                byte[] bytTramite = IOUtils.toByteArray(inpStream);
                Blob bloAux = null;
                bloAux = new javax.sql.rowset.serial.SerialBlob(bytTramite);
                String strNombre = uplTramite.getFileName();
                String strTipo = uplTramite.getContentType();
                FacesContext fc = FacesContext.getCurrentInstance();
                Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
                objSeguimiento.setSeguimiento_id(Long.parseLong(params.get("idSeguimiento")));
                objSeguimiento.getTramite_fk().setTramite_id(Long.parseLong(params.get("idTramite")));
                objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getRol_id().setRol_id(Long.parseLong(params.get("idRol")));
                objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().setUsuario_id(Long.parseLong(params.get("idUsuario")));
                objSeguimiento.getTrabajo_fk().getDepartamento_fk().setDepartamento_id(Long.parseLong(params.get("idDepartamento")));
                objComentario.setSeguimiento_fk(objSeguimiento);
                if (ComentarioSeguimientoModelo.insertarComentarioSeguimiento(objComentario)) {
                    if (SeguimientoArchivoModelo.insertarSeguimientoArchivo(new SeguimientoArchivoEntidad(objSeguimiento,
                            bloAux, strTipo, strNombre))) {
                        objSeguimiento.setSeguimiento_fecha_fin(new Timestamp(new Date().getTime()));
                        if (SeguimientoModelo.actualizarSeguimientoFechaFin(objSeguimiento) > 0) {
                            objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getRol_id().setRol_id(Long.parseLong(strIds[0]));
                            objSeguimiento.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().setUsuario_id(Long.parseLong(strIds[1]));
                            objSeguimiento.getTrabajo_fk().getDepartamento_fk().setDepartamento_id(Long.parseLong(strIds[2]));
                            objSeguimiento.setSeguimiento_fecha_lapso(new Timestamp(datPlazoAsignacion.getTime()));
                            if (SeguimientoModelo.insertarSeguimiento(objSeguimiento) > 0) {
                                if (SeguimientoModelo.actualizarSeguimientoAsignacion(objSeguimiento) > 0) {
                                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("asignacionControlador");
                                    RequestContext.getCurrentInstance().update("frmMiAsignacionTramite:pnlMiTramite");
                                    Util.addSuccessMessage("Se asignó correctamente el seguimiento del Trámite.");
                                    this.destroy();
                                    RequestContext.getCurrentInstance().execute("{PF('wgMiAsignacion').hide()}");

                                } else {
                                    Util.addErrorMessage("No se pudo asigar correctamente el seguimiento del Trámite.");
                                }
                            } else {
                                Util.addErrorMessage("No se pudo asigar correctamente el seguimiento del Trámite.");
                            }
                        } else {
                            Util.addErrorMessage("No se pudo asigar correctamente el seguimiento del Trámite.");
                        }
                    } else {
                        Util.addErrorMessage("No se pudo asigar correctamente el seguimiento del Trámite.");
                    }
                } else {
                    Util.addErrorMessage("No se pudo asigar correctamente el seguimiento del Trámite.");
                }
            } else {
                Util.addErrorMessage("Seleccione el documento de la asignación.");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    void destroy() {
        this.datPlazoAsignacion = null;
        this.objComentario = null;
        this.objSeguimiento = null;
        this.strRolUsuarioDepartamento = null;
        this.uplTramite = null;
    }
    //</editor-fold>
}
