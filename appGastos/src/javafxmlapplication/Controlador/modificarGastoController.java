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
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class modificarGastoController implements Initializable {

    @FXML
    private TextField addNombre;
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
    @FXML
    private TextField addCoste;
    @FXML
    private Label errorAceptar;
    Image imagen;
    private boolean datosV;

    private Charge gasto;
    @FXML
    private Label errorCantidad;
    boolean cambioCat=false;
    private Category cat;

    /**
     * Initializes the controller class.
     */
    
    public void setCosas(Charge c){
        addCategoria.setCellFactory(i -> new modificarGastoController.catBoxListCell());
        addCategoria.setButtonCell(new modificarGastoController.catButtCell());
<<<<<<< HEAD
        gasto=c;
        cat= gasto.getCategory();
        addNombre.setText(gasto.getName());
=======
        gasto = c;
        addNombre.setText(gasto.getName());
        cat = gasto.getCategory();
>>>>>>> 1cf1eb33b81363399a9a9fca7f7618ad4622b915
        addFecha.setValue(gasto.getDate());
        addDesc.setText(gasto.getDescription());
        addCategoria.getSelectionModel().select(cat);
        textoImagen.setText(gasto.getImageScan().toString());
        
        if(gasto.getImageScan()!=null){
            imagen = gasto.getImageScan();

        }
        addCoste.setText(Double.toString(gasto.getCost()));
    
    }
    public void initialize(URL url, ResourceBundle rb) {
       addCoste.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    addCoste.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
       addCategoria.valueProperty().addListener(
               (ob,oldV,newV) ->{cambioCat=true;       }
       
       );
       
        
        addCategoria.setCellFactory(c -> new modificarGastoController.catBoxListCell());
        addCategoria.setButtonCell(new modificarGastoController.catButtCell());
        

        errorCantidad.setVisible(false);

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
        addCategoria.setValue(gasto.getCategory());
        
        }
        catch(Exception e){
            System.out.println("Error en añadir");
        }
        
    }    

    @FXML
    private void crarCat(ActionEvent event) throws IOException, AcountDAOException {
            FXMLLoader loader= new  FXMLLoader(getClass().getResource("../Vista/VistaCat.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Crear Categoria");
            stage.showAndWait();
            ObservableList<Category> observableList = FXCollections.observableList(Acount.getInstance().getUserCategories());
            addCategoria.setItems(observableList);
            
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
    private void aceptar(ActionEvent event) throws AcountDAOException, IOException {
        if(!addCoste.getText().matches("\\d*\\.?\\d+")){
            errorCantidad.setVisible(true);
            addCoste.styleProperty().setValue("-fx-background-color: #FF8B8B");
        }
            else{
                    
            if(getNombre()!=null && getCoste()!=null && getDate()!=null && getDesc()!=null ){
                if(!cambioCat){
                    addCategoria.setValue(cat);
                    
                }
                datosV=true;
                Stage stage1 = (Stage) buttonAceptar.getScene().getWindow();
                stage1.close();
                
            }
            else{
                errorAceptar.setVisible(true);
            }
        }
    }
        
        
    public String getNombre(){
    
        return addNombre.getText();
    }
    public Double getCoste(){
        return Double.parseDouble(addCoste.getText());
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
    private void lookImage(ActionEvent event) {
        FileChooser choose = new FileChooser();
        File file = choose.showOpenDialog(new Stage());
        if(file != null){
            imagen = new Image(file.toURI().toString(),500,500,false,false); //hacer que todas las imagenes sean 500x500
            textoImagen.setText(file.getName());
        }
    }
    class catBoxListCell extends ComboBoxListCell<Category>{
        @Override
        public void updateItem(Category t, boolean bln) {
            super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            if(t == null || bln){setText(null);}
            else{setText(t.getName());}
        }
    }
    
    class catButtCell extends ListCell<Category>{
        @Override
        protected void updateItem(Category t, boolean bln) {
            super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            if(t == null || bln){setText(null);}
            else{setText(t.getName());}
        }
    }
    
    private void checkCoste(){
        if(addCoste.getText().matches("[0-9]*.??[0-9]*")){
            errorCantidad.setVisible(false);
            addCoste.styleProperty().setValue(null); 
        }else{
            errorCantidad.visibleProperty().set(true);
            addCoste.styleProperty().setValue("-fx-background-color: #FF8B8B"); 
        }
    }
}
