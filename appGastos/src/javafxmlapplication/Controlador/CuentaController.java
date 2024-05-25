/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.StackWalker.Option;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Charge;
import model.User;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CuentaController implements Initializable {

    @FXML
    private TableView<Charge> tabla;
    @FXML
    private TableColumn<Charge, String> cGasto;
    @FXML
    private TableColumn<Charge, String> cCat;
    @FXML
    private TableColumn<Charge, String> cFecha;
    @FXML
    private TableColumn<Charge, String> cCoste;
    
    private ObservableList<Charge> datos=null;
    @FXML
    private Button borrarButton;
    @FXML
    private ImageView imagenCuenta;
    @FXML
    private Button modificarButton;
    @FXML
    private Button catButton;
    @FXML
    private MenuButton buttonMenu;
    @FXML
    private Button buttonDetalles;
    private Charge gasto;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private DatePicker fechaHasta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        borrarButton.setDisable(true);
        borrarButton.disableProperty().bind(Bindings.equal(-1, tabla.getSelectionModel().selectedIndexProperty()));
        modificarButton.setDisable(true);
        modificarButton.disableProperty().bind(Bindings.equal(-1, tabla.getSelectionModel().selectedIndexProperty()));
        buttonDetalles.setDisable(true);
        buttonDetalles.disableProperty().bind(Bindings.equal(-1, tabla.getSelectionModel().selectedIndexProperty()));
        
        
        
        try{
            
            iniModelo();
            imagenCuenta.setImage(Acount.getInstance().getLoggedUser().getImage());
            cGasto.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getName()) );
        
            cCat.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getCategory().getName()) );
            cFecha.setCellValueFactory(
                (cargo)-> new SimpleStringProperty(cargo.getValue().getDate().toString()) ); 
            cCoste.setCellValueFactory(
                (cargo)-> new SimpleStringProperty((Double.toString(cargo.getValue().getCost()))) );
            fechaHasta.setValue(LocalDate.now());
            fechaDesde.setValue(fechaMin(Acount.getInstance().getUserCharges()));
            fechaHasta.valueProperty().addListener((ob,oldV,newV)->{
                   try{
                    actDatos(fechaDesde.getValue(),newV);
                    }
                   catch(Exception e){
                       System.out.println("Excepcion en fecha hasta");
                   }
            
            });
            fechaDesde.valueProperty().addListener((ob,oldV,newV)->{
            
                try{
                    actDatos(newV,fechaHasta.getValue());
                    }
                   catch(Exception e){
                       System.out.println("Excepcion en fecha desde");
                   }
                
            });
            
        }
        catch(Exception e){
                    System.out.println("Error en initialize"+ e.getMessage());
                }
        
    }
    private void actDatos(LocalDate fechaDesde, LocalDate fechaHasta) throws AcountDAOException, IOException{
        List <Charge> lista = Acount.getInstance().getUserCharges();
        
        for(int i=0;i<lista.size();i++){
            if((menorqueFecha(lista.get(i).getDate(),fechaHasta) && mayorqueFecha(lista.get(i).getDate(),fechaDesde))!=true){
                lista.remove(i);
            }
            
        }
        ArrayList<Charge> misdatos= new ArrayList<>(lista);
        datos = FXCollections.observableArrayList(misdatos);
        tabla.setItems(datos);
        datos = tabla.getItems();
        tabla.refresh();
        

        
    
    }
    private boolean mayorqueFecha(LocalDate x, LocalDate y){
        boolean res = false;
        if(x.getYear()>y.getYear()){
            res= true;
        }
        else if(x.getYear()==y.getYear()){
            if(x.getMonthValue()>y.getMonthValue()){
                res= true;
            }
            else if(x.getMonthValue()==y.getMonthValue()){
                if(x.getDayOfMonth()>=y.getDayOfMonth()){
                    res= true;
                }
                
            }
        }
        return res;
        
    }
    private boolean menorqueFecha(LocalDate x, LocalDate y){
        boolean res = false;
        if(x.getYear()<y.getYear()){
            res= true;
        }
        else if(x.getYear()==y.getYear()){
            if(x.getMonthValue()<y.getMonthValue()){
                res= true;
            }
            else if(x.getMonthValue()==y.getMonthValue()){
                if(x.getDayOfMonth()<=y.getDayOfMonth()){
                    res= true;
                }
                
            }
        }
        return res;
    
    }
    private LocalDate fechaMin(List<Charge> lista){
        LocalDate min= LocalDate.MAX;
        for(int i =0;i<lista.size();i++){
            if(lista.get(i).getDate().getYear()<min.getYear()){
                min= lista.get(i).getDate();
            }
            else if(lista.get(i).getDate().getYear()==min.getYear()){
                if(lista.get(i).getDate().getMonthValue()<min.getMonthValue()){
                    min= lista.get(i).getDate();
                }
                else if(lista.get(i).getDate().getMonthValue()==min.getMonthValue()){
                    if(lista.get(i).getDate().getDayOfMonth()<min.getDayOfMonth()){
                        min=lista.get(i).getDate();
                    }
                }
            }
            
        }
        
        return min;
    }
    private void iniModelo() throws AcountDAOException, IOException{
        ArrayList<Charge> misdatos= new ArrayList<>(Acount.getInstance().getUserCharges());
        datos = FXCollections.observableArrayList(misdatos);
        tabla.setItems(datos);
        datos = tabla.getItems();
        
        
    }

    @FXML
    private void añadir(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/addGasto.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        AddGastoController controlador = loader.getController();

        stage.showAndWait();
        if(controlador.getDatosV()){
            Acount.getInstance().registerCharge(controlador.getNombre(),controlador.getDesc(),Double.parseDouble(controlador.getCoste()),1,controlador.getImage(),controlador.getDate(),controlador.getCategory());
            datos.add(Acount.getInstance().getUserCharges().get(datos.size()));
        }
            
            

       
        
    }

    @FXML
    private void borrar(ActionEvent event) throws AcountDAOException, IOException {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Borrar gasto");
        alerta.setContentText("Está seguro que desea borrar el gasto seleccionado ?");
        Optional <ButtonType> opciones = alerta.showAndWait();
        if(opciones.isPresent() && opciones.get()==ButtonType.OK){
            boolean bol=Acount.getInstance().removeCharge(tabla.getSelectionModel().getSelectedItem());
            datos.remove(tabla.getSelectionModel().getSelectedIndex());

            alerta.close();
            
        }
        else{
            alerta.close();
        }
        
                
                
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Cerrar Sesión");
        alerta.setContentText("Está seguro que desea salir ?");
        Optional <ButtonType> opciones = alerta.showAndWait();
        if(opciones.isPresent() && opciones.get()==ButtonType.OK){
            alerta.close();
            Stage stage1 = (Stage) borrarButton.getScene().getWindow();
            stage1.close();
            FXMLLoader loader= new  FXMLLoader(getClass().getResource("../Vista/Vista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("App Gastos");
            stage.show();
        }
        else{
            alerta.close();
        }
        
        
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/modificarGasto.fxml"));
        Parent root = loader.load();
        Charge cargo= tabla.getSelectionModel().getSelectedItem();
        int indice = tabla.getSelectionModel().getSelectedIndex();
        modificarGastoController mod = loader.getController();
        mod.setCosas(tabla.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        modificarGastoController post = loader.getController();
        if(post.getDatosV()){
            cargo.setName(post.getNombre());
            cargo.setCost(post.getCoste());
            cargo.setDate(post.getDate());
            cargo.setCategory(post.getCategory());
            cargo.setDescription(post.getDesc());
            cargo.setImageScan(post.getImage());
            cargo.setUnits(1);
            tabla.refresh();
        }
        
            
        
        
    }
    public Charge gastoSeleccionado(){
        return gasto;
    }

    @FXML
    private void verCategorias(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/VistaLista.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
    }

    @FXML
    private void detalles(ActionEvent event) throws IOException {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/detalles.fxml"));
        Parent root = loader.load();
        DetallesController det = loader.getController();
        Scene scene = new Scene(root);
        det.setCosas(tabla.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
        

  /*  private void pdf(ActionEvent event) throws AcountDAOException, IOException {
        User user = Acount.getInstance().getLoggedUser();
        PrintWriter write = new PrintWriter(user.getNickName() + " - Cuenta MyMoney.pdf");
        Charge[] cuenta = Acount.getInstance().getUserCharges().toArray(new Charge[0]);
        write.println(user.getName() + " " + user.getSurname() + " (" + user.getNickName() + ")");
        write.println("\n");
        for(int i = 0; i < cuenta.length; i++){
            write.println(cuenta[i].getDate() + " - " + cuenta[i].getName() + ", " + 
                    cuenta[i].getCost() + ", " + cuenta[i].getCategory() + ", " + 
                    cuenta[i].getDescription() + ", " + cuenta[i].getId());
        }
        write.close();
    }*/

    @FXML
    private void pdf(ActionEvent event) throws AcountDAOException, IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxmlapplication/Vista/imprimir.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("App Gastos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        
    }


    
    
     class ImagenTabCell extends TableCell<Charge, String> {
            private ImageView view = new ImageView();
            private Image imagen;
            @Override
            protected void updateItem(String t, boolean bln) {
            super.updateItem(t, bln);
            if (t == null || bln) {
                setText(null);
                setGraphic(null);
            } else {
                imagen = new Image(t, 25, 25, true, true);
                view.setImage(imagen);
                setGraphic(view);
            }
        }
    }
     
}