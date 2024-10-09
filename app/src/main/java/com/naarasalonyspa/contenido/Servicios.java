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
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Servicios extends Fragment {

    private Context contexto;
    private LinearLayout diseno;

    public Servicios(Context contexto) {
        this.contexto = contexto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Crear LinearLayout de manera programática
        diseno = new LinearLayout(contexto);
        diseno.setOrientation(LinearLayout.VERTICAL);
        diseno.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        diseno.setBackgroundColor(0xFFDFA5A5); // Color de fondo #DFA5A5

        // Ejecutar AsyncTask para obtener datos de la API
        new FetchServiciosTask().execute("https://www.naarasalonyspa.com/serviciosapi/");

        return diseno;
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

                        // Crear TextViews para mostrar el contenido
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

                        // Añadir TextViews al layout
                        diseno.addView(tvNombre);
                        diseno.addView(tvDescripcion);
                        diseno.addView(tvFechaCreacion);
                        diseno.addView(tvPrecio);

                        // Descargar y mostrar la imagen
                        new ImageDownloaderTask().execute(linkImagen);
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
                // Crear ImageView para la imagen del servicio
                ImageView ivImagenServicio = new ImageView(contexto);
                ivImagenServicio.setAdjustViewBounds(true);
                ivImagenServicio.setImageBitmap(result);
                diseno.addView(ivImagenServicio);
            }
        }
    }
}
