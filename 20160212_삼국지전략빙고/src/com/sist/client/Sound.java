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
	public final static String choiceNa="sound\\진영선택창.wav";
	public final static String bell="sound\\징소리.wav";
	
	public final static String winSd="sound\\승패-승리.wav";
	public final static String loseSd="sound\\승패-패배.wav";
	
	public final static String wrclick="sound\\대기실클릭.wav";
	public final static String endBtn="sound\\마무리.wav";
	
	public final static String btnAtt="sound\\스킬-공격.wav";
	public final static String attImg="sound\\스킬-공격이미지.wav";
	public final static String attCore="sound\\스킬-공격필살기.wav";
	
	public final static String btnDef="sound\\스킬-방어.wav";
	public final static String defImg="sound\\스킬-방어이미지.wav";
	public final static String defCore="sound\\스킬-방어필살기.wav";
	
	public final static String btnTrik="sound\\스킬-책략.wav";
	public final static String trikImg="sound\\스킬-책략이미지.wav";
	public final static String trikCore="sound\\스킬-책략필살기.wav";
	
	public final static String iconCl="sound\\스킬-아이콘클릭.wav";
	
	
	
	
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
