/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ArchivoEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.modelos.ArchivoModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the strFile
     */
    public StreamedContent getStrFile() {
        try {
            ArchivoEntidad objArchivo=ArchivoModelo.obtenerArchivoBlob(objSelTramite.getTramite_id());
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
        arrLisTramite = new ArrayList<>();
        objSelTramite = new TramiteEntidad();
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

    void Destroy() {
    }
    //</editor-fold>
}
