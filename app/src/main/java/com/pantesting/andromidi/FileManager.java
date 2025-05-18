package com.pantesting.andromidi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.jetbrains.annotations.NotNull;

import java.io.*;

import static android.os.Environment.MEDIA_MOUNTED;
import static android.os.Environment.MEDIA_MOUNTED_READ_ONLY;

public class FileManager {
    public static final int REQUEST_CODE = 100; // Choisissez un nombre entier unique
    public static boolean permission_granted = false;
    public static void ask_permission(Activity activity, Context my_context){
        if (ContextCompat.checkSelfPermission(my_context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            FileManager.permission_granted = true;
        }
    }

    public StringBuilder get_file_content_on_sdcard(String dir_path, String file_name) throws IOException {
        File file = getFile(dir_path, file_name);

        StringBuilder file_content = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                file_content.append(line);
                Log.d("Line", line);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Error reading file: " + e.getMessage());
        }

        return file_content;
    }

    private static @NotNull File getFile(String dir_path, String file_name) throws IOException {
        File sdCard = Environment.getExternalStorageDirectory();
        String sdCard_status = Environment.getExternalStorageState();

        if (!sdCard_status.equals(Environment.MEDIA_MOUNTED) &&
                !sdCard_status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            throw new IOException("SD card not mounted correctly");
        }

        File dir = new File(sdCard.getAbsolutePath() + "/" + dir_path);
        File file = new File(dir, file_name);

        if (!file.exists()) {
            throw new IOException("File does not exist: " + file.getAbsolutePath());
        }
        return file;
    }}
