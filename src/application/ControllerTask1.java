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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerTask1 {
    private application.Test1 mTest1;
    private Timeline mTimelinePriceChange;
    private Timeline mTimelineTimerChange;
    private int mRemainingTime;
    private final int mTimeLimit = 30;
    private Stage mStage;
    private Scene mScene;
    private Parent mParrent;

    @FXML
    private Button mDialogButton;
    @FXML
    private TextField mTextFieldTimer;
    @FXML
    private TextField mTextFieldBrentBuy;
    @FXML
    private TextField mTextFieldBrentSell;
    @FXML
    private TextField mTextFieldMoney;
    @FXML
    private TextField mTextFieldBrentPrice;
    @FXML
    private LineChart<String, Number> mLineChartBrent;

    @FXML
    private XYChart.Series<String, Number> mChartBrent;

    @FXML
    private XYChart.Series<String, Number> mChartBrentDual;

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

        mChartBrentDual = new XYChart.Series<>();

        mTest1 = new Test1();
        mTest1.startTest();

        mObsListProducts = FXCollections.observableArrayList(mTest1.getProductBrent());
        mTextFieldBrentPrice.setText(""+ mTest1.getBrentPrice());

        mTextFieldMoney.setText(""+ mTest1.getMoney());
        updateChartValues();
        mChartBrent.setName(mTest1.getProductBrent().getName());

        mChartBrentDual.setName(mTest1.getProductBrent().getName());


        mLineChartBrent.getData().add(mChartBrent);

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
        mTest1.buyBrent(Integer.parseInt(mTextFieldBrentBuy.getText()));

        mTextFieldMoney.setText(""+ mTest1.getMoney());
        mTableViewProduct.refresh();
    }

    @FXML
    public void buttonSellBrentAction (){
        mTest1.sellBrent(Integer.parseInt(mTextFieldBrentSell.getText()));

        mTextFieldMoney.setText(""+ mTest1.getMoney());
        mTableViewProduct.refresh();

    }


    @FXML
    public void buttonNextStageAction(){
        mTest1.changePrice();
        mTextFieldBrentPrice.setText(""+ mTest1.getBrentPrice());
        mTest1.nextTurn();
        updateChartValues();
        mTextFieldMoney.setText(""+mTest1.getMoney());

        mTableViewProduct.refresh();
        startTimer();



    }
    @FXML
    private void reset() {
        mTest1.startTest();
        mChartBrent.getData().clear();

        mChartBrentDual.getData().clear();
        mTextFieldBrentPrice.setText(""+ mTest1.getBrentPrice());
        updateChartValues();
        mTextFieldMoney.setText("" + mTest1.getMoney());
        mTextFieldBrentBuy.setText("0");
        mTextFieldBrentSell.setText("0");
        mObsListProducts = FXCollections.observableArrayList(mTest1.getProductBrent());
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
        mChartBrent.getData().add(new XYChart.Data<String, Number>(""+ mTest1.getTurn(), mTest1.getBrentPrice()));
        mChartBrentDual.getData().add(new XYChart.Data<String, Number>(""+ mTest1.getTurn(), mTest1.getBrentPrice()));

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



}
