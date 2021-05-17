package com.futurumgame.base.enums;

public enum DataSize {
    Byte(0), KiloByte(10), MegaByte(20), GigaByte(30), TerraByte(40);

    private final int bitShift;

    private DataSize(int bitShift) {
        this.bitShift = bitShift;
    }

    public final int convertIn(int value) {
        if (bitShift == 40 || (value << bitShift) < 0) {
            throw new IllegalArgumentException();
        }
        return Integer.rotateLeft(value, bitShift);
    }

    public final long convertIn(long value) {
        if ((value << bitShift) < 0) {
            throw new IllegalArgumentException();
        }
        return Long.rotateLeft(value, bitShift);
    }
}
