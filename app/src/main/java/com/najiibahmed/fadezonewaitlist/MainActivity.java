package com.najiibahmed.fadezonewaitlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnCustomerListener {
    private static final String TAG = "MainActivity";
    public static final int ADD_CUSTOMER= 50;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Vars
    private ArrayList<String> customerNames = new ArrayList<>();
    private ArrayList<String> customerPhoneNumbers = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        recyclerView = findViewById(R.id.reyclerview);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationViewMainActivity);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.itemMenuProfile:
                        displayMessage("Profile selected...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.itemMenuLogout:
                        displayMessage("Logout selected...");
                        drawerLayout.closeDrawers();
                        return true;
                }


                return false;
            }
        });


        adapter = new RecyclerViewAdapter (this, customerNames, customerPhoneNumbers, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new CustomerListItemHelper(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        adapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);


    }

    public void OnClick_AddNewCustomer(View view) {
        // handles clicks on the add button
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivityForResult(intent, ADD_CUSTOMER);
    }

    private void displayMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==ADD_CUSTOMER && resultCode == RESULT_OK){
            String customerName = data.getStringExtra("customerName");
            String customerPhoneNumber = data.getStringExtra("customerPhoneNumber");
            customerNames.add(customerName);
            customerPhoneNumbers.add(customerPhoneNumber);



//            adapter = new RecyclerViewAdapter (this, customerNames, customerPhoneNumbers, this);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            ItemTouchHelper.Callback callback = new CustomerListItemHelper(adapter);
//            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//            adapter.setItemTouchHelper(itemTouchHelper);
//            itemTouchHelper.attachToRecyclerView(recyclerView);
//            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();



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


    public void onClick_Done(View view) {

        boolean checked = ((CheckBox)(view)).isChecked();

    }

    @Override
    public void onCustomerClick(int position) {
        customerNames.get(position);
    }
}
