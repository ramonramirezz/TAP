package com.example.tap;


import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Bienvenido extends Activity implements OnClickListener, OnItemClickListener {
	Conexion con = new Conexion(this);

	TextView nombreUsuario, _tvNewTest, _tvHistory;
	String id;
    ListView historial;
	ImageButton _nuevoTest ,hystory;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Bienvenido");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
		_tvNewTest = (TextView) findViewById(R.id.tvNewTest);
		_tvHistory = (TextView) findViewById(R.id.tvScore);
		nombreUsuario =(TextView) findViewById(R.id.tvNombreUsuario);
		historial = (ListView) findViewById(R.id.listView1);
		
		_nuevoTest = (ImageButton) findViewById(R.id.btnNewTest);
		hystory = (ImageButton) findViewById(R.id.btnScore);
		
		
		_tvNewTest.setOnClickListener(this);
		_tvHistory.setOnClickListener(this);
		_nuevoTest.setOnClickListener(this);
		hystory.setOnClickListener(this);
		historial.setOnItemClickListener(this);
		
		id = getIntent().getStringExtra("id_user");		
		this.getNameUser(id);
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.btnNewTest:
			Intent i = new Intent(Bienvenido.this, Encuesta.class);
			i.putExtra("id_user", id);
			startActivity(i);
			break;
		case R.id.btnScore:
			
			this.getScoreHistory(id);
			break;
		case R.id.tvNewTest:
			Intent inte = new Intent(Bienvenido.this, Encuesta.class);
			inte.putExtra("id_user", id);
			startActivity(inte);
			break;
		case R.id.tvScore:
			this.getScoreHistory(id);
			
			break;


	}
	}
	
	public void getNameUser(String id){
		
		try{
			con.abrir();
			ArrayList<String> registro= con.getUser(id);
			con.cerrar();
			nombreUsuario.setText(registro.get(2));
		}catch (Exception ex){
			//request.setText(ex.toString());
		}
	}
	
	public void getScoreHistory(String id){
		try{					
			con.abrir();
			ArrayList<String> history = con.getHistory(id);;
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1,history);
		
			historial.setAdapter(dataAdapter);
			
			
		}catch(Exception ex){
			
		}
	}


	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		String selected = adapter.getItemAtPosition(position).toString();
       
		Intent i = new Intent(Bienvenido.this, PopScore.class);
		i.putExtra("data", selected);
		
		this.startActivity(i);
	}
    
}