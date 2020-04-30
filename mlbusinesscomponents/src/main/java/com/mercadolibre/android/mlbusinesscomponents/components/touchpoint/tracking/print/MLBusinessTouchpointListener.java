package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.MLBusinessTouchpointView;

public final class MLBusinessTouchpointListener {

    private static final int ACTION_UP = MotionEvent.ACTION_UP;

    private final TouchpointViewFinder finder;
    @Nullable private TouchpointChildPrintable printable;

    public MLBusinessTouchpointListener() {
        finder = new TouchpointViewFinder(new Rect());
    }

    /**
     * Reset history of tracked prints
     */
    public void resetTrackedPrints() {
        if (printable != null) {
            printable.cleanHistory();
        }
    }

    /**
     * Do print if view is visible
     *
     * @param container A {@link ViewGroup}
     */
    public void print(final ViewGroup container) {
        printable = finder.find(container);
        if (printable != null) {
            printable.print();
        }
    }

    /**
     * Listen the event {@value ACTION_UP} of the container
     *
     * @param container A {@link ViewGroup}
     */
    public void setOnTouchListener(final ViewGroup container) {
        print(container);
        container.setOnTouchListener(this::isActionUp);
    }

    private boolean isActionUp(final View viewGroup, final MotionEvent event) {
        if (event.getAction() == ACTION_UP) {
            print((ViewGroup) viewGroup);
        }
        return false;
    }
}
