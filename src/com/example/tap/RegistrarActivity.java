package com.example.tap;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarActivity extends Activity implements OnClickListener {

	Button aceptar, cancelar;
	EditText _nombre,_user, _contra, _contra1;
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
        _nombre = (EditText) findViewById(R.id.edNombre);
        _user = (EditText) findViewById(R.id.edUsuario);
        _contra = (EditText) findViewById(R.id.edContra);
        _contra1 = (EditText) findViewById(R.id.edContra1);
        
        cancelar = (Button) findViewById(R.id.btnCancelar);
        aceptar = (Button) findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        
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
		case R.id.btnAceptar:
			boolean status;
			try {
				String nombre,usuario,contra1,contra2; 
				nombre = _nombre.getText().toString();
				usuario = _user.getText().toString();
				contra1 = _contra.getText().toString();
				contra2 = _contra1.getText().toString();
			    
				if(nombre.equals("") || usuario.equals("") || contra1.equals("") || contra2.equals("")){
						
					Toast toast = Toast.makeText(getApplicationContext(), "Todos los campos tienes que estar llenos", Toast.LENGTH_SHORT);
					toast.show();
						
				}
				
			     if (contra1.equals(contra2)) {
						Conexion con = new Conexion(RegistrarActivity.this);
						con.abrir();
						status = con.addUser(nombre, usuario, contra1);
						con.cerrar();
						
						if (status) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Se creo su usuario.", Toast.LENGTH_SHORT);
							toast.show();
								startActivity(new Intent (RegistrarActivity.this, MainActivity.class));
							
						}else {
							Toast toast = Toast.makeText(getApplicationContext(), "No se creo usuario.", Toast.LENGTH_SHORT);
							toast.show();
							
							_contra.setText("");
							_contra1.setText("");
						}
				}
				startActivity(new Intent(RegistrarActivity.this, MainActivity.class));
			} catch (Exception e) {
				// TODO: handle exception
				status = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funciona :(");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
			}

			break;
			
		case R.id.btnCancelar:
			_nombre.setText("");
			_user.setText("");
			_contra.setText("");
			_contra1.setText("");
			
			startActivity(new Intent (RegistrarActivity.this, MainActivity.class));
			break;
		}
		
		
	}
}
