/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaLibro;
import Modelo.ConsultaListaLibros;
import Modelo.Libro;
import Modelo.QRGenerador;
import Vista.Erro;
import Vista.ListaLibros;
import Vista.nuevoLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rant AA
 */
public class ctrlLibro implements ActionListener {

    private Libro es;
    private ConsultaLibro cones;
    private nuevoLibro frm;

    public ctrlLibro(Libro es, ConsultaLibro cones, nuevoLibro frm) {
        this.es = es;
        this.cones = cones;
        this.frm = frm;
        this.frm.btnBuscarLibro.addActionListener(this);
        this.frm.btnEliminarLibro.addActionListener(this);
        this.frm.btnGuardarLibro.addActionListener(this);
        this.frm.btnLimpiarLibro.addActionListener(this);
        this.frm.btnModificarLibro.addActionListener(this);
        this.frm.btnListadoLibros.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Control Libros");
        frm.setLocationRelativeTo(null);
        frm.txtidlibro.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardarLibro) {
            try {
                if (cones.Verificar(frm.txisbn.getText())) {
                    JOptionPane.showMessageDialog(null, "El ISBN ya ha sido ingresado");
                } else {
                    int can, total;
                    total = Integer.parseInt(frm.txttotalLibro.getText());
                    can = Integer.parseInt(frm.txtExistenciaLibro.getText());
                    if (can >= 0 && can <= total) {
                        es.setIsbn(frm.txisbn.getText());
                        es.setTitulo(frm.txtit.getText());
                        es.setAutor(frm.txtau.getText());
                        es.setEditorial(frm.txed.getText());
                        es.setAnho(frm.txa.getText());
                        es.setTotal(Integer.parseInt(frm.txttotalLibro.getText()));
                        es.setExistencia(Integer.parseInt(frm.txtExistenciaLibro.getText()));
                        try {
                            if (cones.Registro(es)) {
                                JOptionPane.showMessageDialog(null, "Guardado correctamente");
                                //cones.ActualizarQr(es.getId(), ruta);
                                QRGenerador q = new QRGenerador();
                                q.QrLibro(es.getIsbn(), es.getTitulo()+ " " + es.getIsbn());

                                es = cones.getLibroPorTituloAutorISBN(es.getIsbn(), 3);
                                
                                cones.ActualizarQr(es.getId(), "C:\\Users\\Admin\\Pictures\\Codigos\\Libros\\" + q.getRuta() + ".png");
                                
                                limpia();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error " + e);
                                limpia();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                            Erro er = new Erro();
                            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            er.getError(e.toString());
                            er.setVisible(true);
                            er.setTitle("Ha ocurrido un error!");
                        } catch (IOException ex) {
                            Logger.getLogger(ctrlLibro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El numero de cantidad de libros no puede ser mayor al del total");
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlLibro.class.getName()).log(Level.SEVERE, null, ex);
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(e.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }

        }
        if (e.getSource() == frm.btnModificarLibro) {
            int can, total;
            total = Integer.parseInt(frm.txttotalLibro.getText());
            can = Integer.parseInt(frm.txtExistenciaLibro.getText());
            if (can >= 0 && can <= total) {
                es.setIsbn(frm.txisbn.getText());
                es.setTitulo(frm.txtit.getText());
                es.setAutor(frm.txtau.getText());
                es.setEditorial(frm.txed.getText());
                es.setAnho(frm.txa.getText());
                es.setId(Integer.parseInt(frm.txtidlibro.getText()));
                es.setTotal(Integer.parseInt(frm.txttotalLibro.getText()));
                es.setExistencia(Integer.parseInt(frm.txtExistenciaLibro.getText()));
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
                    er.getError(e.toString());
                    er.setVisible(true);
                    er.setTitle("Ha ocurrido un error!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El numero de cantidad de libros no puede ser mayor al del total");
            }

        }
        if (e.getSource() == frm.btnEliminarLibro) {
            es.setId(Integer.parseInt(frm.txtidlibro.getText()));

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
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(e.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == frm.btnBuscarLibro) {
            es.setIsbn((frm.txisbn.getText()));

            try {
                if (cones.Buscar(es)) {
                    frm.txtit.setText(es.getTitulo());
                    frm.txtau.setText(es.getAutor());
                    frm.txisbn.setText(es.getIsbn());
                    frm.txed.setText(es.getEditorial());
                    frm.txa.setText(es.getAnho());
                    frm.txtidlibro.setText(String.valueOf(es.getId()));
                    frm.txttotalLibro.setText(String.valueOf(es.getTotal()));
                    frm.txtExistenciaLibro.setText(String.valueOf(es.getExistencia()));
                } else {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                    limpia();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(e.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
            }
        }
        if (e.getSource() == frm.btnLimpiarLibro) {
            limpia();
        }
        if (e.getSource() == frm.btnListadoLibros) {
            Libro es = new Libro();
            ConsultaLibro con = new ConsultaLibro();
            ListaLibros form = new ListaLibros();
            ConsultaListaLibros consEs = new ConsultaListaLibros();

            ctrlListaLibros ctrl = new ctrlListaLibros(form, con, es, consEs);
            ctrl.iniciar();
            form.setVisible(true);
            form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    public void limpia() {
        frm.txtidlibro.setText(null);
        frm.txtit.setText(null);
        frm.txtau.setText(null);
        frm.txisbn.setText(null);
        frm.txed.setText(null);
        frm.txa.setText(null);
        frm.txtidlibro.setText(null);
        frm.txttotalLibro.setText(null);
        frm.txtExistenciaLibro.setText(null);
    }
}
