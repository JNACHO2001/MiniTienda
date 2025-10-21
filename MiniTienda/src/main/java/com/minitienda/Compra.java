/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minitienda;

/**
 *
 * @author Coder
 */
public class Compra {

    private String nombre;
    private int cantidadCompra;
    private double precio;
    private double total;

    public Compra(String nombre, int cantidadCompra, double precio, double total) {
        this.nombre = nombre;
        this.cantidadCompra = cantidadCompra;
        this.precio = precio;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public double getPrecio() {
        return precio;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "nombre=  " + nombre + " precio=  " + precio + ", total=  " + total + "    ";
    }

}
