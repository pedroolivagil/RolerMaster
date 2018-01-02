package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.Entity;

import java.util.List;

/**
 * Created by Oliva on 02/01/2018.
 */

public class SpinnerAdapter extends ArrayAdapter<Entity> {

    private Activity activity;
    private List<com.olivadevelop.rolermaster.persistence.Entity> objects;


    public SpinnerAdapter(Activity activity, int textViewResourceId, List<Entity> objects) {
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
        final TextView label = (TextView) row.findViewById(R.id.tv_spinnervalue);
        label.setText(objects.get(position).toString());
        return row;
    }
}
