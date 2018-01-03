package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;

import java.util.List;

/**
 * Created by Oliva on 02/01/2018.
 */

public class SpinnerAdapter extends ArrayAdapter<KeyValuePair> {

    private Activity activity;
    private List<KeyValuePair> objects;


    public SpinnerAdapter(Activity activity, @LayoutRes int textViewResourceId, List<KeyValuePair> objects) {
        super(activity, textViewResourceId, objects);
        this.objects = objects;
        this.activity = activity;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();//for fragment
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);

        KeyValuePair map = objects.get(position);
        if (Tools.isNotNull(map.getKey())) {
            TextView tvKey = row.findViewById(R.id.spinner_key);
            Object key = map.getKey();
            tvKey.setText(key.toString());
        }
        if (Tools.isNotNull(map.getKey())) {
            TextView tvLabel = row.findViewById(R.id.spinner_value);
            String value = map.getLabel();
            tvLabel.setText(value);
        }
        return row;
    }
}
