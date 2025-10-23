package com.mycompany.main;

//import com.mycompany.main.repository.jdbc.JdbcProductoRepository;
//import com.mycompany.main.Models.Producto;
//import java.sql.Connection;
//import com.mycompany.main.Bd.Conexion;
//import java.sql.SQLException;
import com.mycompany.main.View.ProductView;

public class Main {

    public static void main(String[] args) {

        ProductView vista = new ProductView();
        vista.mostrarMenu();

    }
}
