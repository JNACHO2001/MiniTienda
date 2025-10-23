package com.mycompany.main.Bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {

    private static final HikariDataSource pool;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/tienda_db");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(5);

        pool = new HikariDataSource(config);
        System.err.println("pool lista");

    }

    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

}
