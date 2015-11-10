package com.akin8529.soundandvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.*;

public class SoundActivity extends AppCompatActivity
{
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    private Button videoButton;
    private MediaPlayer soundPlayer;
    private SeekBar soundSeekBar;
    private Thread soundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        startButton = (Button) findViewById(R.id.playButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
        videoButton = (Button) findViewById(R.id.videoButton);
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.pokemon);

        setupListeners();

        soundThread = new Thread(this);
        soundThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sound, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListeners()
    {
        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                soundPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                soundPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View currentView)
            {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(), r.raw.pokemon)
            }
        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {}
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    soundPlayer.seekTo(progress);
                }
            }
        });
    }

}
