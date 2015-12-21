/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ArchivoEntidad;
import ec.gob.tiwintza.entidades.ComentarioTramiteEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.modelos.ArchivoModelo;
import ec.gob.tiwintza.modelos.ComentarioTramiteModelo;
import ec.gob.tiwintza.modelos.PersonaModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import ec.gob.tiwintza.sesiones.SesionUsuarioDataManager;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eborja
 */
@ManagedBean
@SessionScoped
public class TramiteControlador {

    private TramiteEntidad objTramite;
    private String strNombreApellidoCodigoPersona;
    private ComentarioTramiteEntidad objComentario;
    private UploadedFile uplTramite;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the objComentario
     */
    public ComentarioTramiteEntidad getObjComentario() {
        return objComentario;
    }

    /**
     * @param objComentario the objComentario to set
     */
    public void setObjComentario(ComentarioTramiteEntidad objComentario) {
        this.objComentario = objComentario;
    }

    /**
     * @return the strNombreCodigoPersona
     */
    public String getStrNombreApellidoCodigoPersona() {
        return strNombreApellidoCodigoPersona;
    }

    /**
     * @param strNombreApellidoCodigoPersona
     */
    public void setStrNombreApellidoCodigoPersona(String strNombreApellidoCodigoPersona) {
        this.strNombreApellidoCodigoPersona = strNombreApellidoCodigoPersona;
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
     * Creates a new instance of TramiteControlador
     */
    public TramiteControlador() {
        uplTramite = new DefaultUploadedFile();
        objTramite = new TramiteEntidad();
        objComentario = new ComentarioTramiteEntidad();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void ingresoApellidoAutomatico() {
        String[] strAux = strNombreApellidoCodigoPersona.split("-");
        RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmTramite:inpApellido\").value = '" + strAux[1] + "'}");
    }

    public void fileUploadListener(FileUploadEvent event) throws IOException {
        this.uplTramite = event.getFile();
        RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmTramite:outNombreArchivo\").innerHTML='" + uplTramite.getFileName() + "'}");
    }

    public void ingresarTramite() {
        try {
            SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
            String[] strAux = strNombreApellidoCodigoPersona.split("-");
            if (uplTramite != null) {
                InputStream inpStream = uplTramite.getInputstream();
                byte[] bytTramite = IOUtils.toByteArray(inpStream);
                Blob bloAux = null;
                bloAux = new javax.sql.rowset.serial.SerialBlob(bytTramite);
                String strNombre = uplTramite.getFileName();
                String strTipo = uplTramite.getContentType();
                if (strAux.length > 1) {
                    if (strAux[2] != null) {
                        objTramite.getPersona_fk().setPersona_id(Long.parseLong(strAux[2]));
                        objTramite.getTrabajo_fk().getRol_usuario_fk().getRol_id().setRol_id(sesion.getSesionRolUsuarioActual().getRol_id().getRol_id());
                        objTramite.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().setUsuario_id(sesion.getSesionUsuarioActual().getUsuario_id());
                        objTramite.getTrabajo_fk().getDepartamento_fk().setDepartamento_id(sesion.getSesionTrabajoUsuarioActual().getDepartamento_fk().getDepartamento_id());
                        long lonTramiteId = TramiteModelo.insertarTramite(objTramite);
                        if (lonTramiteId > 0) {
                            objComentario.getTramite_fk().setTramite_id(lonTramiteId);
                            ComentarioTramiteModelo.insertarComentarioTramite(objComentario);
                            int intPaso = objTramite.getPersona_fk().getPersona_apellido().length();
                            String strId = cifrarId(lonTramiteId, intPaso);
                            objTramite.setTramite_id(lonTramiteId);
                            objTramite.setTramite_codigo(strId);
                            TramiteModelo.actualizarTramiteCodigo(objTramite);
                            ArchivoModelo.insertarArchivo(new ArchivoEntidad(objTramite, bloAux, strTipo, strNombre));
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tramiteControlador");
                            Util.addSuccessMessage("Se subio correctamente el trámite");
                            RequestContext.getCurrentInstance().execute("PF('bar').show();");
                            RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmCodigo:outCodigo\").innerHTML = 'Código de consulta: " + strId + "'}");
                            this.Destroy();
                        } else {
                            Util.addErrorMessage("No se pudo subir el trámite");
                        }
                    }
                } else {
                    objTramite.getPersona_fk().setPersona_nombre(strNombreApellidoCodigoPersona);
                    objTramite.getPersona_fk().setPersona_id(PersonaModelo.insertarPersonaAutomatico(objTramite.getPersona_fk()));
                    objTramite.getTrabajo_fk().getRol_usuario_fk().getRol_id().setRol_id(sesion.getSesionRolUsuarioActual().getRol_id().getRol_id());
                    objTramite.getTrabajo_fk().getRol_usuario_fk().getUsuario_id().setUsuario_id(sesion.getSesionUsuarioActual().getUsuario_id());
                    objTramite.getTrabajo_fk().getDepartamento_fk().setDepartamento_id(sesion.getSesionTrabajoUsuarioActual().getDepartamento_fk().getDepartamento_id());
                    long lonTramiteId = TramiteModelo.insertarTramite(objTramite);
                    if (lonTramiteId > 0) {
                        objComentario.getTramite_fk().setTramite_id(lonTramiteId);
                        ComentarioTramiteModelo.insertarComentarioTramite(objComentario);
                        int intPaso = objTramite.getPersona_fk().getPersona_apellido().length();
                        String strId = cifrarId(lonTramiteId, intPaso);
                        objTramite.setTramite_id(lonTramiteId);
                        objTramite.setTramite_codigo(strId);
                        TramiteModelo.actualizarTramiteCodigo(objTramite);
                        ArchivoModelo.insertarArchivo(new ArchivoEntidad(objTramite, bloAux, strTipo, strNombre));
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tramiteControlador");
                        Util.addSuccessMessage("Se subio correctamente el trámite");
                        RequestContext.getCurrentInstance().execute("PF('bar').show();");
                        RequestContext.getCurrentInstance().execute("{document.getElementById(\"frmCodigo:outCodigo\").innerHTML = 'Código de consulta: " + strId + "'}");
                        this.Destroy();
                    } else {
                        Util.addErrorMessage("No se pudo subir el trámite");
                    }
                }
            } else {
                Util.addErrorMessage("Seleccione el documento del tramite a realizar.");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    void Destroy() {
        objTramite = null;
        strNombreApellidoCodigoPersona = null;
        uplTramite = null;
        objComentario = null;
    }

    String cifrarId(long lonId, int intPaso) {
        String strAbc[] = {"Y", "R", "B", "S", "D", "P", "F", "W", "T", "X", "H", "I", "J", "Z", "K", "N",
            "O", "E", "Q", "L", "U", "C", "V", "A", "G", "M"};
        String strId[] = String.valueOf(lonId).split("");
        String strResultado = "";
        for (int i = 1; i <= (strId.length - 1); i++) {
            strResultado = strResultado + "" + strAbc[(Integer.parseInt(strId[i]) + (strId.length - 1)) % 26] + "";
        }
        int i = 0;
        while (strResultado.length() < 6) {
            strResultado = strAbc[((i++) + intPaso) % 26] + strResultado;
        }
        return strResultado;
    }
    //</editor-fold>
}
