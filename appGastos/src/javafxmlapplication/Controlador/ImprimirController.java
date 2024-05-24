/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Acount;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class ImprimirController implements Initializable {

    @FXML
    private TextArea textArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Charge cargo;
        try{
                List<Charge> cargos =Acount.getInstance().getUserCharges();
                String mensaje="";

                for(int i=0;i<cargos.size();i++){
                    cargo =cargos.get(i);
                    mensaje= mensaje + "Id: " + cargo.getId() + " Nombre: " + cargo.getName() + " Categoria: " + cargo.getCategory().getName() + " Coste: " + cargo.getCost() + " Unidades: " + cargo.getUnits() + " Fecha: " + cargo.getDate().toString() + "\n";
                }
                textArea.setText(mensaje);

        }
        catch(Exception e){
            System.out.println("Error en imprimir controller");
        }
    }    
    
}
