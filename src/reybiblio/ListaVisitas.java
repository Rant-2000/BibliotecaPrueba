/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlListaVisitas;
import Modelo.ConsultaVisita;
import Modelo.Visita;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Rant AA
 */
public class ListaVisitas {
    public static void main(String[] args) throws SQLException, ParseException {
        Visita vis=new Visita();
         ConsultaVisita conv=new ConsultaVisita();
         Vista.ListaVisitas frm=new Vista.ListaVisitas();
        
        ctrlListaVisitas con=new ctrlListaVisitas(vis, conv, frm);
        con.iniciar();
        frm.setVisible(true);
    }
    
}
