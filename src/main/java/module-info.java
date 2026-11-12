module com.example.basedatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires net.sf.jasperreports.core;

    opens com.example.basedatos.POJO;
    exports com.example.basedatos.POJO;
    opens com.example.basedatos.ADMON to javafx.base;
    opens com.example.basedatos to javafx.fxml, javafx.base;
    exports com.example.basedatos;
    exports EjemploJasper;
}
