/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaListaEstudiantes;
import Modelo.ConsultaListaLibros;
import Modelo.ConsultaRegistro;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.Registro;
import Vista.ListaEstudiantes;
import Vista.ListaLibros;
import Vista.ListaLibrosGral;
import Vista.ListaRegistros;
import Vista.Menu;
import Vista.formEstudiante;
import Vista.formRegistro;
import Vista.nuevoLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reybiblio.registros;

/**
 *
 * @author Rant AA
 */
public class ctrlMenu implements ActionListener {

    Menu m;

    public ctrlMenu(Menu m) {
        this.m = m;
        this.m.btnApartado.addActionListener(this);
        this.m.btnListaLibrosGral.addActionListener(this);
        this.m.btnVisitas.addActionListener(this);
        this.m.btnCrudEs.addActionListener(this);
        this.m.btnCrudLib.addActionListener(this);
        this.m.btnCrudReg.addActionListener(this);
        this.m.ControlEs.addActionListener(this);
        this.m.ControlLib.addActionListener(this);
        this.m.ControlReg.addActionListener(this);
    }

    public void iniciar() {
        m.setTitle("Menu");
        m.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m.btnApartado) {

            Registro es = new Registro();
            ConsultaRegistro con = new ConsultaRegistro();
            formRegistro form = new formRegistro();

            ctrlRegistro ctrl = new ctrlRegistro(es, con, form);
            ctrl.iniciar();
            form.setVisible(true);

        }
        if (e.getSource() == m.btnVisitas) {
            //Aun 
        }
        if (e.getSource() == m.btnListaLibrosGral) {
            ListaLibrosGral llg = new ListaLibrosGral();
            llg.setVisible(true);
        }
        if (e.getSource() == m.btnCrudEs) {
            Estudiante es = new Estudiante();
            ConsultaEstudiante con = new ConsultaEstudiante();
            formEstudiante form = new formEstudiante();

            ctrlEstudiante ctrl = new ctrlEstudiante(es, con, form);
            ctrl.iniciar();
            form.setVisible(true);
        }
        if (e.getSource() == m.btnCrudLib) {
            Libro es = new Libro();
            ConsultaLibro con = new ConsultaLibro();
            nuevoLibro form = new nuevoLibro();

            ctrlLibro ctrl = new ctrlLibro(es, con, form);
            ctrl.iniciar();
            form.setVisible(true);
        }
        if (e.getSource() == m.btnCrudReg) {
            Registro es = new Registro();
            ConsultaRegistro con = new ConsultaRegistro();
            formRegistro form = new formRegistro();

            ctrlRegistro ctrl = new ctrlRegistro(es, con, form);
            ctrl.iniciar();
            form.setVisible(true);
        }
        if (e.getSource() == m.ControlEs) {
            Estudiante es = new Estudiante();
            ConsultaEstudiante con = new ConsultaEstudiante();
            ListaEstudiantes form = new ListaEstudiantes();
            ConsultaListaEstudiantes consEs = new ConsultaListaEstudiantes();

            ctrlListaEstudiantes ctrl = new ctrlListaEstudiantes(form, consEs, con);
            ctrl.iniciar();
            form.setVisible(true);
        }
        if (e.getSource() == m.ControlLib) {
            Libro es = new Libro();
            ConsultaLibro con = new ConsultaLibro();
            ListaLibros form = new ListaLibros();
            ConsultaListaLibros consEs = new ConsultaListaLibros();

            ctrlListaLibros ctrl = new ctrlListaLibros(form, con, es, consEs);
            ctrl.iniciar();
            form.setVisible(true);
        }
        if (e.getSource() == m.ControlReg) {
            Libro lib = new Libro();
            Estudiante es = new Estudiante();
            ConsultaLibro con = new ConsultaLibro();

            ListaRegistros lr = new ListaRegistros();
            ConsultaListaLibros consEs = new ConsultaListaLibros();
            ConsultaEstudiante conses = new ConsultaEstudiante();

            ctrlListaRegistros ctrl = new ctrlListaRegistros(lr, lib, es, conses, con);
            ctrl.iniciar();
            lr.setVisible(true);
        }
    }
}
