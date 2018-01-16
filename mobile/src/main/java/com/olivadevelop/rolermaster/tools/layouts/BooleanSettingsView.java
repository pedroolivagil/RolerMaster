package com.olivadevelop.rolermaster.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;

/**
 * TODO: document your custom view class.
 */
public class BooleanSettingsView extends LinearLayout implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvSubtitle;
    private Switch swValue;
    private boolean active;

    public BooleanSettingsView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BooleanSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BooleanSettingsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BooleanSettingsView, defStyle, 0);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_boolean_option_settings, this, true);

        tvTitle = findViewById(R.id.boolean_opt_settings_title);
        tvSubtitle = findViewById(R.id.boolean_opt_settings_subtitle);
        swValue = findViewById(R.id.boolean_opt_settings_value);

        if (Tools.isNotNull(tvTitle)) {
            tvTitle.setText(a.getString(R.styleable.BooleanSettingsView_titleBooleanSettings));
            tvTitle.setOnClickListener(this);
        }
        if (Tools.isNotNull(tvSubtitle)) {
            tvSubtitle.setText(a.getString(R.styleable.BooleanSettingsView_subtitleBooleanSettings));
            tvSubtitle.setOnClickListener(this);
        }
        active = !a.getBoolean(R.styleable.BooleanSettingsView_disableSwitchBooleanSettings, false);
        a.recycle();
        setEnable();
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvSubtitle() {
        return tvSubtitle;
    }

    public void setTvSubtitle(TextView tvSubtitle) {
        this.tvSubtitle = tvSubtitle;
    }

    public Switch getSwValue() {
        return swValue;
    }

    public void setSwValue(Switch swValue) {
        this.swValue = swValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        setEnable();
    }

    public void setEnable() {
        if (active) {
            swValue.setClickable(true);
            swValue.setEnabled(true);
            tvSubtitle.setTextColor(getResources().getColor(R.color.darkGray));
            tvTitle.setTextColor(getResources().getColor(R.color.darkGray));
            tvTitle.setOnClickListener(this);
            tvSubtitle.setOnClickListener(this);
        } else {
            swValue.setClickable(false);
            swValue.setEnabled(false);
            tvTitle.setTextColor(getResources().getColor(R.color.clearGray));
            tvSubtitle.setTextColor(getResources().getColor(R.color.clearGray));
            tvTitle.setOnClickListener(null);
            tvSubtitle.setOnClickListener(null);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvSubtitle || view == tvTitle) {
            toogle();
        }
    }

    private void toogle() {
        if (swValue.isChecked()) {
            swValue.setChecked(false);
        } else {
            swValue.setChecked(true);
        }
    }
}
