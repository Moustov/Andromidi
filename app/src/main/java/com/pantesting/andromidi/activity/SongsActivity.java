package com.pantesting.andromidi.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class SongsActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100; // Choisissez un nombre entier unique
    public List<Song> songs = null;

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

        ListView listView = findViewById(R.id.songs_lv);
        checkPermissions_andLoadSongs();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (songs != null) {
                    String selectedItem = songs.get(position).getSong();
                    Toast.makeText(SongsActivity.this, "Vous avez cliqué sur: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPermissions_andLoadSongs() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission non accordée
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Afficher une explication à l'utilisateur
                Toast.makeText(this, "Cette permission est nécessaire pour accéder aux fichiers.", Toast.LENGTH_LONG).show();
                openMobileAuthorization();
            } else {
                // Aucune explication n'est nécessaire, on peut demander la permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            // Permission déjà accordée, continuez avec votre logique
            accessFiles();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Appel à la méthode super

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée
                accessFiles();
            } else {
                // Permission refusée
                Toast.makeText(this, "Permission refusée.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void accessFiles() {
        Toast.makeText(this, "Accès aux fichiers accordé!", Toast.LENGTH_SHORT).show();
        try {
            songs = loadSongsFromJSON();
            if (songs != null) {
                // Utilisez votre adapter pour afficher les chansons
                SongAdapter adapter = new SongAdapter(this, this.songs);
                ListView listView = findViewById(R.id.songs_lv);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Erreur lors du chargement des chansons", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Problème pendant le chargement des chansons : " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void openMobileAuthorization() {
        Toast.makeText(SongsActivity.this, "Vous devez activer la permission depuis les paramètres", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private List<Song> loadSongsFromJSON() throws IOException {
        // Logique pour charger les chansons depuis un fichier
        // Assurez-vous que vous avez les permissions nécessaires ici
        String jsonBuilder = this.get_file_content_on_sdcard("Andromidi", "effects.json");
        Gson gson = new GsonBuilder().create();
        SongsResponse response = gson.fromJson(jsonBuilder, SongsResponse.class);
        return response.getSongs();
    }

    public String get_file_content_on_sdcard(String dir_path, String file_name) throws IOException {
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

        StringBuilder file_content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                file_content.append(line);
                Log.d("Line", line);
            }
        }
        return file_content.toString();
    }
}