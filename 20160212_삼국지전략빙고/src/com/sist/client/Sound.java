package com.sist.client;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Clip clip;
	public final static String lgSound="sound\\배경음악-로그인.wav";
	public final static String wrSound="sound\\배경음악-대기실.wav";
	public final static String gmSound="sound\\배경음악-인게임.wav";
	
	
	public static void Sound(String file, boolean Loop){
	    try {
	      	AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));

	      	clip = AudioSystem.getClip();
	      	clip.open(ais);
	      	clip.start();

	      	if (Loop) {
	      		clip.loop(-1);//반복재생
	      	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
