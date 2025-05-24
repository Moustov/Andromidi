package com.pantesting.andromidi.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
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
    public TextView usb_in_txtvw, usb_out_txtvw;
    private Boolean is_looper_menu_activated;
    private Boolean is_drum_menu_activated;
    private Boolean is_looper_play_activated;
    private Boolean is_drum_play_activated;
    private TextView midi_device_txtvw;
//    private TextView version_txtvw;
    private MidiCCListenerThread midiThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.midi_device_txtvw = findViewById(R.id.midi_device_txtvw);
        this.usb_in_txtvw = findViewById(R.id.midi_device_in_txtvw);
        this.usb_out_txtvw = findViewById(R.id.midi_device_out_txtvw);
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
        MatriboxIIPro.set_context(this.getApplicationContext(), this.usb_in_txtvw, this.usb_out_txtvw);
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
                // Créer une intention pour ouvrir la seconde activité
                Intent intent = new Intent(MainActivity.this, SongsActivity.class);
                startActivity(intent);
            }
        });
        del_loop_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatriboxIIPro.sendLoop_delete();
                Snackbar.make(v, "Loop deleted", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Loop deleted", Toast.LENGTH_SHORT).show();
            }
        });
        prev_preset_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatriboxIIPro.sendBankPrevWaitMode();
                Snackbar.make(v, "prev preset", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "prev preset", Toast.LENGTH_SHORT).show();
//                MatriboxIIPro.sendPresetSync_off();
            }
        });
        next_preset_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatriboxIIPro.sendBankNextWaitMode();
                Snackbar.make(v, "next preset", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "next preset", Toast.LENGTH_SHORT).show();
            }
        });
        tap_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatriboxIIPro.sendTap();
                Snackbar.make(v, "tap", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "tap", Toast.LENGTH_SHORT).show();
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
                if (is_looper_menu_activated) {
                    MatriboxIIPro.sendActivateLooperMenu();
                    looper_imgbtn.setImageResource(R.drawable.looper_menu_off);
                    is_looper_menu_activated = false;
                    Snackbar.make(v, "Looper menu off", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Looper menu off", Toast.LENGTH_SHORT).show();
                }
                else{
                    MatriboxIIPro.sendDeactivateLooperMenu();
                    looper_imgbtn.setImageResource(R.drawable.looper_menu_on);
                    is_looper_menu_activated = true;
                    drum_imgbtn.setImageResource(R.drawable.drum_menu_off);
                    is_drum_menu_activated = false;
                    Snackbar.make(v, "Looper menu on", Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "Looper menu on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        play_drum_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        looper_play_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        drum_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            this.midiThread = new MidiCCListenerThread(this.getApplicationContext(), MatriboxIIPro.my_device, this.usb_in_txtvw, this.usb_out_txtvw);
            this.midiThread.start();
        }
    }

    public void updateMidiDevice(View v){
        // Pour arrêter le thread plus tard :
        if (midiThread != null) {
            midiThread.interrupt();
        }
        if(MatriboxIIPro.my_device != null) {
            midiThread = new MidiCCListenerThread(getApplicationContext(), MatriboxIIPro.my_device, this.usb_in_txtvw, this.usb_out_txtvw);
            midiThread.start();
        }

        MatriboxIIPro.set_context(getApplicationContext(), this.usb_in_txtvw, this.usb_out_txtvw);
        MatriboxIIPro.connectToMatribox();
        midi_device_txtvw.setText(MatriboxIIPro.manufacturer + " " + MatriboxIIPro.product);
        Snackbar.make(v, "MatriboxIIPro device updated", Snackbar.LENGTH_SHORT).show();
    }
}