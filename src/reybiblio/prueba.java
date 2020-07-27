/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Modelo.Conexion;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaVisita;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.Visita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.org.objectweb.asm.TypeReference;

/**
 *
 * @author Rant AA
 */
public class prueba extends Conexion {

    PreparedStatement ps;
    ResultSet rs;

    public static void main(String args[]) throws SQLException, ParseException {
        Visita v = new Visita();
        ConsultaVisita consv = new ConsultaVisita();
        v.setIdVisita(16);
        v.setIdEstudiante(7);
        v.setFecha("2020-07-22");
        if (consv.Modificar(v)) {
            System.out.println("Modificado");
        }
        
        /*inicio("18710133");

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("Hora actual: " + dateFormat.format(date));*/
    }

    private static void inicio(String nc) throws SQLException, ParseException {
        ConsultaEstudiante cons = new ConsultaEstudiante();
        Estudiante es = cons.getEstudiantePorNombreNc(nc, 1);
        System.out.println("Estudiante: " + es.getNomEs());
        ConsultaVisita consv = new ConsultaVisita();
        Visita v = new Visita();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: " + sdf.format(cal.getTime()));

        final Date currentTime = new Date();
        final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC time: " + sdf2.format(currentTime));

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("Hora actual: " + dateFormat.format(date));
        String hora = dateFormat.format(date);
        System.out.println("HORA " + hora);

        v.setFecha(sdf.format(cal.getTime()));

        v.setIdEstudiante(es.getId());
        v.setHora(hora);
        consv.AltaV(v);

    }

    public static int VerifDiasPosibles(int dia, int mes, int an) {
        Calendar ped = Calendar.getInstance();
        ped.set(an, mes - 1, dia);

        ped.set(Calendar.HOUR_OF_DAY, 0);
        ped.set(Calendar.MINUTE, 0);
        ped.set(Calendar.SECOND, 0);

        Calendar hoyes = Calendar.getInstance();

        hoyes.set(Calendar.HOUR_OF_DAY, 0);
        hoyes.set(Calendar.MINUTE, 0);
        hoyes.set(Calendar.SECOND, 0);

        long inicio = ped.getTimeInMillis();
        long fin = hoyes.getTimeInMillis();
        int dias = (int) ((Math.abs(fin - inicio)) / (1000 * 60 * 60 * 24));
        //System.out.println("Cantidad de dias en el metodo: " + dias);

        return dias;
    }

    public int VerificarCantidad(Libro es) {
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT existencia FROM Libros WHERE ISBN=?");
            ps.setString(1, es.getIsbn());
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Existencia: " + rs.getInt("existencia"));
                return rs.getInt("existencia");
            }

            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaLibro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
