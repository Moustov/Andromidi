package com.pantesting.andromidi;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MatriboxIIPro device;
    private ImageButton looper_imgbtn;
    private ImageButton looper_play_imgbtn;
    private ImageButton drum_imgbtn;
    private ImageButton tap_imgbtn, prev_preset_imgbtn;
    private SeekBar knob1_sb, knob2_sb, knob3_sb, volume_preset_sb;
    private Boolean is_looper_menu_activated;
    private Boolean is_drum_menu_activated;
    private Boolean is_looper_play_activated;
    private TextView midi_device_txtvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.midi_device_txtvw = findViewById(R.id.midi_device_txtvw);
        this.tap_imgbtn = findViewById(R.id.tap_imgbtn);
        this.looper_imgbtn = findViewById(R.id.looper_imgbtn);
        this.looper_play_imgbtn = findViewById(R.id.play_loop_imgbtn);
        this.drum_imgbtn = findViewById(R.id.drum_imgbtn);
        this.prev_preset_imgbtn = findViewById(R.id.prev_preset_imgbtn);
        this.knob1_sb = findViewById(R.id.seekBar1);
        this.knob2_sb = findViewById(R.id.seekBar2);
        this.knob3_sb = findViewById(R.id.seekBar3);
        this.volume_preset_sb = findViewById(R.id.volume_preset);
        this.is_looper_menu_activated = false;
        this.is_drum_menu_activated = false;
        this.is_looper_play_activated = false;
        this.device = new MatriboxIIPro(this.getBaseContext());
        this.device.connectToMatribox();
        this.midi_device_txtvw.setText(this.device.manufacturer + " " + this.device.product);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prev_preset_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                device.sendPresetSync_off();
            }
        });
        tap_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                device.sendTap();
            }
        });
        midi_device_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                device = new MatriboxIIPro(getBaseContext());
                device.connectToMatribox();
                midi_device_txtvw.setText(device.manufacturer + " " + device.product);
            }
        });

        looper_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_looper_menu_activated) {
                    device.sendActivateLooperMenu();
                    looper_imgbtn.setImageResource(R.drawable.looper_menu_off);
                    is_looper_menu_activated = false;
                }
                else{
                    device.sendDeactivateLooperMenu();
                    looper_imgbtn.setImageResource(R.drawable.looper_menu_on);
                    is_looper_menu_activated = true;
                    drum_imgbtn.setImageResource(R.drawable.drum_menu_off);
                    is_drum_menu_activated = false;
                }
            }
        });

        looper_play_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_looper_play_activated) {
                    device.sendLooperPlay_Off();
                    looper_play_imgbtn.setImageResource(R.drawable.looper_play);
                    is_looper_play_activated = false;
                }
                else{
                    device.sendLooperPlay();
                    looper_play_imgbtn.setImageResource(R.drawable.looper_play_on);
                    is_looper_play_activated = true;
                }
            }
        });

        drum_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_drum_menu_activated) {
                    device.sendDeactivateDrumMenu();
                    drum_imgbtn.setImageResource(R.drawable.drum_menu_off);
                    is_drum_menu_activated = false;
                }
                else{
                    device.sendActivateDrumMenu();
                    drum_imgbtn.setImageResource(R.drawable.drum_menu_on);
                    is_drum_menu_activated = true;
                    looper_imgbtn.setImageResource(R.drawable.looper_menu_off);
                    is_looper_menu_activated = false;
                }
            }
        });

        knob1_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Action à réaliser lorsque la valeur change
                device.sendKnobValue(1, progress);
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
                device.sendKnobValue(2, progress);
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
                device.sendKnobValue(3, progress);
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
                device.sendPresetVolume(progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action à réaliser lorsque la valeur change
            }
        });
    }

}