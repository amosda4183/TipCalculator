package com.example.atlbr.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DecimalFormat;


public class EnterTip extends AppCompatActivity {

    double input;
    double tenCost, fifteenCost, twentyCost, totalCost;

    final int TEN_PERCENT = 10;
    final int FIFTEEN_PERCENT = 15;
    final int TWENTY_PERCENT = 20;

    //create currency object for formatting output
    DecimalFormat currency = new DecimalFormat("$###.##");

    String tenTip, fifteenTip, twentyTip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_tip);

        Bundle inputData = getIntent().getExtras();
        if (inputData==null)
            return;

        //Receiving tip choice data from Calculate.java activity
        String tipChoice = inputData.getString("selectedTip");
    }

    public void onClick(View view){

        Intent i = new Intent(this,Calculate.class);

        //creating object to extract data from user input
        final EditText billInput =  findViewById(R.id.txtInput);

        //assign value of user input for the bill amount into a variable
        input = Double.parseDouble(billInput.getText().toString());

        //calculate the different tip amounts
        tenCost = (TEN_PERCENT * input)/100;
        fifteenCost = (FIFTEEN_PERCENT * input) / 100;
        twentyCost = (TWENTY_PERCENT * input) / 100;

        //Pass calculated tip amounts into String variables to be sent to other activity
         tenTip = currency.format(tenCost);
         fifteenTip = currency.format(fifteenCost);
         twentyTip = currency.format(twentyCost);

        //Use putExtra function to send data to the other activity
        i.putExtra("tenTip",tenTip);
        i.putExtra("fifteenTip", fifteenTip);
        i.putExtra("twentyTip",twentyTip);

                startActivityForResult(i,0);


    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        final TextView txtTotalPaid = findViewById(R.id.txtTotalPaid);

       //Receive user input from other activity and set textview to reflect the total bill
        if(requestCode == 0 && resultCode == RESULT_OK) {


            String selectedTip = data.getStringExtra("selectedTip").toString();

            if(Integer.parseInt(selectedTip) == 10) {
                totalCost = tenCost + input;
                txtTotalPaid.setText("Your total bill including tip will be: " + currency.format(totalCost));
            }

            if (Integer.parseInt(selectedTip) == 15){
                totalCost = fifteenCost + input;
                txtTotalPaid.setText("Your total bill including tip will be: " + currency.format(totalCost));
            }

            if (Integer.parseInt(selectedTip) == 20) {
                totalCost = twentyCost + input;
                txtTotalPaid.setText("Your total bill including tip will be: " + currency.format(totalCost));
            }

        }



    }



}
