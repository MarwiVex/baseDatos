package com.example.basedatos.ADMON;

import com.example.basedatos.POJO.Cafe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AdmonCafe {
    private Datos datos;

    public AdmonCafe(){
        datos = Datos.getInstance();
    }

    public void agregar(Cafe cafe)throws Exception{
        String consulta = String.format("INSERT INTO Cafe(Productor, VariedadCafe, Cantidad) VALUES('%s', %d, %f)",
                cafe.getProductor(), cafe.getVariedadCafe(), cafe.getCantidad());
        try{
            datos.ejecutarInstruccion(consulta);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void editar(Cafe cafe)throws Exception{
        String consulta = String.format("UPDATE Cafe SET Productor='%s', VariedadCafe=%d, Cantidad=%f WHERE IDCafe=%d",
                cafe.getProductor(), cafe.getVariedadCafe(), cafe.getCantidad(), cafe.getIdcafe());
        try{
            datos.ejecutarInstruccion(consulta);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void borrar(Integer idCafe)throws Exception{
        String consulta = String.format("DELETE FROM Cafe WHERE IDCafe=%d", idCafe);
        try{
            datos.ejecutarInstruccion(consulta);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public LinkedList<Cafe> obtenerCafe() throws Exception{
        LinkedList<Cafe> Cafes = new LinkedList<>();
        String consulta = "SELECT * FROM Cafe";
        try{
            ResultSet resultado = datos.ejecutarConsulta(consulta);
            if(resultado != null){
                while(resultado.next()){
                    Cafes.add(new Cafe(resultado.getInt("IDCafe"), resultado.getString("Productor"), resultado.getInt("VariedadCafe"), resultado.getFloat("Cantidad")));
                }
                resultado.close();
            }
        }catch(SQLException e){
            System.err.println("Error al cargar Cafes");
            System.err.println(e.getMessage());
        }
        return Cafes;
    }
}
