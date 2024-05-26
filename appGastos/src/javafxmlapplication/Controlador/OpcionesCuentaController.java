/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.User;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class OpcionesCuentaController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private TextField apodo;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contraseñas;
    @FXML
    private Button guardar;
    @FXML
    private ImageView foto;
    private Image imagen;
    private BooleanProperty userB;
    private BooleanProperty contraB;
    private BooleanProperty correoB;
    private Label errorCorreo;
    @FXML
    private Text errCorreo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            errCorreo.setVisible(false);
            imagen =Acount.getInstance().getLoggedUser().getImage() ;
            foto.setImage(imagen);
            nombre.setText(Acount.getInstance().getLoggedUser().getName());
            apodo.setText(Acount.getInstance().getLoggedUser().getNickName());
            correo.setText(Acount.getInstance().getLoggedUser().getEmail());
            contraseñas.setText(Acount.getInstance().getLoggedUser().getPassword());
            
            
        }
        catch(Exception e){System.out.println("No hay imagen");}

    }
    private void hideErrorMessage(Text errorText,TextField textField)
    {
        errorText.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }

    @FXML
    private void cambiarImagen(ActionEvent event) throws AcountDAOException, IOException {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file != null){
            imagen = new Image(file.toURI().toString(),500,500,false,false); 
        }
        foto.setImage(imagen);
    }

    @FXML
    private void actguardar(ActionEvent event) throws AcountDAOException, IOException {
            if(User.checkEmail(correo.getText())){
                errCorreo.setVisible(false);
                correo.styleProperty().setValue("-fx-background-color: lightgreen");

                Acount.getInstance().getLoggedUser().setEmail(correo.getText());
                Acount.getInstance().getLoggedUser().setImage(imagen);
                Stage stage1 = (Stage) guardar.getScene().getWindow();
                stage1.close();
            
            }
            else{
                errCorreo.setVisible(true);
                correo.styleProperty().setValue("-fx-background-color: #FF8B8B");
            }
            
            

    }
       

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage1 = (Stage) guardar.getScene().getWindow();
        stage1.close();
        
    }
    private void lookImage(ActionEvent event) {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file != null){
            imagen = new Image(file.toURI().toString(),500,500,false,false); //hacer que todas las imagenes sean 500x500
            
        }
    }
    private void checkEmail(){
        if(!User.checkEmail(correo.getText())){
            
        }else{
            manageCorrect(errCorreo,correo,correoB);
        }
    }
    private void manageCorrect(Text errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }

    @FXML
    private void cambiarContra(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/cambioContrasenya.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        CambioConstrasenyaController contra = loader.load();
        
        
    }
    
}
