package com.futurumgame.base.enums;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public enum DataEncoding {
    UTF8(StandardCharsets.UTF_8), UTF16(StandardCharsets.UTF_16);

    private final Charset charset;

    private DataEncoding(Charset charset) {
        this.charset = charset;

    }

    public String decode(byte[] data) {
        return new String(data, charset);
    }

    public byte[] encode(String data) {
        return data.getBytes(charset);
    }
}
