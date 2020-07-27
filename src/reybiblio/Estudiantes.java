/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlEstudiante;
import Modelo.ConsultaEstudiante;
import Modelo.Estudiante;
import Vista.formEstudiante;


public class Estudiantes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Estudiante es=new Estudiante();
        ConsultaEstudiante con=new ConsultaEstudiante();
        formEstudiante form=new formEstudiante();
        
        
        ctrlEstudiante ctrl=new ctrlEstudiante(es, con, form);
        ctrl.iniciar();
        form.setVisible(true);
    }
    
}
