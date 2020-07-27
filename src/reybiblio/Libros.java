/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlLibro;
import Modelo.ConsultaLibro;
import Modelo.Libro;
import Vista.nuevoLibro;

/**
 *
 * @author Rant AA
 */
public class Libros {
    public static void main(String[] args) {
        Libro es=new Libro();
        ConsultaLibro con=new ConsultaLibro();
        nuevoLibro form=new nuevoLibro();
        
        
        ctrlLibro ctrl=new ctrlLibro(es, con, form);
        ctrl.iniciar();
        form.setVisible(true);
    }
}
