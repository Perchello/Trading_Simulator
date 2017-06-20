package application;

public class Test2 {
    private double mMoney;
    private int mTurn;
    private Product mProductBrent;
    private Product mProductFuelOil;
    private boolean mEnoughMoney;
    private double mRequiredMoney;


    public void startTest (){
        mProductBrent = new Product("Brent");
        mProductFuelOil = new Product("Fuel Oil");
        mEnoughMoney = true;

        setMoney(20000);
        setTurn(1);

    }


    public void buyBrent (int quantity){
        if ((mProductBrent.getQuantity()+quantity)< 0){
            mProductBrent.setTotalValue(-(mProductBrent.getQuantity()+quantity)*mProductBrent.getAvgPrice());
            mMoney += mProductBrent.getAvgPrice()*quantity;

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(-mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()+quantity) == 0){
            mProductBrent.setTotalValue(0);

            mMoney += mProductBrent.getAvgPrice()*quantity;

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(0);



        } else if ((mProductBrent.getQuantity()+quantity) > 0 && mProductBrent.getQuantity()<0){
            mRequiredMoney = (-mProductBrent.getQuantity() * mProductBrent.getAvgPrice())-quantity* mProductBrent.getPrice();
            if (mMoney+ mRequiredMoney > 0){
                mProductBrent.setTotalValue((mProductBrent.getQuantity() + quantity) * mProductBrent.getPrice());
                mMoney += -mProductBrent.getQuantity() * mProductBrent.getAvgPrice();
                mProductBrent.setQuantity(quantity);
                mMoney -= mProductBrent.getQuantity() * mProductBrent.getPrice();

                mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / mProductBrent.getQuantity());
            } else {
                mEnoughMoney=false;
            }

        } else {
            mRequiredMoney = quantity*mProductFuelOil.getPrice();
            if (mMoney- mRequiredMoney>=0){
                mProductBrent.setTotalValue(mProductBrent.getTotalValue() + (quantity * mProductBrent.getPrice()));

                mMoney -= quantity * mProductBrent.getPrice();
                mProductBrent.setQuantity(quantity);

                mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / mProductBrent.getQuantity());
            } else {
                mEnoughMoney=false;

            }

        }
    }
    public void sellBrent (int quantity){
        if ((mProductBrent.getQuantity()-quantity)> 0){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()-quantity)*mProductBrent.getAvgPrice());

            mMoney += mProductBrent.getAvgPrice()*quantity;

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()-quantity) == 0){
            mProductBrent.setTotalValue(0);

            mMoney += mProductBrent.getAvgPrice()*quantity;

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(0);


        } else if ((mProductBrent.getQuantity()-quantity) <0 && mProductBrent.getQuantity()>0){
            mRequiredMoney = mProductBrent.getQuantity()*mProductBrent.getAvgPrice()-(-quantity*mProductBrent.getPrice());
            if (mMoney+mRequiredMoney>=0) {
                mProductBrent.setTotalValue(-(mProductBrent.getQuantity() - quantity) * mProductBrent.getPrice());

                mMoney += mProductBrent.getQuantity() * mProductBrent.getAvgPrice();
                mProductBrent.setQuantity(-quantity);
                mMoney -= -mProductBrent.getQuantity() * mProductBrent.getPrice();

                mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());
            } else {
                mEnoughMoney = false;
            }


        } else {
            mRequiredMoney = quantity*mProductBrent.getPrice();
            if (mMoney-mRequiredMoney>=0) {
                mProductBrent.setTotalValue(mProductBrent.getTotalValue() + (quantity * mProductBrent.getPrice()));

                mMoney -= quantity * mProductBrent.getPrice();

                mProductBrent.setQuantity(-quantity);
                mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());
            } else {
                mEnoughMoney = false;
            }

        }
    }

    public void buyFuelOil (int quantity){
        if ((mProductFuelOil.getQuantity()+quantity)< 0){
            mProductFuelOil.setTotalValue(-(mProductFuelOil.getQuantity()+quantity)*mProductFuelOil.getAvgPrice());

            mMoney += mProductFuelOil.getAvgPrice()*quantity;

            mProductFuelOil.setQuantity(quantity);
            mProductFuelOil.setAvgPrice(-mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());


        } else if ((mProductFuelOil.getQuantity()+quantity) == 0){
            mProductFuelOil.setTotalValue(0);

            mMoney += mProductFuelOil.getAvgPrice()*quantity;

            mProductFuelOil.setQuantity(quantity);
            mProductFuelOil.setAvgPrice(0);



        } else if ((mProductFuelOil.getQuantity()+quantity) > 0 && mProductFuelOil.getQuantity()<0){
            mRequiredMoney = (-mProductFuelOil.getAvgPrice()*mProductFuelOil.getQuantity())-quantity*mProductFuelOil.getPrice();
            if (mMoney+ mRequiredMoney >=0) {
                mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity() + quantity) * mProductFuelOil.getPrice());

                mMoney += -mProductFuelOil.getAvgPrice() * mProductFuelOil.getQuantity();
                mProductFuelOil.setQuantity(quantity);
                mMoney -= mProductFuelOil.getQuantity() * mProductFuelOil.getPrice();

                mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / mProductFuelOil.getQuantity());
            } else {
                mEnoughMoney = false;
            }

        } else {
            mRequiredMoney = quantity*mProductFuelOil.getPrice();
            if (mMoney-mRequiredMoney>=0) {
                mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue() + (quantity * mProductFuelOil.getPrice()));

                mMoney -= quantity * mProductFuelOil.getPrice();
                mProductFuelOil.setQuantity(quantity);

                mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / mProductFuelOil.getQuantity());
            } else {
                mEnoughMoney = false;
            }

        }

    }

    public void sellFuelOil (int quantity) {

        if ((mProductFuelOil.getQuantity()-quantity)> 0){
            mProductFuelOil.setTotalValue((mProductFuelOil.getQuantity()-quantity)*mProductFuelOil.getAvgPrice());

            mMoney += mProductFuelOil.getAvgPrice()*quantity;

            mProductFuelOil.setQuantity(-quantity);
            mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue()/mProductFuelOil.getQuantity());


        } else if ((mProductFuelOil.getQuantity()-quantity) == 0){
            mProductFuelOil.setTotalValue(0);

            mMoney += mProductFuelOil.getAvgPrice()*quantity;

            mProductFuelOil.setQuantity(-quantity);
            mProductFuelOil.setAvgPrice(0);


        } else if ((mProductFuelOil.getQuantity()-quantity) <0 && mProductFuelOil.getQuantity()>0){
            mRequiredMoney = mProductFuelOil.getAvgPrice()*mProductFuelOil.getQuantity()-(-quantity*mProductFuelOil.getPrice());
            if (mMoney+mRequiredMoney>=0) {
                mProductFuelOil.setTotalValue(-(mProductFuelOil.getQuantity() - quantity) * mProductFuelOil.getPrice());
                mMoney += mProductFuelOil.getAvgPrice() * mProductFuelOil.getQuantity();
                mProductFuelOil.setQuantity(-quantity);
                mMoney -= -mProductFuelOil.getQuantity() * mProductFuelOil.getPrice();
                mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / -mProductFuelOil.getQuantity());
            } else {
                mEnoughMoney = false;
            }

        } else {
            mRequiredMoney = quantity*mProductFuelOil.getPrice();
            if (mMoney-mRequiredMoney>=0) {
                mProductFuelOil.setTotalValue(mProductFuelOil.getTotalValue() + (quantity * mProductFuelOil.getPrice()));
                mMoney -= quantity * mProductFuelOil.getPrice();

                mProductFuelOil.setQuantity(-quantity);
                mProductFuelOil.setAvgPrice(mProductFuelOil.getTotalValue() / -mProductFuelOil.getQuantity());
            } else {
                mEnoughMoney = false;
            }
        }


    }



    public void setBalance(double brentDifference, double fuelOilDifference) {

        if (mProductBrent.getQuantity() > 0 ){
            mMoney += brentDifference*mProductBrent.getQuantity();
            System.out.println ("Brent price changed by " + brentDifference);
        } else if (mProductBrent.getQuantity()<0){
            mMoney += brentDifference*mProductBrent.getQuantity();
            System.out.println ("Brent price changed by " + brentDifference);

        }
        if (mProductFuelOil.getQuantity() > 0 ){
            mMoney += fuelOilDifference*mProductFuelOil.getQuantity();
            System.out.println ("Fuel Oil price changed by " + fuelOilDifference);
        } else if (mProductFuelOil.getQuantity()<0){
            mMoney += fuelOilDifference*mProductFuelOil.getQuantity();
            System.out.println ("Fuel Oil price changed by " + fuelOilDifference);

        }

    }
    public void changePrice (){

        setBalance(mProductBrent.changePrice(), mProductFuelOil.changePrice());
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
        mMoney = value;
    }

    public double getMoney (){
        return mMoney;
    }

    public int getTurn() {
        return mTurn;
    }

    public void setTurn(int pTurn) {
        this.mTurn = pTurn;
    }
    public void nextTurn (){
        mTurn +=1;

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

    public boolean getEnoughMoney(){
      return mEnoughMoney;
    }
    public void setEnoughMoney(boolean enoughMoney){
        mEnoughMoney = enoughMoney;

    }
    public double getRequiredMoney(){
        return mRequiredMoney;
    }


}
