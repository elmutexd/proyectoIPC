/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.Controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;


/**
 *
 * @author svalero
 */
public class FXMLSignUpController implements Initializable {


 
    //properties to control valid fieds values. 
    private BooleanProperty validPassword;
    private BooleanProperty validEmail;
    private BooleanProperty equalPasswords; 
    private BooleanProperty validName;
    private BooleanProperty validLName;
    private BooleanProperty validUsername;

    //private BooleanBinding validFields;
    
    //When to strings are equal, compareTo returns zero
    private final int EQUALS = 0;  
    @FXML
    private Button accept;
    @FXML
    private Button cancel;
    @FXML
    private TextField email;
    @FXML
    private Label lIncorrectEmail;
    @FXML
    private PasswordField password;
    @FXML
    private Label lIncorrectPassword;
    @FXML
    private PasswordField rpassword;
    @FXML
    private Label lIncorrectPasswordR;
    @FXML
    private TextField name;
    @FXML
    private TextField lname;
    @FXML
    private Label lIncorrectName;
    @FXML
    private TextField username;
    @FXML
    private Label lIncorrectUsername;
    
    Image pfp;
    
    @FXML
    private Label pictureName;

    /**
     * Updates the boolProp to false.Changes to red the background of the edit. 
     * Makes the error label visible and sends the focus to the edit. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
 
    }
    /**
     * Updates the boolProp to true. Changes the background 
     * of the edit to the default value. Makes the error label invisible. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }
    /**
     * Changes to red the background of the edit and
     * makes the error label visible
     * @param errorLabel
     * @param textField 
     */
    private void showErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FCE5E0");    
    }
    /**
     * Changes the background of the edit to the default value
     * and makes the error label invisible.
     * @param errorLabel
     * @param textField 
     */
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }

    private void checkEditEmail(){
        if(!Utils.checkEmail(email.textProperty().getValueSafe())){
            manageError(lIncorrectEmail,email,validEmail);
        }else{
            manageCorrect(lIncorrectEmail,email,validEmail);
        }
    }
    
    private void checkEditPassword(){
        //String p = password.textProperty().toString();
        //if(p.length() < 8 || p.length() > 15 || !p.trim().equals(p)){
        if(!Utils.checkPassword(password.textProperty().getValueSafe())){
            manageError(lIncorrectPassword,password,validPassword);
        }else{
            manageCorrect(lIncorrectPassword,password,validPassword);
        }
    }
    
    private void checkEquals(){
        if(password.textProperty().getValueSafe().compareTo(rpassword.textProperty().getValueSafe()) != EQUALS){
            showErrorMessage(lIncorrectPasswordR,rpassword);
            equalPasswords.setValue(Boolean.FALSE);
            password.textProperty().setValue("");
            rpassword.textProperty().setValue("");
            password.requestFocus();
        }else{
            manageCorrect(lIncorrectPasswordR,rpassword,equalPasswords);
        }
    }
    
    private void checkEditName(){ //EN UTILS AVERIGUAR COMO ACEPTAR TAMBIÉN UN ESPACIO EN MEDIO
        if(!Utils.checkNames(name.textProperty().getValueSafe())){
            manageError( lIncorrectName, name, validName);
        }else{
            manageCorrect(lIncorrectName, name, validName);
        }
    } 
    
    private void checkEditLName(){ //EN UTILS AVERIGUAR COMO ACEPTAR TAMBIÉN UN ESPACIO EN MEDIO
        if(!Utils.checkNames(lname.textProperty().getValueSafe())){
            manageError( lIncorrectName, lname, validLName);
        }else{
            manageCorrect(lIncorrectName, lname, validLName);
        }
    }
    
    private void checkEditUsername(){
        if(!Utils.checkUsername(username.textProperty().getValueSafe())){
            manageError(lIncorrectUsername,username,validUsername);
        }else{
            manageCorrect(lIncorrectUsername,username,validUsername);
        }
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        validEmail = new SimpleBooleanProperty();
        validPassword = new SimpleBooleanProperty();   
        equalPasswords = new SimpleBooleanProperty();
        validName = new SimpleBooleanProperty();
        validLName = new SimpleBooleanProperty();
        validUsername = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        equalPasswords.setValue(Boolean.FALSE);
        validName.setValue(Boolean.FALSE);
        validLName.setValue(Boolean.FALSE);
        validUsername.setValue(Boolean.FALSE);
        
        
        email.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEditEmail();}
        });
       
        password.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEditPassword();}
        });
        
        rpassword.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEquals();}
        });
        
        name.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEditName();}
        });
        
        lname.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEditLName();}
        });
        
        username.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){checkEditUsername();}
        });
        
        BooleanBinding validFields = Bindings.and(validEmail, validPassword)
                 .and(equalPasswords).and(validName).and(validLName).and(validUsername);
        
        accept.disableProperty().bind(Bindings.not(validFields));
        
        cancel.setOnAction((event)->{
            cancel.getScene().getWindow().hide();
        });
    } 

    @FXML
    private void AcceptOnAction(ActionEvent event) throws AcountDAOException, IOException {
        String uname = name.textProperty().toString();
            uname = uname.substring(0, 1).toUpperCase() + uname.substring(1);//Primer Mayuscula
        String ulname = lname.textProperty().toString();
            ulname = ulname.substring(0, 1).toUpperCase() + ulname.substring(1);//Primer Mayuscula
        String uusername = username.textProperty().toString();        
        String uemail = email.textProperty().toString();
        String upass = password.textProperty().toString();
        if(pfp == null){
            pfp = new Image(File.separator+"icons"+File.separator+"default_profile_2.png");
        }
        boolean reg = Acount.getInstance().registerUser
            (uname,ulname,uemail,uusername,upass,pfp,LocalDate.now());
        email.textProperty().setValue("");
        password.textProperty().setValue("");
        rpassword.textProperty().setValue("");
        name.textProperty().setValue("");
        lname.textProperty().setValue("");
        username.textProperty().setValue("");
        pfp = null;
        pictureName.setText("");
        
        validPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        equalPasswords.setValue(Boolean.FALSE);
        validName.setValue(Boolean.FALSE);
        validLName.setValue(Boolean.FALSE);
        validUsername.setValue(Boolean.FALSE);
    }

    @FXML
    private void CancelOnAction(ActionEvent event) {
        email.textProperty().setValue("");
        password.textProperty().setValue("");
        rpassword.textProperty().setValue("");
        name.textProperty().setValue("");
        lname.textProperty().setValue("");
        username.textProperty().setValue("");
        pfp = null;
        pictureName.setText("");
        
        validPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        equalPasswords.setValue(Boolean.FALSE);
        validName.setValue(Boolean.FALSE);
        validLName.setValue(Boolean.FALSE);
        validUsername.setValue(Boolean.FALSE);
    }

    @FXML
    private void lookImage(ActionEvent event) {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file != null){
            pfp = new Image(file.toURI().toString(),500,500,false,false); //hacer que todas las imagenes sean 500x500
            pictureName.setText(file.getName());
        }
    }
}