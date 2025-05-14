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
    private SeekBar seekBar1, seekBar2, seekBar3;
    private Boolean is_looper_menu_activated;
    private Boolean is_drum_menu_activated;
    private Boolean is_looper_play_activated;
    private TextView andromidi_txtvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.andromidi_txtvw = findViewById(R.id.andromidi_txtvw);
        this.looper_imgbtn = findViewById(R.id.looper_imgbtn);
        this.looper_play_imgbtn = findViewById(R.id.play_loop_imgbtn);
        this.drum_imgbtn = findViewById(R.id.drum_imgbtn);
        this.seekBar1 = findViewById(R.id.seekBar1);
        this.seekBar2 = findViewById(R.id.seekBar2);
        this.seekBar3 = findViewById(R.id.seekBar3);
        this.is_looper_menu_activated = false;
        this.is_drum_menu_activated = false;
        this.is_looper_play_activated = false;
        this.device = new MatriboxIIPro(this.getBaseContext());
        this.device.connectToMatribox();
        this.andromidi_txtvw.setText(this.device.manufacturer + " " + this.device.product);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        andromidi_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                device = new MatriboxIIPro(getBaseContext());
                device.connectToMatribox();
                andromidi_txtvw.setText(device.manufacturer + " " + device.product);
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

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
    }

}