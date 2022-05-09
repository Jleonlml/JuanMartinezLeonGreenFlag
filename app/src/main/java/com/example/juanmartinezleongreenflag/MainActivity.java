package com.example.juanmartinezleongreenflag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juanmartinezleongreenflag.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //region Variable declaration
    private ActivityMainBinding binding;
    private View btn;
    //endregion

    //region Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn = findViewById(R.id.btn_create_account);

        getFeatures();
        disableViewListScroll(); //to avoid nested scroll
        setupListener();
        setupAnimations();
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
        btn.setOnClickListener(View -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
    //endregion

    //region animations
    private void setupAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        FragmentContainerView logoFrag = findViewById(R.id.fragment_container_view);
        logoFrag.startAnimation(anim);

        Animation animLeftRight = AnimationUtils.loadAnimation(this, R.anim.leftto_right_anim);
        TextView textAnim = findViewById(R.id.textToAnim);
        textAnim.startAnimation(animLeftRight);

        Animation animLeftRightDelay = AnimationUtils.loadAnimation(this, R.anim.leftto_right_anim_delay);
        ListView list = findViewById(R.id.lvFeatures);
        list.startAnimation(animLeftRightDelay);

        Animation fadeAnimDelay = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim_delay);
        btn.startAnimation(fadeAnimDelay);
    }
    //endregion
}