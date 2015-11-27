/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.sesiones;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.RolUsuarioEntidad;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import ec.gob.tiwintza.modelos.RolUsuarioModelo;
import ec.gob.tiwintza.modelos.UsuarioModelo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import org.primefaces.context.RequestContext;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class SesionControlador {

    private long intIdRol;
    private UsuarioEntidad objUsuario;
    private ArrayList<RolUsuarioEntidad> arrLisRolUsuarioAux;
    @ManagedProperty(value = "#{sesionUsuarioDataManager}")
    private SesionUsuarioDataManager dm;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the arrLisRolUsuarioAux
     * @throws java.lang.Exception
     */
    public ArrayList<RolUsuarioEntidad> getArrLisRolUsuarioAux() throws Exception {
        try {
            String strQuery = "call bd_st.pr_select_rol_usuario_sesion(\"" + dm.getSesionUsuarioActual().getUsuario_id() + "\")";
            setArrLisRolUsuarioAux(RolUsuarioModelo.obtenerRolUsuario(strQuery));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza");
        }
        return arrLisRolUsuarioAux;
    }

    /**
     * @param arrLisRolUsuarioAux the arrLisRolUsuarioAux to set
     */
    public void setArrLisRolUsuarioAux(ArrayList<RolUsuarioEntidad> arrLisRolUsuarioAux) {
        this.arrLisRolUsuarioAux = arrLisRolUsuarioAux;
    }

    /**
     * @return the intIdRol
     */
    public long getIntIdRol() throws IOException {
        try {
            intIdRol = dm.getSesionRolUsuarioActual().getRol_id().getRol_id();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza");
        }
        return intIdRol;
    }

    /**
     * @param intIdRol the intIdRol to set
     */
    public void setIntIdRol(long intIdRol) {
        this.intIdRol = intIdRol;
    }

    /**
     * @return the objUsuario
     */
    public UsuarioEntidad getObjUsuario() {
        return objUsuario;
    }

    /**
     * @param objUsuario the objUsuario to set
     */
    public void setObjUsuario(UsuarioEntidad objUsuario) {
        this.objUsuario = objUsuario;
    }

    /**
     * @return the dm
     */
    public SesionUsuarioDataManager getDm() {
        return dm;
    }

    /**
     * @param dm the dm to set
     */
    public void setDm(SesionUsuarioDataManager dm) {
        this.dm = dm;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    /**
     * Creates a new instance of SesionControlador
     */
    public SesionControlador() {
        objUsuario = new UsuarioEntidad();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void cambioRol() throws IOException {
        try {
            dm.getSesionRolUsuarioActual().getRol_id().setRol_id(intIdRol);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza/faces/templates/templateFormularios.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/jpaSGCest");
        }
    }

    public String cerrarSesion() throws IOException {
        this.dm.Destroy();
        this.Destroy();
        return "/index?faces-redirect=true";
    }
    
    public String comprobarClave(String strClave){
        if(strClave.length()<128){
            return "0"+strClave;
        }else
            return strClave;
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public String login() throws Exception {
        try {
            String strEncryptPass = encryptPassword(objUsuario.getUsuario_password());
            strEncryptPass=comprobarClave(strEncryptPass);
            Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
            Cookie cooUserSesion = (Cookie) cookies.get("user");
            Cookie cooPassSesion = (Cookie) cookies.get("pass");
            if (cooPassSesion != null && cooUserSesion != null) {
                if (!(cooPassSesion.getValue().equals(strEncryptPass) && cooUserSesion.getValue().equals(objUsuario.getUsuario_cuenta()))) {
                    RequestContext.getCurrentInstance().execute("{deleteCookies()}");
                }
            }
            String strQuery = "call bd_st.pr_select_usuario_sesion(\"" + objUsuario.getUsuario_cuenta() + "\",\"" + strEncryptPass + "\")";
            ArrayList<UsuarioEntidad> arrListAux = UsuarioModelo.obtenerUsuario(strQuery);
            UsuarioEntidad objUsuarioAux = arrListAux.get(0);
            strQuery = "call bd_st.pr_select_rol_usuario_sesion(\"" + objUsuarioAux.getUsuario_id() + "\")";
            setArrLisRolUsuarioAux(RolUsuarioModelo.obtenerRolUsuario(strQuery));
            this.dm.setSesionUsuarioActual(objUsuarioAux);
            this.dm.setSesionRolUsuarioActual(getArrLisRolUsuarioAux().get(0));
            return "/templates/templateFormularios?faces-redirect=true";
        } catch (Exception e) {
            Util.addErrorMessage("Usuario o contraseña INCORRECTAS");
            RequestContext.getCurrentInstance().execute("{reiniciarCookies()}");
            return null;
        }
    }

    public void keepMeLogued() {
        try {
            Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
            Cookie cooUserSesion = (Cookie) cookies.get("user");
            Cookie cooPassSesion = (Cookie) cookies.get("pass");
            String strUserSesion = cooUserSesion.getValue();
            String strPassSesion = cooPassSesion.getValue();
            String strQuery = "call bd_st.pr_select_usuario_sesion(\"" + strUserSesion + "\",\"" + strPassSesion + "\")";
            ArrayList<UsuarioEntidad> arrListAux = UsuarioModelo.obtenerUsuario(strQuery);
            UsuarioEntidad objUsuarioAux = arrListAux.get(0);
            strQuery = "call bd_st.pr_select_rol_usuario_sesion(\"" + objUsuarioAux.getUsuario_id() + "\")";
            setArrLisRolUsuarioAux(RolUsuarioModelo.obtenerRolUsuario(strQuery));
            this.dm.setSesionUsuarioActual(objUsuarioAux);
            this.dm.setSesionRolUsuarioActual(getArrLisRolUsuarioAux().get(0));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza/faces/templates/templateFormularios.xhtml");
        } catch (Exception e) {

        }
    }

    void Destroy() {
        intIdRol = 0;
        objUsuario = null;
    }
    //</editor-fold>
}
