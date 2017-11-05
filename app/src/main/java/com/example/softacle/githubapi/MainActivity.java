package com.example.softacle.githubapi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Currencies>> {

     String Github_url_string = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH%2CBTC&tsyms=" +
            "USD%2CEUR%2CNGN%2CRUB%2CCAD%2CJPY%2CGBP%2CAUD%2CINR%2CHKD%2CIDR2CSGD%2CCHF%2CCNY%2CZAR%2CTHB%2CSAR%2CKRW%2CGHS%2CBRL";
    static URL CYPTO_REQUEST_URL;

    static Currencies current_currency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This is to check if phone is currently connected to the internet or not
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            //network is available go get the data
            getSupportLoaderManager().initLoader(1, null, MainActivity.this);

        }
        else {
            //if there is already fetched_developers
            if (CurrencyLoader.fetched_currencies == null) {

                //hide progress bar
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.GONE);
                //internet is not available; make it known
                TextView empty_view = (TextView) findViewById(R.id.empty_view);
                //make it visible
                empty_view.setVisibility(View.VISIBLE);
                empty_view.setText("No internet connection");
                ListView listView = (ListView) findViewById(R.id.list_quake);
                listView.setEmptyView(findViewById(R.id.empty_view));

            }
        }


    }

    @Override
    public Loader<ArrayList<Currencies>> onCreateLoader(int id, Bundle args) {

        //Parsing string to uri
        Uri baseUri = Uri.parse(Github_url_string);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        //Parsing uri string to URL
        try {
            CYPTO_REQUEST_URL = new URL(uriBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

        return new CurrencyLoader(this);

    }

    //Everything that should occur after the loading should appear in this onLoadFinished method
    @Override
    public void onLoadFinished(Loader<ArrayList<Currencies>> loader, ArrayList<Currencies> data) {
        //Hide the progressbar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        //Updating te UI
        updateUi(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Currencies>> loader) {
        updateUi(new ArrayList<Currencies>());
    }

    //This is the update UI method
    private void updateUi(final ArrayList<Currencies> developers) {
        ListView listView = (ListView) findViewById(R.id.list_quake);
        CurrenciesAdapter currencyAdapter = new CurrenciesAdapter(this, developers);
        listView.setAdapter(currencyAdapter);

        //What happens when the list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //use the position of the item clicked on the listview to
                // get the corresponding url from the developer list
                Currencies cur = developers.get(position);
                current_currency = cur;
                Intent convert = new Intent(getApplicationContext(), Convert.class);
                startActivity(convert);


            }
        });

        //show empty if result is empty
        if (currencyAdapter.isEmpty() || currencyAdapter == null) {
            TextView empty_view = (TextView) findViewById(R.id.empty_view);
            //make it visible
            empty_view.setVisibility(View.VISIBLE);
            listView.setEmptyView(findViewById(R.id.empty_view));
        }


    }


}

