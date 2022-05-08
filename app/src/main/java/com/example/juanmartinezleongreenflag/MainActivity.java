package com.example.juanmartinezleongreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.juanmartinezleongreenflag.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //region Variable declaration
    private ActivityMainBinding binding;
    //endregion

    //region Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getFeatures();
        disableViewListScroll(); //to avoid nested scroll
        setupListener();
    }
    //endregion

    //region program logic
    private void getFeatures() {

        String[] features = {"Car health updates", "Request a rescue online", "PolicyInformation"};

        ArrayList<FeatureItem> featureArrayList = new ArrayList<>();

        for (String feature: features){
            FeatureItem featureObj = new FeatureItem(feature);
            featureArrayList.add(featureObj);
        }
        ListAdapter listAdapter = new ListAdapter(MainActivity.this, featureArrayList);

        binding.lvFeatures.setAdapter(listAdapter);
    }

    private void disableViewListScroll() {
        View listView = findViewById(R.id.lvFeatures);
        listView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }

    private void setupListener() {
        View btn = findViewById(R.id.btn_create_account);
        btn.setOnClickListener(View -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
    //endregion
}