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
    private TextField descTextField;
    
    private Category cat;
    
    private boolean edit = false;
    
    public void initCat(Category p){
        cat = p;
        nombreTextField.setText(p.getName());
        descTextField.setText(p.getDescription());
        edit = true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    

    @FXML
    private void aceptar(ActionEvent event) throws AcountDAOException, IOException {
        if(edit = true){
            cat.setName(nombreTextField.getText());
            cat.setDescription(descTextField.getText());
        }
        else{
            Acount.getInstance().registerCategory(nombreTextField.getText(),descTextField.getText());
        }
        nombreTextField.getScene().getWindow().hide();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        nombreTextField.getScene().getWindow().hide();
    }
}
