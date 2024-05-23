/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

/**
 * FXML Controller class
 *
 * @author coozy
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Acount;
import model.AcountDAOException;
import model.User;
import static model.User.checkPassword;

/**
 * FXML Controller class
 *
 * @author coozy
 */
public class InicioSesionController implements Initializable {

    
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    @FXML
    private PasswordField passField;
    @FXML
    private HBox HBox_ocultarPass;
    @FXML
    private HBox HBox_mostrarPass;
    @FXML
    private TextField showPass;
    @FXML
    private TextField userTxt;
    boolean isOk;
    @FXML
    private Text txt_uNoRegistrado;
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPass.textProperty().bindBidirectional(passField.textProperty());
        // Ruta del archivo de vídeo

        String videoFile = "src/imagenes/videoDinero.mp4";

        // Crear objeto Media
        media = new Media(new File(videoFile).toURI().toString());
        // Crear objeto MediaPlayer
        mediaPlayer = new MediaPlayer(media);
        // Asociar MediaPlayer al MediaView
        mediaView.setMediaPlayer(mediaPlayer);
        // Reproducir el vídeo
        // Agregar listener para reiniciar el video al finalizar
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            });

        mediaPlayer.play();
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.ZERO);
        }
        
        
         // Bind the size of the MediaView to the size of the AnchorPane
        mediaView.fitWidthProperty().bind(gridPane.widthProperty());
        mediaView.fitHeightProperty().bind(gridPane.heightProperty());
        mediaView.setPreserveRatio(true);
    }

    
        
    
    
    
    @FXML
    private void mostrarPassword(MouseEvent event) {
       
       HBox_ocultarPass.setVisible(false);
       HBox_mostrarPass.setVisible(true);
    }

    @FXML
    private void ocultarPass(MouseEvent event) {
        
        HBox_mostrarPass.setVisible(false);
        HBox_ocultarPass.setVisible(true);
       
       
    }

    @FXML
    private void Cambiar_contrasenya(MouseEvent event) {
    }

    @FXML
    private void Iniciar_sesion(ActionEvent event) throws AcountDAOException, IOException {
      isOk = Acount.getInstance().logInUserByCredentials(userTxt.getText(), showPass.getText());
      if(isOk){ //SE INICIA SESIÓN
          try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Vista/Cuenta.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva escena y un nuevo stage
            Stage stage = new Stage();
            stage.setTitle("Cuenta de " + Acount.getInstance().getLoggedUser().getName());
            stage.setScene(new Scene(root));
            stage.show();
            // Cerrar la ventana actual (Vista.xml)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      
      }
      else{
          if(!Acount.getInstance().existsLogin(userTxt.getText())){
              txt_uNoRegistrado.setText("Usuario no registrado");
              txt_uNoRegistrado.setVisible(true);}
          else{
              txt_uNoRegistrado.setText("Contraseña incorrecta");
              txt_uNoRegistrado.setVisible(true);
          }
      }
    }

    @FXML
    private void registrarse(MouseEvent event) {
        try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Vista/Registroo.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva escena y un nuevo stage
            Stage stage = new Stage();
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));
            stage.show();
            // Cerrar la ventana actual (Vista.xml)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void terminos_condiciones(MouseEvent event) {
        try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Vista/TerminosyCondiciones.fxml"));
            Parent root = fxmlLoader.load();

            // Crear una nueva escena y un nuevo stage
            Stage stage = new Stage();
            stage.setTitle("Términos y condiciones");
            stage.setScene(new Scene(root));
            stage.show();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
