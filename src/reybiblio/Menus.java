/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlMenu;
import Vista.Menu;

/**
 *
 * @author Rant AA
 */
public class Menus {
    public static void main(String[] args) {
        Menu m=new Menu();
        ctrlMenu con=new ctrlMenu(m);        
        con.iniciar();
        m.setVisible(true);
    }
}
