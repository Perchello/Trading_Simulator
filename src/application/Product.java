package application;


/**
 * Created by Perchello on 12/06/2017.
 */
public class Product {
    private String mName;
    private Price mPrice;
    private int mQuantity;
    private double mTotalValue;
    private double mAvgPrice;
    private double mCurrentPrice;
    private double mDifference;
    private int mCurrentTurn;


    public Product(String name) {
        mName = name;
        mQuantity = 0;
        mTotalValue = 0;
        mAvgPrice = 0;
        if (name == "Brent") {
            mCurrentPrice = 100;
            mPrice = new Price(mCurrentPrice, mName);
        } else if (name == "Fuel Oil") {
            mCurrentPrice = 200;
            mPrice = new Price(mCurrentPrice, mName);
        }
    }
    public Product (int turn, String name, int quantity, double price, double total){
        mCurrentTurn = turn;
        mName = name;
        mQuantity = quantity;
        mPrice = new Price (price, mName);
        mCurrentPrice = mPrice.getPrice();
        mTotalValue = total;


    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity += quantity;
    }

    public double getPrice() {
        return mCurrentPrice;
    }

    public double changePrice() {
        mDifference = mPrice.changePrice();
        mCurrentPrice=mPrice.getPrice();
        return mDifference;
    }

    public double getTotalValue() {
        return mTotalValue;
    }

    public void setTotalValue(double mTotalValue) {
        this.mTotalValue = mTotalValue;
    }

    public double getAvgPrice() {
        return mAvgPrice;
    }

    public void setAvgPrice(double mAvgPrice) {
        this.mAvgPrice = mAvgPrice;
    }
    public int getCurrentTurn(){
        return mCurrentTurn;

    }
    public double getCurrentPrice(){
        return mCurrentPrice;
    }

}
