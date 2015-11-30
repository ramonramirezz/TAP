package com.example.adapters;

public class ItemPregunta {
   private String pregunta;
   private int selected_radiobutton_id;
   private int score;
	
    public ItemPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public int getSelected_radiobutton_id() {
        return selected_radiobutton_id;
    }

    public void setSelected_radiobutton_id(int selected_radiobutton_id) {
        this.selected_radiobutton_id = selected_radiobutton_id;
    }
    public int getScore(){
    	return score;
    }
    
    public void setScore(int score){
    	this.score = score;
    }

    
}
