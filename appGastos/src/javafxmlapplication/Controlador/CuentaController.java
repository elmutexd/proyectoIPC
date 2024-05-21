/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Charge;
/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CuentaController implements Initializable {

    @FXML
    private TableView<Charge> tabla;
    @FXML
    private TableColumn<Charge, String> cGasto;
    @FXML
    private TableColumn<Charge, String> cCat;
    @FXML
    private TableColumn<Charge, String> cFecha;
    @FXML
    private TableColumn<Charge, String> cCoste;
    @FXML
    private TableColumn<Charge, String> cDesc;
    
    private ObservableList<Charge> datos=null;
    @FXML
    private TableColumn<Charge, String> cImagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniModelo();
        
        
    }
     private void iniModelo(){
        ArrayList<Charge> misdatos= new ArrayList<>();
        datos = FXCollections.observableArrayList(misdatos);
        tabla.setItems(datos);
        cGasto.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getName()) );
        
        cCat.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getCategory().getName()) );
        cFecha.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getDate().toString()) ); 
        cCoste.setCellValueFactory(
                (cargo)-> new SimpleStringProperty((Double.toString(cargo.getValue().getCost())+"$")) );
        cImagen.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        cImagen.setCellFactory(c-> new ImagenTabCell());
        
    
    }
     class ImagenTabCell extends TableCell<Charge, String> {
            private ImageView view = new ImageView();
            private Image imagen;
            @Override
            protected void updateItem(String t, boolean bln) {
            super.updateItem(t, bln);
            if (t == null || bln) {
                setText(null);
                setGraphic(null);
            } else {
                imagen = new Image(t, 25, 25, true, true);
                view.setImage(imagen);
                setGraphic(view);
            }
        }
}
     
}