package com.najiibahmed.fadezonewaitlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddCustomerActivity extends AppCompatActivity {
    private Toolbar toolbarAddCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        toolbarAddCustomer = findViewById(R.id.toolbarAddCustomer);
        toolbarAddCustomer.setTitle("Add A Customer");
        toolbarAddCustomer.setNavigationIcon(R.drawable.ic_arrow_back_);
        setSupportActionBar(toolbarAddCustomer);

    }
}
