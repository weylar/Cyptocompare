package com.example.softacle.githubapi;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class CurrencyLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Currencies>> {

    //Storing the github url from the main activity to github url variable
    URL CYPTO_URL = MainActivity.CYPTO_REQUEST_URL;


    //to hold the list of already fetched items, so there wouldnt be the need to query anotertime
    public static ArrayList<Currencies> fetched_currencies = null;


    public CurrencyLoader(Context context) {
        super(context);

    }

    @Override
    public ArrayList<Currencies> loadInBackground() {

        if (fetched_currencies == null) {
            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(CYPTO_URL);
            } catch (IOException e) {
                // TODO Handle the IOException
            }


            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Currencies> currencies = extractCurrencies(jsonResponse);

            return currencies;
        } else {
            return fetched_currencies;
        }
    }

    // This is required to tell the loader to start doing the background load
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    //Make an HTTP request to the given URL and return a String as the response.
    private String makeHttpRequest(URL url) throws IOException {
         String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /*
//return a list of{@link Currencies} object that has been built up from
//parsing a json response
*/
    public ArrayList<Currencies> extractCurrencies(String jsonResponse) {

        //create an empty arraylist that we can start adding earthquake to
        ArrayList<Currencies> currenciesVal = new ArrayList<>();

        /* try to parse the sample response, if there is an error
           //in the way it is array exception should be thrown
         */
        try {
            //build up array of developers with the corresponding data
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            //Getting the object for the ETH value pair
            JSONObject ethObj = baseJsonResponse.getJSONObject("ETH");

            //Getting the string for btc strings
            double usdE = (ethObj.getDouble("USD"));
            double eurE = ethObj.getDouble("EUR");
            double ngnE = ethObj.getDouble("NGN");
            double rubE = ethObj.getDouble("RUB");
            double cadE = ethObj.getDouble("CAD");
            double jpyE = ethObj.getDouble("JPY");
            double gbpE = ethObj.getDouble("GBP");
            double audE = ethObj.getDouble("AUD");
            double inrE = ethObj.getDouble("INR");
            double hkdE = ethObj.getDouble("HKD");
            double idrE = ethObj.optDouble("IDR");
            double sgdE = ethObj.optDouble("SGD");
            double chfE = ethObj.getDouble("CHF");
            double cnyE = ethObj.getDouble("CNY");
            double zarE = ethObj.getDouble("ZAR");
            double thbE = ethObj.getDouble("THB");
            double sarE = ethObj.getDouble("SAR");
            double krwE = ethObj.getDouble("KRW");
            double ghsE = ethObj.getDouble("GHS");
            double brlE = ethObj.getDouble("BRL");


            //Getting the value for theBTC value pair
            JSONObject btcObj = baseJsonResponse.getJSONObject("BTC");

            //Getting the string for btc strings
            double usdB = btcObj.getDouble("USD");
            double eurB = btcObj.getDouble("EUR");
            double ngnB = btcObj.getDouble("NGN");
            double rubB = btcObj.getDouble("RUB");
            double cadB = btcObj.getDouble("CAD");
            double jpyB = btcObj.getDouble("JPY");
            double gbpB = btcObj.getDouble("GBP");
            double audB = btcObj.getDouble("AUD");
            double inrB = btcObj.getDouble("INR");
            double hkdB = btcObj.getDouble("HKD");
            double idrB = btcObj.optDouble("IDR");
            double sgdB = btcObj.optDouble("SGD");
            double chfB = btcObj.getDouble("CHF");
            double cnyB = btcObj.getDouble("CNY");
            double zarB = btcObj.getDouble("ZAR");
            double thbB = btcObj.getDouble("THB");
            double sarB = btcObj.getDouble("SAR");
            double krwB = btcObj.getDouble("KRW");
            double ghsB = btcObj.getDouble("GHS");
            double brlB = btcObj.getDouble("BRL");

            //This adds up the new currencies value to new currency custom class
            currenciesVal.add(new Currencies("USD",usdB, usdE));
            currenciesVal.add(new Currencies("EUR",eurB,eurE));
            currenciesVal.add(new Currencies("NGN",ngnB,ngnE));
            currenciesVal.add(new Currencies("RUB",rubB,rubE));
            currenciesVal.add(new Currencies("CAD",cadB,cadE));
            currenciesVal.add(new Currencies("JPY",jpyB,jpyE));
            currenciesVal.add(new Currencies("GBP",gbpB,gbpE));
            currenciesVal.add(new Currencies("AUD",audB,audE));
            currenciesVal.add(new Currencies("INR",inrB,inrE));
            currenciesVal.add(new Currencies("HKD",hkdB,hkdE));
            currenciesVal.add(new Currencies("IDR",idrB,idrE));
            currenciesVal.add(new Currencies("SGD",sgdB,sgdE));
            currenciesVal.add(new Currencies("CHF",chfB,chfE));
            currenciesVal.add(new Currencies("CNY",cnyB,cnyE));
            currenciesVal.add(new Currencies("ZAR",zarB,zarE));
            currenciesVal.add(new Currencies("THB",thbB,thbE));
            currenciesVal.add(new Currencies("SAR",sarB,sarE));
            currenciesVal.add(new Currencies("KRW",krwB,krwE));
            currenciesVal.add(new Currencies("GHS",ghsB,ghsE));
            currenciesVal.add(new Currencies("BRL",brlB,brlE));











        } catch (JSONException e) {
        }

        fetched_currencies = currenciesVal;

        return currenciesVal;
    }


}



