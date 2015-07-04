package com.example.arjuns.mypiano;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

/**
 * Created by arjuns on 7/4/2015.
 */
public class Piano extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_screen);
        Drawable drawable;
        FrameLayout myFrameLayout = (FrameLayout)findViewById(R.id.pianoFrame);
        myFrameLayout.setOnTouchListener((PianoBitmap) findViewById(R.id.img_view1));
    }


    //@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

}
