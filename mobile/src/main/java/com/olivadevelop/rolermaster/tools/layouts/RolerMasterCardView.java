package com.olivadevelop.rolermaster.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;

/**
 * TODO: document your custom view class.
 */
public class RolerMasterCardView extends LinearLayout {
/*
    private TextView tvTitle;
    private TextView tvSubtitle;
    private boolean active;*/
    private TextView id_card_view;
    private TextView title_card_view;
    private TextView descript_card_view;
    private ImageView image_card_view;
    private boolean active;

    public RolerMasterCardView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RolerMasterCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RolerMasterCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RolerMasterCardView, defStyle, 0);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_roler_master_card, this, true);

       /* tvTitle = findViewById(R.id.item_opt_settings_title);
        tvSubtitle = findViewById(R.id.item_opt_settings_subtitle);

        if (Tools.isNotNull(tvTitle)) {
            tvTitle.setText(a.getString(R.styleable.ItemSettingsView_titleItemSettings));
        }
        if (Tools.isNotNull(tvSubtitle)) {
            tvSubtitle.setText(a.getString(R.styleable.ItemSettingsView_subtitleItemSettings));
        }
        active = !a.getBoolean(R.styleable.ItemSettingsView_disableItemSettings, false);*/
        a.recycle();
        setEnable();
    }

   /* public TextView getTvTitle() {
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
        setEnable();
    }*/

    public void setEnable() {
       /* if (active) {
            tvSubtitle.setTextColor(getResources().getColor(R.color.darkGray));
            tvTitle.setTextColor(getResources().getColor(R.color.darkGray));
        } else {
            tvTitle.setTextColor(getResources().getColor(R.color.clearGray));
            tvSubtitle.setTextColor(getResources().getColor(R.color.clearGray));
        }*/
    }
}