package EjemploJasper;

import com.example.basedatos.ADMON.AdmonCafe;
import com.example.basedatos.ADMON.Datos;
import com.example.basedatos.POJO.Cafe;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EjemploJasper {
    public static void main(String[] args){
        try {
            AdmonCafe admon = new AdmonCafe();
            LinkedList<Cafe> cafes = admon.obtenerCafe();

            for(Cafe cafe : cafes){
                System.out.println(cafe);
            }

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("anio", "2067");

            //JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(cafes);
            Connection conexion = Datos.getInstance().getConexion();

            //JasperPrint reporte = JasperFillManager.fillReport("ReporteCafe.jasper", parametros, datos);
            JasperPrint reporte = JasperFillManager.fillReport("ReporteCafe.jasper", parametros, conexion);

            JasperExportManager.exportReportToPdfFile(reporte, "ReporteCafe.pdf");

            JasperViewer vista = new JasperViewer(reporte, false);
            vista.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
