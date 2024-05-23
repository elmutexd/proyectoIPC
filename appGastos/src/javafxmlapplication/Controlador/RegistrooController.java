/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.User;



/**
 * FXML Controller class
 *
 * @author coozy
 */
public class RegistrooController implements Initializable {

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
    
    @FXML
    private TextField txtApellido;
    @FXML
    private Text usuarioAdded;
    @FXML
    private PasswordField txtRepPass;
    @FXML
    private Text errUsuarioyaRegistrado;
    @FXML
    private Text errPassword2;
    
    
    @FXML
    private Label rutafoto;
    @FXML
    private ImageView fotoPerfil;
        
    Image pfp;
    @FXML
    private Text errCorreo;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniFoto(null);
        // TODO
       
    }    
    
    
    @FXML
    private void cancelarR(ActionEvent event) {
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
    private boolean sinCamposVacios(String nombre, String apellido, String nickname, String password,String password2, String correo){
    if(nombre.isEmpty() || apellido.isEmpty() || nickname.isEmpty() || password.isEmpty() ||password2.isEmpty() || correo.isEmpty()){
            errDatos.setVisible(true);
            if(nombre.isEmpty()){txtNombre.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtNombre.styleProperty().setValue(""); }
            if(apellido.isEmpty()){txtApellido.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtApellido.styleProperty().setValue(""); }
            if(nickname.isEmpty()){txtNickname.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtNickname.styleProperty().setValue(""); }
            if(password.isEmpty()){txtPassword.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtPassword.styleProperty().setValue(""); }
            if(password2.isEmpty()){txtRepPass.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtRepPass.styleProperty().setValue(""); }
            if(correo.isEmpty()){txtCorreo.styleProperty().setValue("-fx-background-color: #FF8B8B");   }
            else{txtCorreo.styleProperty().setValue(""); }
        return false;
        }
        errDatos.setVisible(false);
        txtNombre.styleProperty().setValue(""); 
        txtApellido.styleProperty().setValue(""); 
        txtNickname.styleProperty().setValue(""); 
        txtPassword.styleProperty().setValue(""); 
        txtRepPass.styleProperty().setValue(""); 
        txtCorreo.styleProperty().setValue(""); 
        return true;
    }
    
    private boolean nickSinEspacios(String nickname){
    if(nickname.contains(" ")){
            showErrorMessage(errNickname, txtNickname);
            return false;
        }
        hideErrorMessage(errNickname, txtNickname);
        return true;
    }
    
    private boolean userNotInUse(String nickname) throws AcountDAOException, IOException{
        if(Acount.getInstance().existsLogin(nickname)){
        showErrorMessage(errUsuarioyaRegistrado, txtNickname);
        return false;
        }
        hideErrorMessage(errUsuarioyaRegistrado, txtNickname);
        return true;
    }
    private boolean isPasswordCorrect(String password){
    if(password.length()<=6){
            showErrorMessage(errPassword,txtPassword);
            return false;
        }
    hideErrorMessage(errPassword, txtPassword);
    return true;
    }
    private boolean passwordsMatch(String password, String password2){
        if(!password.equals(password2)){
            errPassword2.visibleProperty().set(true);
            txtPassword.styleProperty().setValue("-fx-background-color: #FF8B8B");  
            txtRepPass.styleProperty().setValue("-fx-background-color: #FF8B8B");  
            return false;
        }
        errPassword2.visibleProperty().set(false);
            txtPassword.styleProperty().setValue("");  
            txtRepPass.styleProperty().setValue(""); 
       return true;
    }
    
    private void showErrorMessage(Text errorText,TextField textField)
    {
        errorText.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FF8B8B");    
    }
    private void hideErrorMessage(Text errorText,TextField textField)
    {
        errorText.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    
    @FXML
    private void aceptarR(ActionEvent event) throws AcountDAOException, IOException {
        errNickname.setVisible(false);
        errPassword.setVisible(false);
        usuarioAdded.setVisible(false);
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nickname = txtNickname.getText();
        String password = txtPassword.getText();
        String password2 = txtRepPass.getText();
        String correo = txtCorreo.getText();
        LocalDate fecha= LocalDate.now();
        
        boolean bemail = true, bpassword = true, bnickname = true;
        hideErrorMessage(errCorreo,txtCorreo);
        hideErrorMessage(errPassword,txtPassword);
        hideErrorMessage(errNickname,txtNickname);
        hideErrorMessage(errUsuarioyaRegistrado,txtNickname);
         
        
        //Image fotoPerfil = new Image("C:\\Users\\coozy\\Desktop\\CODIGO\\JAVAFX\\plantilla\\MyMoney\\src\\imagenes\\user.png");
        
        //PARA DAR UN ORDEN DE VERIFICACIÓN
        if(sinCamposVacios( nombre, apellido, nickname, password,password2, correo)){
        /*    if(nickSinEspacios(nickname)){
                userNotInUse(nickname);
            
            
            }
            if(User.checkPassword(password)){
                passwordsMatch(password,password2);
            }
        } if(!nickname.isEmpty()){
            if(nickSinEspacios(nickname)){userNotInUse(nickname);}
        }
        if(!password.isEmpty()){
            if(isPasswordCorrect(password)){passwordsMatch(password,password2);}
        }
        
        //REGISTRAR
        if(sinCampVac &&nickSinEsp &&uNotInUse &&passCorrect &&passMatch){
            usuarioAdded.setVisible(true);*/
        if(!User.checkEmail(correo)){
            showErrorMessage(errCorreo,txtCorreo);
            bemail = false;
        }
        if(!User.checkPassword(password)){
            showErrorMessage(errPassword,txtPassword);
            bpassword = false;
        }else{bpassword = passwordsMatch(password,password2);}
            
        if(!User.checkNickName(nickname)){
            showErrorMessage(errNickname,txtNickname);
            bnickname = false;
        }else if(Acount.getInstance().existsLogin(nickname)){
          showErrorMessage(errUsuarioyaRegistrado,txtNickname);
          bnickname = false;
        }
        
        if(bemail && bpassword && bnickname){
            Acount.getInstance().registerUser(nombre,apellido,correo,nickname,password,pfp,fecha);
            usuarioAdded.setVisible(true);
        }
            
        
        }
        
        
    }

    @FXML
    private void buscarImagen(ActionEvent event) {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file!=null){
            pfp = new Image(file.toURI().toString(),500,500,false,false);
            rutafoto.setText(file.getName());
            
            fotoPerfil.setImage(pfp);
        }
        
    }

    @FXML
    private void iniFoto(ActionEvent event) {
        pfp = new Image("imagenes/default_profile_2.png");
        fotoPerfil.setImage(null);
        rutafoto.setText("");
    }
    

}