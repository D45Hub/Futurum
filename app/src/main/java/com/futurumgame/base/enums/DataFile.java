package com.futurumgame.base.enums;

import android.util.Log;

import com.futurumgame.base.interfaces.IDataCreator;
import com.futurumgame.base.serialization.AndroidFileHelper;
import com.futurumgame.base.serialization.ByteCompressor;
import com.futurumgame.base.serialization.datacreation.FactorySystemDataCreator;
import com.futurumgame.base.serialization.datacreation.UnlockDataCreator;
import com.futurumgame.base.serialization.datacreation.WareHouseDataCreator;

import java.io.FileNotFoundException;
import java.io.IOException;


public enum DataFile {

    FactorySystem("factorysystem", FactorySystemDataCreator.Creator),
    Unlockables("unlock", UnlockDataCreator.Creator),
    WareHouse("stocks", WareHouseDataCreator.Creator);

    private static final String ReadTag = "File Reading";
    private static final String SaveTag = "File Saving";
    private static final String ReadFileMessageFormat = "reading file: {0}";
    private static final String SaveFileMessageFormat = "saving file: {0}";
    private static final String FileEnding = ".ftrm";
    private static final ByteCompressor Compressor = ByteCompressor.newInstance(4, DataSize.KiloByte);

    private final String fileName;
    private final IDataCreator<?> dataCreator;

    DataFile(String fileName, IDataCreator<?> dataCreator) {
        this.fileName = fileName+FileEnding;
        this.dataCreator = dataCreator;
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
        byte[] rawData = dataCreator.createData();
        byte[] compressed = Compressor.compress(rawData);
        try {
            AndroidFileHelper.save(fileName, compressed);
        } catch (IOException e) {
            Log.e(SaveTag, String.format(SaveFileMessageFormat, fileName), e);
        }
    }
}
