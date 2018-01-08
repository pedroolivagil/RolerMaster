package com.olivadevelop.rolermaster.tools.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 08/01/2018.
 */

public class CustomList<T> extends ArrayList<T> {

    public CustomList() {
    }

    public CustomList(int initialCapacity) {
        super(initialCapacity);
    }

    public CustomList(@NonNull Collection<? extends T> c) {
        super(c);
    }

    public int lastPosition() {
        int pos = -1;
        if (!isEmpty()) {
            pos = size() - 1;
        }
        return pos;
    }

    public T last() {
        T last = null;
        if (lastPosition() >= 0) {
            last = get(lastPosition());
        }
        return last;
    }
}
