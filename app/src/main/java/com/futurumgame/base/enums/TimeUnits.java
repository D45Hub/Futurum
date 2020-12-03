package com.futurumgame.base.enums;

public enum TimeUnits {

    Millisecond(1L), Second(1000L),
    Minute(60000L), Hour(3600000L),
    Day(86400000L);

    private final long timeInMilliseconds;

    private TimeUnits(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public long inThisUnit(long count) {
        return count * timeInMilliseconds;
    }

    public long convertFrom(long time, TimeUnits unit) {
        return time * (unit.timeInMilliseconds / timeInMilliseconds);
    }

    public long convertTo(long time, TimeUnits unit) {
        return time * (timeInMilliseconds / unit.timeInMilliseconds);
    }
}
