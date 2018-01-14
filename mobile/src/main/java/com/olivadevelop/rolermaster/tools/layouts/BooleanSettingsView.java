package com.olivadevelop.rolermaster.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;

/**
 * TODO: document your custom view class.
 */
public class BooleanSettingsView extends LinearLayout {


    private TextView tvTitle;
    private TextView tvSubtitle;
    private Switch swValue;

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
        inflater.inflate(R.layout.boolean_option_settings_layout, this, true);

        tvTitle = findViewById(R.id.boolean_opt_settings_title);
        tvSubtitle = findViewById(R.id.boolean_opt_settings_subtitle);
        swValue = findViewById(R.id.boolean_opt_settings_value);

        if (Tools.isNotNull(tvTitle)) {
            tvTitle.setText(a.getString(R.styleable.BooleanSettingsView_titleBooleanSettings));
        }
        if (Tools.isNotNull(tvSubtitle)) {
            tvSubtitle.setText(a.getString(R.styleable.BooleanSettingsView_subtitleBooleanSettings));
        }
        a.recycle();
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
}
