package com.xaxage.musicland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses={"xaxage@gmail.com"};
    String subject="Order from Musicland";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your order");

        Intent receivedOrderIntent=getIntent();
        String userName=receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName=receivedOrderIntent.getStringExtra("goodsName");
        int quantity=receivedOrderIntent.getIntExtra("quantity",0);
        int orderPrice=receivedOrderIntent.getIntExtra("orderPrice",0);
        int price=receivedOrderIntent.getIntExtra("price",0);

        emailText="Customer name: "+userName+ "\n" + "Goods name: "+goodsName + "\n"+
                "Quantity: "+quantity+"\n" + "Price for one: "+price +"\n"+"Order price: "+orderPrice ;
        TextView orderTextView=findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}