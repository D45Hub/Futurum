package com.futurumgame.base.serialization;

import android.util.Log;

import androidx.annotation.Nullable;

import com.futurumgame.base.enums.DataSize;
import com.futurumgame.base.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ByteCompressor  {

    private final int bufferSize;

    private ByteCompressor(int bufferSize) {
        this(bufferSize, DataSize.Byte);
    }

    private ByteCompressor(int bufferSize, DataSize size){
        this.bufferSize = size.convertIn(bufferSize);
    }

    public byte[] compress(byte[] rawData) {
        if(rawData.length == 0) {
            return rawData;
        }
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        byte[] buffer = new byte[bufferSize];
        deflater.setInput(rawData);
        deflater.finish();
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while (!deflater.finished()) {
                int length = deflater.deflate(buffer);
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            Logger.e(getClass(), "error during compression returning empty array", e);
        }
        return new byte[0];
    }

    public byte[] decompress(@Nullable byte[] compressedData) {
        if(compressedData.length == 0){
            return compressedData;
        }
        Inflater inflater = new Inflater();
        byte[] buffer = new byte[bufferSize];
        inflater.setInput(compressedData);
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while (!inflater.finished()) {
                int length = inflater.inflate(buffer);
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException | DataFormatException e) {
            Logger.e(getClass(), "error during decompression returning empty array", e);
        }
        return new byte[0];
    }

    public static ByteCompressor newInstance(int bufferSize) {
        return new ByteCompressor(bufferSize);
    }

    public static ByteCompressor newInstance(int bufferSize, DataSize size) {
        return new ByteCompressor(bufferSize, size);
    }
}
