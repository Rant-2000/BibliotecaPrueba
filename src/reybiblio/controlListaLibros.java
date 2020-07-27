/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlListaLibros;
import Modelo.ConsultaLibro;
import Modelo.ConsultaListaLibros;
import Modelo.Libro;
import Vista.ListaLibros;

/**
 *
 * @author Rant AA
 */
public class controlListaLibros {
    public static void main(String[] args) {
        Libro es=new Libro();
        ConsultaLibro con=new ConsultaLibro();
        ListaLibros form=new ListaLibros();
        ConsultaListaLibros consEs=new ConsultaListaLibros();
        
        ctrlListaLibros ctrl=new ctrlListaLibros(form,con ,es, consEs);
        ctrl.iniciar();
        form.setVisible(true);
    }
   
}
