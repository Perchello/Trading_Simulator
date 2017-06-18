package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
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
    private application.Test1 pTest1;

    @FXML
    private TextField pTextFieldBrentBuy;
    @FXML
    private TextField pTextFieldBrentSell;
    @FXML
    private TextField pTextFieldFuelOilBuy;
    @FXML
    private TextField pTextFieldFuelOilSell;
    //@FXML
    //private ChoiceBox pChoiceBox;
    @FXML
    private TextField pTextFieldMoney;
    @FXML
    private TextField pTextFieldBrentPrice;
    @FXML
    private TextField pTextFieldFuelOilPrice;
    @FXML
    private LineChart <String, Number> pLineChartBrent;
    @FXML
    private LineChart <String, Number> pLineChartFuelOil;
    @FXML
    private LineChart <String, Number> pLineChartBrentFuel;
    @FXML
    private XYChart.Series<String, Number> pChartBrent;
    @FXML
    private XYChart.Series<String, Number> pChartFuelOil;
    @FXML
    private XYChart.Series<String, Number> pChartBrentDual;
    @FXML
    private XYChart.Series<String, Number> pChartFuelOilDual;
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
        pChartFuelOilDual = new XYChart.Series<>();
        pChartBrentDual = new XYChart.Series<>();

        pTest1 = new Test1();
        pTest1.startTest();

        pObsListProducts = FXCollections.observableArrayList(pTest1.getProductBrent(), pTest1.getProductFuelOil());
        //pChoiceBoxOptions = FXCollections.observableArrayList(pTest1.getProductBrent().getName(), pTest1.getProductFuelOil().getName());
        //pChoiceBox.setItems(pChoiceBoxOptions);
        //pChoiceBox.setValue(pTest1.getProductBrent().getName());
        pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldFuelOilPrice.setText(""+pTest1.getFuelOilPrice());
        pTextFieldMoney.setText(""+ pTest1.getMoney());
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOilDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrent.setName(pTest1.getProductBrent().getName());
        pChartFuelOil.setName(pTest1.getProductFuelOil().getName());
        pChartBrentDual.setName(pTest1.getProductBrent().getName());
        pChartFuelOilDual.setName(pTest1.getProductFuelOil().getName());

        pLineChartBrent.getData().add(pChartBrent);
        pLineChartFuelOil.getData().add(pChartFuelOil);
        pLineChartBrentFuel.getData().add(pChartBrentDual);
        pLineChartBrentFuel.getData().add(pChartFuelOilDual);
        pTableViewProduct.itemsProperty().setValue(pObsListProducts);
        pColumnProductName.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getName()));
        pColumnProductQuantity.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getQuantity()));
        pColumnProductAvgPrice.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAvgPrice()));


        pTextFieldBrentBuy.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    pTextFieldBrentBuy.setText(oldValue);
                }
            }

        });

        pTextFieldBrentSell.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    pTextFieldBrentSell.setText(oldValue);
                }
            }

        });

        /*pChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (pChoiceBox.getItems().get((Integer) number2)=="Brent"){
                    pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
                    pLineChartBrent.getData().remove(pChartFuelOil);
                    pLineChartBrent.getData().add(pChartBrent);



                }
                else if (pChoiceBox.getItems().get((Integer) number2)=="Fuel Oil"){
                    pTextFieldBrentPrice.setText(""+pTest1.getFuelOilPrice());
                    pLineChartBrent.getData().remove(pChartBrent);
                    pLineChartBrent.getData().add(pChartFuelOil);

                }
            }
        });*/


    }

    @FXML
    public void buttonBuyBrentAction (){
        pTest1.buyBrent(Integer.parseInt(pTextFieldBrentBuy.getText()));

        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();
    }

    @FXML
    public void buttonSellBrentAction (){
        pTest1.sellBrent(Integer.parseInt(pTextFieldBrentSell.getText()));

        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();

    }

    @FXML
    public void buttonBuyFuelOilAction (){
        pTest1.buyFuelOil(Integer.parseInt(pTextFieldFuelOilBuy.getText()));

        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();
    }

    @FXML
    public void buttonSellFuelOilAction (){
        pTest1.sellFuelOil(Integer.parseInt(pTextFieldFuelOilSell.getText()));

        pTextFieldMoney.setText(""+pTest1.getMoney());
        pTableViewProduct.refresh();

    }

    @FXML
    public void buttonNextStageAction(){
        pTest1.changePrice();
        pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldFuelOilPrice.setText(""+pTest1.getFuelOilPrice());

        pTest1.nextTurn();
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOilDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));

        pTableViewProduct.refresh();


    }
    @FXML
    private void reset() {
        pTest1.startTest();
        pChartBrent.getData().clear();
        pChartFuelOil.getData().clear();
        pChartFuelOilDual.getData().clear();
        pChartBrentDual.getData().clear();
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOilDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldFuelOilPrice.setText(""+pTest1.getFuelOilPrice());

        pTextFieldMoney.setText("" +pTest1.getMoney());
        pTextFieldBrentBuy.setText("0");
        pTextFieldBrentSell.setText("0");
        pTextFieldFuelOilBuy.setText("0");
        pTextFieldFuelOilSell.setText("0");
        pObsListProducts = FXCollections.observableArrayList(pTest1.getProductBrent(), pTest1.getProductFuelOil());
        pTableViewProduct.itemsProperty().setValue(pObsListProducts);


    }


    public static void main(String[] args) {
        launch(args);

    }

}