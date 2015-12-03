/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.sesiones;

import ec.edu.espoch.sga.recursos.Util;
import ec.gob.tiwintza.entidades.UsuarioEntidad;
import ec.gob.tiwintza.modelos.UsuarioModelo;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class PerfilControlador {

    private SesionUsuarioDataManager sesion;
    private String strPasswordActual;
    private String strPasswordNueva;
    private String strConfirmarPasswordNueva;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the strPasswordActual
     */
    public String getStrPasswordActual() {
        return strPasswordActual;
    }

    /**
     * @param strPasswordActual the strPasswordActual to set
     */
    public void setStrPasswordActual(String strPasswordActual) {
        this.strPasswordActual = strPasswordActual;
    }

    /**
     * @return the strPasswordNueva
     */
    public String getStrPasswordNueva() {
        return strPasswordNueva;
    }

    /**
     * @param strPasswordNueva the strPasswordNueva to set
     */
    public void setStrPasswordNueva(String strPasswordNueva) {
        this.strPasswordNueva = strPasswordNueva;
    }

    /**
     * @return the strConfirmarPasswordNueva
     */
    public String getStrConfirmarPasswordNueva() {
        return strConfirmarPasswordNueva;
    }

    /**
     * @param strConfirmarPasswordNueva the strConfirmarPasswordNueva to set
     */
    public void setStrConfirmarPasswordNueva(String strConfirmarPasswordNueva) {
        this.strConfirmarPasswordNueva = strConfirmarPasswordNueva;
    }

    /**
     * @return the sesion
     */
    public SesionUsuarioDataManager getSesion() {
        return sesion;
    }

    /**
     * @param sesion the sesion to set
     */
    public void setSesion(SesionUsuarioDataManager sesion) {
        this.sesion = sesion;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Creates a new instance of PerfilControlador
     */
    public PerfilControlador() {
        sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public String comprobarClave(String strClave) {
        if (strClave.length() < 128) {
            return "0" + strClave;
        } else {
            return strClave;
        }
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public void actualizarPerfil() {
        try {
            if (UsuarioModelo.actualizarUsuario(sesion.getSesionUsuarioActual()) > 1) {
                Util.addSuccessMessage("Perfil ACTUALIZADO");
            } else {
                actualizarSesion();
                Util.mostrarMensaje("No se pudo actualizar el Perfil");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizarPassword() {
        try {
            String strPasswordActualEncrypt = encryptPassword(strPasswordActual);
            strPasswordActualEncrypt = comprobarClave(strPasswordActualEncrypt);
            String strPasswordNuevaEncrypt = encryptPassword(strPasswordNueva);
            strPasswordNuevaEncrypt = comprobarClave(strPasswordNuevaEncrypt);
            String strConfirmarPasswordNuevaEncrypt = encryptPassword(strConfirmarPasswordNueva);
            strConfirmarPasswordNuevaEncrypt = comprobarClave(strConfirmarPasswordNuevaEncrypt);
            if (strPasswordActualEncrypt.equals(sesion.getSesionUsuarioActual().getUsuario_password())) {
                sesion.getSesionUsuarioActual().setUsuario_password(strConfirmarPasswordNuevaEncrypt);
                if (UsuarioModelo.actualizarUsuarioPassword(sesion.getSesionUsuarioActual()) > 1) {
                    Util.addSuccessMessage("Perfil ACTUALIZADO");
                } else {
                    actualizarSesion();
                    Util.mostrarMensaje("No se pudo actualizar el Perfil");
                }
            } else {
                Util.addErrorMessage("Contraseña INCORRECTA");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void actualizarSesion() throws Exception {
        String strQuery = "call bd_st.pr_select_usuario_sesion(\"" + sesion.getSesionUsuarioActual().getUsuario_cuenta() + "\",\"" + sesion.getSesionUsuarioActual().getUsuario_password() + "\")";
        ArrayList<UsuarioEntidad> arrListAux = UsuarioModelo.obtenerUsuario(strQuery);
        UsuarioEntidad objUsuarioAux = arrListAux.get(0);
        sesion.setSesionUsuarioActual(objUsuarioAux);
    }
    //</editor-fold>

}
