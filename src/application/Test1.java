package application;

import java.util.Random;

public class Test1 {
    private int pMoney;
    private int pTurn;
    private Product mProductBrent;
    private Product mProductFuelOil;

    public void startTest (){
        mProductBrent = new Product("Brent");
        mProductFuelOil = new Product("Fuel Oil");

        setMoney(0);
        setTurn(1);


    }

    public void buyBrent (int quantity){
        if (mProductBrent.getQuantity()<=0 && -mProductBrent.getQuantity()<quantity){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()+quantity)*mProductBrent.getPrice());
        } else {
            mProductBrent.setTotalValue (mProductBrent.getTotalValue() + quantity*mProductBrent.getPrice());
        }
        mProductBrent.setQuantity(quantity);
        pMoney -=quantity*mProductBrent.getPrice();
        if (mProductBrent.getQuantity()==0){
            mProductBrent.setAvgPrice(0);
        } else {
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());
        }
    }
    public void sellBrent (int quantity){
        if(quantity <= mProductBrent.getQuantity()) {
            if (mProductBrent.getQuantity() >= 0 && mProductBrent.getQuantity() < quantity) {
                mProductBrent.setTotalValue((mProductBrent.getQuantity() - quantity) * mProductBrent.getPrice());
            } else {
                mProductBrent.setTotalValue(mProductBrent.getTotalValue() - quantity * mProductBrent.getPrice());
            }
            mProductBrent.setQuantity(-quantity);
            pMoney += quantity * mProductBrent.getPrice();
            if (mProductBrent.getQuantity() == 0) {
                mProductBrent.setAvgPrice(0);
            } else {
                mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());
            }
        }
    }

    public void buyFuelOil (int quantity){
        if (mProductFuelOil.getQuantity()<=0 && -mProductFuelOil.getQuantity()<quantity){
            mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity()+quantity)*mProductFuelOil.getPrice());
        } else {
            mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue() + quantity*mProductFuelOil.getPrice());
        }
        mProductFuelOil.setQuantity(quantity);
        pMoney -=quantity*mProductFuelOil.getPrice();

        if (mProductFuelOil.getQuantity()==0){
            mProductFuelOil.setAvgPrice(0);
        } else {
            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());

        }
    }

    public void sellFuelOil (int quantity) {
        if (quantity <= mProductFuelOil.getQuantity()) {
            if (mProductFuelOil.getQuantity() >= 0 && mProductFuelOil.getQuantity() < quantity) {
                mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity() - quantity) * mProductFuelOil.getPrice());
            } else {
                mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue() - quantity * mProductFuelOil.getPrice());
            }

            mProductFuelOil.setQuantity(-quantity);
            pMoney += quantity * mProductFuelOil.getPrice();

            if (mProductFuelOil.getQuantity() == 0) {
                mProductFuelOil.setAvgPrice(0);
            } else {
                mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / mProductFuelOil.getQuantity());
            }
        }
    }


    public void changePrice (){
        mProductBrent.setPrice(new Random().nextInt(10)-5);
        mProductFuelOil.setPrice(new Random().nextInt(10)-5);
    }

    public double getBrentPrice (){
        return mProductBrent.getPrice();
    }

    public double getFuelOilPrice (){
        return mProductFuelOil.getPrice();
    }

    public int getBrentQuantity (){
        return mProductBrent.getQuantity();
    }

    public int getFuelOilQuantity (){
        return mProductFuelOil.getQuantity();
    }

    public void setMoney (int value) {
        pMoney = value;
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


    public int getAverageBrentPrice() {
        return mProductBrent.getAvgPrice();
    }

    public int getAverageFuelOilPrice() {
        return mProductFuelOil.getAvgPrice();
    }
    public Product getProductBrent (){
        return mProductBrent;
    }

    public Product getProductFuelOil(){
        return mProductFuelOil;
    }

}
