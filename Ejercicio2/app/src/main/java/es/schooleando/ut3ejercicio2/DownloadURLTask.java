package es.schooleando.ut3ejercicio2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.sip.SipAudioCall;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.id.input;


/**
 * Created by ruben on 18/11/16.
 */

//public class DownloadURLTask extends AsyncTask {
public class DownloadURLTask extends AsyncTask<Object, Integer, Bitmap> {

    private Context mContext;

    public DownloadURLTask(Context context) {
        mContext = context;
    }


    @Override
    protected Bitmap doInBackground(Object[] params) {
        int lenght = tryGetFileSize(params[0].toString());

        Log.i("doInBackground", "lenght = " + String.valueOf(lenght));


        for (int i = 0; i < lenght; i++) {
            publishProgress((int) ((i / (float) lenght) * 100));
        }

        return downloadImage(params[0].toString());
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //   Log.i("onProgressUpdate", values[0].toString());
        ((DownloadActivity) mContext).progreso.setProgress(values[0]);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((DownloadActivity) mContext).progreso.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onPostExecute(Bitmap bmp) {
        super.onPostExecute(bmp);

        Log.i("onPostExecute", bmp.toString());
        ((DownloadActivity) mContext).progreso.setVisibility(View.INVISIBLE);
        ((DownloadActivity) mContext).imagen.setImageBitmap(bmp);
    }


    private int tryGetFileSize(String _url) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }


    public Bitmap downloadImage(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
