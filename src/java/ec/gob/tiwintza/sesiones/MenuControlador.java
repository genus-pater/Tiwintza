/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.sesiones;

import ec.gob.tiwintza.entidades.MenuEntidad;
import ec.gob.tiwintza.modelos.MenuModelo;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author eborja
 */
@ManagedBean
@RequestScoped
public class MenuControlador {

    private List<MenuEntidad> lisMenus;
    private MenuModel objMenuModel;

    /**
     * Creates a new instance of MenuControlador
     */
    public MenuControlador() {

    }

    @PostConstruct
    public void init() {
        try {
            this.listarMenu();
            this.setObjMenuModel(new DefaultMenuModel());
            this.establecerPermisos();
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza");
            } catch (IOException ex) {
                Logger.getLogger(MenuControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarMenu() {
        try {
            setLisMenus(MenuModelo.obtenerMenu());
        } catch (Exception e) {
            //mensaje jsf
        }
    }

    public void establecerPermisos() throws IOException {
        SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
        for (MenuEntidad m : sesion.getLisMenu()) {
            if (m.getMenu_tipo().equals("SubMenu")) {
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getMenu_nombre());
                for (MenuEntidad i : getLisMenus()) {
                    MenuEntidad subMenu = i.getMenu();
                    if (subMenu != null) {
                        if (Objects.equals(subMenu.getMenu_id(), m.getMenu_id())) {
                            DefaultMenuItem item = new DefaultMenuItem(i.getMenu_nombre());
                            //item.setUpdate(":paginas");
                            //item.setUrl(i.getMenuEntidadruta());
                            item.setAjax(true);
                            item.setOutcome(i.getMenu_ruta());
                            firstSubmenu.addElement(item);
                        }
                    }
                }
                getObjMenuModel().addElement(firstSubmenu);
            } else {
                if (m.getMenu() == null && verificar(m)) {
                    DefaultSubMenu item = new DefaultSubMenu(m.getMenu_nombre());
                    getObjMenuModel().addElement(item);
                }
            }
        }
    }

    public boolean verificar(MenuEntidad obj) throws IOException {
        boolean res = false;
        try {
            SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");
            if (sesion.getSesionRolUsuarioActual().getRol_id().getRol_id() == obj.getRol_fk().getRol_id()) {
                res = true;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tiwintza");
        }
        return res;
    }

    /**
     * @return the lisMenus
     */
    public List<MenuEntidad> getLisMenus() {
        return lisMenus;
    }

    /**
     * @param lisMenus the lisMenus to set
     */
    public void setLisMenus(List<MenuEntidad> lisMenus) {
        this.lisMenus = lisMenus;
    }

    /**
     * @return the objMenuModel
     */
    public MenuModel getObjMenuModel() {
        return objMenuModel;
    }

    /**
     * @param objMenuModel the objMenuModel to set
     */
    public void setObjMenuModel(MenuModel objMenuModel) {
        this.objMenuModel = objMenuModel;
    }

}
