package edu.domain.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String selectedGood = receivedOrderIntent.getStringExtra("selectedGood");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        int totalPrice = receivedOrderIntent.getIntExtra("totalPrice", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(userName + "\n" + selectedGood +"\n" +  quantity +"\n" +  totalPrice);
    }
}