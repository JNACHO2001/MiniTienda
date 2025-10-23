package com.mycompany.main.Services;

import com.mycompany.main.Exepciones.DatoInvalidoException;
import com.mycompany.main.Exepciones.RecursoNoEncontradoExcepcion;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.repository.jdbc.JdbcProductoRepository;
import java.util.List;

public class serviceProduct implements ProductoServicioI {

    private final JdbcProductoRepository repo;

    public serviceProduct(JdbcProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Producto crearProducto(Producto p) {
        if (p == null || p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new DatoInvalidoException("Nombre requerido");
        }
        if (p.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que 0");

        }
        if (p.getStock() < 0) {
            throw new DatoInvalidoException("El stock no puede ser negativo");

        }
        return repo.crear(p);

    }

    @Override
    public Producto buscarProductoPorId(int id) {

        return repo.buscarPorId(id);
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = repo.buscarTodos();

        if (productos.isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("No existen productos  " );

        }
        
        return  productos;

    }

    @Override
    public Producto actualizarProducto(Producto p) {
        
        if (p == null  || p.getId() <= 0) {
            throw new DatoInvalidoException("Debe indicar un producto válido con ID.");
        }
        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new DatoInvalidoException("El nombre del producto es obligatorio.");
        }
        if (p.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que 0.");
        }
        
        return repo.actualizar(p);
    }

    @Override
    public void eliminarProducto(int id) {
        
        if (id <= 0) {
            throw new RecursoNoEncontradoExcepcion("El producto con este ID no se encuantra " + id);
            
        }
        
        repo.eliminar(id);
    }
    
}
