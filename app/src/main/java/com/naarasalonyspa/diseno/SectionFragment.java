package com.naarasalonyspa.diseno;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.fragment.app.Fragment;

public abstract class SectionFragment extends Fragment {

    protected String sectionName;
    protected Context contecto;

    public SectionFragment(Context contecto, String sectionName) {
      
        this.contecto = contecto;
        this.sectionName = sectionName;
        
    }
   
}
