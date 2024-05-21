/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    @FXML
    private Button borrarButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniModelo();
        borrarButton.setDisable(true);
        borrarButton.disableProperty().bind(Bindings.equal(-1, tabla.getSelectionModel().selectedIndexProperty()));
        
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

    @FXML
    private void añadir(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/añadirGasto.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void borrar(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
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