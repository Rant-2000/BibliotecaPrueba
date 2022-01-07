/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaRegistro;
import Modelo.Libro;
import Modelo.Registro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rant AA
 */
public class PruebaCORP {

    public static void main(String[] args) {
        Registro res = new Registro();
        ConsultaEstudiante consEs = new ConsultaEstudiante();
        ConsultaLibro conslib =new ConsultaLibro();
        Libro lib=new Libro();
        
        ConsultaRegistro cones=new ConsultaRegistro();
        res.setFechaentrega("2020-11-31");
        res.setFechapedido("2020-11-29");
        res.setEstado(false);
        try {
            res.setIdEstudiante(consEs.getID("1871111"));

            res.setIdLibro(conslib.getID("mouse"));
            
            lib=conslib.getLibroPorTituloAutorISBN("mouse", 3);
            int can1 = lib.getExistencia();
            
            System.out.println("  Existencia actual "+can1);
            cones.GuardarRegistro(res, "mouse", "1871111", can1-1);
        } catch (SQLException ex) {
            Logger.getLogger(PruebaCORP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
