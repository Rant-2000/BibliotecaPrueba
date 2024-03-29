/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rant AA
 */
public class ConsultaListaRegistros {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Registro> buscar(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 1:
                    tex = "fecha_pedido";
                    break;
                case 2:
                    tex = "fecha_entrega";
                    break;
                case 8:
                    tex = "estadoEntregado";
                    break;
                default:
                    System.err.println("ERROR");
            }
            System.out.println("Elegiste: " + tex);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,fecha_pedido,fecha_entrega,estadoEntregado,idEstudiante,idLibro FROM registros WHERE " + tex + "= '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Registro> Estudiosos = new ArrayList<Registro>();
            while (rs.next()) {
                if (rs.getString(tex).equals(dato)) {
                    System.out.println("Dato con el que entro " + rs.getString(tex));
                    System.out.println("Entro al rs.next ");
                    Registro provi = new Registro();

                    provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                    provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                    provi.setEstado(rs.getBoolean("estadoEntregado"));
                    provi.setIdRegistro(rs.getInt("id"));
                    provi.setIdEstudiante(rs.getInt("idEstudiante"));
                    provi.setIdLibro(rs.getInt("idLibro"));
                    Estudiosos.add(provi);

                }
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

    public Registro buscarIndividual(String dato, int tipo) {
        try {
            String tex = "";
            switch (tipo) {
                case 0:
                    tex = "id";
                    break;
                case 1:
                    tex = "fecha_pedido";
                    break;
                case 2:
                    tex = "fecha_entrega";
                    break;
                case 3:
                    tex = "idEstudiante";
                    break;
                case 4:
                    tex = "idLibro";
                    break;
                case 8:
                    tex = "extadoEntregado";
                    break;
                default:

            }
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM libros WHERE " + tex + " '" + dato + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            Registro provi = new Registro();
            while (rs.next()) {
                System.out.println("Entro al rs.next ");

                provi.setIdRegistro(rs.getInt("id"));
                provi.setFechapedido(rs.getString("fecha_pedido"));
                provi.setFechaentrega(rs.getString("fecha_entrega"));
                provi.setIdEstudiante(rs.getInt("idEstudiante"));
                provi.setIdLibro(rs.getInt("idLibro"));
                provi.setEstado(rs.getBoolean("estadoEntregado"));

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

    public Registro buscarconId(int id) {
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM registros WHERE id='" + id + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            Registro provi = new Registro();
            while (rs.next()) {
                System.out.println("Entro al rs.next ");

                provi.setIdRegistro(rs.getInt("id"));
                provi.setFechapedido(rs.getString("fecha_pedido"));
                provi.setFechaentrega(rs.getString("fecha_entrega"));
                provi.setIdEstudiante(rs.getInt("idEstudiante"));
                provi.setIdLibro(rs.getInt("idLibro"));
                provi.setEstado(rs.getBoolean("estadoEntregado"));

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

    public ArrayList<Registro> buscarEstudiante(String dato, int tipo) {
        try {
            ConsultaEstudiante conses = new ConsultaEstudiante();
            Estudiante enc = conses.getEstudiantePorNombreNc(dato, tipo);
            System.out.println("El estudiante existe nom " + enc.getNomEs());
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,fecha_pedido,fecha_entrega,estadoEntregado,idEstudiante,idLibro FROM registros WHERE idEstudiante= '" + enc.getId() + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Registro> Estudiosos = new ArrayList<Registro>();
            while (rs.next()) {
                //System.out.println("Dato con el que entro "+rs.getString(tex));
                System.out.println("Entro al rs.next ");
                Registro provi = new Registro();
                provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                provi.setEstado(rs.getBoolean("estadoEntregado"));
                provi.setIdRegistro(rs.getInt("id"));
                provi.setIdEstudiante(rs.getInt("idEstudiante"));
                provi.setIdLibro(rs.getInt("idLibro"));
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

    public ArrayList<Registro> buscarLibro(String dato, int tipo) {
        try {
            ConsultaLibro conses = new ConsultaLibro();
            Libro enc = conses.getLibroPorTituloAutorISBN(dato, tipo);
            System.out.println("El Libro existe tit " + enc.getTitulo());
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,fecha_pedido,fecha_entrega,estadoEntregado,idEstudiante,idLibro FROM registros WHERE idLibro= '" + enc.getId() + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Registro> Estudiosos = new ArrayList<Registro>();
            while (rs.next()) {
                //System.out.println("Dato con el que entro "+rs.getString(tex));
                System.out.println("Entro al rs.next ");
                Registro provi = new Registro();
                provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                provi.setEstado(rs.getBoolean("estadoEntregado"));
                provi.setIdRegistro(rs.getInt("id"));
                provi.setIdEstudiante(rs.getInt("idEstudiante"));
                provi.setIdLibro(rs.getInt("idLibro"));
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

    public boolean Eliminar(Registro es) throws SQLException {
        Connection con = null;
        try {
            Conexion conn = new Conexion();
            con = conn.getConnection();
            //con = getConnection();
            ps = con.prepareStatement("DELETE FROM registros WHERE id=?");
            ps.setInt(1, es.getIdRegistro());

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

    public boolean ModificarRegistro(Registro es) throws SQLException, ParseException {
        Connection con = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Conexion conn = new Conexion();
            con = conn.getConnection();
            ps = con.prepareStatement("UPDATE registros SET fecha_pedido=?,fecha_entrega=?,idEstudiante=?,idLibro=?,estadoEntregado=?   WHERE id=?");
            java.util.Date datePedido = sdf.parse(es.getFechapedido());
            java.util.Date dateEntrega = sdf.parse(es.getFechaentrega());
            java.sql.Date sqlDatePedido = new Date(datePedido.getTime());
            java.sql.Date sqlDateEntrega = new Date(dateEntrega.getTime());
            ps.setDate(1, sqlDatePedido);
            ps.setDate(2, sqlDateEntrega);

            ps.setInt(3, es.getIdEstudiante());
            ps.setInt(4, es.getIdLibro());
            ps.setBoolean(5, es.isEstado());
            ps.setInt(6, es.getIdRegistro());
            ps.execute();
            return true;

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

    public ArrayList<Registro> getRegistros(int tipo, String dato, int sem) throws SQLException {
        try{
        
            //0 Dia
            //1 Mes
            //2 Year
            //3 Sem
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
String sql="";
            if (tipo==0) {
                 sql = "SELECT id,fecha_pedido,fecha_entrega,estadoEntregado,idEstudiante,idLibro FROM registros WHERE fecha_pedido= '" + dato + "'";
            }else{
                 sql = "SELECT id,fecha_pedido,fecha_entrega,estadoEntregado,idEstudiante,idLibro FROM registros ";
            }
           
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Registro> Estudiosos = new ArrayList<Registro>();

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date date1 = sdf1.parse(dato);
            //java.sql.Date sqlDate = new Date(date.getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            System.out.println("Año: " + cal1.get(Calendar.MONTH));
            int mes = cal1.get(Calendar.MONTH);
            System.out.println("Mes del parametro "+mes);
            int anho = cal1.get(Calendar.YEAR);
            while (rs.next()) {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                java.util.Date date = sdf.parse(rs.getString("fecha_pedido"));
                //java.sql.Date sqlDate = new Date(date.getTime());
                System.out.println("Fecha pedido: "+rs.getString("fecha_pedido"));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                //System.out.println("Año: " + cal.get(Calendar.MONTH));
                int mesFecha = cal.get(Calendar.MONTH);
                System.out.println("Mes del registro "+mesFecha);
                int anhoFecha = cal.get(Calendar.YEAR);
                if (tipo == 0 && rs.getString("fecha_pedido").equals(dato)) {
        
                    System.out.println("Entro al rs.next del dia ");
                    Registro provi = new Registro();

                    provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                    provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                    provi.setEstado(rs.getBoolean("estadoEntregado"));
                    provi.setIdRegistro(rs.getInt("id"));
                    provi.setIdEstudiante(rs.getInt("idEstudiante"));
                    provi.setIdLibro(rs.getInt("idLibro"));
                    Estudiosos.add(provi);

                }
                if (tipo == 1 && mesFecha == mes) {
                    
                    System.out.println("Entro al rs.next del mes");
                    Registro provi = new Registro();

                    provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                    provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                    provi.setEstado(rs.getBoolean("estadoEntregado"));
                    provi.setIdRegistro(rs.getInt("id"));
                    provi.setIdEstudiante(rs.getInt("idEstudiante"));
                    provi.setIdLibro(rs.getInt("idLibro"));
                    Estudiosos.add(provi);
                }
                if (tipo == 2 && anhoFecha == anho) {
        
                    System.out.println("Entro al rs.next ");
                    Registro provi = new Registro();

                    provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                    provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                    provi.setEstado(rs.getBoolean("estadoEntregado"));
                    provi.setIdRegistro(rs.getInt("id"));
                    provi.setIdEstudiante(rs.getInt("idEstudiante"));
                    provi.setIdLibro(rs.getInt("idLibro"));
                    Estudiosos.add(provi);
                }
                if (tipo == 3 && sem == 0) {
                    if (mesFecha < 6) {
        
                    
                        Registro provi = new Registro();

                        provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                        provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                        provi.setEstado(rs.getBoolean("estadoEntregado"));
                        provi.setIdRegistro(rs.getInt("id"));
                        provi.setIdEstudiante(rs.getInt("idEstudiante"));
                        provi.setIdLibro(rs.getInt("idLibro"));
                        Estudiosos.add(provi);
                    }

                }
                if (tipo == 3 && sem == 1) {
                    if (mesFecha >= 6 && mesFecha < 12) {
        
                        
                        Registro provi = new Registro();

                        provi.setFechapedido(rs.getDate("fecha_pedido").toString());
                        provi.setFechaentrega(rs.getDate("fecha_entrega").toString());
                        provi.setEstado(rs.getBoolean("estadoEntregado"));
                        provi.setIdRegistro(rs.getInt("id"));
                        provi.setIdEstudiante(rs.getInt("idEstudiante"));
                        provi.setIdLibro(rs.getInt("idLibro"));
                        Estudiosos.add(provi);
                    }
                }
            }

            return Estudiosos;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(ex.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            ex.getStackTrace();
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaListaRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
