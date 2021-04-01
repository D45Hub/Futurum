package com.futurumgame.base.enums;

import android.annotation.SuppressLint;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.INotationFormatter;
import com.futurumgame.base.normedimplementations.NormedNotationFormatter;

import java.lang.reflect.Method;

@SuppressLint("DefaultLocale")
public enum Notation {
    Scientific(u -> {
        return String.format("%.2fe%.0f", u.getValue(), u.getScale());
    }), Engineering(u->{
        int scale = (int)(u.getScale()%3);
        return String.format("%.2fe%.0f", u.getValue()*Math.pow(10,scale), u.getScale()-scale);
    }), Logarithmic(u->{
        return String.format("e%.2f", Math.log10(u.getValue())+u.getScale());
    });

    private INotationFormatter formatter;

    private Notation(INotationFormatter formatter) {
        this.formatter = formatter;
    }

    public String applyNotationFormat(Units units) {
        String normedFormat = NormedNotationFormatter.Singleton.format(units);
        if(normedFormat!= null){
            return normedFormat;
        }
        return formatter.format(units);
    }
}
