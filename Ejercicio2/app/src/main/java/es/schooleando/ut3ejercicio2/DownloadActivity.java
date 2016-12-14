package es.schooleando.ut3ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DownloadActivity extends AppCompatActivity {

	TextView txt_url;
	ImageView imagen;
	Button btn_descargar;
	ProgressBar progreso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);

		imagen = (ImageView) findViewById(R.id.imageView);
		btn_descargar = (Button) findViewById(R.id.button);
		txt_url = (TextView) findViewById(R.id.textURL);
		progreso = (ProgressBar) findViewById(R.id.progressBar);

		btn_descargar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String stringUrl = txt_url.getText().toString();
				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					new DownloadURLTask().execute(stringUrl);

				} else {
					txt_url.setText("No network connection available.");

				}

			}

		});

		/*
		 * @Override public void onImageLoaded (Bitmap bitmap){
		 * 
		 * imagen.setImageBitmap(bitmap); }
		 */
	}

}
