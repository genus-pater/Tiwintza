/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.tiwintza.modelos;

import ec.gob.tiwintza.accesodatos.AccesoDatos;
import ec.gob.tiwintza.accesodatos.ConjuntoResultado;
import ec.gob.tiwintza.accesodatos.Parametro;
import ec.gob.tiwintza.entidades.MenuEntidad;
import ec.gob.tiwintza.entidades.RolEntidad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eborja
 */
public class MenuModelo {

    public static ArrayList<MenuEntidad> obtenerMenu() throws Exception {
        ArrayList<MenuEntidad> arrLstMenu = new ArrayList<>();
        try {
            String strSql = "call bd_st.pr_select_menu(); ";
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql);
            arrLstMenu = llenarMenu(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstMenu;
    }

    public static ArrayList<MenuEntidad> llenarMenu(ConjuntoResultado conResultado) throws Exception {
        ArrayList<MenuEntidad> arrLstMenu = new ArrayList<>();
        MenuEntidad objMenu;
        try {
            while (conResultado.next()) {
                try {
                    objMenu = new MenuEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                            conResultado.getString(1), conResultado.getString(2),
                            new RolEntidad(Long.parseLong(conResultado.getBigInteger(3).toString())),
                            conResultado.getBoolean(4), new MenuEntidad(Long.parseLong(conResultado.getBigInteger(5).toString())),
                            conResultado.getString(6));
                    arrLstMenu.add(objMenu);
                } catch (Exception e) {
                    objMenu = new MenuEntidad(Long.parseLong(conResultado.getBigInteger(0).toString()),
                            conResultado.getString(1), conResultado.getString(2),
                            new RolEntidad(Long.parseLong(conResultado.getBigInteger(3).toString())),
                            conResultado.getBoolean(4));
                    arrLstMenu.add(objMenu);
                }
            }
        } catch (Exception e) {
            arrLstMenu.clear();
            throw e;
        }
        return arrLstMenu;
    }

    public static List<MenuEntidad> obtenerMenuSesion(long lonId) throws Exception {
        ArrayList<MenuEntidad> arrLstMenu = new ArrayList<>();
        try {
            ArrayList<Parametro> arrLisPar=new ArrayList<>();
            String strSql = "call bd_st.pr_select_menu_sesion(?); ";
            arrLisPar.add(new Parametro(1, lonId));
            ConjuntoResultado conResultado = AccesoDatos.ejecutaQuery(strSql,arrLisPar);
            arrLstMenu = llenarMenu(conResultado);
            conResultado = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arrLstMenu;
    }

}
