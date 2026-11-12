package EjemploJasper;

import com.example.basedatos.ADMON.AdmonCafe;
import com.example.basedatos.POJO.Cafe;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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

            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(cafes);
            JasperPrint reporte = JasperFillManager.fillReport("ReporteCafe.jasper", null, datos);

            JasperViewer vista = new JasperViewer(reporte, false);
            vista.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
