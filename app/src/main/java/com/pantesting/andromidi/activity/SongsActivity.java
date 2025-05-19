package com.pantesting.andromidi.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pantesting.andromidi.R;
import com.pantesting.andromidi.song.Song;
import com.pantesting.andromidi.song.SongAdapter;
import com.pantesting.andromidi.song.SongsResponse;

import java.io.*;
import java.util.List;

public class SongsActivity extends AppCompatActivity  implements ActivityCompat.OnRequestPermissionsResultCallback{
    public static final int REQUEST_CODE = 100; // Choisissez un nombre entier unique
    public static List<Song> songs = null;
    private ActivityResultLauncher<Intent> openDocumentLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_songs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        copyAssets();
        ListView listView = findViewById(R.id.songs_lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (songs != null) {
                    String selectedItem = songs.get(position).getSong();
                    Toast.makeText(SongsActivity.this, "Vous avez cliqué sur: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }
        });

        openDocumentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            readFile(uri);
                        }
                        else{
                            Toast.makeText(SongsActivity.this, "Problème sur le nom de fichier sélectionné", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SongsActivity.this, "Problème sur le sélecteur de documents", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        this.openFile();
    }

    public void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
            for (String filename : files) {
                InputStream in = assetManager.open(filename);
                String outFileName = getFilesDir() + "/" + filename; // ou un autre chemin
                OutputStream out = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                in.close();
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/json"); // Type de fichier JSON
        openDocumentLauncher.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_DOCUMENT && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                readFile(uri);
            }
        }
    }

    private void readFile(Uri uri) {
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonContent = stringBuilder.toString();
            Gson gson = new GsonBuilder().create();
            SongsResponse response = gson.fromJson(jsonContent, SongsResponse.class);
            songs = response.getSongs();
            if (songs != null) {
                // Utilisez votre adapter pour afficher les chansons
                SongAdapter adapter = new SongAdapter(this, songs);
                ListView listView = findViewById(R.id.songs_lv);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Erreur lors du chargement des chansons", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de la lecture du fichier.", Toast.LENGTH_SHORT).show();
        }
    }
}