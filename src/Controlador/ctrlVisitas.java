/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaVisita;
import Modelo.Estudiante;
import Modelo.Visita;
import Vista.formVisitas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rant AA
 */
public class ctrlVisitas implements ActionListener {

    ConsultaVisita consv;
    Visita vis;
    Estudiante es;
    ConsultaEstudiante conses;
    formVisitas frm;

    public ctrlVisitas(formVisitas frm) {
        this.frm = frm;
    }

    public void iniciar() {
        frm.setTitle("Control Libros");
        frm.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
