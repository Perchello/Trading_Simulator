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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by Perchello on 18/06/2017.
 */
public class ControllerTask2 {
    private Test2 mTest2;
    private Timeline mTimelinePriceChange;
    private Timeline mTimelineTimerChange;
    private int mRemainingTime;
    private final int mTimeLimit = 16;
    private Stage mStage;
    private Scene mScene;
    private Parent mParrent;
    private ControllerNoMoneyDialog mControllerNoMoneyDialog;

    @FXML
    private TextField mTextFieldTimer;
    @FXML
    private TextField mTextFieldBrentBuy;
    @FXML
    private TextField mTextFieldBrentSell;
    @FXML
    private TextField mTextFieldFuelOilBuy;
    @FXML
    private TextField mTextFieldFuelOilSell;
    @FXML
    private TextField mTextFieldMoney;
    @FXML
    private TextField mTextFieldBrentPrice;
    @FXML
    private TextField mTextFieldFuelOilPrice;
    @FXML
    private LineChart<String, Number> mLineChartBrent;
    @FXML
    private LineChart <String, Number> mLineChartFuelOil;
    @FXML
    private LineChart <String, Number> mLineChartBrentFuel;
    @FXML
    private XYChart.Series<String, Number> mChartBrent;
    @FXML
    private XYChart.Series<String, Number> mChartFuelOil;
    @FXML
    private XYChart.Series<String, Number> mChartBrentDual;
    @FXML
    private XYChart.Series<String, Number> mChartFuelOilDual;
    @FXML
    private TableView<Product> mTableViewProduct;
    @FXML
    private TableColumn<Product, String> mColumnProductName;
    @FXML
    private TableColumn<Product, String> mColumnProductQuantity;
    @FXML
    private TableColumn<Product, String> mColumnProductAvgPrice;


    ObservableList <Product> mObsListProducts;

    public void initialize () {
        startTest1();

    }

