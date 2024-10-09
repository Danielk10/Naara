import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;


// Clase para descargar la imagen en segundo plano
		public  class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
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