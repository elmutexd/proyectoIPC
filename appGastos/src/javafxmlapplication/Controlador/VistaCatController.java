/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Acount;
import model.AcountDAOException;
import model.Category;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaCatController implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descTextField;
    
    private Category cat;
    
    private boolean edit = false;
    @FXML
    private Label etiquetaError;
    
    public void initCat(Category p){
        cat = p;
        nombreTextField.setText(p.getName());
        descTextField.setText(p.getDescription());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etiquetaError.setVisible(false);
        
    }    

    @FXML
    private void aceptar(ActionEvent event) throws AcountDAOException, IOException {
        if(nombreTextField.getText()!=null && descTextField.getText()!=null){
            Acount.getInstance().registerCategory(nombreTextField.getText(),descTextField.getText());
            nombreTextField.getScene().getWindow().hide();
        }
        else{
            etiquetaError.setVisible(true);

        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        nombreTextField.getScene().getWindow().hide();
    }
}
