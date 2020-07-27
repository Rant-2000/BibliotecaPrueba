/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Rant AA
 */
public class ConsultaVisita extends Conexion {

    PreparedStatement ps;
    ResultSet rs;

    public boolean AltaV(Visita v) throws ParseException, SQLException {
        Connection con = null;
        try {
            System.out.println("Fecha obtenida de v " + v.getFecha());
            System.out.println("Hora de v " + v.getHora());
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO visitas ( Fecha, idEstudiante,Hora) VALUES (?,?,?)");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datePedido = sdf.parse(v.getFecha());
            java.sql.Date sqlDatePedido = new Date(datePedido.getTime());

            String fajr_prayertime = v.getHora();
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

            java.sql.Time timeValue = new java.sql.Time(formatter.parse(fajr_prayertime).getTime());

            System.out.println("Hora de visita AltaV " + timeValue);
            Conexion conn = new Conexion();
            con = conn.getConnection();

            ps.setDate(1, sqlDatePedido);
            ps.setInt(2, v.getIdEstudiante());
            ps.setTime(3, timeValue);

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

    public boolean Modificar(Visita es) throws SQLException, ParseException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE visitas SET Fecha=?, idEstudiante=?,Hora=?  WHERE idvisitas=?");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datePedido = sdf.parse(es.getFecha());
            java.sql.Date sqlDatePedido = new Date(datePedido.getTime());
            ps.setDate(1, sqlDatePedido);
            ps.setInt(2, es.getIdEstudiante());

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            java.util.Date d1 = (java.util.Date) format.parse(es.getHora());

            java.sql.Time ppstime = new java.sql.Time(d1.getTime());

            ps.setTime(3, ppstime);
            ps.setInt(4, es.getIdVisita());

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

    public boolean Eliminar(Visita es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM visitas WHERE idvisitas=?");
            ps.setInt(1, es.getIdVisita());

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

    public Visita Buscar(Visita es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM visitas WHERE idvisitas=?");
            ps.setInt(1, es.getIdVisita());
            rs = ps.executeQuery();

            if (rs.next()) {
                es.setIdEstudiante(rs.getInt("idEstudiante"));
                es.setIdVisita(rs.getInt("idvisitas"));
                es.setFecha(rs.getDate("Fecha").toString());
                es.setHora(rs.getTime("Hora").toString());
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

    public ArrayList<Visita> BuscarPorFecha(String fechapues) throws SQLException, ParseException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM visitas WHERE Fecha=?");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateEntrega = sdf.parse(fechapues);
            java.sql.Date sqlDatePedido = new Date(dateEntrega.getTime());
            ps.setDate(1, sqlDatePedido);
            rs = ps.executeQuery();
            ArrayList<Visita> vis = new ArrayList<Visita>();
            while (rs.next()) {
                Visita tem = new Visita();
                tem.setFecha(rs.getDate("Fecha").toString());
                tem.setIdEstudiante(rs.getInt("idEstudiante"));
                tem.setIdVisita(rs.getInt("idvisitas"));
                tem.setHora(rs.getTime("Hora").toString());
                vis.add(tem);
            }
            return vis;

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

    public ArrayList<Visita> BuscarPorId(int id) throws SQLException, ParseException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM visitas WHERE idvisitas=?");

            ps.setInt(1, id);
            rs = ps.executeQuery();
            ArrayList<Visita> vis = new ArrayList<Visita>();
            while (rs.next()) {
                Visita tem = new Visita();
                tem.setFecha(rs.getDate("Fecha").toString());
                tem.setIdEstudiante(rs.getInt("idEstudiante"));
                tem.setIdVisita(rs.getInt("idvisitas"));
                tem.setHora(rs.getTime("Hora").toString());
                vis.add(tem);
            }
            return vis;

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

    public ArrayList<Visita> getTodasVisitas() throws SQLException, ParseException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM visitas ");

            rs = ps.executeQuery();
            ArrayList<Visita> vis = new ArrayList<Visita>();
            while (rs.next()) {
                Visita tem = new Visita();
                tem.setFecha(rs.getDate("Fecha").toString());
                tem.setIdEstudiante(rs.getInt("idEstudiante"));
                tem.setIdVisita(rs.getInt("idvisitas"));
                tem.setHora(rs.getTime("Hora").toString());
                vis.add(tem);
            }
            return vis;

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

    public ArrayList<Visita> buscarEstudiante(String dato, int tipo) {
        try {
            ConsultaEstudiante conses = new ConsultaEstudiante();
            Estudiante enc = conses.getEstudiantePorNombreNc(dato, tipo);
            System.out.println("El estudiante existe nom " + enc.getNomEs());
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT * FROM visitas WHERE idEstudiante= '" + enc.getId() + "'";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<Visita> Estudiosos = new ArrayList<Visita>();
            while (rs.next()) {
                //System.out.println("Dato con el que entro "+rs.getString(tex));
                System.out.println("Entro al rs.next ");
                Visita provi = new Visita();
                provi.setFecha("Fecha");
                provi.setHora(rs.getTime("Hora").toString());
                provi.setIdEstudiante(rs.getInt("idEstudiante"));
                provi.setIdVisita(rs.getInt("idvisitas"));
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
}
