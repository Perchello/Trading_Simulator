package application;

public class Test1 {
    private double mMoney;
    private int mTurn;
    private Product mProductBrent;

    public void startTest (){
        mProductBrent = new Product("Brent");
        setMoney(0);
        setTurn(1);
    }


    public void buyBrent (int quantity){
        if ((mProductBrent.getQuantity()+quantity)< 0){
            mProductBrent.setTotalValue(-(mProductBrent.getQuantity()+quantity)*mProductBrent.getAvgPrice());
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());
            mMoney += mProductBrent.getAvgPrice()*quantity;

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(-mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()+quantity) == 0){
            mProductBrent.setTotalValue(0);

            mMoney += quantity*mProductBrent.getAvgPrice();

            mProductBrent.setQuantity(quantity);
            mProductBrent.setAvgPrice(0);



        } else if ((mProductBrent.getQuantity()+quantity) > 0 && mProductBrent.getQuantity()<0){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()+quantity)*mProductBrent.getPrice());
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());
            mMoney += -mProductBrent.getQuantity()*mProductBrent.getAvgPrice();
            mProductBrent.setQuantity(quantity);
            mMoney -= mProductBrent.getQuantity()* mProductBrent.getPrice();

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        } else {

            mProductBrent.setTotalValue(mProductBrent.getTotalValue() + (quantity*mProductBrent.getPrice()));
            System.out.println ("Total Value after buy " + mProductBrent.getTotalValue());

            mMoney -= quantity * mProductBrent.getPrice();
            mProductBrent.setQuantity(quantity);

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue()/mProductBrent.getQuantity());


        }
    }
    public void sellBrent (int quantity){
        if ((mProductBrent.getQuantity()-quantity)> 0){
            mProductBrent.setTotalValue((mProductBrent.getQuantity()-quantity)*mProductBrent.getAvgPrice());
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            mMoney += quantity*mProductBrent.getAvgPrice();

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / mProductBrent.getQuantity());


        } else if ((mProductBrent.getQuantity()-quantity) == 0){
            mProductBrent.setTotalValue(0);

            mMoney += quantity*mProductBrent.getAvgPrice();

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(0);


        } else if ((mProductBrent.getQuantity()-quantity) <0 && mProductBrent.getQuantity()>0){
            mProductBrent.setTotalValue(-(mProductBrent.getQuantity()-quantity) * mProductBrent.getPrice());
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            mMoney += mProductBrent.getQuantity()*mProductBrent.getAvgPrice();
            mProductBrent.setQuantity(-quantity);
            mMoney -= -mProductBrent.getQuantity()*mProductBrent.getPrice();

            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());


        } else {
            mProductBrent.setTotalValue(mProductBrent.getTotalValue()+(quantity*mProductBrent.getPrice()));
            System.out.println ("Total Value after sell " + mProductBrent.getTotalValue());

            mMoney -= quantity*mProductBrent.getPrice();

            mProductBrent.setQuantity(-quantity);
            mProductBrent.setAvgPrice(mProductBrent.getTotalValue() / -mProductBrent.getQuantity());

        }
    }

    public void setBalance(double difference) {
        if (mProductBrent.getQuantity() > 0 ){
            mMoney += difference*mProductBrent.getQuantity();
            System.out.println ("Price went up by " + difference);
        } else if (mProductBrent.getQuantity()<0){
            mMoney -= difference*mProductBrent.getQuantity();
            System.out.println ("Price went down by " + difference);

        }

    }
    public void changePrice (){

        double i = mProductBrent.changePrice();
        System.out.println("i = " + i);
                setBalance(i);
    }

    public double getBrentPrice (){
        return mProductBrent.getPrice();
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

    public Product getProductBrent (){
        return mProductBrent;
    }



}
