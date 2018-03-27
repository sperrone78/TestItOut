/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testitout;

//import java.awt.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Background;
import javafx.scene.shape.ArcType;
import testitout.helpers.GameClock;
import testitout.model.*;

/**
 *
 * @author sperr
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ChoiceBox droneTypeBox;
    @FXML
    private Label droneBuildResultLabel;
    
    private MainCharacter mc;
    private Factory fac;
    private ArrayList<Drone> droneList;
    //private ObservableList<String> droneInfo;
    @FXML
    private Button button;
    @FXML
    private TextField shipNameField;
    @FXML
    private Button shipBuildButton;
    @FXML
    private Button buildDroneButtone;
    @FXML
    private ImageView gridImageBay1;
    @FXML
    private TextField gridNameTextBay1;
    @FXML
    private TextArea gridTextAreaBay1;
    @FXML
    private ImageView gridImageBay2;
    @FXML
    private TextField gridNameTextBay2;
    @FXML
    private TextArea gridTextAreaBay2;
    @FXML
    private ImageView gridImageBay3;
    @FXML
    private TextField gridNameTextBay3;
    @FXML
    private TextArea gridTextAreaBay3;
    @FXML
    private ImageView gridImageBay4;
    @FXML
    private TextField gridNameTextBay4;
    @FXML
    private TextArea gridTextAreaBay4;
    @FXML
    private ImageView gridImageBay5;
    @FXML
    private TextField gridNameTextBay5;
    @FXML
    private TextArea gridTextAreaBay5;
    @FXML
    private ImageView gridImageBay6;
    @FXML
    private TextField gridNameTextBay6;
    @FXML
    private TextArea gridTextAreaBay6;
    @FXML
    private TextField totalBaysText;
    @FXML
    private TextField baysInUseText;
    @FXML
    private TextField factoryAvailSteelText;
    @FXML
    private TextField factoryAvailAlumText;
    @FXML
    private TextField factoryAvailFuelText;
    @FXML
    private TextField availBaysText;
    @FXML
    private TextField droneNameText;
    @FXML
    private TextField droneStockSteelText;
    @FXML
    private TextField droneStockAluminumText;
    @FXML
    private TextField droneStockFuelText;
    @FXML
    private TextField droneReqFuelText;
    @FXML
    private TextField droneReqAluminumText;
    @FXML
    private TextField droneReqSteelText;
    private Tab charNameTab;
    @FXML
    private Canvas mapCanvas;
    @FXML
    private Button stationButton;
    @FXML
    private Button mapButton;
    @FXML
    private Label timeLabel;
    @FXML
    private Canvas drawCanvas;
    @FXML
    private TabPane mainTab;
    @FXML
    private ButtonBar menuButtonBar;
    @FXML
    private Button menuButton;
    private GraphicsContext gcBack, gc;
    private long initialGameTime, currentTime;
    private GameClock gameClock;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mc = new MainCharacter();
        createGameClock();
        createMenu();
        createStation();
        createMap();
        //charNameTab.setText(mc.getName());
    }    
    
    @FXML
    private void handleDroneBuildAction (ActionEvent event) {
        System.out.println("Build a Drone!");
        
        Boolean checkDrone = fac.produceDrone(droneNameText.getText(),droneTypeBox.getSelectionModel().getSelectedItem().toString());
        if (checkDrone) {
            //String tempInfo = droneNameText.getText() + " : " + droneTypeBox.getSelectionModel().getSelectedItem().toString();
            //droneInfo.add(tempInfo);
            //droneInfoList.setItems(droneInfo);
            factoryAvailSteelText.setText(Integer.toString(fac.getInStockSteel()));
            factoryAvailAlumText.setText(Integer.toString(fac.getInStockAlum()));
            factoryAvailFuelText.setText(Integer.toString(fac.getInStockFuel()));
            availBaysText.setText(Integer.toString(fac.getAvailBays()));
            baysInUseText.setText(Integer.toString( (fac.getTotalBays()) - fac.getAvailBays() ));
            totalBaysText.setText(Integer.toString(fac.getTotalBays()));

            //Setup "Build a Drone" Information
            droneStockSteelText.setText(Integer.toString(fac.getInStockSteel()));
            droneStockAluminumText.setText(Integer.toString(fac.getInStockAlum()));
            droneStockFuelText.setText(Integer.toString(fac.getInStockFuel()));
            droneBuildResultLabel.setText("Successfully built " + droneNameText.getText() + " <" + droneTypeBox.getSelectionModel().getSelectedItem().toString() + ">. Moving to Hanger now.");
            
            //Setup Hanger Information
            int count = 1;
            File partyDroneFile = new File ("src/testitout/images/partyDrone.png");
            Image partyDroneImage = new Image(partyDroneFile.toURI().toString()); 

            for (Drone x : droneList) {
                if (count == 1) {
                    gridNameTextBay1.setText(x.getName());
                    gridImageBay1.setImage(partyDroneImage);
                    
                count++;
                }
            }
        } else {
                droneBuildResultLabel.setText("Insuffient Resources or Number of Bays");
        }
    }
    
    private void handleShipBuildAction (ActionEvent event) {
        System.out.println("Build a Ship");
    }

    @FXML
    private void handleStationButtonAction(ActionEvent event) {
        mainTab.toFront();
        menuButtonBar.toFront();
    }

    @FXML
    private void handleMapButtonAction(ActionEvent event) {
        mapCanvas.toFront();
        drawCanvas.toFront();
        menuButtonBar.toFront();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) {
    }

    private void createMenu() {
        setTime();
    }

    private void createStation() {
        //Setup Factory Home Information
        fac = mc.getFactory();
        mainTab.setStyle("-fx-background-color: grey;");
        factoryAvailSteelText.setText(Integer.toString(fac.getInStockSteel()));
        factoryAvailAlumText.setText(Integer.toString(fac.getInStockAlum()));
        factoryAvailFuelText.setText(Integer.toString(fac.getInStockFuel()));
        availBaysText.setText(Integer.toString(fac.getAvailBays()));
        baysInUseText.setText(Integer.toString( (fac.getTotalBays()) - fac.getAvailBays() ));
        totalBaysText.setText(Integer.toString(fac.getTotalBays()));
        
        //Setup "Build a Drone" Information
        droneStockSteelText.setText(Integer.toString(fac.getInStockSteel()));
        droneStockAluminumText.setText(Integer.toString(fac.getInStockAlum()));
        droneStockFuelText.setText(Integer.toString(fac.getInStockFuel()));
        droneReqSteelText.setText("25");
        droneReqAluminumText.setText("25");
        droneReqFuelText.setText("25");
        droneTypeBox.setItems(FXCollections.observableArrayList(
            "Scout", "Mining", "Party")
        );
        droneTypeBox.getSelectionModel().selectFirst();
        
        //Setup the Hanger
        //droneInfo = FXCollections.observableArrayList();
        droneList = mc.getDroneList();
        int count = 1;
        for (Drone x : droneList) {
            if (count == 1) {
                gridNameTextBay1.setText(x.getName());
            }
            count++;
        }
        //droneInfoList.setItems(FXCollections.observableArrayList(droneInfo));
    }

    private void createMap() {
        gcBack = mapCanvas.getGraphicsContext2D();
        //gcBack.setFill(Color.BLUE);
        File canvasBackground = new File ("src/testitout/images/canvasBkg.jpg");
        Image canvasBackgroundImage = new Image(canvasBackground.toURI().toString()); 
        
        gc = drawCanvas.getGraphicsContext2D();
        drawShapes(gc);
        gcBack.drawImage(canvasBackgroundImage, 0, 0);
    }

    private void setTime(){
        long nanoTime = System.nanoTime();
        long timeDiff = nanoTime - initialGameTime;
        double seconds = (double)timeDiff/1000000000.0;
        timeLabel.setText(String.valueOf(seconds));
        /*
        //Clock clock = Clock.system(ZoneId.systemDefault());
        DateTimeFormatter formatter =
        DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                     .withLocale( Locale.US )
                     .withZone( ZoneId.systemDefault() );  
        Instant instant = Instant.now();
        String currentStringTime = formatter.format( instant );
        timeLabel.setText(currentStringTime);
        */
    }

    private void createGameClock() {
        gameClock = new GameClock();
        gameClock.Pause();
    }
}
