package com.minitienda;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {
     private ArrayList<String> nombres;
    private double[] precios;
    private HashMap<String, Integer> stock;
    private int capacidad;
    private int size;
    private double totalCompras; // acumulado de todas las compras

    public Inventario() {
        this.capacidad = 10;
        this.nombres = new ArrayList<>();
        this.precios = new double[capacidad];
        this.stock = new HashMap<>();
        this.size = 0;
        this.totalCompras = 0;
    }

    // === Agregar producto ===
    public void addProducto(String nombre, double precio, int cantidad) {
        if (size == capacidad) expandPrecios();

        nombres.add(nombre);
        precios[size] = precio;
        stock.put(nombre, cantidad);
        size++;
    }

    private void expandPrecios() {
        capacidad *= 2;
        double[] nuevosPrecios = new double[capacidad];
        for (int i = 0; i < precios.length; i++) {
            nuevosPrecios[i] = precios[i];
        }
        precios = nuevosPrecios;
    }

    // === Mostrar productos ===
    public String[] Productos() {
        String[] productos = new String[size];
        for (int i = 0; i < size; i++) {
            String nombre = nombres.get(i);
            double precio = precios[i];
            int cantidad = stock.get(nombre);
            productos[i] = "Nombre: " + nombre + " | Precio: $" + precio + " | Cantidad: " + cantidad;
        }
        return productos;
    }

    // === Comprar producto ===
    public boolean comprarProducto(String nombre, int cantidad) {
        int index = nombres.indexOf(nombre);
        if (index == -1) return false;

        int stockDisponible = stock.get(nombre);
        if (cantidad > stockDisponible) return false;

        double precio = precios[index];
        totalCompras += precio * cantidad;
        stock.put(nombre, stockDisponible - cantidad);

        return true;
    }

    // === Buscar producto (coincidencias parciales) ===
    public String buscarProducto(String nombreParcial) {
        StringBuilder sb = new StringBuilder("Resultados:\n\n");
        boolean encontrado = false;

        for (int i = 0; i < size; i++) {
            String nombre = nombres.get(i);
            if (nombre.toLowerCase().contains(nombreParcial.toLowerCase())) {
                double precio = precios[i];
                int cantidad = stock.get(nombre);
                sb.append("Nombre: ").append(nombre)
                  .append(" | Precio: $").append(precio)
                  .append(" | Stock: ").append(cantidad)
                  .append("\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            return "No se encontraron productos con ese nombre.";
        }
        return sb.toString();
    }

    // === Estadísticas: precio mínimo y máximo ===
    public String estadisticas() {
        if (size == 0) return "No hay productos registrados.";

        double min = precios[0];
        double max = precios[0];
        String prodMin = nombres.get(0);
        String prodMax = nombres.get(0);

        for (int i = 1; i < size; i++) {
            if (precios[i] < min) {
                min = precios[i];
                prodMin = nombres.get(i);
            }
            if (precios[i] > max) {
                max = precios[i];
                prodMax = nombres.get(i);
            }
        }

        return """
               === ESTADÍSTICAS ===
               Producto más barato: %s ($%.2f)
               Producto más caro:   %s ($%.2f)
               """.formatted(prodMin, min, prodMax, max);
    }

    // === Ticket final ===
    public double getTotalCompras() {
        return totalCompras;
    }

  
}
