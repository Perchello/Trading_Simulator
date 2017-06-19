package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Perchello on 18/06/2017.
 */
public class ControllerMainMenu {
    private Stage mStage;
    private Scene mScene;
    private Parent mParrent;



    @FXML
    public void initialize() {
    }





    @FXML
    public void onClickStartTest1(Event event){
        mParrent = null;
        try {
            mParrent = FXMLLoader.load(getClass().getResource("Test1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mScene = new Scene(mParrent);
        mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mStage.setScene(mScene);
        mStage.show();

    }
    @FXML
    public void onClickStartTest2(Event event){
        mParrent = null;
        try {
            mParrent = FXMLLoader.load(getClass().getResource("Test2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mScene = new Scene(mParrent);
        mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mStage.setScene(mScene);
        mStage.show();

    }


}
