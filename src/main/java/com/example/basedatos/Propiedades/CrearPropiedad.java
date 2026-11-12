package com.example.basedatos.Propiedades;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class CrearPropiedad {
    public static void main(String[] args){
        Properties propiedad = new Properties();
        propiedad.setProperty("app.nombre", "Skibidi app");
        propiedad.setProperty("app.version", "67.67.1");
        propiedad.setProperty("app.debug", "true");

        try(FileOutputStream fos = new FileOutputStream("config.properties")){
            propiedad.store(fos, "pues chad no?");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
