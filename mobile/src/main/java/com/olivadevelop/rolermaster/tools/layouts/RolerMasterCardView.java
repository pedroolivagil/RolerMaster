package com.olivadevelop.rolermaster.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.ImagePicasso;

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

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        id_card_view = findViewById(R.id.id_card_view);
        title_card_view = findViewById(R.id.title_card_view);
        descript_card_view = findViewById(R.id.descript_card_view);
        image_card_view = findViewById(R.id.image_card_view);

        setId_card_view(a.getString(R.styleable.RolerMasterCardView_idGameCardView));
        setTitle_card_view(a.getString(R.styleable.RolerMasterCardView_titleGameCardView));
        setDescript_card_view(a.getString(R.styleable.RolerMasterCardView_descriptionGameCardView));
        setImage_card_view(a.getString(R.styleable.RolerMasterCardView_imageGameCardView));
        setActive(!a.getBoolean(R.styleable.RolerMasterCardView_disableCardView, false));
        a.recycle();
        setEnable();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        setEnable();
    }

    public TextView getId_card_view() {
        return id_card_view;
    }

    public void setId_card_view(Integer id_card_view) {
        if (Tools.isNotNull(id_card_view)) {
            this.id_card_view.setText(String.valueOf(id_card_view));
        }
    }

    public void setId_card_view(String id_card_view) {
        if (Tools.isNotNull(id_card_view)) {
            this.id_card_view.setText(id_card_view);
        }
    }

    public TextView getTitle_card_view() {
        return title_card_view;
    }

    public void setTitle_card_view(String title_card_view) {
        if (Tools.isNotNull(title_card_view)) {
            this.title_card_view.setText(title_card_view);
        }
    }

    public TextView getDescript_card_view() {
        return descript_card_view;
    }

    public void setDescript_card_view(String descript_card_view) {
        if (Tools.isNotNull(descript_card_view)) {
            this.descript_card_view.setText(descript_card_view);
        }
    }

    public ImageView getImage_card_view() {
        return image_card_view;
    }

    public void setImage_card_view(String fileUrl) {
        if (Tools.isNotNull(image_card_view)) {
            ImagePicasso.load(this.getContext(), fileUrl, this.image_card_view);
        }
    }

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