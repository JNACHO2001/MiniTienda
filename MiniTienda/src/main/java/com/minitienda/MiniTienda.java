package com.minitienda;

import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class MiniTienda {

    private Inventario inventario = new Inventario(); // Mantiene los productos en la sesión

    public static void main(String[] args) {
        MiniTienda tienda = new MiniTienda();
        tienda.iniciar();
    }

    public void iniciar() {
        int opcion = 0;

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu()));

                switch (opcion) {
                    case 1 ->
                        agregarProducto();
                    case 2 ->
                        mostrarProductos();
                    case 3 ->
                        comprarProducto();
                    case 4 ->
                        mostrarEstadisticas();
                    case 5 ->
                        buscarProducto();
                    case 6 ->
                        salir();

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            }

        } while (opcion != 6);
    }
    // === 1. Agregar producto ===

    public void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        if (nombre == null || nombre.isEmpty()) {
            return;
        }

        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad en stock:"));

        inventario.addProducto(nombre, precio, cantidad);
        JOptionPane.showMessageDialog(null, " Producto agregado correctamente.");
    }

    // === 2. Mostrar productos ===
    public void mostrarProductos() {
        String[] lista = inventario.Productos();
        if (lista.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE PRODUCTOS ===\n\n");
        for (String p : lista) {
            sb.append(p).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // === 3. Comprar producto ===
    public void comprarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a comprar:"));

        boolean exito = inventario.comprarProducto(nombre, cantidad);
        if (exito) {
            JOptionPane.showMessageDialog(null, " Compra realizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, " No existe el producto o no hay suficiente stock.");
        }
    }

    // === 4. Mostrar estadísticas ===
    public void mostrarEstadisticas() {
        JOptionPane.showMessageDialog(null, inventario.estadisticas());
    }

    // === 5. Buscar producto ===
    public void buscarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese nombre o parte del nombre a buscar:");
        JOptionPane.showMessageDialog(null, inventario.buscarProducto(nombre));
    }

    // === 6. Salir ===
    public void salir() {
        JOptionPane.showMessageDialog(null, """
                === TICKET FINAL ===
                Total acumulado de compras: $%.2f
                """.formatted(inventario.getTotalCompras()));
        System.exit(0);
    }

    // === Menú ===
    public String menu() {
        return """
                === MENÚ PRINCIPAL ===

                1. Agregar producto
                2. Listar productos
                3. Comprar producto
                4. Mostrar estadísticas
                5. Buscar producto por nombre
                6. Salir con ticket final

                Elija una opción:
                """;
    }
}
