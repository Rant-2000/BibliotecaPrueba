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
public class Libro {
    private String titulo, autor, anho, editorial,isbn;
private int id,total,existencia;
boolean estado;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Libro(String titulo, String autor, String anho, String editorial, String isbn, int id, int total, int existencia,boolean estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.anho = anho;
        this.editorial = editorial;
        this.isbn=isbn;
        this.id=id;
        this.total=total;
        this.existencia=existencia;
        this.estado=estado;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return titulo + "%" + autor + "%" + anho + "%" + editorial + '%'+isbn;
    }
    public boolean revisarEstado(){
        return total==existencia;
    }
    
}
