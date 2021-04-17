package com.futurumgame.base.serialization;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.exceptions.FileTooLargeException;
import com.futurumgame.base.gameinternals.GameRoutine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class AndroidFileHelper {

    private static AndroidFileHelper singleton;

    private final Context context;

    private AndroidFileHelper(Context context) {
        this.context = context;
    }

    private File getFile(String fileName){
        return new File(context.getFilesDir(), fileName);
    }

    public static void init(MainActivity activity) {
        if(singleton != null) {
            return;
        }
        singleton = new AndroidFileHelper(activity);
    }

    public static byte[] read(String fileName) throws IOException {
        File file = singleton.getFile(fileName);
        if(!file.exists()) {
            Log.i(AndroidFileHelper.class.getSimpleName(), "file does not exist, assuming it hasn't been created yet");
            return new byte[0];
        }
        int fileLength = (int)file.length();
        if(fileLength < 0){
            throw new FileTooLargeException();
        }
        byte[] data = new byte[fileLength];
        try (FileInputStream fStream = new FileInputStream(file)) {
            for (int i = 0; i < fileLength; i++){
                data[i] = (byte) fStream.read();
            }
        }
        return data;
    }

    public static void save(String fileName, byte[] data) throws IOException {
        File file = singleton.getFile(fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            saveSDK26AndUp(file, data);
            return;
        }
        saveDefault(file, data);
    }

    private static void saveDefault(File file, byte[] data) throws IOException {
        try (FileOutputStream fStream = new FileOutputStream(file)) {
          fStream.write(data);
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private static void saveSDK26AndUp(File file, byte[] data) throws IOException {
        Files.write(file.toPath(), data, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
