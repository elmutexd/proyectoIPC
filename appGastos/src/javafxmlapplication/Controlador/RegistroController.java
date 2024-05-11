/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafxmlapplication.Modelo.Usuario;
import javafxmlapplication.Controlador.VistaController;

/**
 * FXML Controller class
 *
 * @author coozy
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNickname;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Text errNickname;
    @FXML
    private Text errPassword;
    @FXML
    private Text errDatos;
    
    private ObservableList<String> nicknames;
    @FXML
    private TextField txtApellido;
    @FXML
    private Text usuarioAdded;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nicknames = FXCollections.observableArrayList();
    }    

    @FXML
    private void cancelarR(ActionEvent event) {
    }

    @FXML
    private void aceptarR(ActionEvent event) {
        errNickname.setVisible(false);
        errPassword.setVisible(false);
        usuarioAdded.setVisible(false);
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nickname = txtNickname.getText();
        String password = txtPassword.getText();
        String correo = txtCorreo.getText();
        LocalDateTime fecha= LocalDateTime.now();
        Image fotoPerfil = new Image("../imagenes/usuarioPorDefecto.png");
        if(nombre.equals("") || apellido.equals("") || nickname.equals("") || password.equals("") || correo.equals("")){
            errDatos.setVisible(true);
        }
        else if(nickname.contains(" ")){
            errNickname.setText("El Nickname no puede contener espacios");
            errNickname.setVisible(true);
        }
        else if(nicknames.contains(nickname)){
           errNickname.setText("El Nickname ya existe");
           errNickname.setVisible(true);
        }
        else if(password.length()<=6){
            errPassword.setVisible(true);
        }else{
            usuarioAdded.setVisible(true);
            acount.registerUser(nombre,apellido,correo,nickname,password,fotoPerfil,fecha);
            Usuario.addUser(nombre, nickname, password, correo, fotoPerfil);
        
        }
        
        
    }
    
}
