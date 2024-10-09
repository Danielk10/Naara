package com.naarasalonyspa.naara;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import com.naarasalonyspa.diseno.SectionsPagerAdapter;
import com.naarasalonyspa.contenido.Inicio;
import com.naarasalonyspa.contenido.AcercaDe;
import com.naarasalonyspa.contenido.Precios;
import com.naarasalonyspa.contenido.Servicios;
import com.naarasalonyspa.contenido.Contacto;
import com.naarasalonyspa.servidor.FetchServiciosTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "447c7f85-0c9e-4470-a7b3-86c55255b873",
                                  Analytics.class, Crashes.class);
                                  
// Crear ConstraintLayout como el contenedor principal
        ConstraintLayout mainLayout = new ConstraintLayout(this);
        mainLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, 
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));

// Crear TabLayout
        TabLayout tabLayout = new TabLayout(this);
        tabLayout.setId(View.generateViewId());
        tabLayout.setBackgroundColor(0xFF41B7FF); // Color de fondo #41B7FF
        ConstraintLayout.LayoutParams tabLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        mainLayout.addView(tabLayout, tabLayoutParams);
        
  // Crear ViewPager
          ViewPager viewPager = new ViewPager(this);
          viewPager.setId(View.generateViewId());
          ConstraintLayout.LayoutParams viewPagerParams = new ConstraintLayout.LayoutParams(
                  ConstraintLayout.LayoutParams.MATCH_PARENT,
                  0
          );
          viewPagerParams.topToBottom = tabLayout.getId();
          viewPagerParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
          viewPagerParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
          viewPagerParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
          viewPagerParams.height = 0; // Asigna el peso con constraints
          mainLayout.addView(viewPager, viewPagerParams);
       

        // Crear lista de fragmentos para cada sección
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Inicio(this,"Inicio"));
        fragments.add(new AcercaDe(this,"Acerca de"));
        fragments.add(new Servicios(this,"Servicios"));
        fragments.add(new Precios(this,"Precios"));
        fragments.add(new Contacto(this,"Contacto"));

        // Configurar adaptador del ViewPager
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        
        tabLayout.setupWithViewPager(viewPager);
        
        setContentView(mainLayout);
        
// Esto es importante para que el menú se muestre
        invalidateOptionsMenu();
        
// Llamada a la API
        new FetchServiciosTask().execute("http://127.0.0.1:8000/servicios/");
     
    }
    
    
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Agregar elementos del menú programáticamente
        MenuItem menuServicios = menu.add(Menu.NONE, 1, 1, "Servicios");
        MenuItem menuContacto = menu.add(Menu.NONE, 2, 2, "Contacto");

        // Configurar acciones de los elementos
        menuServicios.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, "Servicios seleccionados", Toast.LENGTH_SHORT).show();
            return true;
        });

        menuContacto.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, "Contacto seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar eventos del menú
        switch (item.getItemId()) {
            case 1: // Servicios
                Toast.makeText(this, "Servicios seleccionados", Toast.LENGTH_SHORT).show();
                return true;
            case 2: // Contacto
                Toast.makeText(this, "Contacto seleccionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}