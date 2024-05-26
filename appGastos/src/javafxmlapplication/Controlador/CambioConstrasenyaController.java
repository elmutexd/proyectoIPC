/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.User;

/**
 * FXML Controller class
 *
 * @author coozy
 */

public class CambioConstrasenyaController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private HBox HBoxHideCont;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private HBox HBoxShowCont;
    @FXML
    private TextField txtShowPass;
    @FXML
    private HBox HBoxHideRepCont;
    @FXML
    private PasswordField txtRepPass;
    @FXML
    private HBox HBoxShowRepCont;
    @FXML
    private TextField txtShowRepPass;
    @FXML
    private Text errFormatoIncorrecto;
    @FXML
    private Text errDatos;
    @FXML
    private Text contrCambiada;
    @FXML
    private Text errPass;
    private boolean isOk;
    private boolean bPass = false,bNewPass = false;
    @FXML
    private ImageView txtHideNewPass;
    @FXML
    private ImageView txtShowNewPass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // Crear un ChangeListener que verificará si los tres campos están vacíos
        ChangeListener<String> listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkFields(txtUser, txtPassword, txtRepPass);
            }
        };

        // Agregar el listener a cada TextField
        txtUser.textProperty().addListener(listener);
        txtPassword.textProperty().addListener(listener);
        txtRepPass.textProperty().addListener(listener);
        
        
        
        txtShowPass.textProperty().bindBidirectional(txtPassword.textProperty());
        txtShowRepPass.textProperty().bindBidirectional(txtRepPass.textProperty());
        txtUser.focusedProperty().addListener((ob,oldV,newV)->{if(!newV){
            txtUser.styleProperty().setValue("");
        }});
        txtPassword.focusedProperty().addListener((ob,oldV,newV)->{if(!newV){try {
            checkPass1();
            } catch (IOException ex) {
                Logger.getLogger(CambioConstrasenyaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AcountDAOException ex) {
                Logger.getLogger(CambioConstrasenyaController.class.getName()).log(Level.SEVERE, null, ex);
            }
}});
        txtRepPass.focusedProperty().addListener((ob,oldV,newV)->{if(!newV){
            checkPassword();}});
    }    
    private boolean sinCamposVacios(String nickname, String password,String password2){
    if( nickname.isEmpty() || password.isEmpty() ||password2.isEmpty()){
            errDatos.setVisible(true);
            
            if(nickname.isEmpty()){txtUser.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtUser.styleProperty().setValue(""); }
            if(password.isEmpty()){txtPassword.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtPassword.styleProperty().setValue(""); }
            if(password2.isEmpty()){txtRepPass.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtRepPass.styleProperty().setValue(""); }
            
        return false;
        }
        errDatos.setVisible(false);
        txtUser.styleProperty().setValue(""); 
        txtPassword.styleProperty().setValue(""); 
        txtRepPass.styleProperty().setValue(""); 
        return true;
    }
    
    private void checkFields(TextField tf1, TextField tf2, TextField tf3) {
        if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()) {
            errDatos.setVisible(true);
        } else {
            errDatos.setVisible(false);
        }
    }
    
    
    private void checkPassword(){
        if(!User.checkPassword(txtRepPass.getText())){
            bNewPass = false;
            txtRepPass.styleProperty().setValue("-fx-background-color: #FF8B8B");
            txtShowRepPass.styleProperty().setValue("-fx-background-color: #FF8B8B");
            errFormatoIncorrecto.setVisible(true);
        }else{
            bNewPass = true;
            txtRepPass.styleProperty().setValue("");
            txtShowRepPass.styleProperty().setValue("");
            errFormatoIncorrecto.setVisible(false);
        }
    }
    private void checkPass1() throws IOException, AcountDAOException{
        isOk = Acount.getInstance().logInUserByCredentials(txtUser.getText(), txtShowPass.getText());
        if(!isOk){
            bPass = false;
            errPass.setVisible(true);
            txtPassword.styleProperty().setValue("-fx-background-color: #FF8B8B");
            txtShowPass.styleProperty().setValue("-fx-background-color: #FF8B8B");
            txtUser.styleProperty().setValue("-fx-background-color: #FF8B8B");
        }
        else{
            bPass = true;
            errPass.setVisible(false);
            txtPassword.styleProperty().setValue("");
            txtShowPass.styleProperty().setValue("");
            txtUser.styleProperty().setValue("");
        }
    }
    
    
    

    @FXML
    private void showPass(MouseEvent event) {
        HBoxShowCont.setVisible(true);
        HBoxHideCont.setVisible(false);
        
    }

    @FXML
    private void hidePass(MouseEvent event) {
         HBoxShowCont.setVisible(false);
         HBoxHideCont.setVisible(true);
    }


    private void showRepPass(MouseEvent event) {
        HBoxShowRepCont.setVisible(true);
        HBoxHideRepCont.setVisible(false);
    }

    private void hideRepPass(MouseEvent event) {
        HBoxShowRepCont.setVisible(false);
        HBoxHideRepCont.setVisible(true);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
        try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Vista/inicioSesion.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva escena y un nuevo stage
            Stage stage = new Stage();
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));
            stage.show();
            
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aceptar(ActionEvent event) throws IOException, AcountDAOException {
        String nickname = txtUser.getText();
        String password = txtPassword.getText();
        String password2 = txtRepPass.getText();
        if(sinCamposVacios(nickname, password, password2)){
            errDatos.setVisible(false);
            checkPassword(); checkPass1();
            if(bPass && bNewPass){
                contrCambiada.setVisible(true);
                
            
            }
        
        }else{
        errDatos.setVisible(true);
        }
        
    }

    @FXML
    private void showNewPass(MouseEvent event) {
    }

    @FXML
    private void hideNewPass(MouseEvent event) {
    }
    
}
