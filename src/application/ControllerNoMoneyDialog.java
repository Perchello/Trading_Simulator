package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/**
 * Created by Perchello on 20/06/2017.
 */
public class ControllerNoMoneyDialog {
    @FXML
    private Button mDialogButton;
    @FXML
    private TextField mRequiredMoney;
    @FXML
    private TextField mCurrentMoney;

    @FXML
    public void initialize(){

    }



    @FXML
    public void closeDialog(){
        Stage stage = (Stage) mDialogButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setDialogText (double requiredMoney, double currentMoney) {
        System.out.println("requiredMoney: "+requiredMoney +", currentMoney: " + currentMoney);
        mRequiredMoney.setText(formatDouble(requiredMoney));
        mCurrentMoney.setText(formatDouble(currentMoney));
    }

    public String formatDouble(double value){
        String pattern = "###,###.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String formattedDouble = decimalFormat.format(value);

        return formattedDouble;
    }

}
