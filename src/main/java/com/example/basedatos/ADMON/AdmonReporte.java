package com.example.basedatos.ADMON;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class AdmonReporte {
    private Datos datos;
    public AdmonReporte(){
        try{
            datos = Datos.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JasperPrint reporteCafes(){
        try {
            return JasperFillManager.fillReport("ReporteCafeSQL.jasper", null, datos.getConexion());
        } catch (JRException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
