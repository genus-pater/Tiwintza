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
public class MenuEntidad {

    private long menu_id;
    private String menu_nombre;
    private String menu_tipo;
    private RolEntidad rol_fk;
    private boolean menu_estado;
    private MenuEntidad menu;
    private String menu_ruta;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the menu_id
     */
    public long getMenu_id() {
        return menu_id;
    }

    /**
     * @param menu_id the menu_id to set
     */
    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
    }

    /**
     * @return the menu_nombre
     */
    public String getMenu_nombre() {
        return menu_nombre;
    }

    /**
     * @param menu_nombre the menu_nombre to set
     */
    public void setMenu_nombre(String menu_nombre) {
        this.menu_nombre = menu_nombre;
    }

    /**
     * @return the menu_tipo
     */
    public String getMenu_tipo() {
        return menu_tipo;
    }

    /**
     * @param menu_tipo the menu_tipo to set
     */
    public void setMenu_tipo(String menu_tipo) {
        this.menu_tipo = menu_tipo;
    }

    /**
     * @return the rol_fk
     */
    public RolEntidad getRol_fk() {
        return rol_fk;
    }

    /**
     * @param rol_fk the rol_fk to set
     */
    public void setRol_fk(RolEntidad rol_fk) {
        this.rol_fk = rol_fk;
    }

    /**
     * @return the menu_estado
     */
    public boolean isMenu_estado() {
        return menu_estado;
    }

    /**
     * @param menu_estado the menu_estado to set
     */
    public void setMenu_estado(boolean menu_estado) {
        this.menu_estado = menu_estado;
    }

    /**
     * @return the muenu_id_menu
     */
    public MenuEntidad getMenu() {
        return menu;
    }

    /**
     * @param menu
     */
    public void setMenu(MenuEntidad menu) {
        this.menu = menu;
    }

    /**
     * @return the menu_ruta
     */
    public String getMenu_ruta() {
        return menu_ruta;
    }

    /**
     * @param menu_ruta the menu_ruta to set
     */
    public void setMenu_ruta(String menu_ruta) {
        this.menu_ruta = menu_ruta;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public MenuEntidad(long menu_id, String menu_nombre, String menu_tipo, RolEntidad rol_fk, boolean menu_estado) {
        this.menu_id = menu_id;
        this.menu_nombre = menu_nombre;
        this.menu_tipo = menu_tipo;
        this.rol_fk = rol_fk;
        this.menu_estado = menu_estado;
    }

    public MenuEntidad(long menu_id) {
        this.menu_id = menu_id;
    }

    public MenuEntidad() {
    }

    public MenuEntidad(long menu_id, String menu_nombre, String menu_tipo, RolEntidad rol_fk, boolean menu_estado, MenuEntidad menu, String menu_ruta) {
        this.menu_id = menu_id;
        this.menu_nombre = menu_nombre;
        this.menu_tipo = menu_tipo;
        this.rol_fk = rol_fk;
        this.menu_estado = menu_estado;
        this.menu = menu;
        this.menu_ruta = menu_ruta;
    }

    //</editor-fold> 
}
