package com.naarasalonyspa.diseno;

import android.content.Context;

import androidx.fragment.app.Fragment;

public abstract class SectionFragment extends Fragment {

    protected String sectionName;
    protected Context contexto;

    public SectionFragment(Context contexto, String sectionName) {
      
        this.contexto = contexto;
        this.sectionName = sectionName;
        
    }
   
}
