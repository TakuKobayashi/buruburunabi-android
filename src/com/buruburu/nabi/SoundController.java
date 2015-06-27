package com.buruburu.nabi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.location.Location;
import android.location.LocationListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SoundController extends ContextSingletonBase<SoundController>{

  private HashMap<String, MediaPlayer> mSoundList;
  private MediaPlayer mSound;

  public void init(Context context){
    super.init(context);
    mSoundList = new HashMap<String, MediaPlayer>();
  }

  public int addSound(String fileName){
    mSound = new MediaPlayer();
    try {
      AssetFileDescriptor afd = context.getAssets().openFd(fileName);
      mSound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
      afd.close();
      mSound.prepare();
      mSoundList.put(fileName, mSound);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mSoundList.size();
  }

  public void playCurrentSound(){
    mSound.start();
  }

  public void pauseCurrentSound(){
    mSound.pause();
  }

  public void stopCurrentSound(){
    mSound.stop();
  }

  public void release(){
    for(MediaPlayer sound : mSoundList.values()){
      sound.stop();
      sound.release();
    }
    mSoundList.clear();
    mSound = null;
  }
}