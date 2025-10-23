package com.mycompany.main.Services;

import com.mycompany.main.Models.Producto;
import java.util.List;

public interface ProductoServicioI {

    // Crear un producto
    Producto crearProducto(Producto p);

    // Buscar producto por ID
    Producto buscarProductoPorId(int id);

    // Listar todos los productos
    List<Producto> listarProductos();

    // Actualizar un producto
    Producto actualizarProducto(Producto p);

    // Eliminar un producto por ID
    void eliminarProducto(int id);
}
