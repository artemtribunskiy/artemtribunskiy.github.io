package edu.domain.myapplication3;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;
import java.util.HashMap;

//новый коммит
public class MainActivity extends AppCompatActivity {
    public int quantity = 1;
    int price;

    String goodsName;
    TextView textView4;// = findViewById(R.id.textView4);
    TextView priceTag;// = findViewById(R.id.priceTag);
    HashMap<String, Integer> mList = new HashMap<String, Integer>();
    Spinner mSpinner;// = findViewById(R.id.spinner1);
    ImageView goodsImageView;
    EditText editTextTextPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        textView4 = findViewById(R.id.textView4);
        priceTag = findViewById(R.id.priceTag);
        mSpinner = findViewById(R.id.spinner1);
        goodsImageView = findViewById(R.id.imageView2);

        mList.put("Fender", 990);
        mList.put("Gibson", 1200);
        mList.put("Ibanez", 700);

        Button button_plus = findViewById(R.id.button_plus);
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                Update();
            }
        });

        Collection<String> guitars = mList.keySet();
        String[] array = guitars.toArray(new String[mList.size()]);
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, array);
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        mSpinner.setAdapter(mArrayAdapter);//
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantity = 1;
//                String item = adapterView.getItemAtPosition(i).toString();
                goodsName = mSpinner.getSelectedItem().toString();
                switch (goodsName) {
                    case "Fender":
                        goodsImageView.setImageResource(R.drawable.fender1);
                        break;
                    case "Gibson":
                        goodsImageView.setImageResource(R.drawable.gibson);
                        break;
                    case "Ibanez":
                        goodsImageView.setImageResource(R.drawable.ibanez);
                        break;
                }
                Update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void minusButton(View v) {
        if (quantity > 1) {
            quantity--;
            Update();
        }
    }

    public void Update() {
        textView4.setText(Integer.toString(quantity));
        price = mList.get(mSpinner.getSelectedItem()) * quantity;
        priceTag.setText(String.valueOf(price + "$"));

    }

    public void AddToCart(View view) {
        Order cart = new Order();

        cart.userName = editTextTextPersonName.getText().toString();
        cart.quantity = quantity;
        cart.selectedGood = mSpinner.getSelectedItem().toString();
        cart.totalPrice = price;
        cart.priceForValue = price/quantity;
        //Log.d("HELLO:", cart.userName + ", заказал " + cart.selectedGood + ", количество: " + cart.quantity + " штуковины, на сумму: " + cart.totalPrice + "$");
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", cart.userName);
        orderIntent.putExtra("selectedGood", cart.selectedGood);
        orderIntent.putExtra("quantity", cart.quantity);
        orderIntent.putExtra("totalPrice", cart.totalPrice);
        orderIntent.putExtra("priceForValue", cart.priceForValue);
        startActivity(orderIntent);
    }
}

