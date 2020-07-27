/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rant AA
 */
public class ctrlRegistro implements ActionListener {

    private Registro res;
    private ConsultaRegistro cones;
    private formRegistro frm;
    private Libro lib;
    private Libro lib2;
    private Estudiante es;
    private ConsultaLibro conslib;
    private ConsultaEstudiante consEs;

    public ctrlRegistro(Registro res, ConsultaRegistro cones, formRegistro frm) {
        this.res = res;
        this.cones = cones;
        this.frm = frm;
        this.frm.btnRegistro.addActionListener(this);
        this.frm.btnagregarest.addActionListener(this);
        this.frm.btnagregarLibro.addActionListener(this);
        this.frm.btnEntregar.addActionListener(this);
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
                es = new Estudiante();
                es.setNc(frm.ncregistro.getText());

                //Crear Libro
                lib = new Libro();

                lib2 = new Libro();

                //Consulta libros
                consEs = new ConsultaEstudiante();

                boolean libro1 = false, libro2 = false;
                if (!frm.isbnregistro.getText().equals("")) {
                    libro1 = true;
                    lib.setIsbn(frm.isbnregistro.getText());
                }
                if (!frm.isbnregistro2.getText().equals("")) {
                    libro2 = true;
                    lib2.setIsbn(frm.isbnregistro2.getText());
                }
                int exis = Integer.parseInt(frm.txtExistenciaRegistro.getText());

                boolean permiso = consEs.VerificarPermiso(frm.ncregistro.getText());
                //Constructor
                conslib = new ConsultaLibro();

                if (cones.VerifDiasPosibles(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)) <= 3) {
                    if (exis > 0 && permiso) {

                        System.out.println("Dia de entrega: " + fechaSupuesta);
                        System.out.println("Dia de pedido osea hoy " + diadeHoy.toString());
                        if (libro1) {

                            res.setFechaentrega(fechaSupuesta);
                            res.setFechapedido(diadeHoy.toString());
                            res.setEstado(false);
                            res.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                            res.setIdLibro(conslib.getID(frm.isbnregistro.getText()));
                            int can1 = Integer.parseInt(frm.txtExistenciaRegistro.getText());
                            //System.out.println("Id del libro con el isbn" + conslib.getID(frm.isbnregistro.getText()));

                            can1 = can1 - 1;
                            cones.GuardarRegistro(res, frm.isbnregistro.getText(), frm.ncregistro.getText(), can1);
                            System.out.println("Registro del libro 1");
                        }
                        if (libro2) {
                            System.out.println("Registro del libro 2");
                        }

                        JOptionPane.showMessageDialog(null, "Guardado correctamente");

                    } else {
                        if (exis <= 0) {
                            JOptionPane.showMessageDialog(null, "La cantidad en existencia es 0");
                        }
                        if (permiso == false) {
                            JOptionPane.showMessageDialog(null, "No cuentas con el permiso para apartar libros"
                                    + "\n Revisa tus pedidos anteriores");
                        }
                    }
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

                consEs = new ConsultaEstudiante();
                conslib = new ConsultaLibro();
                System.out.println("Entra a try");
                res.setEstado(true);
                res.setIdEstudiante(consEs.getID(frm.ncregistro.getText()));
                res.setIdLibro(conslib.getID(frm.isbnregistro.getText()));
                if (!frm.ncregistro.getText().equals("")) {

                    consEs.ModificarPermiso(true, frm.ncregistro.getText());
                }
                if (!frm.isbnregistro.getText().equals("")) {

                    int exi1 = Integer.parseInt(frm.txtExistenciaRegistro.getText());
                    exi1 = exi1 + 1;
                    conslib.ModificarExis(frm.isbnregistro.getText(), exi1);
                }
                if (!frm.isbnregistro2.getText().equals("")) {
                    int exi2 = Integer.parseInt(frm.isbnregistro2.getText());
                    exi2 = exi2 + 1;
                    conslib.ModificarExis(frm.isbnregistro2.getText(), exi2);
                }

                cones.EntregarRegistro(res);
            } catch (SQLException exep) {
                Erro er = new Erro();
                er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                er.getError(exep.toString());
                er.setVisible(true);
                er.setTitle("Ha ocurrido un error!");
                System.out.println(exep);
            }
        }
    }
}
