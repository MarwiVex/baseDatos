package com.example.basedatos.ADMON;

import com.example.basedatos.POJO.VariedadCafe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AdmonVariedadCafe {
    private Datos datos;

    public AdmonVariedadCafe(){
        datos = Datos.getInstance();
    }

    public LinkedList<VariedadCafe> obtenerVariedadCafe() throws Exception{
        LinkedList<VariedadCafe> variedadCafes = new LinkedList<>();
        String consulta = "SELECT * FROM VariedadCafe";
        try{
            ResultSet resultado = datos.ejecutarConsulta(consulta);
            if(resultado != null){
                while(resultado.next()){
                    variedadCafes.add(new VariedadCafe(resultado.getInt("IDVariedad"), resultado.getString("Descripcion")));
                }
                resultado.close();
            }
        }catch(SQLException e){
            System.err.println("Error al cargar variedades");
            System.err.println(e.getMessage());
        }
        return variedadCafes;
    }
}
