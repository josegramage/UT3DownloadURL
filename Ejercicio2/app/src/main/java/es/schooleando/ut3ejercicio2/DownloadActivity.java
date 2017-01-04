package es.schooleando.ut3ejercicio2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class DownloadActivity extends Activity implements OnDataSendToActivity{

    TextView txt_url;
    TextView prueba;
    ImageView imagen;
    Button btn_descargar;
    ProgressBar progreso;
    Context context;

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
                ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadURLTask(DownloadActivity.this).execute(stringUrl);
                //    new DownloadURLTask(this).execute(new String[]{stringUrl});
                } else {
                    txt_url.setText("No network connection available.");

                }

            }

        });
    }

    @Override
    public void sendData(String obj) {
      //no lo llego a utilizar
    }
}
