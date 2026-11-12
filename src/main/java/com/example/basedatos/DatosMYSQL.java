package com.example.basedatos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatosMYSQL {
    private static DatosMYSQL datos = null;
    private Connection conexion;

    private DatosMYSQL() {
        try {
            Properties propiedades = new Properties();
            try (InputStream input = DatosMYSQL.class.getResourceAsStream("/database.properties")) {
                if (input != null) {
                    propiedades.load(input);
                }
            } catch (IOException e) {
                System.err.println("Error al leer database.properties: " + e.getMessage());
            }

            String url = propiedades.getProperty("db.url", propiedades.getProperty("url", "jdbc:mysql://localhost:3306/informacion_db"));
            String user = propiedades.getProperty("db.user", propiedades.getProperty("user", "root"));
            String password = propiedades.getProperty("db.password", propiedades.getProperty("password", ""));

            conexion = DriverManager.getConnection(url, user, password);
            System.out.println(">>> Conexión establecida con MySQL (se creó la instancia única) <<<");
        } catch (SQLException e) {
            System.err.println("Error al conectar a MySQL: " + e.getMessage());
        }
    }

    public static synchronized DatosMYSQL getInstance() {
        if (datos == null) {
            datos = new DatosMYSQL();
        }
        return datos;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public ResultSet ejecutarConsulta(String consulta) {
        ResultSet resultado = null;
        try {
            if (this.conexion != null) {
                resultado = this.conexion.prepareStatement(consulta).executeQuery();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultado;
    }

    public boolean ejecutarInstruccion(String consulta) {
        try {
            if (this.conexion != null) {
                this.conexion.prepareStatement(consulta).execute();
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
