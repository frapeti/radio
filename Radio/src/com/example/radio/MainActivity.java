package com.example.radio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText url_text = (EditText) findViewById(R.id.url_text);

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		Button playButton = (Button) findViewById(R.id.playButton);

		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
			}

		});

		playButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					if (mediaPlayer.isPlaying()) {
						mediaPlayer.reset();
					}

					String url = url_text.getText().toString(); // your URL here

					mediaPlayer.setDataSource(url);
					mediaPlayer.prepareAsync();

				} catch (Exception e) {
					Log.e("MainActivity", e.getLocalizedMessage() + "");
					Toast.makeText(MainActivity.this, e.getLocalizedMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		mediaPlayer.release();
	}

}
