package com.sample.util;

public enum ChannelType {
    SPORTS(1), KIDS(2), MUSIC(3), NEWS(4), MOVIES(5), INVALID(-1);

    private int code;

    private ChannelType(int c) {
        code = c;
    }

    public int getCode() {
        return code;
    }
}
