/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reybiblio;

import Controlador.ctrlRegistro;
import Modelo.ConsultaRegistro;
import Modelo.Registro;
import Vista.formRegistro;

/**
 *
 * @author Rant AA
 */
public class registros {
    public static void main(String[] args) {
        Registro es=new Registro();
        ConsultaRegistro con=new ConsultaRegistro();
        formRegistro form=new formRegistro();
        
        
        ctrlRegistro ctrl=new ctrlRegistro(es, con, form);
        ctrl.iniciar();
        form.setVisible(true);
    }
}
