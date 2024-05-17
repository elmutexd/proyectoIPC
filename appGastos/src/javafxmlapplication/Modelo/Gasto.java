/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication.Modelo;

import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public class Gasto {
    private String categoria;
    private LocalDate fecha;
    private int coste;
    private String titulo;
    private String descripcion;
    public Gasto(String c, LocalDate f,int co, String t, String d){
        this.categoria=c;
        this.fecha= f;
        this.coste= co;
        this.titulo= t;
        this.descripcion= d;
    
    }
    public String getCategoria(){
        return this.categoria;
    }
    public LocalDate getFecha(){
        return this.fecha;
    }
    public int getCoste(){
        return this.coste;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    
}
