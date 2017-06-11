package application;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Main extends Application {
    private Scene scene;
    private Stage stage;
    private Pane pane;
    private static int currentMoney;
    private application.Test1 pTest1;

    @FXML
    private Button pButtonSell;
    @FXML
    private Button pButtonBuy;
    @FXML
    private Button pButtonSkip;
    @FXML
    private TextField pTextFieldBuy;
    @FXML
    private TextField pTextFieldSell;
    @FXML
    private ChoiceBox pChoiceBox;
    @FXML
    private TextField pTextFieldBrentQuantity;
    @FXML
    private TextField pTextFieldFuelOilQuantity;
    @FXML
    private TextField pTextFieldMoney;
    @FXML
    private TextField pTextFieldPrice;
    @FXML
    private LineChart pLineChart;

    ObservableList <String> pChoiceBoxOptions = FXCollections.observableArrayList("Brent", "Fuel Oil");


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            pane = (Pane) FXMLLoader.load(Main.class.getResource("task1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene (pane);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void initialize(){
        startTest1();
        System.out.println(pButtonBuy.getText());
    }

    private void startTest1() {
        pTest1 = new Test1();
        pTest1.startTest();
        pChoiceBox.setItems(pChoiceBoxOptions);
        pChoiceBox.setValue("Brent");
        pTextFieldPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldBrentQuantity.setText("" +pTest1.getBrentQuantity());
        pTextFieldFuelOilQuantity.setText("" +pTest1.getFuelOilQuantity());
        pTextFieldMoney.setText(""+ pTest1.getMoney());
        pTextFieldBuy.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    pTextFieldBuy.setText(oldValue);
                }
            }

        });

        pTextFieldSell.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    pTextFieldSell.setText(oldValue);
                }
            }

        });

        pChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (pChoiceBox.getItems().get((Integer) number2)=="Brent"){
                    pTextFieldPrice.setText(""+pTest1.getBrentPrice());

                }
                else if (pChoiceBox.getItems().get((Integer) number2)=="Fuel Oil"){
                    pTextFieldPrice.setText(""+pTest1.getFuelOilPrice());

                }
            }
        });


    }

    @FXML
    public void buttonBuyAction (){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.buyBrent(Integer.parseInt(pTextFieldBuy.getText()));
            pTextFieldBrentQuantity.setText(""+pTest1.getBrentQuantity());
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getBrentPrice());


        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.buyFuelOil(Integer.parseInt(pTextFieldBuy.getText()));
            pTextFieldFuelOilQuantity.setText(""+pTest1.getFuelOilQuantity());
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getFuelOilPrice());


        }
        pTextFieldMoney.setText(""+pTest1.getMoney());


        System.out.println("Price is " +pTest1.getBrentPrice());
        System.out.println("I bought " + pChoiceBox.getValue());
        System.out.println(pTextFieldBuy.getText());
    }

    @FXML
    public void buttonSellAction (){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.sellBrent(Integer.parseInt(pTextFieldSell.getText()));
            pTextFieldBrentQuantity.setText(""+pTest1.getBrentQuantity());
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getBrentPrice());

        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.sellFuelOil(Integer.parseInt(pTextFieldSell.getText()));
            pTextFieldFuelOilQuantity.setText(""+pTest1.getFuelOilQuantity());
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getFuelOilPrice());

        }
        pTextFieldMoney.setText(""+pTest1.getMoney());

        System.out.println("Price is " +pTest1.getBrentPrice());
        System.out.println("I bought " + pChoiceBox.getValue());
        System.out.println(pTextFieldBuy.getText());
    }

    @FXML
    public void buttonSkipAction (){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getBrentPrice());

        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getFuelOilPrice());

        }


    }


    public static void main(String[] args) {
        launch(args);

    }

}