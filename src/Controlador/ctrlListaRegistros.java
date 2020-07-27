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
import Modelo.ConsultaListaRegistros;
import Modelo.ConsultaRegistro;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.Registro;
import Vista.Erro;
import Vista.ListaRegistros;
import Vista.ModRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rant AA
 */
public class ctrlListaRegistros implements ActionListener {

    ListaRegistros lr;
    Libro lib;
    Estudiante es;
    ConsultaEstudiante conses;
    ConsultaLibro conslib;
    ConsultaListaRegistros conslisr;
    ConsultaListaEstudiantes conslises;
    ConsultaListaLibros conslislib;

    public ctrlListaRegistros(ListaRegistros lr, Libro lib, Estudiante es, ConsultaEstudiante conses, ConsultaLibro conslib) {
        this.lib = lib;
        this.es = es;
        this.conses = conses;
        this.conslib = conslib;
        this.lr = lr;
        this.lr.btnBuscarRegistro.addActionListener(this);
        this.lr.btnEliminar.addActionListener(this);
        this.lr.btnModificar.addActionListener(this);
    }

    public void iniciar() {
        lr.setTitle("Control Registros");
        lr.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lr.btnBuscarRegistro) {
            try {
                //es = consles.getEstudianteSeleccionado();
                int tipo = lr.cbxBusquedaRegistrosLista.getSelectedIndex();
                if (tipo == 1 || tipo == 2 || tipo == 8) {
                    //Buscan dato de registro
                    System.out.println("Entro al if 1 2 3");
                    ArrayList<Registro> Estudiosos = null;
                    conslisr = new ConsultaListaRegistros();
                    String boole = "";
                    if (tipo == 8) {
                        if (lr.txtbusquedaRegistro.getText().equalsIgnoreCase("Entregado")) {
                            boole = "1";
                        }
                        if (lr.txtbusquedaRegistro.getText().equalsIgnoreCase("Pendiente")) {
                            boole = "0";
                        }
                        Estudiosos = conslisr.buscar(boole, tipo);
                        DefaultTableModel dtf = new DefaultTableModel();

                        // lr.jtRegistros.setModel(dtf);
                        lr.RellenadoGrupoRegistro(Estudiosos);
                    } else {
                        Estudiosos = conslisr.buscar(lr.txtbusquedaRegistro.getText(), tipo);
                        DefaultTableModel dtf = new DefaultTableModel();

                        // lr.jtRegistros.setModel(dtf);
                        lr.RellenadoGrupoRegistro(Estudiosos);
                    }

                }
                if (tipo == 3 || tipo == 4) {
                    //buscan dato de est
                    System.out.println("Entra al if de 3  4");
                    ArrayList<Registro> Estudiosos = null;
                    ConsultaListaRegistros consre = new ConsultaListaRegistros();
                    if (tipo == 3) {
                        Estudiosos = consre.buscarEstudiante(lr.txtbusquedaRegistro.getText(), 0);
                    } else {
                        Estudiosos = consre.buscarEstudiante(lr.txtbusquedaRegistro.getText(), 1);
                    }
                    lr.RellenadoGrupoRegistro(Estudiosos);
                }
                if (tipo == 5 || tipo == 6 || tipo == 7) {
                    //buscan dato de libro
                    System.out.println("Entra al de libros 5  6 7");
                    ArrayList<Registro> Estudiosos = null;
                    ConsultaListaRegistros consre = new ConsultaListaRegistros();
                    int tipofinal = 0;
                    if (tipo == 5) {
                        tipofinal = 1;
                    }
                    if (tipo == 6) {
                        tipofinal = 2;
                    }
                    if (tipo == 7) {
                        tipofinal = 3;
                    }
                    Estudiosos = consre.buscarLibro(lr.txtbusquedaRegistro.getText(), tipofinal);
                    lr.RellenadoGrupoRegistro(Estudiosos);
                }

                System.out.println("Categoria sel: " + lr.cbxBusquedaRegistrosLista.getSelectedItem());
                //lr.Rellenado(Estudiosos);
            } catch (Exception ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(er.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == lr.btnEliminar) {
            try {

                if (!lr.txtbusquedaRegistro.getText().equals("") && lr.jtRegistros.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un elemento de la tabla para eliminar");

                }

                if (lr.jtRegistros.getSelectedRow() != -1) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    //System.out.println("Elegiste ir por tabla");
                    if (resp == 0) {
                        //System.out.println("Entra al if del jopttion");
                        conslisr = new ConsultaListaRegistros();
                        Registro es = conslisr.buscarconId(Integer.parseInt(lr.jtRegistros.getValueAt(lr.jtRegistros.getSelectedRow(), 0).toString()));
                        System.out.println("id " + lr.jtRegistros.getValueAt(lr.jtRegistros.getSelectedRow(), 0));
                        //es = (consles.BuscarEs());
                        System.out.println("Registro: " + es.getIdRegistro() + " idEs " + es.getIdEstudiante());
                        conslisr.Eliminar(es);
                        System.out.println("Eliminado?");
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
        if (e.getSource() == lr.btnModificar) {
            try {
                if (lr.jtRegistros.getSelectedRow() != -1) {
                    //Por tabla
                    conslisr = new ConsultaListaRegistros();
                    Registro es = conslisr.buscarconId(Integer.parseInt(lr.jtRegistros.getValueAt(lr.jtRegistros.getSelectedRow(), 0).toString()));
                    System.out.println("id " + lr.jtRegistros.getValueAt(lr.jtRegistros.getSelectedRow(), 0));
                    //es = (consles.BuscarEs());
                    System.out.println("Registro: " + es.getIdRegistro() + " idEs " + es.getIdEstudiante());
                    //Registro es = conslislib.BuscarEsTabla(lislib);

                    ModRegistro mo = new ModRegistro();
                    mo.fped.setText(es.getFechapedido());
                    mo.fent.setText(es.getFechaentrega());
                    mo.txides.setText(String.valueOf(es.getIdEstudiante()));
                    mo.txidli.setText(String.valueOf(es.getIdLibro()));
                    if (es.isEstado()) {
                        mo.rbtrue.setSelected(true);
                    } else {
                        mo.rbfalse.setSelected(true);
                    }
                    mo.txidRegistro.setText(String.valueOf(es.getIdRegistro()));
                    mo.txidRegistro.setVisible(false);

                    mo.setDefaultCloseOperation(mo.DISPOSE_ON_CLOSE);
                    mo.setVisible(true);
                }
                if (lr.jtRegistros.getSelectedRow() != -1 && !lr.txtbusquedaRegistro.getText().equals("")) {
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
