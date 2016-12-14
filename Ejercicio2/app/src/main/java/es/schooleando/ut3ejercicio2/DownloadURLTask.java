package es.schooleando.ut3ejercicio2;

import android.os.AsyncTask;

/**
 * Created by ruben on 18/11/16.
 */

public class DownloadURLTask extends AsyncTask {
	//public class DownloadURLTask extends AsyncTask<String, Void, String> {

    private static final String DEBUG_TAG = "HttpExample";

    ProgressDialog progressDialog;


    @Override
    protected Object doInBackground(Object[] params) {
        try {
            int lenght = tryGetFileSize(params[0].toString());
            Log.i(DEBUG_TAG, "lenght = " + String.valueOf(lenght));

            return downloadUrl(params[0].toString());
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
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


    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


}

