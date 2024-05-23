/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.Category;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class AddGastoController implements Initializable {

    @FXML
    private TextField addNombre;
    @FXML
    private TextField addCoste;
    @FXML
    private DatePicker addFecha;
    @FXML
    private TextArea addDesc;
    @FXML
    private ComboBox<Category> addCategoria;
    @FXML
    private TextField textoImagen;
    @FXML
    private Button subirImagen;
    @FXML
    private Button buttonAceptar;
    
    private boolean datosV;
    Image imagen;
    @FXML
    private Label errorAceptar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        addCoste.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    addCoste.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        errorAceptar.setVisible(false);
        addFecha.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
            
            public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.of(2020, Month.JANUARY, 1);
            setDisable(empty || date.compareTo(today) < 0 );
                }
            };
         });
        try{
        ObservableList<Category> observableList = FXCollections.observableList(Acount.getInstance().getUserCategories());
        addCategoria.setItems(observableList);
        }
        catch(Exception e){
            System.out.println("Error en añadir");
        }
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        if(getNombre()!=null && getCoste()!=null && getDate()!=null && getDesc()!=null /*&& getCategory()!=null && getImage()!=null*/){
            datosV=true;
            Stage stage1 = (Stage) buttonAceptar.getScene().getWindow();
            stage1.close();
            
        }
        else{
            errorAceptar.setVisible(true);
        }
        
    }
    public String getNombre(){
    
        return addNombre.getText();
    }
    public String getCoste(){
        return addCoste.getText();
    }
    public LocalDate getDate(){
        return addFecha.getValue();
    }
    public String getDesc(){
        return addDesc.getText();
    }
    public Category getCategory(){
        return addCategoria.getValue();
    }
    public Image getImage(){
        return imagen;
    }
    public boolean getDatosV(){
        return datosV;
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
       
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Cerrar Sesión");
        alerta.setContentText("Está seguro que desea salir ?");
        Optional <ButtonType> opciones = alerta.showAndWait();
        if(opciones.isPresent() && opciones.get()==ButtonType.OK){
             datosV=false;
            alerta.close();
            Stage stage1 = (Stage) buttonAceptar.getScene().getWindow();
            stage1.close();
            
        }
        else{
            alerta.close();
        }
    }

    @FXML
    private void rutaImagen(ActionEvent event) {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file != null){
            imagen = new Image(file.toURI().toString(),500,500,false,false); 
            textoImagen.setText(file.getName());
        }
        
    }

    @FXML
    private void crarCat(ActionEvent event) throws IOException {
            FXMLLoader loader= new  FXMLLoader(getClass().getResource("../Vista/VistaCat.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Crear Categoría");
            stage.show();
    }
    
}
