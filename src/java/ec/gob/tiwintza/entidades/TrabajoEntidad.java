/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.entidades;

/**
 *
 * @author eborja
 */
public class TrabajoEntidad {

    private RolUsuarioEntidad rol_usuario_fk;
    private DepartamentoEntidad departamento_fk;
    private boolean trabajo_estado;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">  

    /**
     * @return the rol_usuario_fk
     */
    public RolUsuarioEntidad getRol_usuario_fk() {
        return rol_usuario_fk;
    }

    /**
     * @param rol_usuario_fk the rol_usuario_fk to set
     */
    public void setRol_usuario_fk(RolUsuarioEntidad rol_usuario_fk) {
        this.rol_usuario_fk = rol_usuario_fk;
    }

    /**
     * @return the departamento_fk
     */
    public DepartamentoEntidad getDepartamento_fk() {
        return departamento_fk;
    }

    /**
     * @param departamento_fk the departamento_fk to set
     */
    public void setDepartamento_fk(DepartamentoEntidad departamento_fk) {
        this.departamento_fk = departamento_fk;
    }

    /**
     * @return the trabajo_estado
     */
    public boolean isTrabajo_estado() {
        return trabajo_estado;
    }

    /**
     * @param trabajo_estado the trabajo_estado to set
     */
    public void setTrabajo_estado(boolean trabajo_estado) {
        this.trabajo_estado = trabajo_estado;
    }

       //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">

    public TrabajoEntidad(RolUsuarioEntidad rol_usuario_fk, DepartamentoEntidad departamento_fk, boolean trabajo_estado) {
        this.rol_usuario_fk = rol_usuario_fk;
        this.departamento_fk = departamento_fk;
        this.trabajo_estado = trabajo_estado;
    }

    public TrabajoEntidad(RolUsuarioEntidad rol_usuario_fk, DepartamentoEntidad departamento_fk) {
        this.rol_usuario_fk = rol_usuario_fk;
        this.departamento_fk = departamento_fk;
    }

    public TrabajoEntidad(DepartamentoEntidad departamento_fk, boolean trabajo_estado) {
        this.departamento_fk = departamento_fk;
        this.trabajo_estado = trabajo_estado;
    }

    public TrabajoEntidad() {
        rol_usuario_fk=new RolUsuarioEntidad();
        departamento_fk=new DepartamentoEntidad();
    }
    
    //</editor-fold>
}
