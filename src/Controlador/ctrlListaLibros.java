/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaLibro;
import Modelo.ConsultaListaLibros;
import Modelo.Libro;
import Vista.Erro;
import Vista.ListaLibros;
import Vista.ModLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reybiblio.Libros;

/**
 *
 * @author Rant AA
 */
public class ctrlListaLibros implements ActionListener {

    ConsultaListaLibros conslislib;
    Libro lib;
    ListaLibros lislib;
    ConsultaLibro conslib;

    public ctrlListaLibros(ListaLibros lislib, ConsultaLibro conslib, Libro lib, ConsultaListaLibros conslislib) {
        this.lislib = lislib;
        this.conslislib = conslislib;
        this.lib = lib;
        this.lislib.btnBuscarLibroListado.addActionListener(this);
        this.lislib.btnModificarLibroListado.addActionListener(this);
        this.lislib.ElimListaLibros.addActionListener(this);
        this.conslib = conslib;
    }

    public void iniciar() {
        lislib.setTitle("Control Libros");
        lislib.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lislib.btnBuscarLibroListado) {
            try {
                //es = consles.getEstudianteSeleccionado();
                int tipo = lislib.cbxLisLibros.getSelectedIndex();
                ArrayList<Libro> Estudiosos = null;
                Estudiosos = conslislib.buscar(lislib.txtcampolibro.getText(), tipo);
                System.out.println("Categoria sel: " + lislib.cbxLisLibros.getSelectedItem());
                lislib.Rellenado(Estudiosos);
            } catch (Exception ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(er.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == lislib.ElimListaLibros) {
            try {

                if (!lislib.txtcampolibro.getText().equals("") && lislib.jtLibros.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un elemento de la tabla para eliminar");

                }

                if (lislib.txtcampolibro.getText().equals("") && lislib.jtLibros.getSelectedRow() != -1) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    //System.out.println("Elegiste ir por tabla");
                    if (resp == 0) {
                        //System.out.println("Entra al if del jopttion");
                        Libro es = conslislib.BuscarEsTabla(lislib);;
                        //es = (consles.BuscarEs());
                        conslislib.EliminarSeleccion(es);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ok");
                    }

                }
            } catch (Exception ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == lislib.btnModificarLibroListado) {
            try {
                if (lislib.jtLibros.getSelectedRow() != -1 && lislib.txtcampolibro.getText().equals("")) {
                    //Por tabla
                    Libro es = conslislib.BuscarEsTabla(lislib);
                    ModLibro mo = new ModLibro();
                    mo.txtit.setText(es.getTitulo());
                    mo.txisbn.setText(es.getIsbn());
                    mo.txtau.setText(es.getAutor());
                    mo.txed.setText(es.getEditorial());
                    mo.txa.setText(es.getAnho());
                    mo.txttotalLibro.setText(String.valueOf(es.getTotal()));
                    mo.txtExistenciaLibro.setText(String.valueOf(es.getExistencia()));
                    mo.txtidlibro.setText(String.valueOf(es.getId()));
                    mo.setDefaultCloseOperation(mo.DISPOSE_ON_CLOSE);
                    mo.Mostrar(es.getId());
                    mo.setVisible(true);
                }
                if (lislib.jtLibros.getSelectedRow() != -1 && !lislib.txtcampolibro.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione un elemento de la tabla para modificar");
                }

            } catch (Exception ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
    }
}
