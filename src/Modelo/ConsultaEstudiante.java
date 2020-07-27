/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import Vista.ModEstudiante;
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
public class ConsultaEstudiante extends Conexion {

    PreparedStatement ps;
    ResultSet rs;

    public boolean Registro(Estudiante es) throws SQLException, IOException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO estudiante (numCon, Nombre, ApellidoP,ApellidoM, Tel, Correo, Genero, Carrera,Permiso) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, es.getNc());
            ps.setString(2, es.getNomEs());
            ps.setString(3, es.getApp());
            ps.setString(4, es.getApm());
            ps.setString(5, es.getTel());
            ps.setString(6, es.getCorreo());
            ps.setString(7, es.getSex());
            ps.setString(8, es.getCarrera());
            ps.setBoolean(9, es.isPermiso());
            
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

    public boolean Modificar(Estudiante es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE estudiante SET numCon=?,Nombre=?, ApellidoP=?, ApellidoM=?, Tel=?,Correo=?,Genero=?, Carrera=?,Permiso=? WHERE id=?");
            ps.setString(1, es.getNc());
            ps.setString(2, es.getNomEs());
            ps.setString(3, es.getApp());
            ps.setString(4, es.getApm());
            ps.setString(5, es.getTel());
            ps.setString(6, es.getCorreo());
            ps.setString(7, es.getSex());
            ps.setString(8, es.getCarrera());
            ps.setBoolean(9, es.isPermiso());
            ps.setInt(10, es.getId());
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

    public boolean Eliminar(Estudiante es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM estudiante WHERE id=?");
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

    public boolean Buscar(Estudiante es) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT * FROM estudiante WHERE numCon=?");
            ps.setString(1, es.getNc());
            rs = ps.executeQuery();

            if (rs.next()) {
                es.setId(rs.getInt("id"));
                es.setNc(rs.getString("numCon"));
                es.setNomEs(rs.getString("Nombre"));
                es.setApp(rs.getString("ApellidoP"));
                es.setApm(rs.getString("ApellidoM"));
                es.setTel(rs.getString("Tel"));
                es.setCorreo(rs.getString("Correo"));
                es.setSex(rs.getString("Genero"));
                es.setCarrera(rs.getString("Carrera"));
                es.setPermiso(rs.getBoolean("Permiso"));
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
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT numCon FROM estudiante WHERE numCon=?");
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

    public boolean VerificarPermiso(String es) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT permiso FROM estudiante WHERE numCon=?");
            ps.setString(1, es);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("permiso") == true) {
                    return true;
                } else {
                    return false;
                }

            }
            return false;

        } catch (SQLException e) {
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean ModificarPermiso(boolean es, String nc) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE estudiante SET Permiso=? WHERE numCon=?");
            ps.setBoolean(1, es);
            ps.setString(2, nc);
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

            ps = con.prepareStatement("SELECT id FROM estudiante WHERE numCon=?");
            ps.setString(1, es);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
            return 0;

        } catch (SQLException e) {
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            System.out.println(e);
            return 0;
        } finally {
            con.close();
        }
    }

    public Estudiante getEstudiantePorId(int id) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT * FROM estudiante WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Estudiante es = new Estudiante();
            if (rs.next()) {
                es.setId(rs.getInt("id"));
                es.setNc(rs.getString("numCon"));
                es.setNomEs(rs.getString("Nombre"));
                es.setApp(rs.getString("ApellidoP"));
                es.setApm(rs.getString("ApellidoM"));
                es.setTel(rs.getString("Tel"));
                es.setCorreo(rs.getString("Correo"));
                es.setSex(rs.getString("Genero"));
                es.setCarrera(rs.getString("Carrera"));
                es.setPermiso(rs.getBoolean("Permiso"));
                return es;
            }
            return null;

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

    public Estudiante getEstudiantePorNombreNc(String tex, int tipo) throws SQLException {
        Connection con = getConnection();
        try {
            String var = "";
            if (tipo == 0) {
                var = "Nombre";
            } else {
                var = "numCon";
            }

            ps = con.prepareStatement("SELECT * FROM estudiante WHERE " + var + "=?");
            ps.setString(1, tex);
            rs = ps.executeQuery();
            Estudiante es = new Estudiante();
            if (rs.next()) {
                es.setId(rs.getInt("id"));
                es.setNc(rs.getString("numCon"));
                es.setNomEs(rs.getString("Nombre"));
                es.setApp(rs.getString("ApellidoP"));
                es.setApm(rs.getString("ApellidoM"));
                es.setTel(rs.getString("Tel"));
                es.setCorreo(rs.getString("Correo"));
                es.setSex(rs.getString("Genero"));
                es.setCarrera(rs.getString("Carrera"));
                es.setPermiso(rs.getBoolean("Permiso"));
                return es;
            }
            return null;

        } catch (SQLException e) {
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            System.out.println(e);
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
            ps = con.prepareStatement("UPDATE estudiante SET QR=?  WHERE id=?");
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
            Logger.getLogger(ModEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
