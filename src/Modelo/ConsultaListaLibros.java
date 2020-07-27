/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import Vista.ListaLibros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import reybiblio.Libros;

/**
 *
 * @author Rant AA
 */
public class ConsultaListaLibros extends Conexion {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Libro> buscar(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "ISBN";
                    break;
                case 2:
                    tex = "Titulo";
                    break;
                case 3:
                    tex = "Autor";
                    break;
                case 4:
                    tex = "Editorial";
                    System.out.println("Entro a nc");
                    break;
                case 5:
                    tex = "anho";
                    break;
                case 6:
                    tex = "total";
                    break;
                case 7:
                    tex = "existencia";
                    break;
                default:
                    System.err.println("ERROR");
            }
            System.out.println("Elegiste: " + tex);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM libros WHERE " + tex + "= '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            ArrayList<Libro> Estudiosos = new ArrayList<Libro>();

            while (rs.next()) {
                System.out.println("Entro al rs.next ");
                Libro provi = new Libro();
                provi.setId(rs.getInt("id"));
                provi.setIsbn(rs.getString("ISBN"));
                provi.setTitulo(rs.getString("Titulo"));
                provi.setAutor(rs.getString("Autor"));
                provi.setEditorial(rs.getString("Editorial"));
                provi.setAnho(rs.getString("anho"));
                provi.setTotal(rs.getInt("total"));
                provi.setExistencia(rs.getInt("existencia"));

                Estudiosos.add(provi);
            }

            return Estudiosos;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(ex.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        }
    }

    public Libro BuscarEsTabla(ListaLibros les) {
        Connection con = getConnection();
        try {

            int renglon = les.jtLibros.getSelectedRow();

            ps = con.prepareStatement("SELECT * FROM libros WHERE ISBN=?");
            ps.setString(1, les.jtLibros.getValueAt(renglon, 0).toString());
            System.out.println("isbn seleccionado " + les.jtLibros.getValueAt(renglon, 0));
            rs = ps.executeQuery();
            Libro es = new Libro();
            if (rs.next()) {
                System.out.println("Encontro al estudiante");
                es.setId(rs.getInt("id"));
                es.setIsbn(rs.getString("ISBN"));
                es.setTitulo(rs.getString("Titulo"));
                es.setAutor(rs.getString("Autor"));
                es.setEditorial(rs.getString("Editorial"));
                es.setAnho(rs.getString("anho"));
                es.setTotal(rs.getInt("total"));
                es.setExistencia(rs.getInt("existencia"));

                return es;
            }
            return es;
        } catch (Exception e) {
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        }
    }

    public boolean EliminarSeleccion(Libro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            System.out.println("Entro nc " + es.getIsbn());
            ps = con.prepareStatement("DELETE FROM libros WHERE ISBN=?");
            ps.setString(1, es.getIsbn());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return false;
        } finally {
            con.close();
        }

    }

    public ArrayList<Libro> buscar2(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 4:
                    tex = "id";
                    break;
                case 5:
                    tex = "ISBN";
                    break;
                case 6:
                    tex = "Titulo";
                    break;
                case 7:
                    tex = "Autor";
                    break;

                default:
                    System.err.println("ERROR");
            }
            System.out.println("Elegiste: " + tex);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM libros WHERE " + tex + "= '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            ArrayList<Libro> Estudiosos = new ArrayList<Libro>();

            while (rs.next()) {
                System.out.println("Entro al rs.next ");
                Libro provi = new Libro();
                provi.setId(rs.getInt("id"));
                provi.setIsbn(rs.getString("ISBN"));
                provi.setTitulo(rs.getString("Titulo"));
                provi.setAutor(rs.getString("Autor"));
                provi.setEditorial(rs.getString("Editorial"));
                provi.setAnho(rs.getString("anho"));
                provi.setTotal(rs.getInt("total"));
                provi.setExistencia(rs.getInt("existencia"));

                Estudiosos.add(provi);
            }

            return Estudiosos;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(ex.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        }
    }

    public Libro buscarPorId(int id) {
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM libros WHERE id= '" + id + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            
            Libro provi = new Libro();
            while (rs.next()) {
                System.out.println("Entro al rs.next ");

                provi.setId(rs.getInt("id"));
                provi.setIsbn(rs.getString("ISBN"));
                provi.setTitulo(rs.getString("Titulo"));
                provi.setAutor(rs.getString("Autor"));
                provi.setEditorial(rs.getString("Editorial"));
                provi.setAnho(rs.getString("anho"));
                provi.setTotal(rs.getInt("total"));
                provi.setExistencia(rs.getInt("existencia"));

                
            }

            return provi;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(ex.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        }
    }
}
