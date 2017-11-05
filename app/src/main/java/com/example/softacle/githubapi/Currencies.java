package com.example.softacle.githubapi;
/**
 * Created by muilat on 14-Aug-17.
 */

public class Currencies {

    //username of te developer
    String mDefaultCurrency ;

    //developer image
    double mBtcCurrency;

    //url that leads to te developer profile on webrowser
    double mEthCurrency;

    Currencies(String defaultCurrency, double btcCurrency, double ethCurrency){
        this.mDefaultCurrency = defaultCurrency;
        this.mBtcCurrency = btcCurrency;
        this.mEthCurrency = ethCurrency;
    }

    //return username
    public String getDefaultCurrency(){

        return mDefaultCurrency;
    }
    //Return the image
    public double getBtcCurrency(){

        return mBtcCurrency;
    }
    //Return profile url
    public double getEthCurrency(){

        return mEthCurrency;
    }
}
