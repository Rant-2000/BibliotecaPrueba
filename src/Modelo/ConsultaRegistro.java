/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Erro;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Rant AA
 */
public class ConsultaRegistro extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    private ConsultaLibro conslib;
    private ConsultaEstudiante consEs;

    public boolean GuardarRegistro(Registro es, String isbn, String nc, int novoCantidad) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            consEs = new ConsultaEstudiante();
            conslib = new ConsultaLibro();

            consEs.ModificarPermiso(false, nc);
            conslib.ModificarExis(isbn, novoCantidad);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaentrega = format.parse(es.getFechaentrega());
            java.sql.Date sql1 = new java.sql.Date(fechaentrega.getTime());

            Date fechahoy = format.parse(es.getFechapedido());
            java.sql.Date sql2 = new java.sql.Date(fechahoy.getTime());

            ps = con.prepareStatement("INSERT INTO registros (fecha_pedido, fecha_entrega,idEstudiante, idLibro,estadoEntregado) VALUES (?,?,?,?,?)");
            ps.setDate(1, sql2);
            ps.setDate(2, sql1);
            ps.setInt(3, es.getIdEstudiante());
            ps.setInt(4, es.getIdLibro());
            ps.setBoolean(5, es.isEstado());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en guardar registro");
            System.out.println(e);
            Erro er = new Erro();
            er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            er.getError(e.toString());
            er.setVisible(true);
            er.setTitle("Ha ocurrido un error!");
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();

        }
        return false;

    }

    public boolean EntregarRegistro(Registro es) throws SQLException {
        Connection con = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE registros SET estadoEntregado=?  WHERE idEstudiante=? AND idLibro=?");
            ps.setBoolean(1, es.isEstado());
            ps.setInt(3, es.getIdLibro());
            ps.setInt(2, es.getIdEstudiante());

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

    public boolean Eliminar(Registro es) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM registros WHERE id=?");
            ps.setInt(1, es.getIdEstudiante());

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

    public boolean Buscar(Registro es) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT * FROM registros WHERE id=?");
            ps.setInt(1, es.getIdRegistro());
            rs = ps.executeQuery();

            if (rs.next()) {
                es.setEstado(rs.getBoolean("estadoEntregado"));
                es.setFechaentrega(rs.getDate("fecha_entrega").toString());
                es.setFechapedido(rs.getDate("fecha_pedido").toString());
                es.setIdEstudiante(rs.getInt("idEstudiante"));
                es.setIdLibro(rs.getInt("idLibro"));

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
    public boolean BuscarPendiente(Estudiante es) throws SQLException {
        Connection con = getConnection();
        try {

            ps = con.prepareStatement("SELECT * FROM registros WHERE idEstudiante=?");
            ps.setInt(1, es.getId());
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
    public int VerifDiasPosibles(int dia, int mes, int an) {
     
        Calendar hoyes = Calendar.getInstance();

        hoyes.set(Calendar.HOUR_OF_DAY, 0);
        hoyes.set(Calendar.MINUTE, 0);
        hoyes.set(Calendar.SECOND, 0);

        
        System.out.println("An actual en el metodo "+hoyes.get(Calendar.YEAR)+" "+hoyes.get(Calendar.MONTH)+" "+hoyes.get(Calendar.DAY_OF_MONTH));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicial;
        try {
            fechaInicial = dateFormat.parse(String.valueOf(hoyes.get(Calendar.YEAR))+"-"+String.valueOf(hoyes.get(Calendar.MONTH))+"-"+String.valueOf(hoyes.get(Calendar.DAY_OF_MONTH)));
            Date fechaFinal = dateFormat.parse(String.valueOf(an)+"-"+String.valueOf(mes)+"-"+String.valueOf(dia));
            int diastotal = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
            System.out.println("Hay " + diastotal + " dias de diferencia");
            return diastotal;
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        //System.out.println("Cantidad de dias en el metodo: " + dias);

        return 0;
    }

    public Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    //Convert Calendar to Date
    public Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }
}
