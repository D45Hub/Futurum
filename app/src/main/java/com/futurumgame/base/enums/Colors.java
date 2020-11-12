package com.futurumgame.base.enums;

import android.content.res.ColorStateList;
import android.graphics.Color;

public enum Colors {
    Black(0x13,0x13,0x13), White(0xE5, 0xE5, 0xE5), Green(0x50,0xE5, 0x7D), Red(0xDA,0x16,0x16);

    private final int color;

    private Colors(int r, int g, int b) {
        this(r, g, b, 255);
    }

    private Colors(int r, int g, int b, int a) {
        color = Color.argb(a, r, g, b);
    }

    public int getColor() {
        return color;
    }
}
