package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebIconDatabase;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    Button btnTopping;
    public String[] items = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives","Onions", "Red Pepper"};
    public String[] selections = new String[10];
    public int counter = 0;
    Button btnCalculateTotal;
    CheckBox deliveryCheckBox;
    double basePrize = 6.50;
    double checkoutPrize =0;
    double toppingPrize=0;
    double deliveryPrize=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConstraintLayout constLayout = (ConstraintLayout) findViewById(R.id.constraint1);
        btnCalculateTotal = findViewById(R.id.btnCheckout);
        deliveryCheckBox=findViewById(R.id.checkBox);


//
        final ProgressBar pgbar = findViewById(R.id.progressBar);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a Topping")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ImageView iv_item = new ImageView(MainActivity.this);
                        ImageView iv_item2 = new ImageView(MainActivity.this);
                        ViewGroup.LayoutParams imageparm = iv_item.getLayoutParams();
                        final TypedArray img = getResources().obtainTypedArray(R.array.items_images);
                        float layourweight =1;
                        LinearLayout layout1 = new LinearLayout(MainActivity.this);
                        layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,layourweight));
                        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layout1.setOrientation(LinearLayout.HORIZONTAL);
                        layout1.setId(View.generateViewId());
                        layout1.setGravity(Gravity.TOP);
                        layout1.setBackgroundColor(Color.RED);
                        //TextView tv1 = new TextView(MainActivity.this);
                        //layout1.addView(tv1);
                        //tv1.setText("Hello");
                        layout1.addView(iv_item);
                        LinearLayout layout2 = new LinearLayout(MainActivity.this);
                        layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        layout2.setOrientation(LinearLayout.HORIZONTAL);
                        layout2.setId(View.generateViewId());
                        layout2.setGravity(Gravity.TOP);
                        layout2.setBackgroundColor(Color.BLUE);
                        /*TextView tv2 = new TextView(MainActivity.this);
                        layout2.addView(tv2);
                        tv2.setText("Boy");*/
                        layout2.addView(iv_item2);

                      /* ConstraintLayout.LayoutParams constParams =
                                new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);*/

                        constLayout.addView(layout1,0);
                        constLayout.addView(layout2,0);
                        // constLayout.addView(layout2,constParams);

                        ConstraintSet set = new ConstraintSet();

                        set.clone(constLayout);

                        int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics());
                        int topMargin2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics());
                        //int leftMargin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,getResources().getDisplayMetrics());



                        set.connect(layout1.getId(),ConstraintSet.TOP,R.id.constraint1,ConstraintSet.TOP,topMargin);
                        set.connect(layout2.getId(),ConstraintSet.TOP,layout1.getId(),ConstraintSet.BOTTOM,topMargin2);
                        //set.connect(layout1.getId(),ConstraintSet.LEFT,R.id.constraint1,ConstraintSet.LEFT,leftMargin);

                        set.centerHorizontally(layout1.getId(),R.id.constraint1);
                        set.centerHorizontally(layout2.getId(),R.id.constraint1);

                        set.applyTo(constLayout);
                        if(counter < 10){
                            selections[counter] = items[i];
                            iv_item.setImageResource(img.getResourceId(i,-1));
                            img.recycle();
                            if(counter >5)
                            {
                                iv_item2.setImageResource(img.getResourceId(i,-1));
                                img.recycle();
                            }
                            Log.d("demo",counter + "-"+items[i]);
                            if(counter == 0){
                                pgbar.setProgress(10);
                            }else{
                                pgbar.setProgress((counter+1)*10);
                            }
                            counter++;
                        }else{
                            Toast.makeText(getApplicationContext(),"Maximum toppings capacity reached!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        final AlertDialog options = builder.create();

        findViewById(R.id.btnTopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.show();
            }
        });

        btnCalculateTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toppingPrize = (counter * 1.50);
                if(deliveryCheckBox.isChecked())
                {
                    deliveryPrize=4.00;
                }
                else
                {
                    deliveryPrize=0.00;
                }

                checkoutPrize = basePrize + toppingPrize + deliveryPrize;

                Intent intent = new Intent(MainActivity.this, CheckoutDetails.class);
                intent.putExtra("toppingPrize", toppingPrize);
                intent.putExtra("deilveryCharge", deliveryPrize);
                intent.putExtra("basePrize", basePrize);
                intent.putExtra("checkout",checkoutPrize);
                intent.putExtra("selection", selections);
                startActivity(intent);

            }
        });

    }
}

