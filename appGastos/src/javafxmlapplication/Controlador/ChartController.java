/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import static java.time.Month.JANUARY;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author julil
 */
public class ChartController implements Initializable {

    @FXML
    private StackedBarChart<Double, String> grafo;

    ArrayList<String> categorias;
    
    ObservableList<String> datos;
    
    Category[] auxCat;
    
    int current;
    @FXML
    private Text ano;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current = 2024;
        ano.setText(Integer.toString(current));
        try {
            auxCat = Acount.getInstance().getUserCategories().toArray(new Category[0]);
            for(int i = 0; i < auxCat.length; i++){
                categorias.add(auxCat[i].getName());
            }
            datos = FXCollections.observableArrayList(categorias);
            makeChart(Integer.parseInt(ano.getText()));
        } catch (AcountDAOException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void makeChart(int year) throws AcountDAOException, IOException{
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setCategories(datos);
        xAxis.setLabel("Meses");
        yAxis.setLabel("Gastos");
        grafo.setTitle("Gastos por categoría "+ year);
        
        XYChart.Series<Double,String> series1 = new XYChart.Series<>(); series1.setName("Ene");
        XYChart.Series<Double,String> series2 = new XYChart.Series<>(); series1.setName("Feb");
        XYChart.Series<Double,String> series3 = new XYChart.Series<>(); series1.setName("Mar");
        XYChart.Series<Double,String> series4 = new XYChart.Series<>(); series1.setName("Abr");
        XYChart.Series<Double,String> series5 = new XYChart.Series<>(); series1.setName("May");
        XYChart.Series<Double,String> series6 = new XYChart.Series<>(); series1.setName("Jun");
        XYChart.Series<Double,String> series7 = new XYChart.Series<>(); series1.setName("Jul");
        XYChart.Series<Double,String> series8 = new XYChart.Series<>(); series1.setName("Ago");
        XYChart.Series<Double,String> series9 = new XYChart.Series<>(); series1.setName("Set");
        XYChart.Series<Double,String> series10 = new XYChart.Series<>(); series1.setName("Oct");
        XYChart.Series<Double,String> series11 = new XYChart.Series<>(); series1.setName("Nov");
        XYChart.Series<Double,String> series12 = new XYChart.Series<>(); series1.setName("Dic");
        
        Charge[] auxChar = Acount.getInstance().getUserCharges().toArray(new Charge[0]);
        
        for(int i = 0; i <auxChar.length; i++){
            if(auxChar[i].getDate().getYear() == year){
                switch(auxChar[i].getDate().getMonth()){
                    case JANUARY:
                        series1.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case FEBRUARY:
                        series2.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case MARCH:
                        series3.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case APRIL:
                        series4.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case MAY:
                        series5.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case JUNE:
                        series6.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case JULY:
                        series7.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case AUGUST:
                        series8.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case SEPTEMBER:
                        series9.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case OCTOBER:
                        series10.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case NOVEMBER:
                        series11.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                    case DECEMBER:
                        series12.getData().add(new XYChart.Data<>(auxChar[i].getCost(),auxChar[i].getCategory().getName()));
                    break;
                }
            }
        }
        grafo.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8,series9,series10,series11,series12);
    }

    @FXML
    private void antes(ActionEvent event) throws AcountDAOException, IOException {
        current--;
        makeChart(current);
        ano.setText(Integer.toString(current));
    }

    @FXML
    private void despues(ActionEvent event) throws AcountDAOException, IOException{
        current++;
        makeChart(current);
        ano.setText(Integer.toString(current));
    }
}
