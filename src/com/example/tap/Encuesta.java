package com.example.tap;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
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

public class Encuesta extends Activity {
    
	String[] question = {"¿Siente que su familiar le pide más ayuda de la que realmente necesita?", 
			"¿Siente que debido al tiempo que dedica a su familiar no tiene suficiente tiempo para usted?",
			"¿Se siente agobiado entre cuidar a su familiar y tratar de cumplir otras responsabilidades en su trabajo o su familia?",
			"¿Se siente avergonzado por la conducta de su familiar?",
			"¿Se siente irritado(a) (molesto) cuando está cerca de su familiar?",
			"¿Cree que su situación actual afecta negativamente a su relación con otros miembros de su familia o amigos?",
			"¿Siente temor por el futuro que le espera a su familiar?",
			"¿Siente que su familiar depende de usted?",
			"¿Se siente tenso cuando está cerca de su familiar?",
			"¿Siente que su salud se ha resentido por cuidar a su familiar?",
			"¿Siente que no tiene la vida privada que desearía a causa de su familiar?",
			"¿Cree que su vida social se ha visto afectada por cuidar a su familiar?",
			"¿Se siente incómodo por desatender a sus amistades debido a su familiar?",
			"¿Siente que su familiar espera que usted le cuide, como si usted fuera la única persona de quien depende?",
			"¿Siente que no dispone de suficiente dinero para cuidar a su familiar además de sus otros gastos?",
			"¿Cree que será incapaz de cuidarle/a por mucho más tiempo?",
			"¿Siente que ha perdido el control de su vida desde la enfermedad de su familiar?",
			"¿Desearía poder encargar el cuidado de su familiar a otra persona?",
			"¿Se siente inseguro(a) sobre que hacer con su familiar?",
			"¿Siente que debería hacer más de lo que hace por su familiar?",
			"¿Cree que podría cuidar mejor a su familiar mejor de lo que hace?",
			"En general ¿se siente muy sobrecargado al tener que cuidar de su familiar?"};
	
	private LinearLayout mLnScroll;
	private ListView mListView;
	private ArrayList<String> myArrayList;
	private ArrayList<String> myArrayListTemp;
	LinearLayout ln;
	int rowSize = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta_new);
        
        mLnScroll = (LinearLayout) findViewById(R.id.linear_scroll);
        mListView = (ListView) findViewById(R.id.listView1);
        
        myArrayList = new ArrayList<String>();
        myArrayListTemp = new ArrayList<String>();
        
    
        
        for (String quest : question){
        	myArrayList.add(quest);
        }

        int rem = myArrayList.size() % rowSize;
        if (rem > 0) {
			for (int i = 0; i < rowSize - rem; i++) {
				myArrayList.add("");
			}
		}
        addItem(0);
        
        int size = myArrayList.size() / rowSize;
        
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
			myArrayListTemp.add(j, myArrayList.get(i));
			i = i + 1;
		}
		setView();
	}


	private void setView() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Encuesta.this, android.R.layout.simple_list_item_1, myArrayListTemp);
	    mListView.setAdapter(adapter);
	 
	    
	}
    
//	private void createRadiosButtons(){
//		final RadioButton[] rb = new RadioButton[5]; 
//		RadioGroup rg = new RadioGroup(this);
//		rg.setOrientation(RadioGroup.HORIZONTAL);
//		for (int i = 0; i < rb.length; i++) {
//			rb[i] = new RadioButton(this);
//			rg.addView(rb[i]);
//			rb[i].setText("Test");
//		}
//		ln.addView(rg);
//		
//	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
