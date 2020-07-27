/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import Vista.ListaEstudiantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
//import reybiblio.Estudiantes;

/**
 *
 * @author Rant AA
 */
public class ConsultaListaEstudiantes extends Conexion {

    ListaEstudiantes les;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Estudiante> buscar(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "Nombre";
                    break;
                case 2:
                    tex = "ApellidoP";
                    break;
                case 3:
                    tex = "ApellidoM";
                    break;
                case 4:
                    tex = "numCon";
                    System.out.println("Entro a nc");
                    break;
                case 5:
                    tex = "Correo";
                    break;
                case 6:
                    tex = "Tel";
                    break;
                case 7:
                    tex = "Carrera";
                    break;
                default:
                    System.err.println("ERROR");
            }
            System.out.println("Elegiste: " + tex);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Estudiante> Estudiosos = new ArrayList<Estudiante>();

            while (rs.next()) {
                System.out.println("Entro al rs.next ");
                Estudiante provi = new Estudiante();
                provi.setId(rs.getInt("id"));
                provi.setNomEs(rs.getString("Nombre"));
                provi.setApp(rs.getString("ApellidoP"));
                provi.setApm(rs.getString("ApellidoM"));
                provi.setCarrera(rs.getString("Carrera"));
                provi.setCorreo(rs.getString("Correo"));
                provi.setNc(rs.getString("numCon"));
                provi.setPermiso(rs.getBoolean("Permiso"));
                provi.setSex(rs.getString("Genero"));
                provi.setTel(rs.getString("Tel"));
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

    public boolean Modificar(Estudiante es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE estudiante SET numCon=?,Nombre=?, ApellidoP=?, ApellidoM=?, Tel=?,Correo=?, Carrera=?,Permiso=? WHERE id=?");
            ps.setString(1, es.getNc());
            ps.setString(2, es.getNomEs());
            ps.setString(3, es.getApp());
            ps.setString(4, es.getApm());
            ps.setString(5, es.getTel());
            ps.setString(6, es.getCorreo());

            ps.setString(7, es.getCarrera());
            ps.setBoolean(8, es.isPermiso());
            ps.setInt(9, es.getId());

            les = new ListaEstudiantes();
            DefaultTableModel modelo = new DefaultTableModel();
            les.jtEstudiante.setModel(modelo);

            modelo.addColumn("Numero de control");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido P");
            modelo.addColumn("Apellido M");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Carrera");
            modelo.addColumn("Permiso");

            Object[] filas = new Object[1];

            filas[0] = rs.getObject(0 + 1);

            modelo.addRow(filas);

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

    public boolean EliminarSeleccion(Estudiante es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            System.out.println("Entro nc " + es.getNc());
            ps = con.prepareStatement("DELETE FROM estudiante WHERE numCon=?");
            ps.setString(1, es.getNc());

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

    public boolean EliminarPorBusqueda(String es, int tipo) throws SQLException {
        Connection con = null;
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "Nombre";
                    break;
                case 2:
                    tex = "ApellidoP";
                    break;
                case 3:
                    tex = "ApellidoM";
                    break;
                case 4:
                    tex = "numCon";
                    System.out.println("Entro a nc");
                    break;
                case 5:
                    tex = "Correo";
                    break;
                case 6:
                    tex = "Tel";
                    break;
                case 7:
                    tex = "Carrera";
                    break;
                default:
                    System.err.println("ERROR");
            }
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM estudiante WHERE " + tex + " =?");
            ps.setString(1, es);

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(e.getLocalizedMessage());
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

    /*public Estudiante getEstudianteSeleccionado() {
        try {
            les = new ListaEstudiantes();
            int renglon = les.jtEstudiante.getSelectedRow();
            Estudiante es = new Estudiante();
            es.setNc(les.jtEstudiante.getValueAt(renglon, 0).toString());
            es.setNomEs(les.jtEstudiante.getValueAt(renglon, 1).toString());
            es.setApp(les.jtEstudiante.getValueAt(renglon, 2).toString());
            es.setApm(les.jtEstudiante.getValueAt(renglon, 3).toString());
            es.setTel(les.jtEstudiante.getValueAt(renglon, 4).toString());
            es.setCorreo(les.jtEstudiante.getValueAt(renglon, 5).toString());
            if (les.jtEstudiante.getValueAt(renglon, 6).equals("1")) {
                es.setPermiso(true);
            } else {
                es.setPermiso(false);
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
    }*/
    public Estudiante BuscarEsTabla(ListaEstudiantes les) {
        Connection con = getConnection();
        try {
//            les = new ListaEstudiantes();
            int renglon = les.jtEstudiante.getSelectedRow();

            ps = con.prepareStatement("SELECT * FROM estudiante WHERE numCon=?");
            ps.setString(1, les.jtEstudiante.getValueAt(renglon, 0).toString());
            System.out.println("NC seleccionado " + les.jtEstudiante.getValueAt(renglon, 0));
            rs = ps.executeQuery();
            Estudiante es = new Estudiante();
            if (rs.next()) {
                System.out.println("Encontro al estudiante");
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

    public Estudiante BuscarEsDato(String dato, int tipo) {
        Connection con = getConnection();
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "Nombre";
                    break;
                case 2:
                    tex = "ApellidoP";
                    break;
                case 3:
                    tex = "ApellidoM";
                    break;
                case 4:
                    tex = "numCon";
                    System.out.println("Entro a nc");
                    break;
                case 5:
                    tex = "Correo";
                    break;
                case 6:
                    tex = "Tel";
                    break;
                case 7:
                    tex = "Carrera";
                    break;
                default:
                    System.err.println("ERROR");
            }

            ps = con.prepareStatement("SELECT * FROM estudiante WHERE " + tex + "=?");
            ps.setString(1, dato);
            //System.out.println("NC seleccionado "+les.jtEstudiante.getValueAt(renglon, 0));
            rs = ps.executeQuery();
            Estudiante es = new Estudiante();
            if (rs.next()) {
                System.out.println("Encontro al estudiante");
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

    public ArrayList<Estudiante> buscar2(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 3:
                    tex = "Nombre";
                    break;

                case 4:
                    tex = "numCon";
                    System.out.println("Entro a nc");
                    break;

                default:
                    System.err.println("ERROR");
            }
            System.out.println("Elegiste: " + tex);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Estudiante> Estudiosos = new ArrayList<Estudiante>();

            while (rs.next()) {
                System.out.println("Entro al rs.next ");
                Estudiante provi = new Estudiante();
                provi.setId(rs.getInt("id"));
                provi.setNomEs(rs.getString("Nombre"));
                provi.setApp(rs.getString("ApellidoP"));
                provi.setApm(rs.getString("ApellidoM"));
                provi.setCarrera(rs.getString("Carrera"));
                provi.setCorreo(rs.getString("Correo"));
                provi.setNc(rs.getString("numCon"));
                provi.setPermiso(rs.getBoolean("Permiso"));
                provi.setSex(rs.getString("Genero"));
                provi.setTel(rs.getString("Tel"));
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

    public Estudiante buscarPorId(int id) {
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE id= '" + id + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            Estudiante provi = new Estudiante();
            if (rs.next()) {
                System.out.println("Entro al rs.next ");

                provi.setId(rs.getInt("id"));
                provi.setNomEs(rs.getString("Nombre"));
                provi.setApp(rs.getString("ApellidoP"));
                provi.setApm(rs.getString("ApellidoM"));
                provi.setCarrera(rs.getString("Carrera"));
                provi.setCorreo(rs.getString("Correo"));
                provi.setNc(rs.getString("numCon"));
                provi.setPermiso(rs.getBoolean("Permiso"));
                provi.setSex(rs.getString("Genero"));
                provi.setTel(rs.getString("Tel"));

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
