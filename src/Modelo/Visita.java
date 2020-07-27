/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Rant AA
 */
public class Visita {
    private String Fecha,Hora;

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }
    private int idVisita,idEstudiante;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Visita(String Fecha, int idVisita, int idEstudiante,String Hora) {
        this.Fecha = Fecha;
        this.idVisita = idVisita;
        this.idEstudiante = idEstudiante;
        this.Hora=Hora;
    }
    public Visita(){
        
    }
    
}
