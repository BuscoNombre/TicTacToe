package com.buscoNombre.tictactoe;

import java.util.Random;

import com.buscoNombre.tictactoe.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Gato extends Activity {
	
	protected TextView tvTitulo1;
	protected TextView tvTitulo2;
	protected int jugadores;
	
	protected ImageView ivGato;
	
	protected ImageView ivO1;
	protected ImageView ivO2;
	protected ImageView ivO3;
	protected ImageView ivO4;
	protected ImageView ivO5;
	protected ImageView ivO6;
	protected ImageView ivO7;
	protected ImageView ivO8;
	protected ImageView ivO9;
	
	protected int aa=1;
	protected int ab=1;
	protected int ac=1;
	protected int ba=1;
	protected int bb=1;
	protected int bc=1;
	protected int ca=1;
	protected int cb=1;
	protected int cc=1;
	
	protected int turno=1;
	protected int turnoInicial=2;
	int ganador=1;
	
	protected Drawable imgCirculo;
	protected Drawable imgVacio;
	protected Drawable imgEquis;
	
	protected TextView tvGanaO;
	protected TextView tvGanaX;
	protected TextView tvGanaCPU;
	protected TextView tvEmpate;
	protected RelativeLayout layoutr1;
	
	protected int marO=0;
	protected int marX=0;
	
	protected TextView tvPnJ1;
	protected TextView tvPnJ2;
	
	protected TextView tvMarJ1;
	protected TextView tvMarJ2;
	protected TextView tvMarCPU;
	
	protected int tire=0;
	protected int tirado=0;
	protected int setiro=0;
	
	protected int tirojug=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gato);
		
		Bundle bundle = getIntent().getExtras();
		jugadores = bundle.getInt("jugadores");
		imgCirculo = this.getResources().getDrawable(R.drawable.circulo2);
		imgVacio=this.getResources().getDrawable(R.drawable.vacio);
		imgEquis = this.getResources().getDrawable(R.drawable.equis);
		tvGanaO = (TextView) findViewById (R.id.tvGanaO);
		tvGanaX = (TextView) findViewById (R.id.tvGanaX);
		tvGanaCPU = (TextView) findViewById (R.id.tvGanaCPU);
		tvEmpate = (TextView) findViewById (R.id.tvEmpate);
		layoutr1 = (RelativeLayout) findViewById (R.id.layoutr1);
		Typeface MouseDrawn = Typeface.createFromAsset(getAssets(), "Mousedrawn.otf");
		tvGanaO.setTypeface(MouseDrawn);
		tvGanaX.setTypeface(MouseDrawn);
		tvGanaCPU.setTypeface(MouseDrawn);
		tvEmpate.setTypeface(MouseDrawn);
		
		tvPnJ1= (TextView) findViewById (R.id.tvPnJ1);
		tvPnJ2= (TextView) findViewById (R.id.tvPnJ2);
		
		tvMarJ1 = (TextView) findViewById (R.id.tvMarJ1);
		tvMarJ2 = (TextView) findViewById (R.id.tvMarJ2);
		tvMarCPU = (TextView) findViewById (R.id.tvMarCPU);
		
		tvPnJ1.setTypeface(MouseDrawn);
		tvPnJ2.setTypeface(MouseDrawn);
		tvMarJ1.setTypeface(MouseDrawn);
		tvMarJ2.setTypeface(MouseDrawn);
		tvMarCPU.setTypeface(MouseDrawn);
		
		cambioTitulo (jugadores);
		actualizaMarcador ();
		
	}
	
	public void onWindowFocusChanged (boolean hasFocus)
	{
		ivGato= (ImageView) findViewById (R.id.ivGato);
		int ancho = ivGato.getWidth();//consigue el ancho de un elemento
		int alto = ivGato.getHeight();//el alto de un elemento
		
		//consigue el tamaño de la PANTALLA en pixeles
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		
		//consigue la medida de la barra de estado en pizeles
		Rect rectgle= new Rect();
		Window window= getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		int altBarra=window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		
		ordenar(ancho, alto, height, width, altBarra);
		
		resetValues();
	}
	
	public void cambioTitulo (int jugando)
	{
		Typeface MouseDrawn = Typeface.createFromAsset(getAssets(), "Mousedrawn.otf");
		
		tvTitulo1= (TextView) findViewById (R.id.tvTitulo1);
		tvTitulo1.setTypeface(MouseDrawn);
		
		tvTitulo2= (TextView) findViewById (R.id.tvTitulo2);
		tvTitulo2.setTypeface(MouseDrawn);
		
		if (jugando==1 || jugando==3 || jugando==4)
		{
			tvTitulo1.setVisibility(View.VISIBLE);
			tvTitulo2.setVisibility(View.INVISIBLE);
			
			tvMarJ2.setVisibility(View.INVISIBLE);
			tvMarCPU.setVisibility(View.VISIBLE);
		}
		else if (jugando==2)
		{
			tvTitulo1.setVisibility(View.INVISIBLE);
			tvTitulo2.setVisibility(View.VISIBLE);
			
			tvMarJ2.setVisibility(View.VISIBLE);
			tvMarCPU.setVisibility(View.INVISIBLE);
		}
		
	} //fin metodo cambioTitulo
	
	public void ordenar (int anch, int alt, int heig, int wid, int barra)
	{
		//escala
		
		float esc=0;
		int tam=0;
		
		if (anch > alt)
		{
		    esc= (float) alt;
		    esc=esc/480;
		    esc=160*esc;
		}
		else if (alt>=anch)
		{
			esc= (float) anch;
			esc= esc/480;
			esc=160*esc;
		}
		//orden
		
		int wid2=0;
		int heig2=0;
			
        if (anch>alt)
        {
		    wid2= (wid-alt)/2;
            heig2= ((heig-alt)/2)-(barra/2);
        }
        else if (alt>=anch)
        {
		    wid2= (wid-anch)/2;
            heig2= ((heig-anch)/2)-(barra/2);
        }

		ivO1= (ImageView) findViewById (R.id.ivO1);
		
		//cambia los parametros de una view, aqui se cambian los margenes, el alto y el ancho
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)ivO1.getLayoutParams();
		params.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params.height= (int) esc;
		params.width= (int) esc;
		ivO1.setLayoutParams(params); //aplicalos nuevos valores
		
		ivO2= (ImageView) findViewById (R.id.ivO2);
		
		tam= (int) esc;
		wid2= wid2+ tam;
		
		RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)ivO2.getLayoutParams();
		params2.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params2.height= (int) esc;
		params2.width= (int) esc;
		ivO2.setLayoutParams(params2);
		
		ivO3= (ImageView) findViewById (R.id.ivO3);
		
		wid2= wid2+tam;
		
		RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams)ivO3.getLayoutParams();
		params3.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params3.height= (int) esc;
		params3.width= (int) esc;
		ivO3.setLayoutParams(params3);
		
		ivO4 = (ImageView) findViewById (R.id.ivO4);
		
		wid2= wid2-(2*tam);
		heig2= heig2+tam;
		
		RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams)ivO4.getLayoutParams();
		params4.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params4.height= (int) esc;
		params4.width= (int) esc;
		ivO4.setLayoutParams(params4);
		
		ivO5 = (ImageView) findViewById (R.id.ivO5);
		
		wid2= wid2+tam;
		
		RelativeLayout.LayoutParams params5 = (RelativeLayout.LayoutParams)ivO5.getLayoutParams();
		params5.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params5.height= (int) esc;
		params5.width= (int) esc;
		ivO5.setLayoutParams(params5);
		
		ivO6 = (ImageView) findViewById (R.id.ivO6);
		
		wid2= wid2+tam;
		
		RelativeLayout.LayoutParams params6 = (RelativeLayout.LayoutParams)ivO6.getLayoutParams();
		params6.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params6.height= (int) esc;
		params6.width= (int) esc;
		ivO6.setLayoutParams(params6);
        
		ivO7 = (ImageView) findViewById (R.id.ivO7);
		
		wid2= wid2-(2*tam);
		heig2= heig2+tam;
		
		RelativeLayout.LayoutParams params7 = (RelativeLayout.LayoutParams)ivO7.getLayoutParams();
		params7.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params7.height= (int) esc;
		params7.width= (int) esc;
		ivO7.setLayoutParams(params7);
		
		ivO8 = (ImageView) findViewById (R.id.ivO8);
		
		wid2= wid2+tam;
		
		RelativeLayout.LayoutParams params8 = (RelativeLayout.LayoutParams)ivO8.getLayoutParams();
		params8.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params8.height= (int) esc;
		params8.width= (int) esc;
		ivO8.setLayoutParams(params8);
		
		ivO9 = (ImageView) findViewById (R.id.ivO9);
		
		wid2= wid2+tam;
		
		RelativeLayout.LayoutParams params9 = (RelativeLayout.LayoutParams)ivO9.getLayoutParams();
		params9.setMargins(wid2, heig2, 0, 0); //substitute parameters for left, top, right, bottom
		params9.height= (int) esc;
		params9.width= (int) esc;
		ivO9.setLayoutParams(params9);
		
		
	}//fin metodo ordenar
	
	public void resetValues () 
	{
		aa=1;
		ab=1;
		ac=1;
		ba=1;
		bb=1;
		bc=1;
		ca=1;
		cb=1;
		cc=1;
		
		ganador=1;
		
		ivO1.setImageDrawable(imgVacio);
		ivO2.setImageDrawable(imgVacio);
		ivO3.setImageDrawable(imgVacio);
		ivO4.setImageDrawable(imgVacio);
		ivO5.setImageDrawable(imgVacio);
		ivO6.setImageDrawable(imgVacio);
		ivO7.setImageDrawable(imgVacio);
		ivO8.setImageDrawable(imgVacio);
		ivO9.setImageDrawable(imgVacio);
		
		ivO1.setClickable(true);
		ivO2.setClickable(true);
		ivO3.setClickable(true);
		ivO4.setClickable(true);
		ivO5.setClickable(true);
		ivO6.setClickable(true);
		ivO7.setClickable(true);
		ivO8.setClickable(true);
		ivO9.setClickable(true);
		
		tire=0;
		
		if (jugadores==2)
		{
		    if (turnoInicial==3)
		    {
		    	turnoInicial=2;
		    	turno=turnoInicial;
	    	}
    		else if (turnoInicial==2)
	    	{
	    		turnoInicial=3;
	    		turno=turnoInicial;
	    	}
		}
		else if (jugadores ==1 || jugadores ==3 || jugadores ==4)
		{
			turnoInicial=3;
			turno=turnoInicial;
		}
	}//fin metodo resetValues

	public void cambioTurno (int turno1)
	{
		if (turno1 == 2)
		{
			turno=3;
		}
		else if (turno1 ==3)
		{
			turno=2;
		}
	} //fin metodo cambioTurno
	
    public void onClick1 (View v)
    {
    	if (turno==3)
		{
		    switch(v.getId())
		    {
		    case R.id.ivO1:
			    ivO1.setImageDrawable(imgCirculo);
			    ivO1.setClickable(false);
			    cambioTurno (turno);
			    aa=3;
			    gano ();
			    tiroMaquina ();
			    break;
		    case R.id.ivO2:
			    ivO2.setImageDrawable(imgCirculo);
			    ivO2.setClickable(false);
			    cambioTurno (turno);
			    ab=3;
			    gano ();
			    tiroMaquina ();
			    break;
		    case R.id.ivO3:
			    ivO3.setImageDrawable(imgCirculo);
			    ivO3.setClickable(false);
			    cambioTurno (turno);
			    ac=3;
			    gano ();
			    tiroMaquina ();
			    break;
		    case R.id.ivO4:
		    	ivO4.setImageDrawable(imgCirculo);
		    	ivO4.setClickable(false);
		    	cambioTurno (turno);
		    	ba=3;
		    	gano ();
		    	tiroMaquina ();
		    	break;
		    case R.id.ivO5:
	    		ivO5.setImageDrawable(imgCirculo);
		    	ivO5.setClickable(false);
		    	cambioTurno (turno);
	    		bb=3;
	    		gano ();
	    		tiroMaquina ();
	    		break;
	    	case R.id.ivO6:
	    		ivO6.setImageDrawable(imgCirculo);
	    		ivO6.setClickable(false);
	    		cambioTurno (turno);
	    		bc=3;
	    		gano ();
	    		tiroMaquina ();
	    		break;
	    	case R.id.ivO7:
	    		ivO7.setImageDrawable(imgCirculo);
	    		ivO7.setClickable(false);
	    		cambioTurno (turno);
	    		ca=3;
	    		gano ();
	    		tiroMaquina ();
	    		break;
	    	case R.id.ivO8:
	    		ivO8.setImageDrawable(imgCirculo);
	    		ivO8.setClickable(false);
	    		cambioTurno (turno);
	    		cb=3;
	    		gano ();
	    		tiroMaquina ();
	    		break;
	    	case R.id.ivO9:
	    		ivO9.setImageDrawable(imgCirculo);
	    		ivO9.setClickable(false);
	    		cambioTurno (turno);
	    		cc=3;
	    		gano ();
	    		tiroMaquina ();
	    		break;
	    	}//fin switch
		}//fin if
		else if (turno == 2)
		{
			switch (v.getId())
			{
		    case R.id.ivO1:
			    ivO1.setImageDrawable(imgEquis);
			    ivO1.setClickable(false);
			    aa=2;
			    cambioTurno (turno);
			    gano ();
			    break;
		    case R.id.ivO2:
			    ivO2.setImageDrawable(imgEquis);
			    ivO2.setClickable(false);
			    ab=2;
			    cambioTurno (turno);
			    gano ();
			    break;
		    case R.id.ivO3:
			    ivO3.setImageDrawable(imgEquis);
			    ivO3.setClickable(false);
			    cambioTurno (turno);
			    ac=2;
			    gano ();
			    break;
		    case R.id.ivO4:
		    	ivO4.setImageDrawable(imgEquis);
		    	ivO4.setClickable(false);
		    	cambioTurno (turno);
		    	ba=2;
		    	gano ();
		    	break;
		    case R.id.ivO5:
	    		ivO5.setImageDrawable(imgEquis);
		    	ivO5.setClickable(false);
		    	cambioTurno (turno);
	    		bb=2;
	    		gano ();
	    		break;
	    	case R.id.ivO6:
	    		ivO6.setImageDrawable(imgEquis);
	    		ivO6.setClickable(false);
	    		cambioTurno (turno);
	    		bc=2;
	    		gano ();
	    		break;
	    	case R.id.ivO7:
	    		ivO7.setImageDrawable(imgEquis);
	    		ivO7.setClickable(false);
	    		cambioTurno (turno);
	    		ca=2;
	    		gano ();
	    		break;
	    	case R.id.ivO8:
	    		ivO8.setImageDrawable(imgEquis);
	    		ivO8.setClickable(false);
	    		cambioTurno (turno);
	    		cb=2;
	    		gano ();
	    		break;
	    	case R.id.ivO9:
	    		ivO9.setImageDrawable(imgEquis);
	    		ivO9.setClickable(false);
	    		cambioTurno (turno);
	    		cc=2;
	    		gano ();
	    		break;
			}
		}
    }
    
    public void gano ()
    {
    	if ((aa==3 && ab==3 && ac==3) || (ba==3 && bb==3 && bc==3) || (ca==3 && cb==3 && cc==3) || (aa==3 && ba==3 && ca==3) || (ab==3 && bb==3 && cb==3) || (ac==3 && bc==3 && cc==3) || (aa==3 && bb==3 && cc==3) || (ac==3 && bb==3 && ca==3))
    	{
    		marO++;
    		noClicks ();
    		tvGanaO.setVisibility(View.VISIBLE);
    		tvGanaX.setVisibility(View.INVISIBLE);
    		tvGanaCPU.setVisibility(View.INVISIBLE);
    		tvEmpate.setVisibility(View.INVISIBLE);
    		layoutr1.setVisibility(View.VISIBLE);
    		ganador = 2;
    	}
    	else if ((aa==2 && ab==2 && ac==2) || (ba==2 && bb==2 && bc==2) || (ca==2 && cb==2 && cc==2) || (aa==2 && ba==2 && ca==2) || (ab==2 && bb==2 && cb==2) || (ac==2 && bc==2 && cc==2) || (aa==2 && bb==2 && cc==2) || (ac==2 && bb==2 && ca==2))
    	{
    		marX++;
    		if (jugadores==1 || jugadores==3 || jugadores==4)
    		{
    			noClicks ();
    			tvGanaO.setVisibility(View.INVISIBLE);
        		tvGanaX.setVisibility(View.INVISIBLE);
        		tvGanaCPU.setVisibility(View.VISIBLE);
        		tvEmpate.setVisibility(View.INVISIBLE);
        		layoutr1.setVisibility(View.VISIBLE);
        		ganador=2;

    		}
    		else if (jugadores==2)
    		{
    			noClicks ();
    			tvGanaO.setVisibility(View.INVISIBLE);
        		tvGanaX.setVisibility(View.VISIBLE);
        		tvGanaCPU.setVisibility(View.INVISIBLE);
        		tvEmpate.setVisibility(View.INVISIBLE);
        		layoutr1.setVisibility(View.VISIBLE);
        		ganador=2;

    		}
    	}
		else if (aa!=1 && ab!=1 && ac!=1 && ba!=1 && bb!=1 && bc!=1 && ca!=1 && cb!=1 && cc!=1)
		{
			noClicks ();
			tvGanaO.setVisibility(View.INVISIBLE);
    		tvGanaX.setVisibility(View.INVISIBLE);
    		tvGanaCPU.setVisibility(View.INVISIBLE);
    		tvEmpate.setVisibility(View.VISIBLE);
    		layoutr1.setVisibility(View.VISIBLE);
    		ganador=2;
		}

    }
    public void continuar (View v)
    {
    	layoutr1.setVisibility(View.GONE);
    	actualizaMarcador();
    	resetValues ();
    	if (tirojug ==1 && jugadores==3)
    	{
    		tirojug=0;
    		tiroMedio ();
    		
    	}
    	else if (tirojug ==1 && jugadores==4)
    	{
    		tirojug=0;
    		tiroImp ();
    		
    	}
    	else if (tirojug==0)
    	{
    		tirojug=1;
    	}
    	
    }
    
    public void noClicks ()
    {
    	ivO1.setClickable(false);
    	ivO2.setClickable(false);
    	ivO3.setClickable(false);
    	ivO4.setClickable(false);
    	ivO5.setClickable(false);
    	ivO6.setClickable(false);
    	ivO7.setClickable(false);
    	ivO8.setClickable(false);
    	ivO9.setClickable(false);
    }
    
    public void actualizaMarcador ()
    {
    	tvPnJ1.setText(String.valueOf(marO));
    	tvPnJ2.setText(String.valueOf(marX));
    }

    public void tiroMaquina ()
    {
    	if (ganador==1 && jugadores==1)
	    {
	    	tiroFacil ();
	    	cambioTurno (turno);
	    	gano ();
	    }
    	else if (ganador==1 && jugadores ==3)
    	{
    		tiroMedio ();//tiro medio
    		cambioTurno (turno);
    		gano ();
      	}
    	else if (ganador==1 && jugadores ==4)
    	{
    		tiroImp ();//tiro imposible
    		cambioTurno (turno);
    		gano ();
    	}
    	
    }
    
    public void tiroFacil ()
    {
    	Random rand = new Random ();
    	int random1 = rand.nextInt(9)+1;
    	switch (random1)
    	{
    	case 1:
    		if (aa==2 || aa==3)
    		{
    			tiroFacil ();
    		}
    		else if (aa==1)
    		{
    			ivO1.setImageDrawable(imgEquis);
			    ivO1.setClickable(false);
			    aa=2;
    		}
    		break;
    		
    	case 2:
    		if (ab==2 || ab==3)
    		{
    			tiroFacil ();
    		}
    		else if (ab==1)
    		{
    			ivO2.setImageDrawable(imgEquis);
			    ivO2.setClickable(false);
			    ab=2;
    		}
    		break;
    	case 3:
    		if (ac==2 || ac==3)
    		{
    			tiroFacil ();
    		}
    		else if (ac==1)
    		{
    			ivO3.setImageDrawable(imgEquis);
			    ivO3.setClickable(false);
			    ac=2;
    		}
    		break;
    		
    	case 4:
    		if (ba==2 || ba==3)
    		{
    			tiroFacil ();
    		}
    		else if (ba==1)
    		{
    			ivO4.setImageDrawable(imgEquis);
			    ivO4.setClickable(false);
			    ba=2;
    		}
    		break;
    	case 5:
    		if (bb==2 || bb==3)
    		{
    			tiroFacil ();
    		}
    		else if (bb==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
			    ivO5.setClickable(false);
			    bb=2;
    		}
    		break;
    	case 6:
    		if (bc==2 || bc==3)
    		{
    			tiroFacil ();
    		}
    		else if (bc==1)
    		{
    			ivO6.setImageDrawable(imgEquis);
			    ivO6.setClickable(false);
			    bc=2;
    		}
    		break;
    	case 7:
    		if (ca==2 || ca==3)
    		{
    			tiroFacil ();
    		}
    		else if (ca==1)
    		{
    			ivO7.setImageDrawable(imgEquis);
			    ivO7.setClickable(false);
			    ca=2;
    		}
    		break;
    	case 8:
    		if (cb==2 || cb==3)
    		{
    			tiroFacil ();
    		}
    		else if (cb==1)
    		{
    			ivO8.setImageDrawable(imgEquis);
			    ivO8.setClickable(false);
			    cb=2;
    		}
    		break;
    	case 9:
    		if (cc==2 || cc==3)
    		{
    			tiroFacil ();
    		}
    		else if (cc==1)
    		{
    			ivO9.setImageDrawable(imgEquis);
			    ivO9.setClickable(false);
			    cc=2;
    		}
    		break;
    	}
    }
    
    public void tiroMedio ()
    {
    	Random rand = new Random ();
    	int random2 = rand.nextInt(9)+1;
	
    	if (tire==0)
    	{	

        	switch (random2)
        	{
        	case 1:
        		if (aa==2 || aa==3)
        		{
        			tiroMedio ();
        		}
        		else if (aa==1)
        		{
        			ivO1.setImageDrawable(imgEquis);
    			    ivO1.setClickable(false);
    			    aa=2;
    			    tire=random2;
        		}
        		break;
        		
        	case 2:
        		if (ab==2 || ab==3)
        		{
        			tiroMedio ();
        		}
        		else if (ab==1)
        		{
        			ivO2.setImageDrawable(imgEquis);
    			    ivO2.setClickable(false);
    			    ab=2;
    			    tire=random2;
        		}
        		break;
        	case 3:
        		if (ac==2 || ac==3)
        		{
        			tiroMedio ();
        		}
        		else if (ac==1)
        		{
        			ivO3.setImageDrawable(imgEquis);
    			    ivO3.setClickable(false);
    			    ac=2;
    			    tire=random2;
        		}
        		break;
        		
        	case 4:
        		if (ba==2 || ba==3)
        		{
        			tiroMedio ();
        		}
        		else if (ba==1)
        		{
        			ivO4.setImageDrawable(imgEquis);
    			    ivO4.setClickable(false);
    			    ba=2;
    			    tire=random2;
        		}
        		break;
        	case 5:
        		if (bb==2 || bb==3)
        		{
        			tiroMedio ();
        		}
        		else if (bb==1)
        		{
        			ivO5.setImageDrawable(imgEquis);
    			    ivO5.setClickable(false);
    			    bb=2;
    			    tire=random2;
        		}
        		break;
        	case 6:
        		if (bc==2 || bc==3)
        		{
        			tiroMedio ();
        		}
        		else if (bc==1)
        		{
        			ivO6.setImageDrawable(imgEquis);
    			    ivO6.setClickable(false);
    			    bc=2;
    			    tire=random2;
        		}
        		break;
        	case 7:
        		if (ca==2 || ca==3)
        		{
        			tiroMedio ();
        		}
        		else if (ca==1)
        		{
        			ivO7.setImageDrawable(imgEquis);
    			    ivO7.setClickable(false);
    			    ca=2;
    			    tire=random2;
        		}
        		break;
        	case 8:
        		if (cb==2 || cb==3)
        		{
        			tiroMedio ();
        		}
        		else if (cb==1)
        		{
        			ivO8.setImageDrawable(imgEquis);
    			    ivO8.setClickable(false);
    			    cb=2;
    			    tire=random2;
        		}
        		break;
        	case 9:
        		if (cc==2 || cc==3)
        		{
        			tiroMedio ();
        		}
        		else if (cc==1)
        		{
        			ivO9.setImageDrawable(imgEquis);
    			    ivO9.setClickable(false);
    			    cc=2;
    			    tire=random2;
        		}
        		break;
        	} //fin switch 
        	setiro=random2;
    	} //fin if
    	else if (tire != 0)
    	{
    		puedeganar ();
    		if (tirado == 0)
    		{
    			evitaPerder ();
    			if (tirado == 0)
        		{
        		    segundoTiro (setiro);
        		    if (tirado ==0)
        		    {
        		    	tire=0;
        		    	tiroMedio ();
        		    }
        		}
   			}
    		
    		tirado=0;
    	}

    }// fin metodo tiroMedio
    
    public void puedeganar ()
    {
    	if (aa==2 && ab==2 && ac==1)  //casos con aa
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (aa==2 && ac==2 && ab==1)
    	{
    		ivO2.setImageDrawable(imgEquis);
		    ivO2.setClickable(false);
		    ab=2;
		    tirado =1;
    	}
    	else if (aa==2 && ba==2 && ca==1)
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    	else if (aa==2 && bb==2 && cc==1)
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (aa ==2 && ca==2 && ba==1)
    	{
    		ivO4.setImageDrawable(imgEquis);
		    ivO4.setClickable(false);
		    ba=2;
		    tirado =1;
    	}
    	else if (aa==2 && cc==2 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ab==2 && ac==2 && aa==1) //casos de ab
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (ab==2 && bb==2 && cb==1)
    	{
    		ivO8.setImageDrawable(imgEquis);
		    ivO8.setClickable(false);
		    cb=2; 
		    tirado =1;
    	}
    	else if (ab==2 && cb==2 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ac==2 && bb==2 && ca==1) //casos de ac
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    	else if (ac==2 && bc==2 && cc==1)
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (ac==2 && ca==2 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ac==2 && cc==2 && bc==1)
    	{
    		ivO6.setImageDrawable(imgEquis);
		    ivO6.setClickable(false);
		    bc=2;
		    tirado =1;
    	}
    	else if (ba==2 && bb==2 && bc==1) //casos ba
    	{
    		ivO6.setImageDrawable(imgEquis);
		    ivO6.setClickable(false);
		    bc=2;
		    tirado =1;
    	}
    	else if (ba==2 && bc==2 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ba==2 && ca==2 && aa==1)
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (bb==2 && bc==2 && ba==1) //caso de bb
    	{
    		ivO4.setImageDrawable(imgEquis);
		    ivO4.setClickable(false);
		    ba=2;
		    tirado =1;
    	}
    	else if (bb==2 && ca==2 && ac==1)
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (bb==2 && cb==2 && ab==1)
    	{
    		ivO2.setImageDrawable(imgEquis);
		    ivO2.setClickable(false);
		    ab=2;
		    tirado =1;
    	}
    	else if (bb==2 && cc==2 && aa==1)
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (bc==2 && cc==2 && ac==1)// casos bc
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (ca==2 && cb==2 && cc==1) //casos ca
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (ca==2 && cc==2 && cb==1) 
    	{
    		ivO8.setImageDrawable(imgEquis);
		    ivO8.setClickable(false);
		    cb=2;
		    tirado =1;
    	}
    	else if (cb==2 && cc==2 && ca==1)//caso cb
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    } //fin metodo puedeganar

    public void evitaPerder ()
    {
    	if (aa==3 && ab==3 && ac==1)  //casos con aa
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (aa==3 && ac==3 && ab==1)
    	{
    		ivO2.setImageDrawable(imgEquis);
		    ivO2.setClickable(false);
		    ab=2;
		    tirado =1;
    	}
    	else if (aa==3 && ba==3 && ca==1)
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    	else if (aa==3 && bb==3 && cc==1)
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (aa ==3 && ca==3 && ba==1)
    	{
    		ivO4.setImageDrawable(imgEquis);
		    ivO4.setClickable(false);
		    ba=2;
		    tirado =1;
    	}
    	else if (aa==3 && cc==3 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ab==3 && ac==3 && aa==1) //casos de ab
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (ab==3 && bb==3 && cb==1)
    	{
    		ivO8.setImageDrawable(imgEquis);
		    ivO8.setClickable(false);
		    cb=2;
		    tirado =1;
    	}
    	else if (ab==3 && cb==3 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ac==3 && bb==3 && ca==1) //casos de ac
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    	else if (ac==3 && bc==3 && cc==1)
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (ac==3 && ca==3 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ac==3 && cc==3 && bc==1)
    	{
    		ivO6.setImageDrawable(imgEquis);
		    ivO6.setClickable(false);
		    bc=2;
		    tirado =1;
    	}
    	else if (ba==3 && bb==3 && bc==1) //casos ba
    	{
    		ivO6.setImageDrawable(imgEquis);
		    ivO6.setClickable(false);
		    bc=2;
		    tirado =1;
    	}
    	else if (ba==3 && bc==3 && bb==1)
    	{
    		ivO5.setImageDrawable(imgEquis);
		    ivO5.setClickable(false);
		    bb=2;
		    tirado =1;
    	}
    	else if (ba==3 && ca==3 && aa==1)
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (bb==3 && bc==3 && ba==1) //caso de bb
    	{
    		ivO4.setImageDrawable(imgEquis);
		    ivO4.setClickable(false);
		    ba=2;
		    tirado =1;
    	}
    	else if (bb==3 && ca==3 && ac==1)
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (bb==3 && cb==3 && ab==1)
    	{
    		ivO2.setImageDrawable(imgEquis);
		    ivO2.setClickable(false);
		    ab=2;
		    tirado =1;
    	}
    	else if (bb==3 && cc==3 && aa==1)
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
		    tirado =1;
    	}
    	else if (bc==3 && cc==3 && ac==1)// casos bc
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
		    tirado =1;
    	}
    	else if (ca==3 && cb==3 && cc==1) //casos ca
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;
		    tirado =1;
    	}
    	else if (ca==3 && cc==3 && cb==1) 
    	{
    		ivO8.setImageDrawable(imgEquis);
		    ivO8.setClickable(false);
		    cb=2;
		    tirado =1;
    	}
    	else if (cb==3 && cc==3 && ca==1)//caso cb
    	{
    		ivO7.setImageDrawable(imgEquis);
		    ivO7.setClickable(false);
		    ca=2;
		    tirado =1;
    	}
    }

    public void segundoTiro (int ran)
    {
    	switch (ran)
    	{
    	case 1:
    		if (ab==1 && ac==1)
    		{
    			ivO2.setImageDrawable(imgEquis);
    		    ivO2.setClickable(false);
    		    ab=2;
    		    tirado =1;
    		}
    		else if (bb==1 && cc==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		else if (ba==1 && ca==1)
    		{
    			ivO4.setImageDrawable(imgEquis);
    		    ivO4.setClickable(false);
    		    ba=2;
    		    tirado =1;
    		}
    		break;
    	case 2:
    		if (aa==1 && ac==1)
    		{
    			ivO1.setImageDrawable(imgEquis);
    		    ivO1.setClickable(false);
    		    aa=2;
    		    tirado =1;
    		}
    		else if (bb==1 && cb==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		break;
    	case 3:
    		if (aa==1 && ab==1)
    		{
    			ivO2.setImageDrawable(imgEquis);
    		    ivO2.setClickable(false);
    		    ab=2;
    		    tirado =1;
    		}
    		else if (bb==1 && ca==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		else if (bc==1 && cc==1)
    		{
    			ivO6.setImageDrawable(imgEquis);
    		    ivO6.setClickable(false);
    		    bc=2;
    		    tirado =1;
    		}
    		break;
    	case 4:
    		if (aa==1 && ca==1)
    		{
    			ivO1.setImageDrawable(imgEquis);
    		    ivO1.setClickable(false);
    		    aa=2;
    		    tirado =1;
    		}
    		else if (bb==1 && bc==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		break;
    	case 5:
    		if (ab==1 && cb==1)
    		{
    			ivO2.setImageDrawable(imgEquis);
    		    ivO2.setClickable(false);
    		    ab=2;
    		    tirado =1;
    		}
    		else if (ac==1 && ca==1)
    		{
    			ivO3.setImageDrawable(imgEquis);
    		    ivO3.setClickable(false);
    		    ac=2;
    		    tirado =1;
    		}
    		else if (ba==1 && bc==1)
    		{
    			ivO4.setImageDrawable(imgEquis);
    		    ivO4.setClickable(false);
    		    ba=2;
    		    tirado =1;
    		}
    		else if (aa==1 && cc==1)
    		{
    			ivO1.setImageDrawable(imgEquis);
    			ivO1.setClickable(false);
    			aa=2;
    			tirado=1;
    		}
    		break;
    	case 6:
    		if (ac==1 && cc==1)
    		{
    			ivO3.setImageDrawable(imgEquis);
    		    ivO3.setClickable(false);
    		    ac=2;
    		    tirado =1;
    		}
    		else if (ba==1 && bb==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		break;
    	case 7:
    		if (ba==1 && aa==1)
    		{
    			ivO4.setImageDrawable(imgEquis);
    		    ivO4.setClickable(false);
    		    ba=2;
    		    tirado =1;
    		}
    		else if (bb==1 && ac==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		else if (cb==1 && cc==1)
    		{
    			ivO8.setImageDrawable(imgEquis);
    		    ivO8.setClickable(false);
    		    cb=2;
    		    tirado =1;
    		}
    		break;
    	case 8:
    		if (ab==1 && bb==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		else if (ca==1 && cc==1)
    		{
    			ivO7.setImageDrawable(imgEquis);
    		    ivO7.setClickable(false);
    		    ca=2;
    		    tirado =1;
    		}
    		break;
    	case 9:
    		if (cb==1 && ca==1)
    		{
    			ivO8.setImageDrawable(imgEquis);
    		    ivO8.setClickable(false);
    		    cb=2;
    		    tirado =1;
    		}
    		else if (bb==1 && aa==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
    		    ivO5.setClickable(false);
    		    bb=2;
    		    tirado =1;
    		}
    		else if (bc==1 && ac==1)
    		{
    			ivO6.setImageDrawable(imgEquis);
    		    ivO6.setClickable(false);
    		    bc=2;
    		    tirado =1;
    		}
    		break;
    	}
    }

    public void tiroImp ()
    {
    	if (tirojug==1) //si el jugador tiro primero
    	{
    		if (tire==0) //primer turno de x
    		{
    		    if (aa==3 || ac==3 || ca==3 || cc==3) //si el jugador tiro en una esquina
    		    {
    	    		ivO5.setImageDrawable(imgEquis);
    			    ivO5.setClickable(false);
    			    bb=2;
    			    tire++;
    			    setiro=1;
    		    }
    		    else if (bb==3) //si el jugador tiro en el centro
    		    {
    	    		ivO1.setImageDrawable(imgEquis);
    			    ivO1.setClickable(false);
    			    aa=2;
    			    tire++;
    			    setiro=2;
    		    }
    		    else if (ab==3 || ba==3 || bc==3 || cb==3) //si el jugador tiro en una lateral
    		    {
    		    	ivO5.setImageDrawable(imgEquis);
    			    ivO5.setClickable(false);
    			    bb=2;
    			    tire++; 
    			    setiro=3;
    		    }
    		    
    		}
    		else if (tire!=0)
    		{
    			puedeganar ();
    			if (tirado ==0)
    			{
    				evitaPerder();
    				if (tirado ==0)
    				{
    					if (tire ==1) //segundo tiro si no se puede usar "puedeganar" ni "evitaperder"
    					{
    						if (setiro==1)
    						{
    							segundoTiroCaso1 (); 
    						}
    						else if (setiro ==2)
    						{
    							segundoTiroCaso2 ();
    						}
    						else if (setiro ==3)
    						{
    							segundoTiroCaso3 ();
    						}
    					}
    					else if (tire ==2) //tercer tiro
    					{
    						if (setiro==1 || setiro ==2 || setiro==3)
    						{
    							tercerTiroCaso1 ();
    						}
    					}
    					else if (tire==3) //ultimo tiro
    					{
    						if (setiro==1 || setiro ==2 || setiro==3)
    						{
    							tercerTiroCaso1 (); //es el cuarto tiro pero hacen lo mismo
    						}
    					}
    				}
    			}
    			tirado=0;
    			tire++;
    		}
    	}
    	else if (tirojug ==0)// si la maquina va primero
    	{
    		if (tire==0)
    		{
    			ivO7.setImageDrawable(imgEquis);
    		    ivO7.setClickable(false);
    		    ca=2;
    		    tire++;
    		}
    		else if (tire !=0)
    		{
    			puedeganar ();
    			if (tirado==0)
    			{
    				evitaPerder ();
    				if (tirado==0)
    				{
    					if (tire==1)
    					{
    						if (aa==3 || bb==3 || cc==3)
    						{
    							ivO3.setImageDrawable(imgEquis);
    			    		    ivO3.setClickable(false);
    			    		    ac=2;
    			    		   
    						}
    						else if (ab==3 || ba==3 || bc==3)
    						{
    							ivO5.setImageDrawable(imgEquis);
    			    		    ivO5.setClickable(false);
    			    		    bb=2;
    			    		   
    						}
    						else if (ac==3 || cb==3)
    						{
    							ivO1.setImageDrawable(imgEquis);
    			    		    ivO1.setClickable(false);
    			    		    aa=2;
    			    		    
    						}
    					}
    					else if (tire ==2)//tercer tiro
    					{
    						if (ba==3 && ac==3 )
    						{
    							ivO9.setImageDrawable(imgEquis);
    			    		    ivO9.setClickable(false);
    			    		    cc=2;	    
    						}
    						else if (ba==3 && cb==3)
    						{
    							ivO5.setImageDrawable(imgEquis);
    			    		    ivO5.setClickable(false);
    			    		    bb=2;
    						}
    					}
    				}
    			}
    			tirado=0;
    			tire++;
    		}
    	}
    }
    
    public void  segundoTiroCaso1 ()
    {
    	if ((aa==3 && bc==3)||(aa==3 && cc==3) || (ac==3 && ba==3) || (ac==3 && ca==3))
    	{
    		ivO8.setImageDrawable(imgEquis);
		    ivO8.setClickable(false);
		    cb=2;
    	}
    	else if ((aa==3 && cb==3) || (ab==3 && ca==3))
    	{
    		ivO6.setImageDrawable(imgEquis);
		    ivO6.setClickable(false);
		    bc=2;
    	}
    	else if ((ac==3 && cb==3) || (ab==3 && cc==3))
    	{
    		ivO4.setImageDrawable(imgEquis);
		    ivO4.setClickable(false);
		    ba=2;
    	}
    	else if ((bc==3 && ca==3) || (ba==3 && cc==3))
    	{
    		ivO2.setImageDrawable(imgEquis);
		    ivO2.setClickable(false);
		    ab=2;
    	}
    }
    
    public void tercerTiroCaso1 ()
    {
    	Random rand= new Random ();
    	int casilla = rand.nextInt(9)+1;
    	switch (casilla)
    	{
    	case 1:
    		if (aa==2 || aa==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (aa==1)
    		{
    			ivO1.setImageDrawable(imgEquis);
			    ivO1.setClickable(false);
			    aa=2;
    		}
    		break;
    	case 2:
    		if (ab==2 || ab==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (ab==1)
    		{
    			ivO2.setImageDrawable(imgEquis);
			    ivO2.setClickable(false);
			    ab=2;
    		}
    		break;
    	case 3:
    		if (ac==2 || ac==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (ac==1)
    		{
    			ivO3.setImageDrawable(imgEquis);
			    ivO3.setClickable(false);
			    ac=2;
    		}
    		break;
    	case 4:
    		if (ba==2 || ba==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (ba==1)
    		{
    			ivO4.setImageDrawable(imgEquis);
			    ivO4.setClickable(false);
			    ba=2;
    		}
    		break;
    	case 5:
    		if (bb==2 || bb==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (bb==1)
    		{
    			ivO5.setImageDrawable(imgEquis);
			    ivO5.setClickable(false);
			    bb=2;
    		}
    		break;
    	case 6:
    		if (bc==2 || bc==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (bc==1)
    		{
    			ivO6.setImageDrawable(imgEquis);
			    ivO6.setClickable(false);
			    bc=2;
    		}
    		break;
    	case 7:
    		if (ca==2 || ca==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (ca==1)
    		{
    			ivO7.setImageDrawable(imgEquis);
			    ivO7.setClickable(false);
			    ca=2;
    		}
    		break;
    	case 8:
    		if (cb==2 || cb==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (cb==1)
    		{
    			ivO8.setImageDrawable(imgEquis);
			    ivO8.setClickable(false);
			    cb=2;
    		}
    		break;
    	case 9:
    		if (cc==2 || cc==3)
    		{
    			tercerTiroCaso1 ();
    		}
    		else if (cc==1)
    		{
    			ivO9.setImageDrawable(imgEquis);
			    ivO9.setClickable(false);
			    cc=2;
    		}
    		break;
      	}
    }
    
    public void segundoTiroCaso2 ()
    {
    	if (bb==3 && cc==3 && ac==1)
    	{
    		ivO3.setImageDrawable(imgEquis);
		    ivO3.setClickable(false);
		    ac=2;
    	}
    }
    
    public void segundoTiroCaso3 ()
    {
    	if ((ab==3 && ba==3)|| (ab==3 && bc==3) || (ab==3 && ca==3)|| (ab==3 && cb==3) || (ab==3 && cc==3) || (ac==3 && ba==3) || (ba==3 && bc==3) || (ba==3 && cb==3) || (ba==3 && cc==3))
    	{
    		ivO1.setImageDrawable(imgEquis);
		    ivO1.setClickable(false);
		    aa=2;
    	}
    	else if ((aa ==3 && bc==3) || (bc==3 && ca==3) || ( bc==3 && cb==3) || (aa==3 && cb==3) || (ac==3 && cb==3))
    	{
    		ivO9.setImageDrawable(imgEquis);
		    ivO9.setClickable(false);
		    cc=2;    		
    	}
    }
}
