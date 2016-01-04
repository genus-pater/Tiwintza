/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ArchivoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoArchivoEntidad;
import ec.gob.tiwintza.entidades.SeguimientoEntidad;
import ec.gob.tiwintza.modelos.ArchivoModelo;
import ec.gob.tiwintza.modelos.SeguimientoArchivoModelo;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class TreeControlador {
    
    private ArrayList<SeguimientoEntidad> arrListSeguimiento;
    TreeNode root;
    private StreamedContent strFile;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the strFile
     */
    public StreamedContent getStrFile() {
        return strFile;
    }

    /**
     * @param strFile the strFile to set
     */
    public void setStrFile(StreamedContent strFile) {
        this.strFile = strFile;
    }

    /**
     * @return the arrListSeguimiento
     */
    public ArrayList<SeguimientoEntidad> getArrListSeguimiento() {
        return arrListSeguimiento;
    }

    /**
     * @param arrListSeguimiento the arrListSeguimiento to set
     */
    public void setArrListSeguimiento(ArrayList<SeguimientoEntidad> arrListSeguimiento) {
        this.arrListSeguimiento = arrListSeguimiento;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reint() {
    }

    /**
     * Creates a new instance of TreeControlador
     */
    public TreeControlador() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("tramiteControlador");
        arrListSeguimiento = new ArrayList<>();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">

    public TreeNode cargarTree(long lonTraId) {
        try {
            arrListSeguimiento = SeguimientoModelo.obtenerSeguimientoTree(lonTraId);
            String strCabeceraRoot = "1. " + TramiteModelo.obtenerTramiteTree(lonTraId);
            root = new DefaultTreeNode("Registros", null);
            TreeNode treAux;
            treAux = new DefaultTreeNode(strCabeceraRoot, root);
            String strCabeceraNodo;
            int intCon = 1;
            for (SeguimientoEntidad objAux : arrListSeguimiento) {
                strCabeceraNodo = ++intCon + ". " + SeguimientoModelo.obtenerSeguimientoNodo(lonTraId, objAux.getSeguimiento_id());
                treAux = new DefaultTreeNode(strCabeceraNodo, root);
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        root.setExpanded(true);
        return root;
    }
    
    public StreamedContent download(String strAux, long lonTraId) throws Exception {
        try {
            String strNum = strAux.substring(0, 1);
            if (strNum.equals("1")) {
                ArchivoEntidad objArchivo = ArchivoModelo.obtenerArchivoBlob(lonTraId);
                strFile = new DefaultStreamedContent(objArchivo.getArchivo_blob().getBinaryStream(), objArchivo.getArchivo_tipo(), objArchivo.getArchivo_nombre());
            }else{
                SeguimientoArchivoEntidad objArchivo=SeguimientoArchivoModelo.obtenerSeguimientoArchivoBlob(arrListSeguimiento.get(Integer.parseInt(strNum)-2));
                strFile = new DefaultStreamedContent(objArchivo.getSeguimiento_archivo_blob().getBinaryStream(), objArchivo.getSeguimiento_archivo_tipo(), objArchivo.getSeguimiento_archivo_nombre());
            }
        } catch (Exception e) {
            Util.addErrorMessage("No se ha subido un archivo todavía. "+ e.getMessage());
        }
        return strFile;
    }
    
    void Destroy() {
    }
    //</editor-fold>
}
