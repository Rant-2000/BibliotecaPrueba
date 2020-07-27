/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlListaEstudiantes;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaListaEstudiantes;
import Modelo.Estudiante;
import Vista.ListaEstudiantes;

/**
 *
 * @author Rant AA
 */
public class controlListaEstudiantes {
    public static void main(String[] args) {
        Estudiante es=new Estudiante();
        ConsultaEstudiante con=new ConsultaEstudiante();
        ListaEstudiantes form=new ListaEstudiantes();
        ConsultaListaEstudiantes consEs=new ConsultaListaEstudiantes();
        
        ctrlListaEstudiantes ctrl=new ctrlListaEstudiantes(form, consEs, con);
        ctrl.iniciar();
        form.setVisible(true);
    }
}
