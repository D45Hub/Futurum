package com.futurumgame.base.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import androidx.annotation.StringRes;

public class UiUtil {

    private UiUtil() {
    }

    public static PointF makeRelativeTo(PointF point, PointF ofs) {
        return new PointF(ofs.x + point.x, ofs.y - point.y);
    }

    public static PointF add(PointF first, PointF second) {
        return new PointF(first.x + second.x, first.y + second.y);
    }

    public static float makeRelativeUnit(Canvas canvas) {
        return Math.min(canvas.getWidth(), canvas.getHeight())*0.1f;
    }

    public static float scaleByRelativeUnit(Canvas canvas, float value){
        return makeRelativeUnit(canvas)*value;
    }
}
