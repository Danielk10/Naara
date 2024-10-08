package com.naarasalonyspa.contenido;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.content.Context;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Inicio extends Fragment {
  
    protected String sectionName;
    protected Context contexto;


    public Inicio(Context contexto, String sectionName) {
      
        this.contexto = contexto;
        this.sectionName = sectionName;
      
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull android.view.LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(contexto);
        textView.setText(sectionName);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(24);
        return textView;
    }
}
