package application;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * Created by Perchello on 21/06/2017.
 */
public class ControllerTradLogDialog {
    @FXML
    private TableView<Product> mTableView;
    @FXML
    private TableColumn<Product, String> mColumnTurn;

    @FXML
    private TableColumn<Product, String> mColumnProductName;
    @FXML
    private TableColumn<Product, String> mColumnProductQuantity;
    @FXML
    private TableColumn<Product, String> mColumnProductPrice;
    @FXML
    private TableColumn<Product, String> mColumnTotal;

    ObservableList<Product> mObsListProducts;



    @FXML
    public void initialize(){

    }

    public void setObsListProducts(ObservableList<Product> product){
        mObsListProducts = product;
        System.out.println(product.get(1).getName());
        mTableView.itemsProperty().setValue(mObsListProducts);
        mColumnTurn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCurrentTurn()));
        mColumnProductName.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getName()));
        mColumnProductQuantity.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getQuantity()));
        mColumnProductPrice.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCurrentPrice()));
        mColumnTotal.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getTotalValue()));




    }
}
