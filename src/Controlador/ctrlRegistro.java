/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaListaEstudiantes;
import Modelo.ConsultaRegistro;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.Registro;
import Vista.Erro;
import Vista.formRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reybiblio.RegistroLibros;

/**
 *
 * @author Rant AA
 */
public class ctrlRegistro implements ActionListener {

    private Registro res;
    private ConsultaRegistro cones;
    private RegistroLibros frm;
    private Libro lib;
    private Libro lib2;
    private Estudiante es;
    private ConsultaLibro conslib;
    private ConsultaEstudiante consEs;
    private ConsultaListaEstudiantes consLisEs;

    public ctrlRegistro(ConsultaEstudiante consEs, ConsultaLibro conslib, Registro res, ConsultaRegistro cones, RegistroLibros frm) {
        this.res = res;
        this.cones = cones;
        this.consEs = consEs;
        this.frm = frm;
        this.conslib = conslib;
        this.frm.btnRegistro.addActionListener(this);
        this.frm.btnagregarest.addActionListener(this);
        this.frm.btnagregarLibro.addActionListener(this);
        this.frm.btnEntregar.addActionListener(this);
        this.frm.btnEscaneoCredencial.addActionListener(this);
        this.frm.bntEscaneolibro.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Apartado de libros");
        frm.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnRegistro) {

            try {
                //Dia de hoy: 
                System.out.println("HOY " + frm.txtDIA.getText());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(frm.txtDIA.getText());
                java.sql.Date diadeHoy = new java.sql.Date(parsed.getTime());

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaSupuesta = sdf.format(frm.jday.getDate());

                Date date = frm.jday.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                //Crear Estudiante
                //es = new Estudiante();
                //es.setNc(frm.ncregistro.getText());
                //Crear Libro
                lib = new Libro();

                lib2 = new Libro();

                //Consulta libros
                consEs = new ConsultaEstudiante();

                boolean libro1 = false, libro2 = false;
                int exis = 0, exis2 = 0;
                if (!frm.isbnregistro.getText().equals("")) {
                    libro1 = true;
                    lib.setIsbn(frm.isbnregistro.getText());
                    //exis = Integer.parseInt(frm.txtExistenciaRegistro.getText());
                    lib = conslib.getLibroPorTituloAutorISBN(frm.isbnregistro.getText(), 3);
                }
                if (!frm.isbnregistro2.getText().equals("")) {
                    libro2 = true;
                    lib2.setIsbn(frm.isbnregistro2.getText());
                    lib2 = conslib.getLibroPorTituloAutorISBN(frm.isbnregistro2.getText(), 3);

                }

                boolean permiso = consEs.VerificarPermiso(frm.ncregistro.getText());
                //Constructor
                conslib = new ConsultaLibro();

                if (cones.VerifDiasPosibles(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)) <= 3) {
                    if (lib.getExistencia() > 0 || lib2.getExistencia() > 0 && permiso) {

                        System.out.println("Dia de entrega: " + fechaSupuesta);
                        System.out.println("Dia de pedido osea hoy " + diadeHoy.toString());
                        if (libro1) {

                            res.setFechaentrega(fechaSupuesta);
                            res.setFechapedido(diadeHoy.toString());
                            res.setEstado(false);
                            res.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                            res.setIdLibro(conslib.getID(frm.isbnregistro.getText()));
                            int can1 = lib.getExistencia();
                            //System.out.println("Id del libro con el isbn" + conslib.getID(frm.isbnregistro.getText()));

                            can1 = can1 - 1;
                            cones.GuardarRegistro(res, frm.isbnregistro.getText(), frm.ncregistro.getText(), can1);
                            System.out.println("Registro del libro 1");
                        }
                        if (libro2) {
                            System.out.println("Registro del libro 2");
                            res.setFechaentrega(fechaSupuesta);
                            res.setFechapedido(diadeHoy.toString());
                            res.setEstado(false);
                            res.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                            res.setIdLibro(conslib.getID(frm.isbnregistro2.getText()));
                            int can1 = lib2.getExistencia();
                            //System.out.println("Id del libro con el isbn" + conslib.getID(frm.isbnregistro.getText()));

                            can1 = can1 - 1;
                            cones.GuardarRegistro(res, frm.isbnregistro.getText(), frm.ncregistro.getText(), can1);
                            System.out.println("Registro del libro 1");

                        }

                        JOptionPane.showMessageDialog(null, "Guardado correctamente");

                    } else {
                        if (exis <= 0 || exis2 <= 0) {
                            JOptionPane.showMessageDialog(null, "La cantidad en existencia es 0");
                        }
                        if (permiso == false) {
                            JOptionPane.showMessageDialog(null, "No cuentas con el permiso para apartar libros"
                                    + "\n Revisa tus pedidos anteriores");
                        }
                    }
                    System.out.println("Esta dentro del rango ");
                } else {
                    System.out.println("no se verifica dia");

                }

            } catch (Exception er) {
                //System.out.println(er.getMessage());
                System.out.println(er);
                Erro rer = new Erro();
                rer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rer.getError(er.toString());
                rer.setVisible(true);
                rer.setTitle("Ha ocurrido un error!");
                System.out.println("error del verificar dias");
            }

        }
        if (e.getSource() == frm.btnEntregar) {
            try {
                Registro r1 = new Registro();
                Registro r2 = new Registro();
                consEs = new ConsultaEstudiante();
                conslib = new ConsultaLibro();
                System.out.println("Entra a try");
                Estudiante est = new Estudiante();
                if (!frm.ncregistro.getText().equals("")) {

//                    consEs.ModificarPermiso(true, frm.ncregistro.getText());
                    
                    est=consEs.getEstudiantePorNombreNc(frm.ncregistro.getText(), 1);
                }
                if (!frm.isbnregistro.getText().equals("")) {
                    r1.setEstado(true);
                    r1.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                    r1.setIdLibro(conslib.getID(frm.isbnregistro.getText()));
                    int exi1 = Integer.parseInt(frm.txtExistenciaRegistro.getText());
                    //buscar por id?
                    exi1 = exi1 + 1;
                    conslib.ModificarExis(frm.isbnregistro.getText(), exi1);
                    
                    cones.EntregarRegistro(r1);
                    if (cones.BuscarPendiente(est)) {
                        consEs.ModificarPermiso(false, frm.ncregistro.getText());
                    }else{
                        consEs.ModificarPermiso(true, frm.ncregistro.getText());
                    }
                }
                if (!frm.isbnregistro2.getText().equals("")) {
                    r2.setEstado(true);
                    r2.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                    r2.setIdLibro(conslib.getID(frm.isbnregistro2.getText()));
                    int exi2 = Integer.parseInt(frm.isbnregistro2.getText());
                    exi2 = exi2 + 1;
                    conslib.ModificarExis(frm.isbnregistro2.getText(), exi2);
                    cones.EntregarRegistro(r2);
                    if (cones.BuscarPendiente(est)) {
                        consEs.ModificarPermiso(false, frm.ncregistro.getText());
                    }else{
                        consEs.ModificarPermiso(true, frm.ncregistro.getText());
                    }
                }

            } catch (SQLException exep) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(exep.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
                System.out.println(exep);
            }
            
        }
        if (e.getSource() == frm.btnEscaneoCredencial) {

            Estudiante es = new Estudiante();
            es.setNc(frm.result_field.getText());
            System.out.println("result field es " + es.getNc());
            consEs = new ConsultaEstudiante();
            try {
                if (consEs.Buscar(es)) {
                    frm.ncregistro.setText(es.getNc());
                    frm.nomregistro.setText(es.getNomEs());
                    frm.carreraregistro.setText(es.getCarrera());
                    frm.aperegistro.setText(es.getApp() + " " + es.getApm());
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == frm.bntEscaneolibro) {
            Libro lib = new Libro();
            lib.setIsbn(frm.result_field.getText());

            try {
                if (conslib.Buscar(lib)) {
                    frm.isbnregistro.setText(lib.getIsbn());
                    frm.tituloregistro.setText(lib.getTitulo());
                    frm.autorregistro.setText(lib.getAutor());
                    frm.editorialregistro.setText(lib.getEditorial());
                    frm.txtanhioRegistroLibro.setText(lib.getAnho());
                    frm.txtTotalRegistro.setText(String.valueOf(lib.getTotal()));
                    frm.txtExistenciaRegistro.setText(String.valueOf(lib.getExistencia()));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == frm.bntEscaneolibro1) {
            Libro lib = new Libro();
            lib.setIsbn(frm.result_field.getText());
            try {
                if (conslib.Buscar(lib)) {
                    frm.isbnregistro2.setText(lib.getIsbn());
                    frm.tituloregistro2.setText(lib.getTitulo());
                    frm.autorregistro2.setText(lib.getAutor());
                    frm.editorialregistro2.setText(lib.getEditorial());
                    frm.txtanhioRegistroLibro2.setText(lib.getAnho());
                    frm.txtTotalRegistro2.setText(String.valueOf(lib.getTotal()));
                    frm.txtExistenciaRegistro2.setText(String.valueOf(lib.getExistencia()));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
