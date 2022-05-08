package com.example.juanmartinezleongreenflag;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<FeatureItem> {

    public ListAdapter(Context context, ArrayList<FeatureItem> featureItemArrayList) {
        super(context, R.layout.feature_item,R.id.tvFeatureItem, featureItemArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        FeatureItem feature = getItem(position);
        Log.d("featureA", feature.description);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feature_item, parent, false);
        }

        TextView tvItem = convertView.findViewById(R.id.tvFeatureItem);

        tvItem.setText(feature.description);

        return convertView;
    }
}
