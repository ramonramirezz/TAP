package com.example.tap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.adapters.CustomAdapterPregunta;
import com.example.adapters.ItemPregunta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Encuesta extends Activity {
    
	String[] question = {"�Siente que su familiar le pide m�s ayuda de la que realmente necesita?", 
			"�Siente que debido al tiempo que dedica a su familiar no tiene suficiente tiempo para usted?",
			"�Se siente agobiado entre cuidar a su familiar y tratar de cumplir otras responsabilidades en su trabajo o su familia?",
			"�Se siente avergonzado por la conducta de su familiar?",
			"�Se siente irritado(a) (molesto) cuando est� cerca de su familiar?",
			"�Cree que su situaci�n actual afecta negativamente a su relaci�n con otros miembros de su familia o amigos?",
			"�Siente temor por el futuro que le espera a su familiar?",
			"�Siente que su familiar depende de usted?",
			"�Se siente tenso cuando est� cerca de su familiar?",
			"�Siente que su salud se ha resentido por cuidar a su familiar?",
			"�Siente que no tiene la vida privada que desear�a a causa de su familiar?",
			"�Cree que su vida social se ha visto afectada por cuidar a su familiar?",
			"�Se siente inc�modo por desatender a sus amistades debido a su familiar?",
			"�Siente que su familiar espera que usted le cuide, como si usted fuera la �nica persona de quien depende?",
			"�Siente que no dispone de suficiente dinero para cuidar a su familiar adem�s de sus otros gastos?",
			"�Cree que ser� incapaz de cuidarle/a por mucho m�s tiempo?",
			"�Siente que ha perdido el control de su vida desde la enfermedad de su familiar?",
			"�Desear�a poder encargar el cuidado de su familiar a otra persona?",
			"�Se siente inseguro(a) sobre que hacer con su familiar?",
			"�Siente que deber�a hacer m�s de lo que hace por su familiar?",
			"�Cree que podr�a cuidar mejor a su familiar mejor de lo que hace?",
			"En general �se siente muy sobrecargado al tener que cuidar de su familiar?"};
	
	private LinearLayout mLnScroll;
	private ListView mListView;
	private ArrayList<ItemPregunta> myArrayListTemp;
	LinearLayout ln;
	int rowSize = 5;
	
	private CustomAdapterPregunta adapter;
	private List<ItemPregunta> dataList;
	
	private Button enviar;
	String id;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta_new);
        
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Encuesta");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
        id = getIntent().getStringExtra("id_user");	
        
        mLnScroll = (LinearLayout) findViewById(R.id.linear_scroll);
        mListView = (ListView) findViewById(R.id.listView1);
        
        dataList = new ArrayList<ItemPregunta>();
        myArrayListTemp = new ArrayList<ItemPregunta>();
        
        enviar = (Button) findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				int score = 0;
				for (int i = 0; i < dataList.size(); i++) {
					score += dataList.get(i).getScore();
				}
				
				try {
					Conexion con = new Conexion(Encuesta.this);
					con.abrir();
					boolean status = con.setHistory(id, Integer.toString(score), date());
					con.cerrar();
					
					if (status) {
						Toast toast = Toast.makeText(getApplicationContext(), "Test enviado", Toast.LENGTH_SHORT);
						toast.show();		
						Intent i = new Intent (Encuesta.this, Bienvenido.class);
						i.putExtra("id_user", id);
						startActivity(i);
					}else{
						Toast toast = Toast.makeText(getApplicationContext(), "Error al enviar test", Toast.LENGTH_SHORT);
						toast.show();						
					}

				} catch (Exception e) {
					// TODO: handle exception
					Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
        
        for (String  quest : question){
        	dataList.add(new ItemPregunta(quest));
        }

        int rem = dataList.size() % rowSize;
        if (rem > 0) {
			for (int i = 0; i < rowSize - rem; i++) {
				dataList.add(new ItemPregunta(""));
			}
		}
        addItem(0);
        
        int size = dataList.size() / rowSize;
        
        for (int i = 0; i < size; i++) {
			final int k;
			k = i;
			final Button btnPage = new Button(Encuesta.this);
			LayoutParams lp = new LinearLayout.LayoutParams(120,LayoutParams.WRAP_CONTENT);
			lp.setMargins(5, 2, 2, 2);
			btnPage.setTextColor(Color.WHITE);
			btnPage.setTextSize(26.0f);
			btnPage.setId(i);
			btnPage.setText(String.valueOf(i + 1));
			mLnScroll.addView(btnPage, lp);
			
			btnPage.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v){
					addItem(k);
				}
			});
		}
    }


    private void addItem(int i) {
		// TODO Auto-generated method stub
		myArrayListTemp.clear();
		
		i = i * rowSize;
		for (int j = 0; j < rowSize; j++) {
			myArrayListTemp.add(j, dataList.get(i));
			i = i + 1;
		}
		setView(myArrayListTemp);
	}


	private void setView(ArrayList<ItemPregunta> dataList) {
		// TODO Auto-generated method stub
		
		
	   
	    for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getPregunta().equals("")) {
				dataList.remove(i);
			}
		}
        if (dataList.get(dataList.size() - 1).getPregunta().equals("")) {
			dataList.remove(dataList.size()-1);
		}
        
	    adapter = new CustomAdapterPregunta(this, R.layout.pregunta, dataList);
	    mListView.setAdapter(adapter);
	}
    

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	public String date(){
		String date = "";
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		date = dateFormat.format(fecha);
		return date;
	}
}
