/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class AñadirGastoController implements Initializable {

    @FXML
    private TextField textGasto;
    @FXML
    private ComboBox<?> comboCat;
    @FXML
    private DatePicker pickFecha;
    @FXML
    private TextField campoCoste;
    @FXML
    private TextArea campoDescripcion;
    @FXML
    private TextField campoRutaImg;
    private Stage stage;
    @FXML
    private Button botonAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stage = (Stage) botonAceptar.getScene().getWindow();
    }    

    @FXML
    private void crearCategoria(ActionEvent event) {
    }

    @FXML
    private void subirImagen(ActionEvent event) {
    }

    @FXML
    private void aceptar(ActionEvent event) {
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Advertencia al cancelar");
        alerta.setHeaderText("Cancelar");
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.isPresent()&& result.get()==ButtonType.OK){
            alerta.close();
            stage.close();
        }
        else{
            alerta.close();
        
        }
        
        
        alerta.show();
    }
    
}
