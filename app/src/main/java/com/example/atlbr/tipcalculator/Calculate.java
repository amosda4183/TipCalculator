package com.example.atlbr.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Calculate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        //Bundle object for receiving passed data from other activity
        Bundle inputData = getIntent().getExtras();
        if (inputData==null)
            return;

        //Create new string variables to store data passed from other activity, utilizing the appropriate key
        String tenTip = inputData.getString("tenTip");
        String fifteenTip = inputData.getString("fifteenTip");
        String twentyTip = inputData.getString("twentyTip");

        //Create textview objects for manipulating output
        final TextView  tenPercent =  findViewById(R.id.tenPercent);
        final TextView fifteenPercent = findViewById(R.id.fifteenPercent);
        final TextView twentyPercent = findViewById(R.id.twentyPercent);

        //Set output for textview objects to reflect tip calculations
        tenPercent.setText("10% Tip: " + tenTip);
        fifteenPercent.setText("15% Tip: " + fifteenTip);
        twentyPercent.setText("20% Tip: " + twentyTip);

    }

    public void onClick(View view) {

        Intent i = new Intent();

        final EditText tipSelection = findViewById(R.id.tipSelection);

        //Variable for gathering user input for tip amount selection
        String selectedTip = tipSelection.getText().toString();
        i.putExtra("selectedTip",selectedTip);

        setResult(RESULT_OK,i);
        finish();


    }
}
