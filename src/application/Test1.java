package application;

import java.util.Random;

public class Test1 {
    private int pBrentPrice;
    private int pFuelOilPrice;
    private int pBrentQuantity;
    private int pFuelOilQuantity;
    private int pTotalBoughtBrentValue;
    private int pTotalBoughtFuelOilValue;
    private int pAverageBrentPrice;
    private int pAverageFuelOilPrice;
    private int pMoney;
    private int pTurn;

    public void startTest (){
        setBrentPrice(90);
        setFuelOilPrice(200);
        setBrentQuantity (0);
        setFuelOilQuantity (0);
        setMoney(0);
        setTurn(1);
        setTotalBoughtBrentValue(0);
        setTotalBoughtFuelOilValue(0);
        setAverageBrentPrice(0);
        setAverageFuelOilPrice(0);

    }

    public void buyBrent (int quantity){
        if (pBrentQuantity<=0 && -pBrentQuantity<quantity){
            setTotalBoughtBrentValue ((pBrentQuantity+quantity)*pBrentPrice);
        } else {
            setTotalBoughtBrentValue (pTotalBoughtBrentValue + quantity*pBrentPrice);
        }
        pBrentQuantity +=quantity;
        pMoney -=quantity*pBrentPrice;
        if (pBrentQuantity==0){
            setAverageBrentPrice(0);
        } else {
            setAverageBrentPrice(pTotalBoughtBrentValue/pBrentQuantity);
        }
    }
    public void sellBrent (int quantity){
        if(quantity <= pBrentQuantity) {
            if (pBrentQuantity >= 0 && pBrentQuantity < quantity) {
                setTotalBoughtBrentValue((pBrentQuantity - quantity) * pBrentPrice);
            } else {
                setTotalBoughtBrentValue(pTotalBoughtBrentValue - quantity * pBrentPrice);
            }
            pBrentQuantity -= quantity;
            pMoney += quantity * pBrentPrice;
            if (pBrentQuantity == 0) {
                setAverageBrentPrice(0);
            } else {
                setAverageBrentPrice(pTotalBoughtBrentValue / pBrentQuantity);
            }
        }
    }

    public void buyFuelOil (int quantity){
        if (pFuelOilQuantity<=0 && -pFuelOilQuantity<quantity){
            setTotalBoughtFuelOilValue ((pFuelOilQuantity+quantity)*pFuelOilPrice);
        } else {
            setTotalBoughtFuelOilValue (pTotalBoughtFuelOilValue + quantity*pFuelOilPrice);
        }
        pFuelOilQuantity +=quantity;
        pMoney -=quantity*pFuelOilPrice;

        if (pFuelOilQuantity==0){
            setAverageFuelOilPrice(0);
        } else {
            setAverageFuelOilPrice(pTotalBoughtFuelOilValue/pFuelOilQuantity);

        }
    }

    public void sellFuelOil (int quantity) {
        if (quantity <= pFuelOilQuantity) {
            if (pFuelOilQuantity >= 0 && pFuelOilQuantity < quantity) {
                setTotalBoughtFuelOilValue((pFuelOilQuantity - quantity) * pFuelOilPrice);
            } else {
                setTotalBoughtFuelOilValue(pTotalBoughtFuelOilValue - quantity * pFuelOilPrice);
            }

            pFuelOilQuantity -= quantity;
            pMoney += quantity * pFuelOilPrice;

            if (pFuelOilQuantity == 0) {
                setAverageFuelOilPrice(0);
            } else {
                setAverageFuelOilPrice(pTotalBoughtFuelOilValue / pFuelOilQuantity);
            }
        }
    }


    public void changePrice (){
        pBrentPrice += new Random().nextInt(10)-5;
        pFuelOilPrice +=new Random().nextInt(20)-10;
    }
    public void setBrentPrice (int amount){
        pBrentPrice=amount;
    }

    public double getBrentPrice (){
        return pBrentPrice;
    }

    public void setFuelOilPrice(int amount){
        pFuelOilPrice= amount;
    }

    public double getFuelOilPrice (){
        return pFuelOilPrice;
    }

    public void setBrentQuantity(int amount){
        pBrentQuantity=amount;
    }

    public int getBrentQuantity (){
        return pBrentQuantity;
    }

    public void setFuelOilQuantity(int amount){
        pFuelOilQuantity=amount;
    }

    public int getFuelOilQuantity (){
        return pFuelOilQuantity;
    }

    public void testStart(){
    }

    public void setMoney (int value) {
        pMoney = value;
    }

    public void addMoney (int amount){
        pMoney += amount;
    }
    public void removeMoney (int amount){
        pMoney -= amount;
    }

    public int getMoney (){
        return pMoney;
    }

    public int getTurn() {
        return pTurn;
    }

    public void setTurn(int pTurn) {
        this.pTurn = pTurn;
    }
    public void nextTurn (){
        pTurn+=1;
    }

    public int getTotalBoughtFuelOilValue() {
        return pTotalBoughtFuelOilValue;
    }

    public void setTotalBoughtFuelOilValue(int pTotalBoughtFuelOilValue) {
        this.pTotalBoughtFuelOilValue = pTotalBoughtFuelOilValue;
    }

    public int getTotalBoughtBrentValue() {
        return pTotalBoughtBrentValue;
    }

    public void setTotalBoughtBrentValue(int pTotalBoughtBrentValue) {
        this.pTotalBoughtBrentValue = pTotalBoughtBrentValue;
    }

    public int getAverageBrentPrice() {
        return pAverageBrentPrice;
    }

    public void setAverageBrentPrice(int pAverageBrentPrice) {
        this.pAverageBrentPrice = pAverageBrentPrice;
    }

    public int getAverageFuelOilPrice() {
        return pAverageFuelOilPrice;
    }

    public void setAverageFuelOilPrice(int pAverageFuelOilPrice) {
        this.pAverageFuelOilPrice = pAverageFuelOilPrice;
    }
}
