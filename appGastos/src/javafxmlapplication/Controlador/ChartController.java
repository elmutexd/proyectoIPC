/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import static java.time.Month.JANUARY;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
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

    ArrayList<String> categorias;
    
    Category[] auxCat;
    
    int current;
    
    @FXML
    private Text ano;
    @FXML
    private NumberAxis gastos;
    @FXML
    private BarChart<String, Double> graph;
    @FXML
    private CategoryAxis cats;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        current = Year.now().getValue();
        categorias = new ArrayList<String>();
        ano.setText(Integer.toString(current));
        try {
            auxCat = Acount.getInstance().getUserCategories().toArray(new Category[0]);
            for(int i = 0; i < auxCat.length; i++){
                categorias.add(auxCat[i].getName());
            };
            makeChart(current);
        } catch (AcountDAOException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void makeChart(int year) throws AcountDAOException, IOException{
        graph.getData().clear();
        graph.setTitle("Gastos por categoría "+ year);
        cats.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago",  "Set", "Oct", "Nov", "Dic")));
        List<Charge> carg = Acount.getInstance().getUserCharges();
            for(int i = 0; i < auxCat.length; i++){
                XYChart.Series<String,Double> seriesaux = new XYChart.Series<>(); seriesaux.setName(auxCat[i].getName());
                Double[] contMes = new Double[12];
                Arrays.fill(contMes, 0.0);
                for(int j = 0; j < carg.size(); j++){
                    if(carg.get(j).getCategory().equals(auxCat[i]) && carg.get(j).getDate().getYear() == year){
                       switch(carg.get(j).getDate().getMonth()){
                            case JANUARY:
                                contMes[0] += carg.get(j).getCost();
                            break;
                            case FEBRUARY:
                                contMes[1] += carg.get(j).getCost();
                            break;
                            case MARCH:
                                contMes[2] += carg.get(j).getCost();
                            break;
                            case APRIL:
                                contMes[3] += carg.get(j).getCost();
                            break;
                            case MAY:
                                contMes[4] += carg.get(j).getCost();
                            break;
                            case JUNE:
                                contMes[5] += carg.get(j).getCost();
                            break;
                            case JULY:
                                contMes[6] += carg.get(j).getCost();
                            break;
                            case AUGUST:
                                contMes[7] += carg.get(j).getCost();
                            break;
                            case SEPTEMBER:
                                contMes[8] += carg.get(j).getCost();
                            break;
                            case OCTOBER:
                                contMes[9] += carg.get(j).getCost();
                            break;
                            case NOVEMBER:
                                contMes[10] += carg.get(j).getCost();
                            break;
                            case DECEMBER:
                                contMes[11] += carg.get(j).getCost();
                            break;
                        } 
                    }
                }
                seriesaux.getData().add(new XYChart.Data<>("Ene", contMes[0]));
                seriesaux.getData().add(new XYChart.Data<>("Feb", contMes[1]));
                seriesaux.getData().add(new XYChart.Data<>("Mar", contMes[2]));
                seriesaux.getData().add(new XYChart.Data<>("Abr", contMes[3]));
                seriesaux.getData().add(new XYChart.Data<>("May", contMes[4]));
                seriesaux.getData().add(new XYChart.Data<>("Jun", contMes[5]));
                seriesaux.getData().add(new XYChart.Data<>("Jul", contMes[6]));
                seriesaux.getData().add(new XYChart.Data<>("Ago", contMes[7]));
                seriesaux.getData().add(new XYChart.Data<>("Set", contMes[8]));
                seriesaux.getData().add(new XYChart.Data<>("Oct", contMes[9]));
                seriesaux.getData().add(new XYChart.Data<>("Nov", contMes[10]));
                seriesaux.getData().add(new XYChart.Data<>("Dic", contMes[11]));
                graph.getData().add(seriesaux);
            }
        /*XYChart.Series<String,Double> series1 = new XYChart.Series<>(); series1.setName("Ene");
        XYChart.Series<String,Double> series2 = new XYChart.Series<>(); series2.setName("Feb");
        XYChart.Series<String,Double> series3 = new XYChart.Series<>(); series3.setName("Mar");
        XYChart.Series<String,Double> series4 = new XYChart.Series<>(); series4.setName("Abr");
        XYChart.Series<String,Double> series5 = new XYChart.Series<>(); series5.setName("May");
        XYChart.Series<String,Double> series6 = new XYChart.Series<>(); series6.setName("Jun");
        XYChart.Series<String,Double> series7 = new XYChart.Series<>(); series7.setName("Jul");
        XYChart.Series<String,Double> series8 = new XYChart.Series<>(); series8.setName("Ago");
        XYChart.Series<String,Double> series9 = new XYChart.Series<>(); series9.setName("Set");
        XYChart.Series<String,Double> series10 = new XYChart.Series<>(); series10.setName("Oct");
        XYChart.Series<String,Double> series11 = new XYChart.Series<>(); series11.setName("Nov");
        XYChart.Series<String,Double> series12 = new XYChart.Series<>(); series12.setName("Dic");
        
        Charge[] auxChar = Acount.getInstance().getUserCharges().toArray(new Charge[0]);
        
        for(int i = 0; i < auxChar.length; i++){
            if(auxChar[i].getDate().getYear() == year){
                Double costAux = auxChar[i].getCost();
                String catAux = auxChar[i].getCategory().getName();
                switch(auxChar[i].getDate().getMonth()){
                    case JANUARY:
                        series1.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case FEBRUARY:
                        series2.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case MARCH:
                        series3.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case APRIL:
                        series4.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case MAY:
                        series5.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case JUNE:
                        series6.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case JULY:
                        series7.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case AUGUST:
                        series8.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case SEPTEMBER:
                        series9.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case OCTOBER:
                        series10.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case NOVEMBER:
                        series11.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                    case DECEMBER:
                        series12.getData().add(new XYChart.Data<>(catAux, costAux));
                    break;
                }
            }
        }
        graph.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8,series9,series10,series11,series12);
    */
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

    @FXML
    private void exit(ActionEvent event) {
        ano.getScene().getWindow().hide();
    }

}
