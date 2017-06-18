package application;

import java.util.Random;

public class Test1 {
    private double pMoney;
    private int pTurn;
    private Product mProductBrent;
    private Product mProductFuelOil;

    public void startTest (){
        mProductBrent = new Product("Brent");
        mProductFuelOil = new Product("Fuel Oil");

        setMoney(0);
        setTurn(1);
        System.out.println ("FO price is" + mProductFuelOil.getPrice());


    }


    public void buyBrent (int quantity){
        if ((mProductBrent.getQuantity()+quantity)< 0){
            mProductBrent.setTotalValue(-(mProductBrent.getQuantity()+quantity)*mProductBrent.getAvgPrice());
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());
            pMoney += mProductBrent.getAvgPrice()*quantity + quantity*(mProductBrent.getAvgPrice()-mProductBrent.getPrice());

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(-mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()+quantity) == 0){
            mProductBrent.setTotalValue(0);

            pMoney += mProductBrent.getAvgPrice()*quantity + quantity*(mProductBrent.getAvgPrice()-mProductBrent.getPrice());

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(0);



        } else if ((mProductBrent.getQuantity()+quantity) > 0 && mProductBrent.getQuantity()<0){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()+quantity)*mProductBrent.getPrice());
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());
            pMoney += -(mProductBrent.getAvgPrice()*mProductBrent.getQuantity() + mProductBrent.getQuantity()*(mProductBrent.getAvgPrice()-mProductBrent.getPrice()));
            mProductBrent.setQuantity(quantity);
            pMoney-= mProductBrent.getQuantity()* mProductBrent.getPrice();

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        } else {

            mProductBrent.setTotalValue(mProductBrent.getTotalValue() + (quantity*mProductBrent.getPrice()));
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());

            pMoney -= quantity * mProductBrent.getPrice();
            mProductBrent.setQuantity(quantity);

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        }
    }
    public void sellBrent (int quantity){
        if ((mProductBrent.getQuantity()-quantity)> 0){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()-quantity)*mProductBrent.getAvgPrice());
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            pMoney += mProductBrent.getAvgPrice()*quantity + quantity*(mProductBrent.getPrice() - mProductBrent.getAvgPrice());

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()-quantity) == 0){
            mProductBrent.setTotalValue(0);

            pMoney += mProductBrent.getAvgPrice()*quantity + quantity*(mProductBrent.getPrice() - mProductBrent.getAvgPrice());

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(0);


        } else if ((mProductBrent.getQuantity()-quantity) <0 && mProductBrent.getQuantity()>0){
            mProductBrent.setTotalValue(-(mProductBrent.getQuantity()-quantity) * mProductBrent.getPrice());
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            pMoney += mProductBrent.getAvgPrice()*mProductBrent.getQuantity() + mProductBrent.getQuantity()*(mProductBrent.getPrice() - mProductBrent.getAvgPrice());
            mProductBrent.setQuantity(-quantity);
            pMoney -= -mProductBrent.getQuantity()*mProductBrent.getPrice();

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());


        } else {
            mProductBrent.setTotalValue(mProductBrent.getTotalValue()+(quantity*mProductBrent.getPrice()));
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            pMoney-= quantity*mProductBrent.getPrice();

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());

        }
    }

    public void buyFuelOil (int quantity){
        if ((mProductFuelOil.getQuantity()+quantity)< 0){
            mProductFuelOil.setTotalValue(-(mProductFuelOil.getQuantity()+quantity)*mProductFuelOil.getAvgPrice());

            pMoney += mProductFuelOil.getAvgPrice()*quantity + quantity*(mProductFuelOil.getAvgPrice()-mProductFuelOil.getPrice());

            mProductFuelOil.setQuantity(quantity);
            mProductFuelOil.setAvgPrice(-mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());


        } else if ((mProductFuelOil.getQuantity()+quantity) == 0){
            mProductFuelOil.setTotalValue(0);

            pMoney += mProductFuelOil.getAvgPrice()*quantity + quantity*(mProductFuelOil.getAvgPrice()-mProductFuelOil.getPrice());

            mProductFuelOil.setQuantity(quantity);
            mProductFuelOil.setAvgPrice(0);



        } else if ((mProductFuelOil.getQuantity()+quantity) > 0 && mProductFuelOil.getQuantity()<0){
            mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity()+quantity)*mProductFuelOil.getPrice());

            pMoney += -(mProductFuelOil.getAvgPrice()*mProductFuelOil.getQuantity() + mProductFuelOil.getQuantity()*(mProductFuelOil.getAvgPrice()-mProductFuelOil.getPrice()));
            mProductFuelOil.setQuantity(quantity);
            pMoney-=mProductFuelOil.getQuantity()*mProductFuelOil.getPrice();

            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());


        } else {

            mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue() + (quantity*mProductFuelOil.getPrice()));

            pMoney -= quantity * mProductFuelOil.getPrice();
            mProductFuelOil.setQuantity(quantity);

            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());


        }

    }

    public void sellFuelOil (int quantity) {

        if ((mProductFuelOil.getQuantity()-quantity)> 0){
            mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity()-quantity)*mProductFuelOil.getAvgPrice());

            pMoney += mProductFuelOil.getAvgPrice()*quantity + quantity*(mProductFuelOil.getPrice() - mProductFuelOil.getAvgPrice());

            mProductFuelOil.setQuantity(-quantity);
            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / mProductFuelOil.getQuantity());


        } else if ((mProductFuelOil.getQuantity()-quantity) == 0){
            mProductFuelOil.setTotalValue(0);

            pMoney += mProductFuelOil.getAvgPrice()*quantity + quantity*(mProductFuelOil.getPrice() - mProductFuelOil.getAvgPrice());

            mProductFuelOil.setQuantity(-quantity);
            mProductFuelOil.setAvgPrice(0);


        } else if ((mProductFuelOil.getQuantity()-quantity) <0 && mProductFuelOil.getQuantity()>0){
            mProductFuelOil.setTotalValue(-(mProductFuelOil.getQuantity()-quantity) * mProductFuelOil.getPrice());

            pMoney += mProductFuelOil.getAvgPrice()*mProductFuelOil.getQuantity() + mProductFuelOil.getQuantity()*(mProductFuelOil.getPrice() - mProductFuelOil.getAvgPrice());
             mProductFuelOil.setQuantity(-quantity);
             pMoney -= -mProductFuelOil.getQuantity()*mProductFuelOil.getPrice();

             mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / -mProductFuelOil.getQuantity());


        } else {
             mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue()+(quantity*mProductFuelOil.getPrice()));
             pMoney-= quantity*mProductFuelOil.getPrice();

            mProductFuelOil.setQuantity(-quantity);
            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / -mProductFuelOil.getQuantity());

        }


    }


    public void changePrice (){
        mProductBrent.changePrice();
        mProductFuelOil.changePrice();
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

    public double getMoney (){
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


    public double getAverageBrentPrice() {
        return mProductBrent.getAvgPrice();
    }

    public double getAverageFuelOilPrice() {
        return mProductFuelOil.getAvgPrice();
    }
    public Product getProductBrent (){
        return mProductBrent;
    }

    public Product getProductFuelOil(){
        return mProductFuelOil;
    }

}
