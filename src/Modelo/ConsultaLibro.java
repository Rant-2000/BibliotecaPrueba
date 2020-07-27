/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rant AA
 */
public class ConsultaLibro extends Conexion {

    PreparedStatement ps;
    ResultSet rs;

    public boolean Registro(Libro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO libros (ISBN, Titulo, Autor,Editorial, anho,total,existencia) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, es.getIsbn());
            ps.setString(2, es.getTitulo());
            ps.setString(3, es.getAutor());
            ps.setString(4, es.getEditorial());
            ps.setString(5, es.getAnho());
            ps.setInt(6, es.getTotal());
            ps.setInt(7, es.getExistencia());

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

    public boolean Modificar(Libro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE libros SET ISBN=?, Titulo=?,Autor=?, Editorial=?, anho=?,total=?,existencia=?  WHERE id=?");
            ps.setString(1, es.getIsbn());
            ps.setString(2, es.getTitulo());
            ps.setString(3, es.getAutor());
            ps.setString(4, es.getEditorial());
            ps.setString(5, es.getAnho());
            ps.setInt(6, es.getTotal());
            ps.setInt(7, es.getExistencia());

            ps.setInt(8, es.getId());

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

    public boolean Eliminar(Libro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM libros WHERE id=?");
            ps.setInt(1, es.getId());

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

    public boolean Buscar(Libro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM Libros WHERE ISBN=?");
            ps.setString(1, es.getIsbn());
            rs = ps.executeQuery();

            if (rs.next()) {
                es.setId(rs.getInt("id"));
                es.setIsbn(rs.getString("ISBN"));
                es.setTitulo(rs.getString("Titulo"));
                es.setAutor(rs.getString("Autor"));
                es.setEditorial(rs.getString("Editorial"));
                es.setAnho(rs.getString("anho"));
                es.setTotal(rs.getInt("total"));
                es.setExistencia(rs.getInt("existencia"));
                return true;
            }
            return false;

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

    public boolean Verificar(String es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT ISBN FROM Libros WHERE ISBN=?");
            ps.setString(1, es);
            rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }
            return false;

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

    public boolean VerificarCantidad(Libro es) {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT existencia FROM Libros WHERE ISBN=?");
            ps.setString(1, es.getIsbn());
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Existencia+ " + rs.getInt("existencia"));
                es.setExistencia(rs.getInt("existencia"));
                return true;
            }

            return false;
        } catch (SQLException e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaLibro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean ModificarExis(String isbn, int nuevaExistencia) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE libros SET existencia=?  WHERE ISBN=?");
            ps.setInt(1, nuevaExistencia);
            ps.setString(2, isbn);

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

    public int getID(String es) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT id FROM libros WHERE ISBN=?");
            ps.setString(1, es);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
            return 0;

        } catch (SQLException e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return 0;
        } finally {
            con.close();
        }
    }

    public Libro getLibroPorId(int id) throws SQLException {
        Connection con = null;
        try {
            Libro es = new Libro();
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM Libros WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
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

        } catch (SQLException e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        } finally {
            con.close();
        }
    }

    public Libro getLibroPorTituloAutorISBN(String dato, int tipo) throws SQLException {
        Connection con = null;
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "Titulo";
                    break;
                case 2:
                    tex = "Autor";
                    break;
                case 3:
                    tex = "ISBN";
                    break;
            }
            Libro es = new Libro();
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM Libros WHERE " + tex + "=?");
            ps.setString(1, dato);
            rs = ps.executeQuery();

            if (rs.next()) {
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

        } catch (SQLException e) {
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return null;
        } finally {
            con.close();
        }
    }

    public void ActualizarQr(int id, String ruta) {

        File fichero = new File(ruta);

        PreparedStatement ps;
        ResultSet rs;
        Conexion objCon = new Conexion();

        try {

            FileInputStream fis = new FileInputStream(fichero);

            Connection con = objCon.getConnection();
            ps = con.prepareStatement("UPDATE libros SET QR=?  WHERE id=?");
            //ps=con.prepareStatement("UPDATE estudiante SET numCon=?, Nombre=?, ApellidoP=?, ApellidoM=?, Tel=?, Correo=?, Genero=?, Carrera=? WHERE id=?");
            ps.setBinaryStream(1, fis, (int) fichero.length());

            ps.setInt(2, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Imagen Guardada");
            //Mostrar(Integer.parseInt(txtid.getText()));
        } catch (HeadlessException | FileNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Guardar Imagen");
            System.out.println(ex);
        } catch (IOException ex) {
            
        }
    }
}
