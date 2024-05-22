package javafxmlapplication.Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxmlapplication.Modelo.Usuario;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author coozy
 */
public class VistaController implements Initializable {

    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passwordField;
    /*private Acount acount;*/
     
    @FXML
    private Text errInicioSesion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {/*
        try {
            acount = acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException, AcountDAOException {
        String nombre = usuarioField.getText();
        String password = passwordField.getText();
        boolean isOK= Acount.getInstance().logInUserByCredentials(nombre,password);
        if(isOK){
        //se inicia sesión
            FXMLLoader registro = new FXMLLoader(getClass().getResource("../Vista/Cuenta.fxml"));
            Parent root = registro.load();
            RegistroController controller = registro.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        }
        else{
            //no se encuentra el usuario como registrado
            errInicioSesion.setVisible(true);
         }
    }


    @FXML
    private void registrar(MouseEvent event) throws IOException {
       FXMLLoader registro = new FXMLLoader(getClass().getResource("../Vista/Registro.fxml"));
        Parent root = registro.load();
        RegistroController controller = registro.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait(); //para "Bloquear" la escena anterior
    }
    }