    private void startTest1() {
        mChartBrent = new XYChart.Series<>();
        mChartFuelOil = new XYChart.Series<>();
        mChartFuelOilDual = new XYChart.Series<>();
        mChartBrentDual = new XYChart.Series<>();
        mControllerNoMoneyDialog = new ControllerNoMoneyDialog();
        mTest2 = new Test2();
        mTest2.startTest();

        mObsListProducts = FXCollections.observableArrayList(mTest2.getProductBrent(), mTest2.getProductFuelOil());
        mTextFieldBrentPrice.setText(""+ mTest2.getBrentPrice());
        mTextFieldFuelOilPrice.setText(""+ mTest2.getFuelOilPrice());
        mTextFieldMoney.setText(""+ mTest2.getMoney());
        updateChartValues();
        mChartBrent.setName(mTest2.getProductBrent().getName());
        mChartFuelOil.setName(mTest2.getProductFuelOil().getName());
        mChartBrentDual.setName(mTest2.getProductBrent().getName());
        mChartFuelOilDual.setName(mTest2.getProductFuelOil().getName());

        mLineChartBrent.getData().add(mChartBrent);
        mLineChartFuelOil.getData().add(mChartFuelOil);
        mLineChartBrentFuel.getData().add(mChartBrentDual);
        mLineChartBrentFuel.getData().add(mChartFuelOilDual);
        mTableViewProduct.itemsProperty().setValue(mObsListProducts);
        mColumnProductName.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getName()));
        mColumnProductQuantity.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getQuantity()));
        mColumnProductAvgPrice.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAvgPrice()));
        mTimelinePriceChange = new Timeline();
        mTimelineTimerChange = new Timeline();
        startTimer();

        mTextFieldBrentBuy.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    mTextFieldBrentBuy.setText(oldValue);
                }
            }

        });

        mTextFieldBrentSell.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    mTextFieldBrentSell.setText(oldValue);
                }
            }

        });

    }

    @FXML
    public void buttonBuyBrentAction (){
        mTest2.buyBrent(Integer.parseInt(mTextFieldBrentBuy.getText()));
        if (mTest2.getEnoughMoney()) {
            mTextFieldMoney.setText("" + mTest2.getMoney());
            mTableViewProduct.refresh();
        } else {
            createDialog();
            mTest2.setEnoughMoney(true);
        }
    }

    @FXML
    public void buttonSellBrentAction (){
        mTest2.sellBrent(Integer.parseInt(mTextFieldBrentSell.getText()));
        if (mTest2.getEnoughMoney()) {
            mTextFieldMoney.setText("" + mTest2.getMoney());
            mTableViewProduct.refresh();
        } else {
            createDialog();
            mTest2.setEnoughMoney(true);
        }

    }

    @FXML
    public void buttonBuyFuelOilAction (){
        mTest2.buyFuelOil(Integer.parseInt(mTextFieldFuelOilBuy.getText()));
        if (mTest2.getEnoughMoney()) {
            mTextFieldMoney.setText("" + mTest2.getMoney());
            mTableViewProduct.refresh();
        } else {
            createDialog();
            mTest2.setEnoughMoney(true);
        }
    }

    @FXML
    public void buttonSellFuelOilAction (){
        mTest2.sellFuelOil(Integer.parseInt(mTextFieldFuelOilSell.getText()));
        if (mTest2.getEnoughMoney()) {

            mTextFieldMoney.setText("" + mTest2.getMoney());
            mTableViewProduct.refresh();
        } else {
            createDialog();
            mTest2.setEnoughMoney(true);
        }

    }

    @FXML
    public void buttonNextStageAction(){
        mTest2.changePrice();
        mTextFieldBrentPrice.setText(""+ mTest2.getBrentPrice());
        mTextFieldFuelOilPrice.setText(""+ mTest2.getFuelOilPrice());

        mTest2.nextTurn();
        updateChartValues();
        mTextFieldMoney.setText(""+mTest2.getMoney());

        mTableViewProduct.refresh();
        startTimer();



    }
    @FXML
    private void reset() {
        mTest2.startTest();
        mChartBrent.getData().clear();
        mChartFuelOil.getData().clear();
        mChartFuelOilDual.getData().clear();
        mChartBrentDual.getData().clear();
        mTextFieldBrentPrice.setText(""+ mTest2.getBrentPrice());
        mTextFieldFuelOilPrice.setText(""+ mTest2.getFuelOilPrice());
        updateChartValues();
        mTextFieldMoney.setText("" + mTest2.getMoney());
        mTextFieldBrentBuy.setText("0");
        mTextFieldBrentSell.setText("0");
        mTextFieldFuelOilBuy.setText("0");
        mTextFieldFuelOilSell.setText("0");
        mObsListProducts = FXCollections.observableArrayList(mTest2.getProductBrent(), mTest2.getProductFuelOil());
        mTableViewProduct.itemsProperty().setValue(mObsListProducts);
        startTimer();
    }

    private void startTimer(){
        mRemainingTime = mTimeLimit -1;
        mTextFieldTimer.setText(""+ mRemainingTime);
        mTimelineTimerChange.stop();
        mTimelinePriceChange.stop();
        mTimelinePriceChange = new Timeline( new KeyFrame(Duration.millis(mTimeLimit *1000), ae -> buttonNextStageAction()));
        mTimelineTimerChange = new Timeline(new KeyFrame(Duration.millis(1000), ae->changeTimer()));
        mTimelinePriceChange.play();
        mTimelineTimerChange.setCycleCount(mTimeLimit);
        mTimelineTimerChange.play();

    }
    private void changeTimer(){
        System.out.println("Current Remaining time is "+ mRemainingTime);
        mTextFieldTimer.setText(""+ mRemainingTime);
        mRemainingTime -=1;
    }

    private void updateChartValues(){
        mChartBrent.getData().add(new XYChart.Data<String, Number>(""+ mTest2.getTurn(), mTest2.getBrentPrice()));
        mChartFuelOil.getData().add(new XYChart.Data<String, Number>(""+ mTest2.getTurn(), mTest2.getFuelOilPrice()));
        mChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+ mTest2.getTurn(), mTest2.getBrentPrice()));
        mChartFuelOilDual.getData().add(new XYChart.Data<String, Number>(""+ mTest2.getTurn(), mTest2.getFuelOilPrice()));

    }

    public void toMainMenu(Event event){
        mTimelineTimerChange.stop();
        mTimelinePriceChange.stop();
        mParrent = null;
        try {
            mParrent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mScene = new Scene(mParrent);
        mStage = (Stage) mTextFieldBrentBuy.getScene().getWindow();
        mStage.setScene(mScene);
        mStage.show();
    }

    public void createDialog () {
        final Stage myDialog = new Stage();
        Scene myDialogScene = null;
        try {
            myDialogScene = new Scene (FXMLLoader.load(getClass().getResource("NoMoneyDialog.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        myDialog.setScene(myDialogScene);
        myDialog.initModality(Modality.APPLICATION_MODAL);
        myDialog.setTitle("Недостаточно денег");
        myDialog.show();

    }

}
