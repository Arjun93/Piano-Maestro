package com.example.arjuns.mypiano;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by arjuns on 7/4/2015.
 */
public class PianoBitmap extends ImageView implements View.OnTouchListener {
    public Context myContext;
    public SoundPool mySoundPool;
    private int[] soundId;
    private Canvas myCanvas;
    public float x = 0, y = 0;// 854x480
    public float rx;
    public float ry;
    private Handler myHandler;
    private final int FRAME_RATE = 30;
    private int touched = 0;
    Bundle bundle ;
    Bundle bundle3=new Bundle();
    int instrumentID=((Activity) getContext()).getIntent().getExtras().getInt("i");
    
    AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    
    public PianoBitmap(Context context, AttributeSet a) {
        super(context, a);
        myContext = context;
        myHandler = new Handler();
        mySoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundId=new int[26];
        bundle=new Bundle();
        bundle.putInt("apple", instrumentID);
        if(instrumentID == 10){
            soundId[0] = mySoundPool.load(getContext(), R.raw.dance_clap, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.dance_crash, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.dance_hihat, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.dance_ride, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.dance_snare, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.dance_tom1, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.dance_tom2, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.dance_tom3, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.dancekick, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.elec_crash1, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.elec_crash2, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.elec_hihatdown, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.elec_kick, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.elec_ride1, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.elec_ride2, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.dance_snare, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.elec_tom1, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.elec_tom2, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.elec_tom3, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.funk_crash, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.funk_crash2, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.funk_crash3, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.funk_tom1, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.funk_tom2, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.funk_tom3, 1);
        }
        if(instrumentID == 8){
            soundId[0] = mySoundPool.load(getContext(), R.raw.m1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.m2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.m3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.m4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.m5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.m6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.m7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.m8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.m9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.m10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.m11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.m12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.m13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.m14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.m15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.m16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.m17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.m18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.m19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.m20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.m21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.m22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.m23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.m24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.m25, 1);

        }
        else if(instrumentID == 4){
            soundId[0] = mySoundPool.load(getContext(), R.raw.trum1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.trum2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.trum3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.trum4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.trum5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.trum6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.trum7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.trum8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.trum9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.trum10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.trum11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.trum12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.trum13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.trum14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.trum15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.trum16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.trum17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.trum18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.trum19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.trum20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.trum21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.trum22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.trum23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.trum24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.trum25, 1);


        }
        else if (instrumentID==2){
            soundId[0] = mySoundPool.load(getContext(), R.raw.organ1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.organ2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.organ3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.organ4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.organ5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.organ6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.organ7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.organ8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.organ9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.organ10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.organ11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.organ12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.organ13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.organ14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.organ15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.organ16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.organ17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.organ18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.organ19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.organ20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.organ21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.organ22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.organ23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.organ24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.organ25, 1);
        }
        else if(instrumentID == 3){
            soundId[0] = mySoundPool.load(getContext(), R.raw.harp1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.harp2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.harp3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.harp4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.harp5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.harp6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.harp7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.harp8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.harp9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.harp10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.harp11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.harp12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.harp13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.harp14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.harp15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.harp16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.harp17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.harp18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.harp19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.harp20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.harp21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.harp22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.harp23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.harp24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.harp25, 1);
        }
        else if(instrumentID == 5){
            soundId[0] = mySoundPool.load(getContext(), R.raw.harmo1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.harmo2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.harmo3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.harmo4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.harmo5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.harmo6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.harmo7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.harmo8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.harmo9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.harmo10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.harmo11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.harmo12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.harmo13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.harmo14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.harmo15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.harmo16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.harmo17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.harmo18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.harmo19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.harmo20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.harmo21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.harmo22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.harmo23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.harmo24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.harmo25, 1);
        }
        else if(instrumentID == 6){
            soundId[0] = mySoundPool.load(getContext(), R.raw.flute1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.flute2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.flute3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.flute4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.flute5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.flute6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.flute7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.flute8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.flute9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.flute10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.flute11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.flute12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.flute13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.flute14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.flute15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.flute16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.flute17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.flute18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.flute19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.flute20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.flute21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.flute22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.flute23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.flute24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.flute25, 1);
            //20,25,23
        }
        else if(instrumentID == 1){
            soundId[0] = mySoundPool.load(getContext(), R.raw.vio1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.vio2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.vio3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.vio4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.vio5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.vio6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.vio7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.vio8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.vio9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.vio10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.vio11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.vio12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.vio13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.vio14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.vio15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.vio16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.vio17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.vio18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.vio19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.vio20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.vio21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.vio22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.vio23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.vio24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.vio25, 1);
        }
        else if(instrumentID == 7){
            soundId[0] = mySoundPool.load(getContext(), R.raw.s1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.s2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.s3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.s4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.s5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.s6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.s7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.s8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.s9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.s10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.s11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.s12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.s13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.s14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.s15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.s16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.s17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.s18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.s19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.s20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.s21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.s22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.s23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.s24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.s25, 1);
        }
        else if(instrumentID == 9){
            soundId[0] = mySoundPool.load(getContext(), R.raw.g1, 1);
            soundId[1] = mySoundPool.load(getContext(), R.raw.g2, 1);
            soundId[2] = mySoundPool.load(getContext(), R.raw.g3, 1);
            soundId[3] = mySoundPool.load(getContext(), R.raw.g4, 1);
            soundId[4] = mySoundPool.load(getContext(), R.raw.g5, 1);
            soundId[5] = mySoundPool.load(getContext(), R.raw.g6, 1);
            soundId[6] = mySoundPool.load(getContext(), R.raw.g7, 1);
            soundId[7] = mySoundPool.load(getContext(), R.raw.g8, 1);
            soundId[8] = mySoundPool.load(getContext(), R.raw.g9, 1);
            soundId[9] = mySoundPool.load(getContext(), R.raw.g10, 1);
            soundId[10] = mySoundPool.load(getContext(), R.raw.g11, 1);
            soundId[11] = mySoundPool.load(getContext(), R.raw.g12, 1);
            soundId[12] = mySoundPool.load(getContext(), R.raw.g13, 1);
            soundId[13] = mySoundPool.load(getContext(), R.raw.g14, 1);
            soundId[14] = mySoundPool.load(getContext(), R.raw.g15, 1);
            soundId[15] = mySoundPool.load(getContext(), R.raw.g16, 1);
            soundId[16] = mySoundPool.load(getContext(), R.raw.g17, 1);
            soundId[17] = mySoundPool.load(getContext(), R.raw.g18, 1);
            soundId[18] = mySoundPool.load(getContext(), R.raw.g19, 1);
            soundId[19] = mySoundPool.load(getContext(), R.raw.g20, 1);
            soundId[20] = mySoundPool.load(getContext(), R.raw.g21, 1);
            soundId[21] = mySoundPool.load(getContext(), R.raw.g22, 1);
            soundId[22] = mySoundPool.load(getContext(), R.raw.g23, 1);
            soundId[23] = mySoundPool.load(getContext(), R.raw.g24, 1);
            soundId[24] = mySoundPool.load(getContext(), R.raw.g24, 1);
        }
    }

