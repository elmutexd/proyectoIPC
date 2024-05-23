/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class DetallesController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Label descripcion;
    @FXML
    private Label categoria;
    @FXML
    private Label coste;
    @FXML
    private Label fecha;
    @FXML
    private ImageView imagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/detalles.fxml"));
            CuentaController cuenta = loader.getController();
            nombre.setText(cuenta.gastoSeleccionado().getName());
            descripcion.setText(cuenta.gastoSeleccionado().getDescription());
            categoria.setText(cuenta.gastoSeleccionado().getCategory().toString());
            coste.setText(Double.toString(cuenta.gastoSeleccionado().getCost()));
            fecha.setText(cuenta.gastoSeleccionado().getDate().toString());
            imagen.setImage(cuenta.gastoSeleccionado().getImageScan());
            
            
            

    }    
    
}
