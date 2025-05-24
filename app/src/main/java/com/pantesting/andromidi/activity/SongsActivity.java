package com.pantesting.andromidi.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pantesting.andromidi.R;
import com.pantesting.andromidi.midi.MatriboxIIPro;
import com.pantesting.andromidi.song.Song;
import com.pantesting.andromidi.song.SongAdapter;
import com.pantesting.andromidi.song.SongsResponse;

import java.io.*;
import java.util.List;

public class SongsActivity extends AppCompatActivity  implements ActivityCompat.OnRequestPermissionsResultCallback{
    public static final int REQUEST_CODE = 100; // Choisissez un nombre entier unique
    public static List<Song> songs = null;
    private ActivityResultLauncher<Intent> openDocumentLauncher;
    private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;
    private int bank_id = 0;

    private MatriboxIIPro device;


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
        this.device.set_context(this.getBaseContext(), null, null);
        this.device.connectToMatribox();
        ListView listView = findViewById(R.id.songs_lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (songs != null) {
                    String song = songs.get(position).getSong();
                    int new_bank_id = songs.get(position).getBankId();
//                    device.connectToMatribox();
                    for (int i=0 ; i<bank_id ;i++){
                        device.sendBankPrevWaitMode();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for (int i=0 ; i<new_bank_id ;i++){
                        device.sendBankNextWaitMode();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    bank_id = new_bank_id;
//                    device.sendBank(position);
                    Snackbar.make(view, "BankID " + bank_id, Snackbar.LENGTH_SHORT).show();
//                    defineSoundEffect(song);
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

    private Song findSongByName(List<Song> songs, String songName) {
        for (Song song : songs) {
            if (song.getSong().equals(songName)) {
                return song; // Retourne l'objet Song correspondant
            }
        }
        return null; // Retourne null si aucune chanson n'a été trouvée
    }

    private void defineSoundEffect(String selected_song){
        Toast.makeText(SongsActivity.this, "Vous avez cliqué sur: " + selected_song, Toast.LENGTH_SHORT).show();
        Song setting = this.findSongByName(songs, selected_song);


        for(int ctrl_id=1; ctrl_id<=4 ; ctrl_id++) {
            String effect = setting.getCtrl(ctrl_id);
            if (effect != null) {
                Toast.makeText(SongsActivity.this, "Effet #" + ctrl_id + ": " + effect, Toast.LENGTH_SHORT).show();
                try {
                    device.setEffect(ctrl_id, effect, getApplicationContext());
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    Toast.makeText(SongsActivity.this, "Erreur sur " + effect + "("+e.toString()+")", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

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