/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmlapplication.Modelo.Gasto;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CuentaController implements Initializable {

    @FXML
    private TableView<Gasto> tabla;
    @FXML
    private TableColumn<Gasto, String> cGasto;
    @FXML
    private TableColumn<Gasto, String> cCat;
    @FXML
    private TableColumn<Gasto, String> cFecha;
    @FXML
    private TableColumn<Gasto, String> cCoste;
    @FXML
    private TableColumn<Gasto, String> cDesc;
    
    private ObservableList<Gasto> datos=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniModelo();
        
        
    }
     private void iniModelo(){
        ArrayList<Gasto> misdatos= new ArrayList<>();
        LocalDate fecha = null;
        misdatos.add(new Gasto("Restaurante",fecha,22,"Venta de Posa","Muy caro"));
        datos = FXCollections.observableArrayList(misdatos);
        tabla.setItems(datos);
            
    
    }
     private class catCell extends TableCell<Gasto,String>{
         
         protected void updateItem(String t, boolean bln){
             super.updateItem(t, bln);
         
         if(t==null || bln){
            setText(null);
        }
         else{
             setText(datos.get(0).getCategoria());
             
         }
     }
    }
}