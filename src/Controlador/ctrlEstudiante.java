/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaListaEstudiantes;
import Modelo.Estudiante;
import Modelo.QRGenerador;
import Vista.Erro;
import Vista.ListaEstudiantes;
import Vista.formEstudiante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reybiblio.controlListaEstudiantes;

/**
 *
 * @author Rant AA
 */
public class ctrlEstudiante implements ActionListener {

    private Estudiante es;
    private ConsultaEstudiante cones;
    private formEstudiante frm;

    public ctrlEstudiante(Estudiante es, ConsultaEstudiante cones, formEstudiante frm) {
        this.es = es;
        this.cones = cones;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnListaLibros.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Control Estudiantes");
        frm.setLocationRelativeTo(null);
        frm.txtid.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            try {
                if (cones.Verificar(frm.txtcontrol.getText())) {
                    JOptionPane.showMessageDialog(null, "El numero de control ya ha sido ingresado");
                } else {
                    es.setNc(frm.txtcontrol.getText());
                    es.setNomEs(frm.txtnom.getText());
                    es.setApp(frm.txtapp2.getText());
                    es.setApm(frm.txtapm.getText());
                    es.setTel(frm.txttel.getText());
                    es.setCorreo(frm.txtcor.getText());
                    es.setSex(frm.txtsex.getSelectedItem().toString());
                    es.setCarrera(frm.txtesp.getSelectedItem().toString());
                    if (frm.rbsi.isSelected()) {
                        es.setPermiso(true);
                    }
                    if (frm.rbno.isSelected()) {
                        es.setPermiso(false);
                    }
                    try {
                        if (cones.Registro(es)) {
                            JOptionPane.showMessageDialog(null, "Guardado correctamente");
                            QRGenerador q = new QRGenerador();
                            q.QrAlumno(es.getNc(), es.getNomEs() + " " + es.getNc());

                            es = cones.getEstudiantePorNombreNc(es.getNc(), 1);
                            cones.ActualizarQr(es.getId(), "C:\\Users\\Admin\\Pictures\\WOW\\" + q.getRuta() + ".png");
                            limpia();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error " + e);
                            limpia();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == frm.btnModificar) {
            es.setId(Integer.parseInt(frm.txtid.getText()));
            es.setNc(frm.txtcontrol.getText());
            es.setNomEs(frm.txtnom.getText());
            es.setApp(frm.txtapp2.getText());
            es.setApm(frm.txtapm.getText());
            es.setTel(frm.txttel.getText());
            es.setCorreo(frm.txtcor.getText());
            es.setSex(frm.txtsex.getSelectedItem().toString());
            es.setCarrera(frm.txtesp.getSelectedItem().toString());
            if (frm.rbsi.isSelected()) {
                es.setPermiso(true);
            } else {
                es.setPermiso(false);
            }
            try {
                if (cones.Modificar(es)) {
                    JOptionPane.showMessageDialog(null, "Modificar correctamente");
                    limpia();
                } else {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                    limpia();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == frm.btnEliminar) {
            es.setId(Integer.parseInt(frm.txtid.getText()));

            try {
                if (cones.Verificar(frm.txtcontrol.getText())) {
                    try {
                        if (cones.Eliminar(es)) {
                            JOptionPane.showMessageDialog(null, "Eliminar correctamente");
                            limpia();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error " + e);
                            limpia();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha ingresado dicho usuario N.C.");
                }
            } catch (SQLException ex) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }

        }
        if (e.getSource() == frm.btnBuscar) {
            es.setNc((frm.txtcontrol.getText()));

            try {
                if (cones.Buscar(es)) {
                    frm.txtnom.setText(es.getNomEs());
                    frm.txtapp2.setText(es.getApp());
                    frm.txtapm.setText(es.getApm());
                    frm.txttel.setText(es.getTel());
                    frm.txtcontrol.setText(es.getNc());
                    frm.txtcor.setText(es.getCorreo());
                    frm.txtsex.setSelectedItem(es.getSex());
                    frm.txtesp.setSelectedItem(es.getCarrera());
                    frm.txtid.setText(String.valueOf(es.getId()));
                    if (es.isPermiso()) {
                        frm.rbsi.setSelected(true);
                        frm.rbno.setSelected(false);
                    } else {
                        frm.rbsi.setSelected(false);
                        frm.rbno.setSelected(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                    limpia();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(ex.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpia();
        }
        if (e.getSource() == frm.btnListaLibros) {
            Estudiante es = new Estudiante();
            ConsultaEstudiante con = new ConsultaEstudiante();
            ListaEstudiantes form = new ListaEstudiantes();
            ConsultaListaEstudiantes consEs = new ConsultaListaEstudiantes();

            ctrlListaEstudiantes ctrl = new ctrlListaEstudiantes(form, consEs, con);
            ctrl.iniciar();
            form.setVisible(true);
        }
    }

    public void limpia() {
        frm.txtapm.setText(null);
        frm.txtapp2.setText(null);
        frm.txtnom.setText(null);
        frm.txttel.setText(null);
        frm.txtcontrol.setText(null);
        frm.txtcor.setText(null);
        frm.txtid.setText(null);
        frm.txtsex.setSelectedIndex(0);
        frm.txtesp.setSelectedIndex(0);
    }
}
