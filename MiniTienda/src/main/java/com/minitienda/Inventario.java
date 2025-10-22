package com.minitienda;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {

    private ArrayList<String> nombres;
    private double[] precios;
    private HashMap<String, Integer> stock;
    private int capacidad;
    private int size;

    public Inventario() {
        this.capacidad = 10; // capacidad inicial
        this.nombres = new ArrayList<>();
        this.precios = new double[capacidad];
        this.stock = new HashMap<>();
        this.size = 0;
    }

    // Método para añadir un nuevo producto
    public void addProducto(String nombre, double precio, int cantidad) {
        if (size == capacidad) {
            expandPrecios();
        }
        nombres.add(nombre);
        precios[size] = precio;
        stock.put(nombre, cantidad);
        size++;
    }

    // Método para expandir el array de precios cuando se llena
    private void expandPrecios() {
        capacidad *= 2;
        double[ ] nuevosPrecios = new double[capacidad];
        for (int i = 0; i < precios.length; i++) {
            nuevosPrecios[i] = precios[i];
        }
        precios = nuevosPrecios;
    }

    // Método para obtener el índice de un nombre de producto
    public int indexOfNombre(String nombre) {
        return nombres.indexOf(nombre);
    }

    // Mostrar todos los productos
    public void mostrarProductos() {
        System.out.println("Inventario:");
        for (int i = 0; i < size; i++) {
            String nombre = nombres.get(i);
            double precio = precios[i];
            int cantidad = stock.get(nombre);
            System.out.printf(" - %s | Precio: %.2f | Stock: %d\n", nombre, precio, cantidad);
        }
    }
}
