package application;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Main extends Application {
    private Scene scene;
    private Stage stage;
    private Pane pane;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            pane = (Pane) FXMLLoader.load(Main.class.getResource("mainmenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene (pane);
        stage.setScene(scene);
        stage.show();


    }

    /*@FXML
    public void onClickStartTask1(Event event){
        Parent home_page_parent = null;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("task1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }*/

}