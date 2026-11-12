package com.example.basedatos.ADMON;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Datos {
    private static Datos datos = null;
    private Connection conexion;

    private Datos(){
        Properties propiedades = new Properties();
        try (InputStream input = Datos.class.getResourceAsStream("/database.properties")) {
            propiedades.load(input);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        String urlServer = propiedades.getProperty("db.url", propiedades.getProperty("url", "jdbc:mysql://localhost:3306/informacion_db"));
        String user = propiedades.getProperty("db.user", propiedades.getProperty("user", "root"));
        String password = propiedades.getProperty("db.password", propiedades.getProperty("password", ""));
        String urlLocal = propiedades.getProperty("db.local.url", propiedades.getProperty("db.url.local", "jdbc:sqlite:informacion.db"));

        try {
            // Intentar conectar al servidor principal (MySQL)
            conexion = DriverManager.getConnection(urlServer, user, password);
            System.out.println("bd conectada al servidor (MySQL)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            try {
                conexion = DriverManager.getConnection(urlLocal);
            } catch (SQLException ex) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static Datos getInstance(){
        if(datos==null){
            datos = new Datos();
        }
        return datos;
    }

    public ResultSet ejecutarConsulta(String consulta){
        ResultSet resultado = null;
        try{
            resultado = this.conexion.prepareStatement(consulta).executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultado;
    }

    public boolean ejecutarInstruccion(String consulta){
        try{
            this.conexion.prepareStatement(consulta).execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }


    public Connection getConexion(){
        return this.conexion;
    }
}
