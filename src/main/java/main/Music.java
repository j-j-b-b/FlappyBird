package main;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	Clip clip;
	InputStream[] sound=new InputStream[30];
	public Music() throws FileNotFoundException {
		sound[1]=new FileInputStream("Musics/¥Ûçu•ﬂ•¡•Î - Éû§∑§§π‚.wav");
		sound[0]=new FileInputStream("Musics/Toby Fox - sans..wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(new BufferedInputStream(sound[i]));
			clip=AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    public void play() {
		clip.start();
	}
    public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
    public void stop() {
		clip.stop();
	}
 

}
