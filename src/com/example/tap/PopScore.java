package com.example.tap;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.TextView;

public class PopScore extends Activity{
	String data;
	TextView _score, _fecha, _califa;
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_score);
        ActionBar bar = this.getActionBar();
        bar.hide();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        
        this.getWindow().setLayout((int)(width * .8),(int)(height * .8));
        
        _score = (TextView) findViewById(R.id.tvActualScore);
        _fecha = (TextView) findViewById(R.id.tvFecha);
        _califa = (TextView) findViewById(R.id.tvResultado);
        
           data = getIntent().getStringExtra("data");	
           
           String[] myArray = data.split(" ");  
           
        _score.setText(myArray[0]);
        _fecha.setText(myArray[2]);
        
       int score = Integer.parseInt(myArray[0]);
       
        
        if (score >= 0  && score <= 20) {
			_califa.setText("Little or no burden");
		}
        if (score >= 21  && score <= 40) {
			_califa.setText("Mild to moderate burden ");
		}
        if (score >= 41  && score <= 60) {
			_califa.setText("Moderate to severe burden ");
		}
        if (score >= 61  && score <= 88) {
			_califa.setText("Severe burden ");
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
