package application;

import java.util.Random;

public class Test1 {
    private double pBrentPrice;
    private double pFuelOilPrice;
    private int pBrentQuantity;
    private int pFuelOilQuantity;
    public int pMoney;

    public void startTest (){
        setBrentPrice(90);
        setFuelOilPrice(200);
        setBrentQuantity (0);
        setFuelOilQuantity (0);
        setMoney(0);

    }

    public void buyBrent (int quantity){
        pBrentQuantity +=quantity;
        pMoney -=quantity*pBrentPrice;
    }
    public void sellBrent (int quantity){
        pBrentQuantity -=quantity;
        pMoney +=quantity*pBrentPrice;
    }

    public void buyFuelOil (int quantity){
        pFuelOilQuantity +=quantity;
        pMoney -=quantity*pFuelOilPrice;
    }

    public void sellFuelOil (int quantity){
        pFuelOilQuantity -=quantity;
        pMoney +=quantity*pFuelOilPrice;
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
}
