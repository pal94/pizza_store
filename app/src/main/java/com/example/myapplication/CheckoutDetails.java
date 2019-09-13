package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CheckoutDetails extends AppCompatActivity {
    TextView baseCharge;
    TextView toppingCharge;
    TextView deilveryCharge;
    TextView arrayTextView;
    TextView totalCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_details);

        Intent intent1 = getIntent();
        baseCharge = findViewById(R.id.basePrize);
        toppingCharge=findViewById(R.id.toppingCharge);
        deilveryCharge = findViewById(R.id.deliveryCharge);
        arrayTextView = findViewById(R.id.arrayTextView);
        totalCharge = findViewById(R.id.totalCharge);


        baseCharge.setText(intent1.getExtras().getString("basePrize"));
        toppingCharge.setText(intent1.getExtras().getString("toppingPrize"));
        deilveryCharge.setText(intent1.getExtras().getString("deilveryCharge"));
        arrayTextView.setText(intent1.getExtras().getString("selection"));
        totalCharge.setText(intent1.getExtras().getString("checkout"));


    }
}
