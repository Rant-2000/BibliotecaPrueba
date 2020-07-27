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
public class Registro {
    private String fechapedido, fechaentrega;
private int idLibro,idEstudiante, idRegistro;

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registro() {
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    private boolean estado;

    public Registro(Libro lib,Estudiante est, String fechapedido, String fechaentrega, boolean estado,int nc, int isbn,int idRegistro) {        
        this.fechapedido = fechapedido;
        this.fechaentrega = fechaentrega;
        this.estado = estado;
        this.idEstudiante=nc;
        this.idLibro=isbn;
        this.idRegistro=idRegistro;
    }

    public String getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(String fechapedido) {
        this.fechapedido = fechapedido;
    }

    public String getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Registro{" + "fechapedido=" + fechapedido + ", fechaentrega=" + fechaentrega + ", estado=" + estado + '}';
    }
    
    
    
}
