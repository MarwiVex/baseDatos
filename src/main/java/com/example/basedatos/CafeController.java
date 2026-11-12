package com.example.basedatos;

import com.example.basedatos.ADMON.AdmonCafe;
import com.example.basedatos.ADMON.AdmonVariedadCafe;
import com.example.basedatos.POJO.Cafe;
import com.example.basedatos.POJO.VariedadCafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CafeController implements Initializable {
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldProductor;
    @FXML
    private TextField textFieldCantidad;
    @FXML
    private ComboBox<VariedadCafe> comboVariedad;
    @FXML
    private TableView<Cafe> tablaCafe;

    private AdmonCafe AdministradorCafe;
    private AdmonVariedadCafe Variedad;

    public CafeController(){
        try{
            AdministradorCafe = new AdmonCafe();
            Variedad = new AdmonVariedadCafe();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void clicInformacion(MouseEvent mouseEvent) {
        Cafe cafe = (Cafe)tablaCafe.getSelectionModel().getSelectedItem();
        if (cafe == null) {
            return;
        }
        textFieldCantidad.setText(String.valueOf(cafe.getCantidad()));
        textFieldID.setText(String.valueOf(cafe.getIdcafe()));
        textFieldProductor.setText(String.valueOf(cafe.getProductor()));
        for(VariedadCafe variedad : comboVariedad.getItems()){
            if(variedad.getIDVariedad().equals(cafe.getVariedadCafe())){
                comboVariedad.getSelectionModel().select(variedad);
            }
        }
    }

    public void clicAgregar(ActionEvent actionEvent) {
        Cafe cafe = new Cafe(null, textFieldProductor.getText(), comboVariedad.getSelectionModel().getSelectedItem().getIDVariedad(), Float.valueOf(textFieldCantidad.getText()));
        try {
            AdministradorCafe.agregar(cafe);
            cargarTabla();
            limpiarCampos();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void clicEditar(ActionEvent actionEvent) {
        Cafe cafe = new Cafe(Integer.valueOf(textFieldID.getText()), textFieldProductor.getText(), comboVariedad.getSelectionModel().getSelectedItem().getIDVariedad(), Float.valueOf(textFieldCantidad.getText()));
        try {
            AdministradorCafe.editar(cafe);
            cargarTabla();
            limpiarCampos();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void clicBorrar(ActionEvent actionEvent) {
        try{
            AdministradorCafe.borrar(Integer.valueOf(textFieldID.getText()));
            cargarTabla();
            limpiarCampos();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarTabla();

        try{
            comboVariedad.getItems().setAll(Variedad.obtenerVariedadCafe());
        }catch(Exception e){
            System.err.println("Error al cargar variedades");
            System.err.println(e.getMessage());
        }

        cargarTabla();
    }

    private void inicializarTabla() {
        TableColumn<Cafe, Integer> tcID = new TableColumn<>("ID");
        TableColumn<Cafe, String> tcProductor = new TableColumn<>("Productor");
        TableColumn<Cafe, Integer> tcVariedadID = new TableColumn<>("VariedadID");
        TableColumn<Cafe, Float> tcCantidad = new TableColumn<>("Cantidad");

        tcID.setCellValueFactory(new PropertyValueFactory<>("IDCafe"));
        tcProductor.setCellValueFactory(new PropertyValueFactory<>("Productor"));
        tcVariedadID.setCellValueFactory(new PropertyValueFactory<>("VariedadCafe"));
        tcCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));

        tablaCafe.getColumns().setAll(tcID, tcProductor, tcVariedadID, tcCantidad);
    }

    public void cargarTabla(){
        try{
            tablaCafe.getItems().setAll(AdministradorCafe.obtenerCafe());
        }catch(Exception e){
            System.err.println("Error al cargar Cafes");
            System.err.println(e.getMessage());
        }
    }

    public void limpiarCampos(){
        textFieldProductor.setText("");
        textFieldID.setText("");
        textFieldCantidad.setText("");
        comboVariedad.getSelectionModel().clearSelection();
    }
}
