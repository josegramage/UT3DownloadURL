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
public class DownloadURLTask extends AsyncTask<Object, Integer, Object> {


    ProgressDialog progressDialog;

    //no funciona
    // Tengo que poner el View.OnclickListener para que pueda llamarlo desde la otra actividad
/*
    OnDataSendToActivity dataSendToActivity;
    public DownloadURLTask(View.OnClickListener activity){
        dataSendToActivity = (OnDataSendToActivity)activity;
    }
*/

    @Override
    protected Object doInBackground(Object[] params) {
        int lenght = tryGetFileSize(params[0].toString());
        Log.i("doInBackground", "lenght = " + String.valueOf(lenght));
        //de momento lo del progressbar aún no está

            return downloadImage(params[0].toString());
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //le llega la imagen
        Log.i("onPostExcute", o.toString());

    /*    Intent i = new Intent(this, MainActivity.class);
        i.putExtra("imagen", o.toString());
        startActivity(i);
*/
    //    dataSendToActivity.sendData(o.toString());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
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


    public Bitmap downloadImage(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
            return  myBitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
