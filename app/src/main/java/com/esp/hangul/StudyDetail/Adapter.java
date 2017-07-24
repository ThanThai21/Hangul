package com.esp.hangul.StudyDetail;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esp.hangul.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder>{

    private Context context;
    private List<Item> itemList;
    private MediaPlayer mediaPlayer;

    public Adapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.study_detail_row_item, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.korean.setText(item.getKorean());
        holder.pronuncation.setText("[" + item.getPronuncation() + "]");
        holder.vietnamese.setText(item.getVietnamese());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMp3(item.getSound());
            }
        });
    }

    private void playMp3(byte[] mp3SoundByteArray) {
        try {
            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("kurchina", "mp3", context.getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(mp3SoundByteArray);
            fos.close();

            // resetting mediaplayer instance to evade problems
            mediaPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView korean;
        TextView pronuncation;
        TextView vietnamese;

        public ItemViewHolder(View itemView) {
            super(itemView);
            korean = (TextView) itemView.findViewById(R.id.korean);
            pronuncation = (TextView) itemView.findViewById(R.id.pronuncation);
            vietnamese = (TextView) itemView.findViewById(R.id.vietnamese);
        }
    }
}
