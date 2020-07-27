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
import Vista.ListaVisitas;
import Vista.ModVisita;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rant AA
 */
public class ctrlListaVisitas implements ActionListener {

    Visita vis;
    ConsultaVisita conv;
    Estudiante es;
    ListaVisitas frm;
    ConsultaEstudiante conses;

    public ctrlListaVisitas(Visita vis, ConsultaVisita conv, ListaVisitas frm) {
        this.es = es;
        this.conv = conv;
        this.frm = frm;
        this.frm.btnBuscarVisita.addActionListener(this);
        this.frm.btnElimVis.addActionListener(this);
        this.frm.btnModVisitas.addActionListener(this);
        this.frm.btnRefrescarVisitas.addActionListener(this);

    }

    public void iniciar() {
        frm.setTitle("Control Visitas");
        frm.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnBuscarVisita) {
            int Seleccion = (frm.cbxbuscarpor.getSelectedIndex());
            conv = new ConsultaVisita();
            vis = new Visita();
            ArrayList<Visita> visar = null;
            try {
                if (Seleccion == 1 || Seleccion == 2) {
                    if (Seleccion == 1) {
                        visar = conv.BuscarPorFecha(frm.txtVisita.getText());
                    }
                    if (Seleccion == 2) {
                        vis.setIdVisita(Integer.parseInt(frm.txtVisita.getText()));
                        visar = conv.BuscarPorId(vis.getIdVisita());
                    }
                }

                if (Seleccion == 3 || Seleccion == 4) {

                    conv = new ConsultaVisita();
                    if (Seleccion == 3) {
                        visar = conv.buscarEstudiante(frm.txtVisita.getText(), 0);
                    }
                    if (Seleccion == 4) {
                        visar = conv.buscarEstudiante(frm.txtVisita.getText(), 1);
                    }
                }
                if (Seleccion != 0) {
                    frm.Rellenar(visar);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrlListaVisitas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == frm.btnElimVis) {
            conv = new ConsultaVisita();
            int idVisita = Integer.parseInt(frm.jtVisitas.getValueAt(frm.jtVisitas.getSelectedRow(), 0).toString());
            Visita vis = new Visita();
            vis.setIdVisita(idVisita);
            try {
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    if (conv.Eliminar(vis)) {
                        JOptionPane.showMessageDialog(null, "El elemento se ha eliminado");
                    }
                } else {

                }

            } catch (SQLException ex) {
                Logger.getLogger(ctrlListaVisitas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == frm.btnModVisitas) {
            ModVisita mod = new ModVisita();
            int idVisita = Integer.parseInt(frm.jtVisitas.getValueAt(frm.jtVisitas.getSelectedRow(), 0).toString());
            Visita vis = new Visita();
            vis.setIdVisita(idVisita);
            ConsultaVisita conv = new ConsultaVisita();
            try {
                vis=conv.Buscar(vis);
                mod.txfecha.setText(vis.getFecha());
                mod.txhora.setText(vis.getHora());
                mod.txides.setText(String.valueOf(vis.getIdEstudiante()));
                mod.idVis.setText(String.valueOf(vis.getIdVisita()));
                mod.setVisible(true);
                
            } catch (SQLException ex) {
                Logger.getLogger(ctrlListaVisitas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == frm.btnRefrescarVisitas) {
            try {
                frm.valid();
            } catch (SQLException ex) {
                Logger.getLogger(ctrlListaVisitas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ctrlListaVisitas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
