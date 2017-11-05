package com.example.softacle.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Convert extends AppCompatActivity {
    Button btn;
    TextView btcVal;
    EditText editText;
    TextView ethVal;
    TextView cur;
    double btcInt;
    double ethInt;
    double editTextVal;



    Currencies currPosition = MainActivity.current_currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        btcVal = (TextView) findViewById(R.id.btcVal);
        ethVal = (TextView) findViewById(R.id.ethVal);
        cur = (TextView) findViewById(R.id.cur);
        editText = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.btn);

        //To set to actual currency value
        cur.setText(currPosition.getDefaultCurrency());


    }

        //Setting the button to do some calculations bro!!
        public void btn(View view) {

        //Checking if edit text is empty
        if (TextUtils.isEmpty(editText.getText().toString())) {
            Toast.makeText(this, "Input a value to check for equivalency", Toast.LENGTH_SHORT).show();
            return;
        }

        //Converting value of edit Text to doubles
        editTextVal = Double.parseDouble(editText.getText().toString());

        //Calculating the value of edit text with the actual parse value
        btcInt = editTextVal * currPosition.getBtcCurrency();
        ethInt = editTextVal * currPosition.getEthCurrency();


        //Set and display of value on text view
        btcVal.setText(btcInt + " ");
        ethVal.setText(ethInt + " ");


    }


}

