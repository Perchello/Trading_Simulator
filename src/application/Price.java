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

        return mPrice;

    }

    private void setPriceChangeCount() {
        mPriceChangeCount=7;
    }

    public int setPriceScenario(){
        mPriceScenario = new Random().nextInt(5)+1;
        return mPriceScenario;
    }

    public double changePrice () {
        if (mPriceChangeCount==0){
            setPriceScenario();
            System.out.println(mName+ " scenario is " + mPriceScenario);
            setPriceChangeCount();
        }
        System.out.println("Changing price");
        double difference;

        if (mPriceScenario == 1){
            difference= (new Random().nextInt(5)-3) *0.25;
        } else if (mPriceScenario == 2) {
            difference= (new Random().nextInt(4)-2) *0.25;


        } else if (mPriceScenario == 3) {
            difference= (new Random().nextInt(5)-2) *0.25;

        } else if (mPriceScenario == 4) {
            difference= (new Random().nextInt(4)-1) *0.25;

        } else if (mPriceScenario == 5) {
            difference= (new Random().nextInt(5)-1) *0.25;
        } else {
            difference = 0;
        }
        mPrice+=difference;
        System.out.println (mName + " difference is " + difference);
        mPriceChangeCount-=1;
        System.out.println(mName + "has turns till scenario change " + mPriceChangeCount);

        return difference;


    }



}
