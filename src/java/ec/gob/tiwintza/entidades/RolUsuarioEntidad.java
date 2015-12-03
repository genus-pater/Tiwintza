/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

import java.sql.Timestamp;

/**
 *
 * @author eborja
 */
public class RolUsuarioEntidad {

    private UsuarioEntidad usuario_id;
    private RolEntidad rol_id;
    private Timestamp rol_usuario_fecha_crecion;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">

    /**
     * @return the usuario_id
     */
    public UsuarioEntidad getUsuario_id() {
        return usuario_id;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(UsuarioEntidad usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * @return the rol_id
     */
    public RolEntidad getRol_id() {
        return rol_id;
    }

    /**
     * @param rol_id the rol_id to set
     */
    public void setRol_id(RolEntidad rol_id) {
        this.rol_id = rol_id;
    }

    /**
     * @return the rol_usuario_fecha_crecion
     */
    public Timestamp getRol_usuario_fecha_crecion() {
        return rol_usuario_fecha_crecion;
    }

    /**
     * @param rol_usuario_fecha_crecion the rol_usuario_fecha_crecion to set
     */
    public void setRol_usuario_fecha_crecion(Timestamp rol_usuario_fecha_crecion) {
        this.rol_usuario_fecha_crecion = rol_usuario_fecha_crecion;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public RolUsuarioEntidad(UsuarioEntidad usuario_id, RolEntidad rol_id, Timestamp rol_usuario_fecha_crecion) {
        this.usuario_id = usuario_id;
        this.rol_id = rol_id;
        this.rol_usuario_fecha_crecion = rol_usuario_fecha_crecion;
    }

    public RolUsuarioEntidad() {
        this.usuario_id=new UsuarioEntidad();
        this.rol_id=new RolEntidad();
    }

    public RolUsuarioEntidad(UsuarioEntidad usuario_id, RolEntidad rol_id) {
        this.usuario_id = usuario_id;
        this.rol_id = rol_id;
    }
    //</editor-fold>
}
