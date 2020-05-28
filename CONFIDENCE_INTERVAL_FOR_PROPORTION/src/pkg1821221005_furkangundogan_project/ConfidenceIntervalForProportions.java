/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1821221005_furkangundogan_project;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


 /*

 Furkan Gündoğan 1821221005 
 Confidence Interval For Proportion
*/

public class ConfidenceIntervalForProportions extends Application {
    public int confidence=95;
    public int n=100;
    public int error=53;
    public double z;
    public double center;
    public double margin;
    
    @Override public void start(Stage stage) {

        
        stage.setTitle("Project");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Parameters");

        final AreaChart<Number,Number> areaChart = new AreaChart<Number,Number>(xAxis,yAxis);
             
        areaChart.setTitle("Confidence Interval For Proportion");
        XYChart.Series series = new XYChart.Series();
        series.setName("1821221005 Furkan Gundogan");       
        areaChart.getData().add(series);
               
               
        // Çizme butonu

        Button button1 = new Button("Draw");
        button1.setLayoutX(600);
        button1.setLayoutY(250);
        
        
        // Sample
        Label lblsample=new Label("Sample: ");
        lblsample.setLayoutX(520);
        lblsample.setLayoutY(120);
        TextField txtSample=new TextField("100");
        txtSample.setLayoutX(580);
        txtSample.setLayoutY(105);
        
        // Error
        Label lblerror=new Label("Error: ");
        lblerror.setLayoutX(520);
        lblerror.setLayoutY(150);
        TextField txtError=new TextField("55");
        txtError.setLayoutX(580);
        txtError.setLayoutY(145);
        
      //  Confidence için
        ObservableList<Integer> values =  FXCollections.observableArrayList(99,98,95,90,80);
        ComboBox cbConfidence=new ComboBox(values);
        cbConfidence.setValue(95);      
        cbConfidence.setLayoutX(590);
        cbConfidence.setLayoutY(200);
        Label lblconfidence=new Label("Confidence: ");
        lblconfidence.setLayoutX(500);
        lblconfidence.setLayoutY(205);
        
        // center
        Label lblp=new Label("Center: "+String.format("%10.3f",center));
        lblp.setLayoutX(520);
        lblp.setLayoutY(300);
        
        // margin
        Label lblmargin=new Label("Margin: "+String.format("%10.3f",margin));
        lblmargin.setLayoutX(635);
        lblmargin.setLayoutY(300);
        
        //
         Label lblAralik=new Label("[,]");
        lblAralik.setLayoutX(550);
        lblAralik.setLayoutY(330);
        
        
        
        EventHandler<ActionEvent> butonClick = new EventHandler<ActionEvent>() { 
            
            public void handle(ActionEvent e) 
            { 
              n=Integer.valueOf(txtSample.getText());
              error=Integer.valueOf(txtError.getText());
              confidence=Integer.valueOf(cbConfidence.getSelectionModel().getSelectedItem().toString());
              z=getZval(confidence);
              center=(double)error/(double)n;
              margin=(center*(1-center))/n;
              margin=Math.sqrt(margin);
              margin=margin*z;
              
              lblp.setText("Center: "+center);
              lblmargin.setText("Margin: "+String.format("%10.3f",margin));
              lblAralik.setText("["+String.format("%10.3f",center-margin)+"     ,"+String.format("%10.3f",center+margin)+"     ]");
               // Update graph
               double mod = confidence%10;
               
              
               series.getData().clear();
               series.getData().add(new XYChart.Data(center,confidence));      
               series.getData().add(new XYChart.Data(center-margin,mod));
               series.getData().add(new XYChart.Data(center+margin,mod));
     
               
               double degisken2=confidence/3;
               series.getData().add(new XYChart.Data((center-margin*0.7),degisken2));
               series.getData().add(new XYChart.Data((center+margin*0.7),degisken2));    
                double degisken3=confidence/2;
               series.getData().add(new XYChart.Data((center-margin*0.5),degisken3));
               series.getData().add(new XYChart.Data((center+margin*0.5),degisken3)); 
               double degisken4=confidence/1.5;
               series.getData().add(new XYChart.Data((center-margin*0.25),degisken4));
               series.getData().add(new XYChart.Data((center+margin*0.25),degisken4)); 
               
               double degisken5=confidence/1.3;
               series.getData().add(new XYChart.Data((center-margin*0.15),degisken5));
               series.getData().add(new XYChart.Data((center+margin*0.15),degisken5));
               
               double degisken6=confidence-2;
               series.getData().add(new XYChart.Data((center-margin*0.1),degisken6));
               series.getData().add(new XYChart.Data((center+margin*0.1),degisken6));
          
                
            } 
        }; 
        button1.setOnAction(butonClick);
        // Pane için
        Pane root = new Pane();
        root.getChildren().addAll(button1,areaChart,txtSample,txtError,cbConfidence,lblconfidence,lblsample,lblerror,lblp,lblmargin,lblAralik);
        Scene scene  = new Scene(root,800,400);
        stage.setScene(scene);
              stage.show();
              
             
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    // alfa/2 değerini geitmek için metot
    public static double getZval(int x){
    switch(x){
        case 99:
            return 2.576;
        case 98:
            return 2.329;
        case 95:
            return 1.96;
        case 90:
            return 1.645;
        case 80:
            return 1.282;
        default:
            return 0;
    }
    }
}
