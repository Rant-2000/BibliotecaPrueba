/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import Vista.cargando;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Rant AA
 */
public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/e-biblioteca?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //JOptionPane.showMessageDialog(null, "Conexion Establecida");
            cargando car = new cargando();
            car.setVisible(true);
            
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    car.dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
        }
        return con;
    }
}
