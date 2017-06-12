package application;

/**
 * Created by Perchello on 12/06/2017.
 */
public class Product {
    private String mName;
    private int mPrice;
    private int mQuantity;
    private int mTotalValue;
    private int mAvgPrice;


    public Product(String name) {
        mName = name;
        mQuantity = 0;
        mTotalValue = 0;
        mAvgPrice = 0;
        if (name == "Brent"){
            mPrice = 100;
        } else if  (name == "Fuel Oil"){
            mPrice = 200;
        }
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

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice += mPrice;
    }

    public int getTotalValue() {
        return mTotalValue;
    }

    public void setTotalValue(int mTotalValue) {
        this.mTotalValue = mTotalValue;
    }

    public int getAvgPrice() {
        return mAvgPrice;
    }

    public void setAvgPrice(int mAvgPrice) {
        this.mAvgPrice = mAvgPrice;
    }
}
