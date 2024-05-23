/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class modificarGastoController implements Initializable {

    @FXML
    private TextField addNombre;
    @FXML
    private DatePicker addFecha;
    @FXML
    private TextArea addDesc;
    @FXML
    private ComboBox<?> addCategoria;
    @FXML
    private TextField textoImagen;
    @FXML
    private Button subirImagen;
    @FXML
    private Button buttonAceptar;
    @FXML
    private TextField addCoste;
    @FXML
    private Label errorAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crarCat(ActionEvent event) {
    }

    @FXML
    private void rutaImagen(ActionEvent event) {
    }

    @FXML
    private void aceptar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
