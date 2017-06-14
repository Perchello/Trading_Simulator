package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
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
    private Button pButtonNextStage;
    @FXML
    private TextField pTextFieldBuy;
    @FXML
    private TextField pTextFieldSell;
    @FXML
    private ChoiceBox pChoiceBox;
    @FXML
    private TextField pTextFieldMoney;
    @FXML
    private TextField pTextFieldPrice;
    @FXML
    private LineChart <String, Number> pLineChart;
    @FXML
    private XYChart.Series<String, Number> pChartBrent;
    @FXML
    private XYChart.Series<String, Number> pChartFuelOil;
    @FXML
    private TableView<Product> pTableViewProduct;
    @FXML
    private TableColumn<Product, String> pColumnProductName;
    @FXML
    private TableColumn<Product, String> pColumnProductQuantity;
    @FXML
    private TableColumn<Product, String> pColumnProductAvgPrice;


    ObservableList <String> pChoiceBoxOptions;
    ObservableList <Product> pObsListProducts;

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

    }

    private void startTest1() {
        pChartBrent = new XYChart.Series<>();
        pChartFuelOil = new XYChart.Series<>();
        pTest1 = new Test1();
        pTest1.startTest();
        //Test
        pObsListProducts = FXCollections.observableArrayList(pTest1.getProductBrent(), pTest1.getProductFuelOil());
        pChoiceBoxOptions = FXCollections.observableArrayList(pTest1.getProductBrent().getName(), pTest1.getProductFuelOil().getName());
        pChoiceBox.setItems(pChoiceBoxOptions);
        pChoiceBox.setValue(pTest1.getProductBrent().getName());
        pTextFieldPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldMoney.setText(""+ pTest1.getMoney());
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrent.setName(pTest1.getProductBrent().getName());
        pChartFuelOil.setName(pTest1.getProductFuelOil().getName());
        pLineChart.getData().add(pChartBrent);
        pTableViewProduct.itemsProperty().setValue(pObsListProducts);
        pColumnProductName.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getName()));
        pColumnProductQuantity.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getQuantity()));
        pColumnProductAvgPrice.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAvgPrice()));


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
                    pLineChart.getData().remove(pChartFuelOil);
                    pLineChart.getData().add(pChartBrent);



                }
                else if (pChoiceBox.getItems().get((Integer) number2)=="Fuel Oil"){
                    pTextFieldPrice.setText(""+pTest1.getFuelOilPrice());
                    pLineChart.getData().remove(pChartBrent);
                    pLineChart.getData().add(pChartFuelOil);

                }
            }
        });


    }

    @FXML
    public void buttonBuyAction (){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.buyBrent(Integer.parseInt(pTextFieldBuy.getText()));
        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.buyFuelOil(Integer.parseInt(pTextFieldBuy.getText()));
        }
        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();
    }

    @FXML
    public void buttonSellAction (){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.sellBrent(Integer.parseInt(pTextFieldSell.getText()));
        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.sellFuelOil(Integer.parseInt(pTextFieldSell.getText()));

        }
        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();

    }

    @FXML
    public void buttonNextStageAction(){
        if (pChoiceBox.getValue()=="Brent"){
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getBrentPrice());

        }
        else if (pChoiceBox.getValue()=="Fuel Oil"){
            pTest1.changePrice();
            pTextFieldPrice.setText(""+ pTest1.getFuelOilPrice());

        }
        pTest1.nextTurn();
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pTableViewProduct.refresh();


    }


    public static void main(String[] args) {
        launch(args);

    }

}