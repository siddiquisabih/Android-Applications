package com.example.android.accordingtolesson;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {


    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        CheckBox whipped = (CheckBox) findViewById(R.id.Button);
        boolean hasWippedCream = whipped.isChecked();
//        Log.v("MainActivity", " hasWippedCream" + hasWippedCream);
          int price = calculatePrice();

        CheckBox chocolate = (CheckBox) findViewById(R.id.ChoclateButton);
        boolean haschoclate = chocolate.isChecked();


       String pricemessage = orderSummary(price, hasWippedCream , haschoclate);
        displayMessage(pricemessage);


    }

    private int calculatePrice() {

        return quantity * 5;

    }


    /**
     * This method is called when the plus button is clicked.
     */


    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private String orderSummary(int price, boolean wipped , boolean choclate) {


        String priceMessage = "Name : Sabih Siddiqui";
        priceMessage += "\nQuantity " + quantity;
        priceMessage += "\nWhipped Cream ?" + wipped;
        priceMessage += "\nChoclate ?" + choclate;
        priceMessage += "\nTotal: Rs " + price;
        priceMessage += "\nThank You";
        return priceMessage;
    }


}