/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaListaEstudiantes;
import Modelo.Estudiante;
import Vista.Erro;
import Vista.ListaEstudiantes;
import Vista.ModEstudiante;
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
public class ctrlListaEstudiantes implements ActionListener {

    ListaEstudiantes les;
    ConsultaListaEstudiantes consles;
    ConsultaEstudiante cones;
    Estudiante es;

    public ctrlListaEstudiantes(ListaEstudiantes les, ConsultaListaEstudiantes consles, ConsultaEstudiante cones) {
        this.les = les;
        this.consles = consles;
        this.cones = cones;
        this.les.btnBuscarListaEs.addActionListener(this);
        this.les.btnElimLisEs.addActionListener(this);
        this.les.bnModListaEs.addActionListener(this);
    }

    public void iniciar() {
        les.setTitle("Control de Estudiantes");
        les.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == les.btnBuscarListaEs) {
            try {
                //es = consles.getEstudianteSeleccionado();
                int tipo = les.cbxBuscarPor_ListaEstudiantes.getSelectedIndex();
                ArrayList<Estudiante> Estudiosos = null;
                Estudiosos = consles.buscar(les.txtBuscadoListaEstudiantes.getText(), tipo);
                System.out.println("Categoria sel: " + les.cbxBuscarPor_ListaEstudiantes.getSelectedIndex());
                les.Rellenado(Estudiosos);
            } catch (Exception ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == les.btnElimLisEs) {
            try {

                //int Seleccion = les.cbxBuscarPor_ListaEstudiantes.getSelectedIndex();
                if (!les.txtBuscadoListaEstudiantes.getText().equals("") && les.jtEstudiante.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un elemento de la tabla para eliminar");
                    
                }
                /*if (les.jtEstudiante.getSelectedRow() == -1 && !les.txtBuscadoListaEstudiantes.getText().equals("")) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    System.out.println("Elegiste ir por texto");
                    if (resp == 0) {
                        es = new Estudiante();
                        consles = new ConsultaListaEstudiantes();
                        String dato = les.txtBuscadoListaEstudiantes.getText();

                        System.out.println("Dentro de resp=1 " + dato + " categoria: " + les.cbxBuscarPor_ListaEstudiantes.getSelectedItem().toString());
                        consles.EliminarPorBusqueda(les.txtBuscadoListaEstudiantes.getText(), Seleccion);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ok");
                    }

                }*/
                if (les.txtBuscadoListaEstudiantes.getText().equals("") && les.jtEstudiante.getSelectedRow() != -1) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    //System.out.println("Elegiste ir por tabla");
                    if (resp == 0) {
                        //System.out.println("Entra al if del jopttion");
                        Estudiante es = consles.BuscarEsTabla(les);;
                        //es = (consles.BuscarEs());
                        consles.EliminarSeleccion(es);
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
        if (e.getSource() == les.bnModListaEs) {
            try {
                if (les.jtEstudiante.getSelectedRow() != -1 && les.txtBuscadoListaEstudiantes.getText().equals("")) {
                    //Por tabla
                    Estudiante es = consles.BuscarEsTabla(les);
                    ModEstudiante mo = new ModEstudiante();
                    mo.txtnomMod.setText(es.getNomEs());
                    mo.txtapp2Mod.setText(es.getApp());
                    mo.txtapmMod.setText(es.getApm());
                    mo.txtcontrolMod.setText(es.getNc());
                    mo.txttelMod.setText(es.getTel());
                    mo.txtcorMod.setText(es.getCorreo());
                    mo.txtsexMod.setSelectedItem(es.getSex());
                    mo.txtespMod.setSelectedItem(es.getCarrera());
                    mo.rbsiMod.setSelected(es.isPermiso());
                    mo.txtid.setText(String.valueOf(es.getId()));
                    mo.setDefaultCloseOperation(mo.DISPOSE_ON_CLOSE);
                    
                    mo.setVisible(true);
                    mo.Mostrar(es.getId());
                }
                /*if (les.jtEstudiante.getSelectedRow() == -1 && !les.txtBuscadoListaEstudiantes.getText().equals("")) {
                    //Por texto
                    int cat = les.cbxBuscarPor_ListaEstudiantes.getSelectedIndex();

                    Estudiante es = consles.BuscarEsDato(les.txtBuscadoListaEstudiantes.getText(), cat);
                    ModEstudiante mo = new ModEstudiante();
                    mo.txtnomMod.setText(es.getNomEs());
                    mo.txtapp2Mod.setText(es.getApp());
                    mo.txtapmMod.setText(es.getApm());
                    mo.txtcontrolMod.setText(es.getNc());
                    mo.txttelMod.setText(es.getTel());
                    mo.txtcorMod.setText(es.getCorreo());
                    mo.txtsexMod.setSelectedItem(es.getSex());
                    mo.txtespMod.setSelectedItem(es.getCarrera());
                    mo.rbsiMod.setSelected(es.isPermiso());
                    mo.txtid.setText(String.valueOf(es.getId()));
                    mo.setDefaultCloseOperation(mo.DISPOSE_ON_CLOSE);
                    mo.setVisible(true);
                }*/

                if (les.jtEstudiante.getSelectedRow() != -1 && !les.txtBuscadoListaEstudiantes.getText().equals("")) {
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
