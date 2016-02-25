package com.sist.client;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Clip clip;
	
	public final static String lgSound="sound\\�������-�α���.wav";
	public final static String wrSound="sound\\�������-����.wav";
	public final static String gmSound="sound\\�������-�ΰ���.wav";
	public final static String choiceNa="sound\\��������â.wav";
	public final static String bell="sound\\¡�Ҹ�.wav";
	
	public final static String winSd="sound\\����-�¸�.wav";
	public final static String loseSd="sound\\����-�й�.wav";
	
	public final static String wrclick="sound\\����Ŭ��.wav";
	public final static String endBtn="sound\\������.wav";
	
	public final static String btnAtt="sound\\��ų-����.wav";
	public final static String attImg="sound\\��ų-�����̹���.wav";
	public final static String attCore="sound\\��ų-�����ʻ��.wav";
	
	public final static String btnDef="sound\\��ų-���.wav";
	public final static String defImg="sound\\��ų-����̹���.wav";
	public final static String defCore="sound\\��ų-����ʻ��.wav";
	
	public final static String btnTrik="sound\\��ų-å��.wav";
	public final static String trikImg="sound\\��ų-å���̹���.wav";
	public final static String trikCore="sound\\��ų-å���ʻ��.wav";
	
	public final static String iconCl="sound\\��ų-������Ŭ��.wav";
	
	
	
	
	public static void Sound(String file, boolean Loop){
	    try {
	      	AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));

	      	clip = AudioSystem.getClip();
	      	clip.open(ais);
	      	clip.start();

	      	if (Loop) {
	      		clip.loop(-1);//�ݺ����
	      	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