    private Bitmap resizeImage( Bitmap image){
        Bitmap resized ;
        float imageHeight=image.getHeight()*ry;
        float imageWidth=image.getWidth()*rx;
        resized=Bitmap.createScaledBitmap(image,(int) imageWidth, (int)imageHeight, true);
        return resized;

    }

    private Runnable r = new Runnable() {
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        rx = (float) xNew / 854;
        ry = (float) yNew / 480;
    }

    public boolean onTouch(View v, MotionEvent event) {
        myHandler.post(new Runnable() {
            public void run() {
                PianoBitmap.this.setVisibility(View.VISIBLE);
            }
        });
        x = event.getX();
        y = event.getY();
        touched = 1;
        return false;
    }

    protected void onDraw(Canvas c) {
        PianoBitmap.this.myCanvas = c;

        if (touched == 1) {

            //white1
            if((x>0&&x<50*rx&&y>367*ry&&y<475*ry)||(x>0&&x<34*rx&&y>169*ry&&y<367*ry))
            {
                x=30*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l1);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[0], volume, volume, 1, 0 , 1f);
            }
            //black1
            if(x>34*rx&&x<57*rx&&y>169*ry&&y<367*ry)
            {
                x=53*rx;
                y=270*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b1);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[1], volume, volume, 1, 0 , 1f);
            }
            //white2
            if((x>60*rx&&x<105*rx&&y>367*ry&&y<475*ry)||(x>72*rx&&x<102*rx&&y>169*ry&&y<367*ry))
            {
                x=87*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l2);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[2], volume, volume, 1, 0 , 1f);
            }
            //black2
            if(x>100*rx&&x<139*rx&&y>169*ry&&y<367*ry)
            {
                y=268*ry;
                x=121*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b2);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[3], volume, volume, 1, 0 , 1f);
            }
            //white3
            if((x>115*rx&&x<171*rx&&y>367*ry&&y<475*ry)||(x>141*rx&&x<171*rx&&y>169*ry&&y<367*ry))
            {
                x=142*rx;
                y=319*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l3);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[4], volume, volume, 1, 0 , 1f);
            }
            //white4//174,227
            if((x>174*rx&&x<227*rx&&y>367*ry&&y<475*ry)||(x>174*rx&&x<202*rx&&y>169*ry&&y<367*ry))
            {
                x=201*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l4);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[5], volume, volume, 1, 0 , 1f);
            }
            //black 3
            if(x>202*rx&&x<rx*240&&y>169*ry&&y<367*ry)
            {
                x=221*rx;
                y=268*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b3);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[6], volume, volume, 1, 0 , 1f);
            }
            //white5
            if((x>rx*232&&x<rx*285&&y>ry*367&&y<ry*475)||(x>239*rx&&x<rx*267&&y>169*ry&&y<367*ry))
            {
                x=258*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l5);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[7], volume, volume, 1, 0 , 1f);
            }
            //black4
            if(x>rx*267&&x<rx*305&&y>169*ry&&y<367*ry)
            {	x=287*rx;
                y=268*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b4);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[8], volume, volume, 1, 0 , 1f);
            }
            //white6
            if((x>289*rx&&x<rx*342&&y>ry*367&&y<ry*475)||(x>305*rx&&x<rx*331&&y>169*ry&&y<367*ry))
            {
                y=318*ry;
                x=316*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l6);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[9], volume, volume, 1, 0 , 1f);
            }
            //black 5
            if(x>rx*333&&x<rx*370&&y>169*ry&&y<367*ry)
            {
                y=268*ry;
                x=352*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b5);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[10], volume, volume, 1, 0, 1f);
            }
            //white7
            if((x>rx*346&&x<rx*400&&y>ry*367&&y<ry*475)||(x>rx*370&&x<rx*400&&y>169*ry&&y<367*ry))
            {
                y=318*ry;
                x=372*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l7);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[11], volume, volume, 1, 0 , 1f);
            }
            //white8
            if((x>rx*401&&x<rx*456&&y>ry*367&&y<ry*475)||(x>rx*401&&x<431*rx&&y>169*ry&&y<367*ry))
            {
                y=318*ry;
                x=428*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l8);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[12], volume, volume, 1, 0 , 1f);
            }
            //black6
            if(x>rx*433&&x<469*rx&&y>ry*169&&y<ry*367)
            {
                y=268*ry;
                x=451*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b6);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[13], volume, volume, 1, 0 , 1f);
            }
            //white9
            if((x>rx*458&&x<rx*514&&y>ry*367&&y<ry*475)||(x>rx*471&&x<rx*500&&y>ry*169&&y<ry*367))
            {
                y=318*ry;
                x=485*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l9);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[14], volume, volume, 1, 0 , 1f);
            }
            //black7
            if(x>rx*501&&x<rx*537&&y>ry*169&&y<ry*367)
            {
                y=268*ry;
                x=520*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b7);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[15], volume, volume, 1, 0 , 1f);
            }
            //white10
            if((x>rx*517&&x<rx*570&&y>ry*367&&y<ry*475)||(x>rx*536&&x<rx*570&&y>ry*169&&y<ry*367))
            {
                y=318*ry;
                x=541*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l10);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[16], volume, volume, 1, 0 , 1f);
            }
            //white11
            if((x>571*rx&&x<rx*626&&y>ry*367&&y<ry*475)||(x>571*rx&&x<rx*599&&y>ry*169&&y<ry*367))
            {
                x=599*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l11);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[17], volume, volume, 1, 0 , 1f);
            }
            //black8
            if(x>rx*601&&x<rx*637&&y>ry*169&&y<ry*367)
            {
                y=268*ry;
                x=618*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b8);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[18], volume, volume, 1, 0 , 1f);
            }
            //white12
            if((x>629*rx&&x<rx*682&&y>ry*367&&y<ry*475)||(x>rx*637&&x<662*rx&&y>ry*169&&y<ry*367))
            {
                y=318*ry;
                x=654*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l12);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[19], volume, volume, 1, 0 , 1f);
            }
            //black9
            if(x>rx*665&&x<rx*701&&y>ry*169&&y<ry*367)
            {
                y=268*ry;
                x=683*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b9);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[20], volume, volume, 1, 0, 1f);
            }
            //white13
            if((x>rx*684&&x<rx*739&&y>ry*169&&y<ry*475)||(x>rx*701&&x<rx*730&&y>ry*169&&y<ry*367))
            {
                y=318*ry;
                x=713*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l13);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[21], volume, volume, 1, 0 , 1f);
            }
            //black2
            if(x>rx*728&&x<rx*766&&y>ry*169&&y<ry*367)
            {
                y=268*ry;
                x=747*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b2);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[22], volume, volume, 1, 0 , 1f);
            }
            //white14
            if((x>rx*741&&x<795*rx&&y>ry*367&&y<ry*475)||(x>766*rx&&x<rx*794&&y>ry*169&&y<ry*367))
            {
                y=318*ry;
                x=768*rx;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l14);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[23], volume, volume, 1, 0 , 1f);
            }
            //white15FINALLY@$#FDSGEE
            if(x>796*rx&&y>169*ry)
            {
                x=825*rx;
                y=318*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.l15);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[24], volume, volume, 1, 0 , 1f);
            }
            //back button or home screen button
            if(x>25*rx&&x<80*rx&&y>10*ry&&y<50*ry){
                Intent homeScreenIntent = new Intent(getContext().getApplicationContext(),HomeScreen.class);
                homeScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(homeScreenIntent);
            }
            /*myHandler.postDelayed(new Runnable() {


                public void run() {
                    PianoBitmap.this.setVisibility(View.INVISIBLE);
                }
            }, 150);*/


            touched = 0;
        }

        this.setOnTouchListener(this);
        myHandler.postDelayed(r, FRAME_RATE);


    }
}


