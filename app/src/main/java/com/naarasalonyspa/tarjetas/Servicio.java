package com.naarasalonyspa.tarjetas;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Servicio extends Fragment {
  
    private String sectionName;
    private Context contexto;
    private ViewGroup diseno;


    public Servicio(Context contexto, String sectionName) {
      
        this.contexto = contexto;
        this.sectionName = sectionName;
        
        diseno = new LinearLayout(contexto);
        		diseno.setLayoutParams(new LinearLayout.LayoutParams(
        								   LinearLayout.LayoutParams.MATCH_PARENT,
        								   LinearLayout.LayoutParams.MATCH_PARENT
        							   ));
        		diseno.setBackgroundColor(0xFFDFA5A5);
        
      
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        diseno.setBackgroundColor(0xFFFFA5A5); // Color de fondo #FFA5A5
        
        // Añadir el botón al layout
        diseno.addView(button);

        return diseno;
    }
}

