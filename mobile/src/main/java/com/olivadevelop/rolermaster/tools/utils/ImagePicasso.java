package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 16/01/2018.
 * RolerMaster
 */
public class ImagePicasso {
    private static final ImagePicasso ourInstance = new ImagePicasso();

    public static ImagePicasso getInstance() {
        return ourInstance;
    }

    private ImagePicasso() {
    }

    public static void load(Context c, File url, ImageView view) {
        load(c, url, view, new RoundedTransformationBuilder().build());
    }

    public static void load(Context c, String url, ImageView view) {
        load(c, url, view, new RoundedTransformationBuilder().build());
    }

    public static void load(Context c, String url, ImageView view, Transformation transformation) {
        Picasso.with(c).load(url).noFade().fit().transform(transformation).centerCrop()
                .placeholder(R.drawable.image_area_alpha)
                .error(R.drawable.image_broken_variant_alpha)
                .into(view);
    }

    public static void load(Context c, File url, ImageView view, Transformation transformation) {
        Picasso.with(c).load(url).noFade().fit().transform(transformation).centerCrop()
                .placeholder(R.drawable.image_area_alpha)
                .error(R.drawable.image_broken_variant_alpha)
                .into(view);
    }

    public static void loadWithoutTransform(Context c, String url, ImageView view, float[] sizes) {
        float deseado = Tools.convertPixelsToDp(c, 400);
        Picasso.with(c).load(url).noFade().placeholder(R.drawable.image_area_alpha)
                .error(R.drawable.image_broken_variant_alpha)
                .resize((int) deseado, (int) resizeImg(sizes[0], sizes[1], deseado))
                .centerCrop().into(view);
    }

    private static float resizeImg(float anchoOriginal, float altoOriginal, float anchoDeseado) {
        return (anchoDeseado * altoOriginal) / anchoOriginal;
    }


}
