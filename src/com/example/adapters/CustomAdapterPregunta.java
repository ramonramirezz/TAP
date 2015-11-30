package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tap.R;

import java.util.List;

/**
 * Created by Ramon on 28/11/15.
 */

public class CustomAdapterPregunta extends ArrayAdapter<ItemPregunta> {

    Context context;
    List<ItemPregunta> ListItemList;
    int layoutResID;
    
    
    public CustomAdapterPregunta(Context context, int layoutResourceID,
                                 List<ItemPregunta> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.ListItemList = listItems;
        this.layoutResID = layoutResourceID;
        

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ItemHolder pregunta;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            pregunta = new ItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            pregunta.pregunta = (TextView) view
                    .findViewById(R.id.pregunta);
            
            pregunta.respuestas = (RadioGroup) view
                    .findViewById(R.id.radiogroup);
            
            view.setTag(pregunta);

        } else {
            pregunta = (ItemHolder) view.getTag();

        }

      final ItemPregunta dItem = (ItemPregunta) this.ListItemList.get(position);
      
          
        pregunta.pregunta.setText(dItem.getPregunta());

        pregunta.respuestas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
        	private boolean isChacked = true;
        	
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	
                switch(checkedId){
                case R.id.rdNunca:
                	dItem.setScore(0);                	
                	break;

                case R.id.rdRara:
                	dItem.setScore(1);                	
                	break;
                	
                case R.id.rdAlgunas:
                	dItem.setScore(2);                	
                	break;	
                	
                case R.id.rdBastantes:
                	dItem.setScore(3);                	
                	break;
                	
                case R.id.rdSiempre:
                	dItem.setScore(4);                	
                	break;
                }
                    dItem.setSelected_radiobutton_id(checkedId);

            }
        });

        pregunta.respuestas.check(dItem.getSelected_radiobutton_id());
        

        return view;
    }


    private class ItemHolder {
        TextView pregunta;
        RadioGroup respuestas;
    }

}
