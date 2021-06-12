package com.futurumgame.base.enums;

import android.util.Log;

import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.interfaces.IDataProvider;
import com.futurumgame.base.serialization.AndroidFileHelper;
import com.futurumgame.base.serialization.ByteCompressor;

import java.io.IOException;


public enum DataFile {

    FactorySystem("factorysystem", GameRoutine.getFactorySystem()),
    ResourceUnlockables("resource_unlock", GameRoutine.getResourceUnlockables()),
    WareHouse("stocks", GameRoutine.getWareHouse());

    private static final String ReadTag = "File Reading";
    private static final String SaveTag = "File Saving";
    private static final String ReadFileMessageFormat = "reading file: {0}";
    private static final String SaveFileMessageFormat = "saving file: {0}";
    private static final String ClearFileMessageFormat = "clearing file: {0}";
    private static final String FileEnding = ".ftrm";
    private static final ByteCompressor Compressor = ByteCompressor.newInstance(4, DataSize.KiloByte);

    private final String fileName;
    private final IDataProvider provider;

    DataFile(String fileName, IDataProvider provider) {
        this.fileName = fileName + FileEnding;
        this.provider = provider;
    }

    public byte[] read() {
        byte[] compressedData;
        try {
            compressedData = AndroidFileHelper.read(fileName);
        } catch (IOException e) {
            Log.e(ReadTag, String.format(ReadFileMessageFormat, fileName), e);
            compressedData = new byte[0];
        }
        return Compressor.decompress(compressedData);
    }

    public void save() {
        byte[] rawData = provider.provideData();
        byte[] compressed = Compressor.compress(rawData);
        try {
            AndroidFileHelper.save(fileName, compressed);
        } catch (IOException e) {
            Log.e(SaveTag, String.format(SaveFileMessageFormat, fileName), e);
        }
    }

    public void clear() {
        try {
            AndroidFileHelper.save(fileName, new byte[0]);
        } catch (IOException e) {
            Log.e(SaveTag, String.format(ClearFileMessageFormat, fileName), e);
        }
    }
}
