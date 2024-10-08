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


import java.util.ArrayList;
import java.util.List;
import com.naarasalonyspa.diseno.SectionsPagerAdapter;
import com.naarasalonyspa.contenido.Inicio;
import com.naarasalonyspa.contenido.AcercaDe;
import com.naarasalonyspa.contenido.Precios;
import com.naarasalonyspa.contenido.Servicios;
import com.naarasalonyspa.contenido.Contacto;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "447c7f85-0c9e-4470-a7b3-86c55255b873",
                          Analytics.class, Crashes.class);
       
        // Crear ViewPager de manera programática
        ViewPager viewPager = new ViewPager(this);
        viewPager.setId(View.generateViewId());

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
        
        
      setContentView(viewPager);

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
}