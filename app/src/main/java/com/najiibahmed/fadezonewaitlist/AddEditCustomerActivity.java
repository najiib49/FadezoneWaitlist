package com.najiibahmed.fadezonewaitlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditCustomerActivity extends AppCompatActivity {
    private Toolbar toolbarAddCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        toolbarAddCustomer = findViewById(R.id.toolbarAddCustomer);
        Intent intent = getIntent();


        toolbarAddCustomer.setTitle("Add A Customer");
        toolbarAddCustomer.setNavigationIcon(R.drawable.ic_arrow_back_);
        setSupportActionBar(toolbarAddCustomer);

    }

    public void OnClick_addCustomerSubmit(View view) {
        EditText editTextCustomerName = findViewById(R.id.editTextCustomerName);
        String customerName = editTextCustomerName.getText().toString();

        EditText editTextCustomerPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        String customerPhoneNumber = editTextCustomerPhoneNumber.getText().toString();

        if (!customerName.isEmpty() && !customerPhoneNumber.isEmpty()) {
            Intent intent = getIntent();
            intent.putExtra("customerName", customerName);
            intent.putExtra("customerPhoneNumber", customerPhoneNumber);
            setResult(Activity.RESULT_OK,intent);
            finish();
        } else {
            showMyToast("Missing fields required!");
        }
    }

    /**
     * show a quick toast with the message provided
     * @param message the message to show in the toast
     */
    private void showMyToast(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }

}
