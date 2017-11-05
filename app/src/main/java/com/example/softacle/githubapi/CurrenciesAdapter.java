package com.example.softacle.githubapi;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class CurrenciesAdapter extends ArrayAdapter<Currencies> {




    public CurrenciesAdapter(Activity context, List<Currencies> currencies) {

        // Here, I initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, currencies);
    }

    //Handles the inflation of the list_item and assign apprioprate values from the developers class
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Word object from the ArrayAdapter at the appropriate position
        Currencies currency = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //LinearLayout list_item = (LinearLayout) convertView.findViewById(R.id.list_item);
        TextView defaultCur = (TextView) convertView.findViewById(R.id.defaultCurrency);
        TextView btc = (TextView) convertView.findViewById(R.id.btc);
        TextView eth = (TextView) convertView.findViewById(R.id.eth);


        defaultCur.setText(currency.getDefaultCurrency());
        btc.setText(currency.getBtcCurrency() + " ");
        eth.setText(currency.getEthCurrency() +" ");






        return convertView;

    }
}

