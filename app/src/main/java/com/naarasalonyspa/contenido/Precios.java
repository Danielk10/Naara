package com.naarasalonyspa.contenido;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;


public class Precios extends Fragment {
  
    private String sectionName;
    private Context contexto;


    public Precios(Context contexto, String sectionName) {
      
        this.contexto = contexto;
        this.sectionName = sectionName;
      
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Crear ConstraintLayout de manera programática
        ConstraintLayout layout = new ConstraintLayout(contexto);
        layout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));
        layout.setBackgroundColor(0xFFFFA5A5); // Color de fondo #FFA5A5

        // Crear el botón de manera programática
        Button button = new Button(contexto);
        button.setId(View.generateViewId());
        button.setText("Click here");
        button.setTextSize(24);
        
        // Añadir el botón al layout
        layout.addView(button);

        // Configurar las restricciones del botón
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.constrainWidth(button.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 32);
        constraintSet.connect(button.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 32);
        constraintSet.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

        constraintSet.applyTo(layout);

        return layout;
    }
}