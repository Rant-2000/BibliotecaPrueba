/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlRegistro;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaRegistro;
import Modelo.Registro;
import Vista.formRegistro;
import java.time.LocalDate;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 *
 * @author Rant AA
 */
public class registros {

    public static void main(String[] args) {
        Registro es=new Registro();
        ConsultaRegistro con=new ConsultaRegistro();
        //formRegistro form=new formRegistro();
        RegistroLibros  form=new RegistroLibros();
        ConsultaEstudiante consEs=new ConsultaEstudiante();
        ConsultaLibro conslib=new ConsultaLibro();
        ctrlRegistro ctrl=new ctrlRegistro(consEs,conslib,es, con, form);
        ctrl.iniciar();
        form.setVisible(true);
         

        
        
    }
}
