/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlListaRegistros;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaListaLibros;
import Modelo.Estudiante;
import Modelo.Libro;
import Vista.ListaLibros;
import Vista.ListaRegistros;

/**
 *
 * @author Rant AA
 */
public class controlListaRegistros {
    public static void main(String[] args) {
        Libro lib=new Libro();
        Estudiante es=new Estudiante();
        ConsultaLibro con=new ConsultaLibro();
        //ListaLibros form=new ListaLibros();
         ListaRegistros lr=new ListaRegistros();
        ConsultaListaLibros consEs=new ConsultaListaLibros();
        ConsultaEstudiante conses=new ConsultaEstudiante();
        //Libro lib, Estudiante es, ConsultaEstudiante conses, ConsultaLibro conslib
        ctrlListaRegistros ctrl=new ctrlListaRegistros(lr,lib ,es, conses,con);
        ctrl.iniciar();
        lr.setVisible(true);
    }
}
