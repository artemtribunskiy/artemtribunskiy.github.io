package edu.domain.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses = {"fazaprofit@yandex.ru"};
    String subject = "Order From Shop";
    String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String selectedGood = receivedOrderIntent.getStringExtra("selectedGood");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        int totalPrice = receivedOrderIntent.getIntExtra("totalPrice", 0);
        int priceForValue = receivedOrderIntent.getIntExtra("priceForValue", 0);

        emailText = userName + "\n" + selectedGood + "\n" + quantity + "\n" + totalPrice + "\n" + priceForValue;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            TextView orderTextView = findViewById(R.id.orderTextView);
            orderTextView.setText("Куку");
        }
    }
}