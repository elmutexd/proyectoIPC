/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaListaControlador_1 implements Initializable {

    @FXML
    private ListView<Category> catListView;
    @FXML
    private Button addButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button borrarButton;

    private ObservableList<Category> datos = null; // Colecci�n vinculada a la vista.

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            // TODO
            // en el código de inicialización del controlador
            // TODO Auto-generated method stub
            iniLista();
        } catch (AcountDAOException ex) {
            Logger.getLogger(VistaListaControlador_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaControlador_1.class.getName()).log(Level.SEVERE, null, ex);
        }

        //=======================================================
        // inhabilitar botones al arrancar.
        borrarButton.setDisable(true); modificarButton.setDisable(true);
        // si no hay selección el boton Borrar esta disabled
        borrarButton.disableProperty().bind(Bindings.equal(catListView.getSelectionModel().selectedIndexProperty(), -1));
        modificarButton.disableProperty().bind(Bindings.equal(catListView.getSelectionModel().selectedIndexProperty(), -1));
    }
    
    private void iniLista() throws AcountDAOException, IOException{
        datos = FXCollections.observableArrayList(Acount.getInstance().getUserCategories());
        catListView.setItems(datos);
        datos = catListView.getItems();
        catListView.setCellFactory(c -> new CatListCell());
    }

    @FXML
    private void addAccion(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("../Vista/VistaCat.fxml"));
        Parent root = miCargador.load();
        VistaCatController controladorPersona = miCargador.getController();
        Scene scene = new Scene(root,500,300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vista datos categoria");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        iniLista();
    }

    @FXML
    private void borrarAccion(ActionEvent event) throws AcountDAOException, IOException{
        //============================================
        // borramos de la lista
        int aBorrar = catListView.getSelectionModel().getSelectedIndex();
        Category catBorrar = Acount.getInstance().getUserCategories().get(aBorrar);
        Acount.getInstance().removeCategory(catBorrar);
        iniLista();
    }

    @FXML
    private void modAccion(ActionEvent event) throws AcountDAOException, IOException{
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("../Vista/VistaCat.fxml"));
        Parent root = miCargador.load();
        VistaCatController controladorPersona = miCargador.getController();
        int aEditar = catListView.getSelectionModel().getSelectedIndex();
        Category catEditar = Acount.getInstance().getUserCategories().get(aEditar);
        controladorPersona.initCat(catEditar);
        Scene scene = new Scene(root,500,300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vista datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        catEditar.setName(controladorPersona.Nombre());
        catEditar.setDescription(controladorPersona.Descripcion());
        iniLista();
    }

    @FXML
    private void salir(ActionEvent event) {
        catListView.getScene().getWindow().hide();
    }
    
    class CatListCell extends ListCell<Category> {

    @Override
    protected void updateItem(Category t, boolean bln) {
        super.updateItem(t, bln); //To change body of generated methods, choose Tools | Templates.
        if (bln) // esta vacia
        {
            setText("");
        } else {
            setText(t.getName() + ", " + t.getDescription());
        }

    }
}
}