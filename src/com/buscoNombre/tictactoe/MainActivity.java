package com.buscoNombre.tictactoe;

import com.buscoNombre.tictactoe.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	
	protected TextView textView01;
	protected TextView textView02;
	protected TextView textView03;
	protected TextView textView04;
	protected TextView textView05;
	protected View layout1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface MouseDrawn = Typeface.createFromAsset(getAssets(), "Mousedrawn.otf");
		
		textView01= (TextView) findViewById (R.id.textView01);
		textView01.setTypeface(MouseDrawn);
		
		textView02= (TextView) findViewById (R.id.textView02);
		textView02.setTypeface(MouseDrawn);
		
		textView03= (TextView) findViewById (R.id.textView03);
		textView03.setTypeface(MouseDrawn);
		
		Typeface Cheve = Typeface.createFromAsset(getAssets(), "Cheveuxdange.ttf");
		
		textView04= (TextView) findViewById (R.id.textView04);
		textView04.setTypeface(Cheve);
		
		textView05= (TextView) findViewById (R.id.textView05);
		textView05.setTypeface(Cheve);

	}

	public void irGato1 (View view)
	{
		Intent e = new Intent (this, Dificultad.class);
		startActivity (e);
	}
	
	public void irGato2 (View view)
	{
		Intent i = new Intent (this, Gato.class);
		i.putExtra("jugadores", 2);
		startActivity (i);
		
	}
	
}
