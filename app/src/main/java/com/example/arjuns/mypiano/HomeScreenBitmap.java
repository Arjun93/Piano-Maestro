package com.example.arjuns.mypiano;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by arjuns on 7/3/2015.
 */
public class HomeScreenBitmap extends ImageView implements View.OnTouchListener {
    public Context myContext;
    public Intent pianoIntent;
    private Canvas myCanvas;
    public Thread t;
    public float x = 0, y = 0;// 854x480
    public float rx;
    public float ry;
    private Handler myHandler;
    private final int FRAME_RATE = 30;
    private int touched = 0;
    Bundle myBundle;
    int i=0;

    public HomeScreenBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        myContext = context;
        myHandler = new Handler();
        myBundle = new Bundle();
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
        ry = (float) yNew / 418;
    }

    public Bitmap resizeImage(Bitmap image) {
        Bitmap resized;
        float imageHeight, imageWidth;
        imageHeight=image.getHeight()*ry;
        imageWidth=image.getWidth()*rx;
        resized=Bitmap.createScaledBitmap(image, (int) imageWidth, (int)imageHeight, true);
        return resized;
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        myHandler.post(new Runnable() {
            public void run() {
                HomeScreenBitmap.this.setVisibility(View.VISIBLE);
            }
        });

        x = event.getX();
        y = event.getY();
        touched = 1;
        return false;
    }
    protected void onDraw(Canvas c) {
        HomeScreenBitmap.this.myCanvas = c;
        if (touched==1){
            if(x>12*rx&&x<169*rx&&y>12*ry&&y<165*ry)
            {
                i=1;
                x = 91.5f*rx;
                y = 90*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkviolinbutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>185*rx&&x<338*rx&&y>12*ry&&y<165*ry){
                i=2;
                x=260*rx;
                y=90*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkorganbutton2);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>352*rx&&x<507*rx&&y>12*ry&&y<165*ry){
                i=3;
                x=431*rx;
                y=90*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkharpbutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>526*rx&&x<680*rx&&y>12*ry&&y<165*ry){
                i=4;
                x=601.5f*rx;
                y=90*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darktrumphetbutton3);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>692*rx&&x<848*rx&&y>12*ry&&y<165*ry){
                i=5;
                x=772*rx;
                y=90*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkharmobutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }

            else if(x>12*rx&&x<169*rx&&y>191*ry&&y<349*ry)
            {
                i=6;
                x=91*rx;
                y=270*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkflutebutton2);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>185*rx&&x<338*rx&&y>191*ry&&y<349*ry)
            {
                i=7;
                x=262*rx;
                y=270*ry;
                this.setVisibility(View.VISIBLE);
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darksaxbutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>352*rx&&x<507*rx&&y>191*ry&&y<349*ry){
                i=8;
                x=431*rx;
                y=269.5f*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkpianobutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }

            else if(x>526*rx&&x<680*rx&&y>191*ry&&y<349*ry)
            {
                i=9;
                x=601.5f*rx;
                y=270*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkguitarbutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            else if(x>692*rx&&x<848*rx&&y>191*ry&y<349*ry)
            {
                i=10;
                x=771*rx;
                y=270*ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.darkdrumbutton);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                myCanvas.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth()
                        / 2, y - scaledBitmap.getHeight() / 2, null);
                myBundle.putInt("i", i);
                pianoIntent = (Intent) new Intent(getContext(),Piano.class);
                pianoIntent.putExtras(myBundle);
                pianoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(pianoIntent);
            }
            touched=0;
            }
        myHandler.postDelayed(r, FRAME_RATE);
        this.setOnTouchListener(this);

    }
}