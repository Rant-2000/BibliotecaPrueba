/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author Rant AA
 */
public class prueba {

    private int idLibro;
    private String titulo;

    public Vector<prueba> mostrarRegistros() {
        PreparedStatement ps;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        Vector<prueba> datos = new Vector<prueba>();
        prueba dato = null;
        try {

            String autores = "SELECT  id, Titulo FROM libros";
            ps = con.prepareStatement(autores);
            rs = ps.executeQuery();
            dato = new prueba();
            dato.setIdLibro(0);
            dato.setTitulo("Seleccione un titulo");
            datos.add(dato);
            while (rs.next()) {
                dato = new prueba();
                dato.setIdLibro(rs.getInt("id"));
                System.out.println("El id seria: "+rs.getInt("id"));
                dato.setTitulo(rs.getString("Titulo"));
                datos.add(dato);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toString(){
        return this.titulo;
    }
    public static void main(String[] args) {
         Erro er=new Erro();
         er.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        er.getError("Naciste!");
        er.setVisible(true);
        er.setTitle("Ha ocurrido un error!");
    }

}
