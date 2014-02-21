package com.buscoNombre.tictactoe;

import com.buscoNombre.tictactoe.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class Dificultad extends Activity 
{
	protected TextView tvFacil;
	protected TextView tvMedio;
	protected TextView tvImposible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dificultad);
		
		tvFacil= (TextView) findViewById (R.id.tvFacil);
		tvMedio = (TextView) findViewById (R.id.tvMedio);
		tvImposible= (TextView) findViewById (R.id.tvImposible);
		
		Typeface MouseDrawn = Typeface.createFromAsset(getAssets(), "Mousedrawn.otf");
		
		tvFacil.setTypeface(MouseDrawn);
		tvMedio.setTypeface(MouseDrawn);
		tvImposible.setTypeface(MouseDrawn);
		
	}

	
	public void irGatoFacil (View view)
	{
		Intent i = new Intent (this, Gato.class);
		i.putExtra("jugadores", 1);
		startActivity (i);
	}
	
	public void irGatoMed (View view)
	{
		Intent i = new Intent (this, Gato.class);
		i.putExtra("jugadores", 3);
		startActivity (i);
	}
	
	public void irGatoImp (View view)
	{
		Intent i = new Intent (this, Gato.class);
		i.putExtra("jugadores", 4);
		startActivity (i);
	}
}
