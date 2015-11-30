package com.example.tap;

import android.os.Bundle;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	
	EditText _usuario, _contra;
	Button aceptar, registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		_usuario = (EditText)findViewById(R.id.edUsuario);
		_contra = (EditText)findViewById(R.id.edContra);
        
        aceptar = (Button) findViewById(R.id.btnIniciar);
        registrar = (Button) findViewById(R.id.btnRegrist);
        aceptar.setOnClickListener(this);
        registrar.setOnClickListener(this);
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
		case R.id.btnIniciar:
			String usuatrio = _usuario.getText().toString();
			String contrasenia = _contra.getText().toString();
			_usuario.setText("");
			_contra.setText("");
			
			try {
				Intent i = new Intent(MainActivity.this, Bienvenido.class);
				Conexion con = new Conexion(MainActivity.this);
				con.abrir();
				ArrayList<String> registro = con.login(usuatrio, contrasenia);
				if (registro.get(1).equals(usuatrio) && registro.get(3).equals(contrasenia)){
					i.putExtra("id_user", registro.get(0));
					startActivity(i);
					
					
				}else{
					Toast toast = Toast.makeText(getApplicationContext(), "Usuario/Contraseña incorrecto", Toast.LENGTH_SHORT);
					toast.show();
				}
				
				con.cerrar();
			} catch (Exception e) {
				// TODO: handle exception
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error");
				TextView tv = new TextView(this);
				tv.setText("Usuario/contraseña incorrecta");
				d.setContentView(tv);
				d.show();
				
			}
			
			break;
			
		case R.id.btnRegrist:
			startActivity(new Intent (MainActivity.this, RegistrarActivity.class));
			break;
		}
	}
    
}
