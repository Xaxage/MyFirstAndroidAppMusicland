package com.xaxage.musicland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity=0;
    Spinner spinner;
    String goodsName;
    int price;

    EditText userNameEditText;

    ArrayList spinnerArrayList=new ArrayList();
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap=new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        createMap();

        userNameEditText=findViewById(R.id.nameEditText);
    }
    void createSpinner(){
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerArrayList.add("Saxophone");
        spinnerArrayList.add("Trumpet");
        spinnerArrayList.add("Drums");

        spinnerAdapter=new ArrayAdapter(this/*this class*/,android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap(){
        goodsMap.put("Saxophone",1200);
        goodsMap.put("Trumpet",600);
        goodsMap.put("Drums",800);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityTextView=findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView=findViewById(R.id.orderPrice);
        priceTextView.setText("" + quantity * price);;
    }

    public void decreaseQuantity(View view) {
        if(quantity>0) {
            quantity--;
        }
        TextView quantityTextView=findViewById(R.id.quantityTextView);
        TextView priceTextView=findViewById(R.id.orderPrice);
        priceTextView.setText("" + quantity * price);;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    goodsName=spinner.getSelectedItem().toString();
    price=(int)goodsMap.get(goodsName);
    TextView priceTextView=findViewById(R.id.orderPrice);
    priceTextView.setText("" + quantity * price);
        ImageView goodsImageViews=findViewById(R.id.goodsImageView);

        switch (goodsName){
            case"Saxophone":
                goodsImageViews.setImageResource(R.drawable.saxaphone);
                break;
            case"Trumpet":
                goodsImageViews.setImageResource(R.drawable.trumpet);
                break;
            case"Drums":
                goodsImageViews.setImageResource(R.drawable.drums);
                break;
            default:
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order=new Order();
        order.userName=userNameEditText.getText().toString();
        //Log.d("UserName",order.userName);
        order.goodsName=goodsName;
        //Log.d("goodsName",order.goodsName);
        order.quantity=quantity;
        //Log.d("quantity",""+order.quantity);
        order.orderPrice =quantity*price;
        //Log.d("orderPrice",""+order.price);
        order.price=price;

        Intent orderIntent=new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("userNameForIntent",order.userName);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("orderPrice",order.orderPrice);
        orderIntent.putExtra("price",price);
        startActivity(orderIntent);
    }
}