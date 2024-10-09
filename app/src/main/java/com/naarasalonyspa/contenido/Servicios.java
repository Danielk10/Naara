package com.naarasalonyspa.contenido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.TextView;
import android.widget.ImageView;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONArray;
import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.Toast;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.naarasalonyspa.diseno.AdaptadorViewPager;
import com.naarasalonyspa.tarjetas.Servicio;
import android.app.Activity;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;



public class Servicios extends Fragment {

    private Context contexto;
    private String sectionName;
    private LinearLayout diseno;
    private Servicio servicio;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private AdaptadorViewPager adapter;
    private Bitmap imagen;
    private AppCompatActivity actividad;

    public Servicios(Context contexto, AppCompatActivity actividad,String sectionName) {
      
        this.contexto = contexto;
        this.sectionName = sectionName;
        this.imagen = null;
        this.actividad = actividad;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      
// ScrollView contenedor principal
        ScrollView scrollView = new ScrollView(contexto);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
// LinearLayout vertical para los elementos de servicio
        diseno = new LinearLayout(contexto);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
  
  
         scrollView.addView(diseno);

        // Ejecutar AsyncTask para obtener datos de la API
        new FetchServiciosTask().execute("https://www.naarasalonyspa.com/serviciosapi/");

        return scrollView;
    }

    private class FetchServiciosTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } catch (Exception e) {
                return null;
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    // Parsear JSON
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject servicio = jsonArray.getJSONObject(i);
                        String linkImagen = servicio.getString("imagen_servicio");

LinearLayout serviceLayout = new LinearLayout(contexto);
        serviceLayout.setOrientation(LinearLayout.HORIZONTAL);
        serviceLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        serviceLayout.setPadding(16, 16, 16, 16);



// ImageView para la imagen del servicio
			ImageView imageView = new ImageView(contexto);
			LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(200, 200);
			imageView.setLayoutParams(imageParams);

			new ImageDownloaderTask(imageView).execute(linkImagen);	


			// LinearLayout vertical para el texto del servicio
			LinearLayout textLayout = new LinearLayout(contexto);
			textLayout.setOrientation(LinearLayout.VERTICAL);
			textLayout.setLayoutParams(new LinearLayout.LayoutParams(
										   LinearLayout.LayoutParams.MATCH_PARENT,
										   LinearLayout.LayoutParams.WRAP_CONTENT
									   ));
			textLayout.setPadding(16, 0, 0, 0);

			// TextView para el nombre del servicio
			TextView nameTextView = new TextView(contexto);
			nameTextView.setText(servicio.getString("nombre_servicio"));
			nameTextView.setTextSize(18);
			nameTextView.setTypeface(null, Typeface.BOLD);

			// TextView para la descripción del servicio
			TextView descriptionTextView = new TextView(contexto);
			descriptionTextView.setText(servicio.getString("descripcion_servicio"));
			descriptionTextView.setTextSize(14);

			// TextView para el precio del servicio
			TextView priceTextView = new TextView(contexto);
			priceTextView.setText("Precio: " + servicio.getString("precio_servicio"));
			priceTextView.setTextSize(16);
			priceTextView.setTextColor(Color.parseColor("#FF5722")); // Ejemplo de color naranja

			// Añadir los TextViews al layout de texto
			textLayout.addView(nameTextView);
			textLayout.addView(descriptionTextView);
			textLayout.addView(priceTextView);

			// Añadir la imagen y el layout de texto al layout principal de cada servicio
			serviceLayout.addView(imageView);
			serviceLayout.addView(textLayout);

			diseno.addView(serviceLayout);


                        /*// Crear TextViews para mostrar el contenido
                        TextView tvNombre = new TextView(contexto);
                        tvNombre.setText("Nombre: " + servicio.getString("nombre_servicio"));
                        tvNombre.setTextSize(18);

                        TextView tvDescripcion = new TextView(contexto);
                        tvDescripcion.setText("Descripción: " + servicio.getString("descripcion_servicio"));
                        tvDescripcion.setTextSize(16);

                        TextView tvFechaCreacion = new TextView(contexto);
                        tvFechaCreacion.setText("Fecha de Creación: " + servicio.getString("fecha_de_creacion"));

                        TextView tvPrecio = new TextView(contexto);
                        tvPrecio.setText("Precio: $" + servicio.getString("precio_servicio"));
                        diseno.addView(tvNombre);
                        diseno.addView(tvDescripcion);
                      diseno.addView(tvFechaCreacion);
                        diseno.addView(tvPrecio);

                        // Descargar y mostrar la imagen
                        new ImageDownloaderTask().execute(linkImagen);
                        
                            /* viewPager = new ViewPager(contexto);
                             viewPager.setId(View.generateViewId());
                         diseno.addView(viewPager);
                    
                               fragments.add(new Servicio(contexto,servicio.getString("nombre_servicio"),servicio.getString("descripcion_servicio"),servicio.getString("precio_servicio"),imagen));
                                         fragments = new ArrayList<>();
                                    
                                                            AdaptadorViewPager adapter = new AdaptadorViewPager(actividad.getSupportFragmentManager(), fragments);
                                                            viewPager.setAdapter(adapter);
                                                         
                              
                               
                            */   

                    }
                } catch (Exception e) {
                    Toast.makeText(contexto, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(contexto, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Clase para descargar la imagen en segundo plano
    private class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
      
      ImageView imageView;
      
      
      			public ImageDownloaderTask(ImageView imageView) {
      				this.imageView = imageView;
      			}
      

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            				if (result != null) {
            					imageView.setImageBitmap(result);
            				}
            
        }
    }
}
