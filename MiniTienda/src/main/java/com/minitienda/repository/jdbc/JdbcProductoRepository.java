package com.mycompany.main.repository.jdbc;

import com.mycompany.main.Bd.Conexion;
import com.mycompany.main.Exepciones.ErrorSistemaExepcion;
import com.mycompany.main.Exepciones.RecursoNoEncontradoExcepcion;
import com.mycompany.main.Exepciones.RegistroDuplicadoException;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.repository.IProductoRepo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductoRepository implements IProductoRepo {

    @Override
    public Producto buscarPorId(Integer id) {

        String sql = "SELECT * FROM productos WHERE id = ? ";
        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));

                    return p;

                } else {
                    throw new RecursoNoEncontradoExcepcion("No se encontro el producto con este ID: " + id);
                }

            }

        } catch (SQLException e) {
            throw new ErrorSistemaExepcion("Error al buscar producto por ID" + e.getMessage());
        }

    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rs = ps.executeUpdate();
            if (rs == 0) {
                throw new RecursoNoEncontradoExcepcion("No se encontró producto con ID: " + id);
            }

        } catch (SQLException e) {
            throw new ErrorSistemaExepcion("Error al eliminar el producto: " + e.getMessage());
        }

    }

    @Override
    public List<Producto> buscarTodos() {
        String sql = "SELECT * FROM productos";
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));

                productos.add(p);

            }

            return productos;

        } catch (SQLException e) {
            throw new ErrorSistemaExepcion("Error al buscar los productos " + e.getMessage());
        }

    }

    @Override
    public Producto crear(Producto p) {
        String sql = "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }

            }
            System.out.println("se creo");
            return p;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new RegistroDuplicadoException("Ya existe el producto.");

            } else {
                throw new ErrorSistemaExepcion("no se pudo crear " + e.getMessage());

            }
        }
    }

    @Override
    public Producto actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());

            int rs = ps.executeUpdate();

            if (rs == 0) {
                throw new RecursoNoEncontradoExcepcion("No se encontró producto con ID: " + p.getId());

            }

            return p;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new RegistroDuplicadoException("El nombre del producto ya existe " + p.getNombre());

            }
            throw new ErrorSistemaExepcion("Error al actualizar el producto: " + e.getMessage());

        }

    }
}
