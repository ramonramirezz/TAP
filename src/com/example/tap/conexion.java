package com.example.tap;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexion {
	public static final String ID_USUARIO = "_id";
	public static final String ID_SCORE = "id";
	public static final String USUARIO = "usuario";
	public static final String NOMBRE = "nombre";
	public static final String CONTRA = "contrasenia";
	public static final String TIEMPO = "tiempo";
	
	public static final String FECHA = "fecha";
	public static final String SCORE = "score";
	
	private static final String N_BD = "zarit";
	private static final String N_USUARIOS = "usuarios";
	private static final String N_HISTORIAL = "historial";
	
	private static final int VERSION_BD = 1; 
	
	private BDHelper nHelper;
	private final Context nContexto;
	private SQLiteDatabase nBD;	
	
	
	private static class BDHelper extends SQLiteOpenHelper{
		
		public BDHelper(Context context){
			super(context, N_BD, null, VERSION_BD);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL("CREATE TABLE "+ N_USUARIOS + "(" + 
					ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					USUARIO + " TEXT NOT NULL," + 
					NOMBRE + " TEXT NOT NULL," +
					CONTRA + " TEXT NOT NULL);"
		);
			db.execSQL("CREATE TABLE "+ N_HISTORIAL + "(" + 
					ID_SCORE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  
					ID_USUARIO + " TEXT NOT NULL," + 
					SCORE + " TEXT NOT NULL," + 
					TIEMPO + " TEXT NOT NULL," +
					FECHA + " TEXT NOT NULL);"
					);
			
			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ N_USUARIOS);
			db.execSQL("DROP TABLE IF EXISTS "+ N_HISTORIAL);
			onCreate(db);
			}
	}	
	
	public Conexion (Context c){
		nContexto = c;
	}
	
	public Conexion abrir() {
		// TODO Auto-generated method stub
		nHelper = new BDHelper(nContexto);
		nBD = nHelper.getWritableDatabase();
		return this;	
	}	
	
	public void cerrar() {
		// TODO Auto-generated method stub
		nHelper.close();	
	}

	public boolean addUser(String nom, String usuario, String contra) {
		// TODO Auto-generated method stub
		try {
			ContentValues cv = new ContentValues();
			cv.put(USUARIO, usuario);
			cv.put(NOMBRE, nom);
			cv.put(CONTRA, contra);
			nBD.insert(N_USUARIOS, null, cv);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		
				
	}

	public ArrayList<String> login(String usuatrio, String contrasenia) {
		// TODO Auto-generated method stub
		ArrayList<String> registro=new ArrayList<String>();
		Cursor c = nBD.rawQuery("SELECT * FROM " + N_USUARIOS + " WHERE usuario=? AND contrasenia=?", new String[]{usuatrio,contrasenia});
		
				if (c.moveToFirst()) {
				  
				     do {
				    	
				         registro.add(c.getString(0));
				         registro.add(c.getString(1));
				         registro.add(c.getString(2));
				         registro.add(c.getString(3));
				       
				     } while(c.moveToNext());
				}
				return registro;
	}
	

	public ArrayList<String> getHistory (String id){
		ArrayList<String> registro=new ArrayList<String>();
		Cursor c = nBD.rawQuery("SELECT * FROM " + N_HISTORIAL + " WHERE _id=?", new String[]{id});
		 
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	 String item = c.getString(3);
		    	 //String item = c.getString(2) + " puntos " + c.getString(3);
		         registro.add(item); 
		     } while(c.moveToNext());
		}
		return registro;
	}
	
	public ArrayList<String> getUser (String id){
		ArrayList<String> registro=new ArrayList<String>();
		Cursor c = nBD.rawQuery("SELECT * FROM " + N_USUARIOS + " WHERE _id=?", new String[]{id});
		 
		if (c.moveToFirst()) {
	
		     do {

		         registro.add(c.getString(0));
		         registro.add(c.getString(1));
		         registro.add(c.getString(2));
		         registro.add(c.getString(3));
		         
		     
		     } while(c.moveToNext());
		}
		return registro;
	}
	
	public boolean setHistory(String id,String score, String fecha, String tiempo) {
		// TODO Auto-generated method stub
		boolean status;
		try {
			
			ContentValues cv = new ContentValues();
			cv.put(ID_USUARIO, id);
			cv.put(SCORE, score);
			cv.put(TIEMPO, tiempo);
			cv.put(FECHA, fecha);
			nBD.insert(N_HISTORIAL, null, cv);
			 status = true;
			 
	        
		} catch (Exception e) {
			// TODO: handle exception
			status = false;
		}
          return status;
		
				
	}
	
	
}
