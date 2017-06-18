package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by Perchello on 18/06/2017.
 */
public class ControllerTask1 {
    private application.Test1 pTest1;
    private Timeline pTimelinePriceChange;
    private Timeline pTimelineTimerChange;
    private int pRemainingTime;
    private final int pTimeLimit = 16;
    private Stage mStage;
    private Scene mScene;
    private Parent mParrent;

    @FXML
    private TextField pTextFieldTimer;
    @FXML
    private TextField pTextFieldBrentBuy;
    @FXML
    private TextField pTextFieldBrentSell;
    @FXML
    private TextField pTextFieldFuelOilBuy;
    @FXML
    private TextField pTextFieldFuelOilSell;
    @FXML
    private TextField pTextFieldMoney;
    @FXML
    private TextField pTextFieldBrentPrice;
    @FXML
    private TextField pTextFieldFuelOilPrice;
    @FXML
    private LineChart<String, Number> pLineChartBrent;
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


    ObservableList <Product> pObsListProducts;

    public void initialize () {
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
        pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldFuelOilPrice.setText(""+pTest1.getFuelOilPrice());
        pTextFieldMoney.setText(""+ pTest1.getMoney());
        updateChartValues();
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
        pTimelinePriceChange = new Timeline();
        pTimelineTimerChange = new Timeline();
        startTimer();

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
        updateChartValues();

        pTableViewProduct.refresh();
        startTimer();



    }
    @FXML
    private void reset() {
        pTest1.startTest();
        pChartBrent.getData().clear();
        pChartFuelOil.getData().clear();
        pChartFuelOilDual.getData().clear();
        pChartBrentDual.getData().clear();
        pTextFieldBrentPrice.setText(""+pTest1.getBrentPrice());
        pTextFieldFuelOilPrice.setText(""+pTest1.getFuelOilPrice());
        updateChartValues();
        pTextFieldMoney.setText("" +pTest1.getMoney());
        pTextFieldBrentBuy.setText("0");
        pTextFieldBrentSell.setText("0");
        pTextFieldFuelOilBuy.setText("0");
        pTextFieldFuelOilSell.setText("0");
        pObsListProducts = FXCollections.observableArrayList(pTest1.getProductBrent(), pTest1.getProductFuelOil());
        pTableViewProduct.itemsProperty().setValue(pObsListProducts);
        startTimer();
    }

    private void startTimer(){
        pRemainingTime = pTimeLimit-1;
        pTextFieldTimer.setText(""+pRemainingTime);
        pTimelineTimerChange.stop();
        pTimelinePriceChange.stop();
        pTimelinePriceChange = new Timeline( new KeyFrame(Duration.millis(pTimeLimit*1000), ae -> buttonNextStageAction()));
        pTimelineTimerChange = new Timeline(new KeyFrame(Duration.millis(1000), ae->changeTimer()));
        pTimelinePriceChange.play();
        pTimelineTimerChange.setCycleCount(pTimeLimit);
        pTimelineTimerChange.play();

    }
    private void changeTimer(){
        System.out.println("Current Remaining time is "+ pRemainingTime);
        pTextFieldTimer.setText(""+pRemainingTime);
        pRemainingTime-=1;
    }

    private void updateChartValues(){
        pChartBrent.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));
        pChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getBrentPrice()));
        pChartFuelOilDual.getData().add(new XYChart.Data<String, Number>(""+pTest1.getTurn(), pTest1.getFuelOilPrice()));

    }

    public void toMainMenu(Event event){
        pTimelineTimerChange.stop();
        pTimelinePriceChange.stop();
        mParrent = null;
        try {
            mParrent = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mScene = new Scene(mParrent);
        mStage = (Stage) pTextFieldBrentBuy.getScene().getWindow();
        mStage.setScene(mScene);
        mStage.show();
    }

}
