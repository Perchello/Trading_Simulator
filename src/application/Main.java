package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
    private Scene mScene;
    private Stage mStage;
    private Pane mPane;

    @Override
    public void start(Stage stage) {
        this.mStage = stage;
        try {
            mPane = (Pane) FXMLLoader.load(Main.class.getResource("MainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mScene = new Scene (mPane);
        stage.setScene(mScene);
        stage.show();


    }

    /*@FXML
    public void onClickStartTest1(Event event){
        Parent home_page_parent = null;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("Test1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }*/

}