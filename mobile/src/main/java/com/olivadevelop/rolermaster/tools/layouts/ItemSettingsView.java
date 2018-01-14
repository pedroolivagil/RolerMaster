package com.olivadevelop.rolermaster.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;

/**
 * TODO: document your custom view class.
 */
public class ItemSettingsView extends LinearLayout implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvSubtitle;
    private boolean active;

    public ItemSettingsView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ItemSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ItemSettingsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ItemSettingsView, defStyle, 0);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_option_settings_layout, this, true);

        tvTitle = findViewById(R.id.item_opt_settings_title);
        tvSubtitle = findViewById(R.id.item_opt_settings_subtitle);

        if (Tools.isNotNull(tvTitle)) {
            tvTitle.setText(a.getString(R.styleable.ItemSettingsView_titleItemSettings));
            tvTitle.setOnClickListener(this);
        }
        if (Tools.isNotNull(tvSubtitle)) {
            tvSubtitle.setText(a.getString(R.styleable.ItemSettingsView_subtitleItemSettings));
            tvSubtitle.setOnClickListener(this);
        }
        active = !a.getBoolean(R.styleable.ItemSettingsView_disableItemSettings, false);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEnable() {
        if (active) {
            tvSubtitle.setTextColor(getResources().getColor(R.color.darkGray));
            tvTitle.setTextColor(getResources().getColor(R.color.darkGray));
            tvTitle.setOnClickListener(this);
            tvSubtitle.setOnClickListener(this);
        } else {
            tvTitle.setTextColor(getResources().getColor(R.color.clearGray));
            tvSubtitle.setTextColor(getResources().getColor(R.color.clearGray));
            tvTitle.setOnClickListener(null);
            tvSubtitle.setOnClickListener(null);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvSubtitle || view == tvTitle) {
            actionsItem();
        }
    }

    public void actionsItem() {

    }
}
