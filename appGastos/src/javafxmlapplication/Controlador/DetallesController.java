/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Charge;

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
            
            
            
            
            

    }
    public void setCosas(Charge gasto){
        nombre.setText(gasto.getName());
        descripcion.setText(gasto.getDescription());
        categoria.setText(gasto.getCategory().getName());
        coste.setText(gasto.getCost()+ "$");
        fecha.setText(gasto.getDate().toString());
        imagen.setImage(gasto.getImageScan());
    
    }

    @FXML
    private void salir(ActionEvent event) {
        nombre.getScene().getWindow().hide();
    }
    
}
