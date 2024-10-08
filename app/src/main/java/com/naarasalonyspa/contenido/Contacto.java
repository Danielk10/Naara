package com.naarasalonyspa.contenido;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.naarasalonyspa.diseno.SectionFragment;


public Contacto extends SectionFragment {


    public Contacto(Context contexto, String sectionName) {
      super(contexto,sectionName);
      
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
