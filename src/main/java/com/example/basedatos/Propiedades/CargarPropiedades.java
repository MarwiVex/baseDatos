package com.example.basedatos.Propiedades;

import java.io.FileInputStream;
import java.util.Properties;

public class CargarPropiedades {
    public static void main(String[] args){
        String nombre = "";
        String version = "";
        Boolean debug = true;
        Properties propiedad = new Properties();

         try(FileInputStream fis = new FileInputStream("config.properties")){
             propiedad.load(fis);
             nombre = propiedad.getProperty("app.nombre");
             version = propiedad.getProperty("app.version");
             debug = Boolean.parseBoolean(propiedad.getProperty("app.debug"));
         }catch(Exception e){
             System.err.println(e.getMessage());
         }

        System.out.printf("%s %s %b", nombre, version, debug);

    }
}
