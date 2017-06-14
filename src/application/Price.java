package application;

import java.util.Random;

/**
 * Created by Perchello on 13/06/2017.
 */
public class Price {
    private double mPrice;
    private int mPriceScenario;
    private int mPriceChangeCount;
    private String mName;

    public Price (double i, String name) {
        mPrice = i;
        mName = name;
        mPriceChangeCount = 0;
    }
    public double getPrice(){
        if (mPriceChangeCount==0){
            setPriceScenario();
            System.out.println(mName+ " scenario is " + mPriceScenario);
            setPriceChangeCount();
        }
        changePrice();
        mPriceChangeCount-=1;
        System.out.println(mName + "has turns till scenario change " + mPriceChangeCount);

        return mPrice;

    }

    private void setPriceChangeCount() {
        mPriceChangeCount=7;
    }

    public int setPriceScenario(){
        mPriceScenario = new Random().nextInt(5)+1;
        return mPriceScenario;
    }

    public void changePrice () {
        if (mPriceScenario == 1){
            mPrice+= (new Random().nextInt(5)-3) *0.25;
        } else if (mPriceScenario == 2) {
            mPrice+= (new Random().nextInt(4)-2) *0.25;


        } else if (mPriceScenario == 3) {
            mPrice+= (new Random().nextInt(5)-2) *0.25;

        } else if (mPriceScenario == 4) {
            mPrice+= (new Random().nextInt(4)-1) *0.25;

        } else if (mPriceScenario == 5) {
            mPrice+= (new Random().nextInt(5)-1) *0.25;
        }
    }



}
