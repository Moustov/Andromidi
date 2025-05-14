package com.pantesting.andromidi;

import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public class SongsActivity extends AppCompatActivity {

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
        List<Song> songs = loadSongsFromJSON();

        if (songs != null) {
            // Utilisez votre adapter pour afficher les chansons
            SongAdapter adapter = new SongAdapter(this, songs);
            //ListView listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Erreur lors du chargement des chansons", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Song> loadSongsFromJSON() {
        File directory = new File(getFilesDir(), "Andromidi");
        File file = new File(directory, "effects.json");
        StringBuilder jsonBuilder = new StringBuilder();
        Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        // Vérifiez si le fichier existe
        if (!file.exists()) {
            Log.e("FileError", "Le fichier n'existe pas : " + file.getAbsolutePath());
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Utiliser Gson pour parser le contenu JSON
            Gson gson = new GsonBuilder().create();
            SongsResponse response = gson.fromJson(jsonBuilder.toString(), SongsResponse.class);
            return response.getSongs();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur " + e.toString(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}