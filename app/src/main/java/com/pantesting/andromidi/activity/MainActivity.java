package com.pantesting.andromidi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.snackbar.Snackbar;
import com.pantesting.andromidi.midi.MatriboxIIPro;
import com.pantesting.andromidi.R;
import com.pantesting.andromidi.midi.MidiCCListenerThread;

public class MainActivity extends AppCompatActivity {

    private Button songs_btn;
    private ImageButton looper_imgbtn, looper_play_imgbtn, del_loop_imgbtn;
    private ImageButton drum_imgbtn, tap_imgbtn, play_drum_imgbtn;
    private ImageButton midi_imgbtn;
    private ImageButton prev_preset_imgbtn, next_preset_imgbtn;
    private SeekBar knob1_sb, knob2_sb, knob3_sb, volume_preset_sb;
    public TextView song_txtvw, bank_id_txtvw;
    private Boolean is_looper_menu_activated;
    private Boolean is_drum_menu_activated;
    private Boolean is_looper_play_activated;
    private Boolean is_drum_play_activated;
    private TextView midi_device_txtvw;
//    private TextView version_txtvw;
    private MidiCCListenerThread midiThread;
    private int previous_bank_id = 0;
    private int new_bank_id = 0;
    private String song_title = "";
    private int bpm = 0;
    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                new_bank_id = data.getIntExtra("RESULT_SELECTED_SONG_BANK_ID", 0);
                                song_title = data.getStringExtra("RESULT_SELECTED_SONG_TITLE");
                                bpm = data.getIntExtra("RESULT_SELECTED_SONG_BPM", 0);
                                song_txtvw.setText(song_title);

