package com.pantesting.andromidi.song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.pantesting.andromidi.R;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    private final Context context;
    private final List<Song> songs;
    public SongAdapter(Context context, List<Song> songs) {
        super(context, R.layout.item_layout, songs);
        this.context = context;
        this.songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }

        TextView songTextView = convertView.findViewById(R.id.song_tvi);
        TextView ctrl1TextView = convertView.findViewById(R.id.ctrl1_tvi);
        TextView ctrl2TextView = convertView.findViewById(R.id.ctrl2_tvi);
        TextView ctrl3TextView = convertView.findViewById(R.id.ctrl3_tvi);
        TextView ctrl4TextView = convertView.findViewById(R.id.ctrl4_tvi);
        TextView bpmTextView = convertView.findViewById(R.id.bpm_tvi);

        Song item = this.songs.get(position);
        songTextView.setText(item.getSong().toUpperCase() + " (#" + item.getBankId() + ")");
        if (position % 2 == 0) {
            songTextView.setBackgroundColor(0x00C5FF8F);
        }
        else {
            songTextView.setBackgroundColor(0x00000000);
        }
        ctrl1TextView.setText(item.getCtrl1());
        ctrl2TextView.setText(item.getCtrl2());
        ctrl3TextView.setText(item.getCtrl3());
        ctrl4TextView.setText(item.getCtrl4());
        bpmTextView.setText(item.getBpm() + " bpm");
        return convertView;
    }
}