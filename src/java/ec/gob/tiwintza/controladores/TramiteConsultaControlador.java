/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.controladores;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.ComentarioSeguimientoEntidad;
import ec.gob.tiwintza.entidades.TramiteEntidad;
import ec.gob.tiwintza.modelos.SeguimientoArchivoModelo;
import ec.gob.tiwintza.modelos.SeguimientoModelo;
import ec.gob.tiwintza.modelos.TramiteModelo;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class TramiteConsultaControlador {

    private TramiteEntidad objTramite;
    private TramiteEntidad objConsulta;
    private ArrayList<ComentarioSeguimientoEntidad> arrLisSeguimientoConsulta;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the arrLisSeguimientoConsulta
     */
    public ArrayList<ComentarioSeguimientoEntidad> getArrLisSeguimientoConsulta() {
        return arrLisSeguimientoConsulta;
    }

    /**
     * @param arrLisSeguimientoConsulta the arrLisSeguimientoConsulta to set
     */
    public void setArrLisSeguimientoConsulta(ArrayList<ComentarioSeguimientoEntidad> arrLisSeguimientoConsulta) {
        this.arrLisSeguimientoConsulta = arrLisSeguimientoConsulta;
    }

    /**
     * @return the objConsulta
     */
    public TramiteEntidad getObjConsulta() {
        return objConsulta;
    }

    /**
     * @param objConsulta the objConsulta to set
     */
    public void setObjConsulta(TramiteEntidad objConsulta) {
        this.objConsulta = objConsulta;
    }

    /**
     * @return the objTramite
     */
    public TramiteEntidad getObjTramite() throws Exception {
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
    @PostConstruct
    public void reinit() {
        cargarSeguimientoConsulta();
    }

    /**
     * Creates a new instance of TramiteConsultaControlador
     */
    public TramiteConsultaControlador() {
        objTramite = new TramiteEntidad();
        arrLisSeguimientoConsulta = new ArrayList<>();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public String formato(String strDes) {
        int intLen = strDes.length();
        int intPaso = 100;
        int intAux = 0;
        String strRes = "";
        try {
            while (intPaso < intLen) {
                String strAux = "";
                int intCon = 0;
                do {
                    if (intLen - intPaso >= 100) {
                        if (strDes.substring(intPaso - 1, intPaso - 1).equals(" ")) {
                            strAux = strDes.substring(intAux, intPaso);
                            if (intPaso < intLen) {
                                intAux = intPaso;
                                intPaso += 100;
                            }
                            intCon = 6;
                        } else {
                            ++intCon;
                            ++intPaso;
                        }
                    } else {
                        intCon = 6;
                    }
                } while (intCon <= 5);
                if (intPaso < 1000) {
                    strAux = strDes.substring(intAux, intPaso) + "\n";
                } else {
                    strAux = strDes.substring(intAux, 1000) + "\n";
                }
                intAux = intPaso;
                intPaso += 100;
                strRes = strRes + strAux;
            }
        } catch (Exception e) {
            int kk = 0;
        }
        return strRes;
    }

    public void cargarSeguimientoConsulta() {
        try {
            this.arrLisSeguimientoConsulta = SeguimientoModelo.obtenerSeguimientoConsulta(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod"));
        } catch (Exception e) {
        }
    }

    public String loginConsulta() throws Exception {
        if (TramiteModelo.obtenerTramiteConsulta(objTramite.getTramite_codigo()).size() > 0) {
            return "/templates/templateConsulta?cod=" + objTramite.getTramite_codigo() + "&dis=0&faces-redirect=true";
        } else {
            Util.addErrorMessage("No existe el trámite que está buscando.");
            return null;
        }
    }

    public String solicitante() throws Exception {
        try {
            cargarConsulta();
            String strAux = objConsulta.getPersona_fk().getPersona_nombre() + " " + objConsulta.getPersona_fk().getPersona_apellido();
            if (strAux.length() >= 22) {
                strAux = strAux.substring(0, 20).concat("...");
            }
            String strDis = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dis");
            if (objConsulta.isTramite_asignacion() == false && strDis.equals("0")) {
                RequestContext.getCurrentInstance().execute("{PF('barConsulta').show()}");
            }
            return strAux;
        } catch (Exception e) {
            return null;
        }
    }

    public void cargar() throws IOException, Exception {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod") == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza;");
        }
        try {
            cargarConsulta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza;");
        }
    }

    public void cargarConsulta() throws Exception {
        if (objConsulta == null) {
            objConsulta = TramiteModelo.obtenerTramiteConsulta(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod")).get(0);
        }
    }

    public void salir() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza");
    }
    //</editor-fold>
}