                                bank_id_txtvw.setText("" + new_bank_id + " - (" + bpm + " bpm)");
                                Log.d("MainActivity", "new_bank_id : " + new_bank_id);
                                Log.d("MainActivity", "song_title : " + song_title);
                                Log.d("MainActivity", "bpm : " + bpm);
                                for (int i=0 ; i<(previous_bank_id-1) ; i++){
                                    MatriboxIIPro.sendBankPrevWaitMode();
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                for (int i=0 ; i<(new_bank_id-1) ;i++){
                                    MatriboxIIPro.sendBankNextWaitMode();
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                previous_bank_id = new_bank_id;
                            }
                        }
                    });

    public void openSongsActivity() {
        Intent intent = new Intent(MainActivity.this, SongsActivity.class);
        resultLauncher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.midi_device_txtvw = findViewById(R.id.midi_device_txtvw);
        this.song_txtvw = findViewById(R.id.song_title_txtvw);
        this.bank_id_txtvw = findViewById(R.id.bank_id_txtvw);
        this.midi_imgbtn = findViewById(R.id.midi_imgbtn);
        this.tap_imgbtn = findViewById(R.id.tap_imgbtn);
        this.songs_btn = findViewById(R.id.songs_btn);
        this.looper_imgbtn = findViewById(R.id.looper_imgbtn);
        this.looper_play_imgbtn = findViewById(R.id.play_loop_imgbtn);
        this.drum_imgbtn = findViewById(R.id.drum_imgbtn);
        this.play_drum_imgbtn = findViewById(R.id.play_drum_imgbtn);
        this.prev_preset_imgbtn = findViewById(R.id.prev_preset_imgbtn);
        this.next_preset_imgbtn = findViewById(R.id.next_preset_imgbtn);
        this.del_loop_imgbtn = findViewById(R.id.del_loop_imgbtn);
        this.knob1_sb = findViewById(R.id.seekBar1);
        this.knob2_sb = findViewById(R.id.seekBar2);
        this.knob3_sb = findViewById(R.id.seekBar3);
        this.volume_preset_sb = findViewById(R.id.volume_preset);
//        this.version_txtvw = findViewById(R.id.andromidi_version_txtvw);
//        this.version_txtvw.setText(BuildConfig.VERSION_NAME);
        this.is_looper_menu_activated = false;
        this.is_drum_menu_activated = false;
        this.is_looper_play_activated = false;
        this.is_drum_play_activated = false;
        View rootView = findViewById(android.R.id.content);
        MatriboxIIPro.set_context(this.getApplicationContext(), rootView);
        MatriboxIIPro.connectToMatribox();
        this.midi_device_txtvw.setText(MatriboxIIPro.manufacturer + " " + MatriboxIIPro.product + "\n" + MatriboxIIPro.serial_number);

//        FileManager.copyAssets(this.getApplicationContext());
//        FileManager.listCopiedFiles_internal(this.getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        songs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongsActivity();
            }
        });
        bank_id_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBpm(v);
            }
        });
        del_loop_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDeleteLoop(v);
            }
        });
        prev_preset_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBankPrevWaitMode(v);
            }
        });
        next_preset_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBankNextWaitMode(v);
            }
        });
        tap_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTap(v);
            }
        });
        midi_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMidiDevice(v);
            }
        });
        midi_device_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMidiDevice(v);
            }
        });
        looper_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togleLooperScreen(v);
            }
        });
        play_drum_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togleDrumPlaying(v);
            }
        });
        looper_play_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togleLoopPlaying(v);
            }
        });
        drum_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togleDrumScreen(v);
            }
        });
        knob1_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Action à réaliser lorsque la valeur change
                MatriboxIIPro.sendKnobValue(1, progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
        });
        knob2_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Action à réaliser lorsque la valeur change
                MatriboxIIPro.sendKnobValue(2, progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
        });
        knob3_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Action à réaliser lorsque la valeur change
                MatriboxIIPro.sendKnobValue(3, progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
        });
        volume_preset_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Action à réaliser lorsque la valeur change
                MatriboxIIPro.sendPresetVolume(progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
        });
        // deviceInfo est ton MidiDeviceInfo ciblé
        if (MatriboxIIPro.my_device != null) {
            this.midiThread = new MidiCCListenerThread(this.getApplicationContext(), MatriboxIIPro.my_device, this.song_txtvw, this.bank_id_txtvw);
            this.midiThread.start();
        }
        // Rendre l'écran toujours allumé tant que cette activité est visible
        View view = findViewById(R.id.main); // Remplacez par l'ID de votre vue
        view.setKeepScreenOn(true);
    }

    public void sendBpm(View v){
        MatriboxIIPro.sendPresetSync_on();
        MatriboxIIPro.sendPresetBpm(bpm);
        Snackbar.make(v, "Tempo set to " + bpm + " bpm", Snackbar.LENGTH_SHORT).show();
    }

    public void sendDeleteLoop(View v){
        MatriboxIIPro.sendLoop_delete();
        Snackbar.make(v, "Loop deleted", Snackbar.LENGTH_SHORT).show();
    }

    public void sendBankPrevWaitMode(View v){
        MatriboxIIPro.sendBankPrevWaitMode();
        Snackbar.make(v, "prev preset", Snackbar.LENGTH_SHORT).show();
    }

    public void sendBankNextWaitMode(View v){
        MatriboxIIPro.sendBankNextWaitMode();
        Snackbar.make(v, "next preset", Snackbar.LENGTH_SHORT).show();
    }

    public void sendTap(View v){
        MatriboxIIPro.sendTap();
        Snackbar.make(v, "tap", Snackbar.LENGTH_SHORT).show();
    }

    public void togleLoopPlaying(View v){
        if (is_looper_play_activated) {
            MatriboxIIPro.sendLooperPlay_Off();
            looper_play_imgbtn.setImageResource(R.drawable.looper_play_off);
            is_looper_play_activated = false;
//                    Toast.makeText(getApplicationContext(), "Looper play off", Toast.LENGTH_SHORT).show();
            Snackbar.make(v, "Looper play off", Snackbar.LENGTH_SHORT).show();
        }
        else{
            MatriboxIIPro.sendLooperPlay_On();
            looper_play_imgbtn.setImageResource(R.drawable.looper_play_on);
            is_looper_play_activated = true;
            Snackbar.make(v, "Looper play on", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Looper play on", Toast.LENGTH_SHORT).show();
        }
    }

    public void togleDrumScreen(View v){
        if (is_drum_menu_activated) {
            MatriboxIIPro.sendDeactivateDrumMenu();
            drum_imgbtn.setImageResource(R.drawable.drum_menu_off);
            is_drum_menu_activated = false;
            Snackbar.make(v, "Drum menu off", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Drum menu off", Toast.LENGTH_SHORT).show();
        }
        else{
            MatriboxIIPro.sendActivateDrumMenu();
            drum_imgbtn.setImageResource(R.drawable.drum_menu_on);
            is_drum_menu_activated = true;
            looper_imgbtn.setImageResource(R.drawable.looper_menu_off);
            is_looper_menu_activated = false;
            Snackbar.make(v, "Drum menu on", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Drum menu on", Toast.LENGTH_SHORT).show();
        }

    }
    public void togleDrumPlaying(View v){
        if (is_drum_play_activated) {
            MatriboxIIPro.sendDrumPlay_On();
            play_drum_imgbtn.setImageResource(R.drawable.drum_play_off);
            is_drum_play_activated = false;
            Snackbar.make(v, "Drum play off", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Drum play off", Toast.LENGTH_SHORT).show();
        }
        else{
            MatriboxIIPro.sendDrumPlay_Off();
            play_drum_imgbtn.setImageResource(R.drawable.drum_play_on);
            is_drum_play_activated = true;
            Snackbar.make(v, "Drum play on", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Drum play on", Toast.LENGTH_SHORT).show();
        }

    }
    public void togleLooperScreen(View v){
        if (is_looper_menu_activated) {
            MatriboxIIPro.sendActivateLooperMenu();
            looper_imgbtn.setImageResource(R.drawable.looper_menu_off);
            is_looper_menu_activated = false;
            Snackbar.make(v, "Looper menu off", Snackbar.LENGTH_SHORT).show();
        }
        else{
            MatriboxIIPro.sendDeactivateLooperMenu();
            looper_imgbtn.setImageResource(R.drawable.looper_menu_on);
            is_looper_menu_activated = true;
            drum_imgbtn.setImageResource(R.drawable.drum_menu_off);
            is_drum_menu_activated = false;
            Snackbar.make(v, "Looper menu on", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Optionnel : pour s'assurer que le comportement est réinitialisé
        View view = findViewById(R.id.main);
        view.setKeepScreenOn(false);
    }

    public void updateMidiDevice(View v){
        // Pour arrêter le thread plus tard :
        if (midiThread != null) {
            midiThread.interrupt();
        }
        if(MatriboxIIPro.my_device != null) {
            midiThread = new MidiCCListenerThread(getApplicationContext(), MatriboxIIPro.my_device, this.song_txtvw, this.bank_id_txtvw);
            midiThread.start();
        }

        MatriboxIIPro.set_context(getApplicationContext(), v);
        MatriboxIIPro.connectToMatribox();
        midi_device_txtvw.setText(MatriboxIIPro.manufacturer + " " + MatriboxIIPro.product);
        Snackbar.make(v, "MatriboxIIPro device updated", Snackbar.LENGTH_SHORT).show();
    }
}