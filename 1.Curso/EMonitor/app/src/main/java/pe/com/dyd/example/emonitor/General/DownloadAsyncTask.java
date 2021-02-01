package pe.com.dyd.example.emonitor.General;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class DownloadAsyncTask extends AsyncTask<URL, Void, ArrayList<EarthquakeEntity>> {

    public DownloadEqsInterface delegate;

    public interface DownloadEqsInterface {
        void onEqsDownloaded(ArrayList<EarthquakeEntity> eqsData);
    }

    @Override
    protected ArrayList<EarthquakeEntity> doInBackground(URL... urls) {
        String eqData = "";
        ArrayList<EarthquakeEntity> eqList = null;

        try {
            eqData = downloadData(urls[0]);
            eqList = parseDataFromJson(eqData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return eqList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<EarthquakeEntity> earthquakeList) {
        super.onPostExecute(earthquakeList);

        delegate.onEqsDownloaded(earthquakeList);
    }

    private ArrayList<EarthquakeEntity> parseDataFromJson(String eqsData) {
        Log.d("DownloadAsyncTask", eqsData);
        ArrayList<EarthquakeEntity> earthquakeList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(eqsData);
            JSONArray featuresJsonArray =  jsonObject.getJSONArray("features");

            for(int i = 0; i < featuresJsonArray.length(); i++) {
                JSONObject featureJsonObject = featuresJsonArray.getJSONObject(i);
                JSONObject propertiesJsonObject = featureJsonObject.getJSONObject("properties");
                Double magnitude = propertiesJsonObject.getDouble("mag");
                String place = propertiesJsonObject.getString("place");
                earthquakeList.add(new EarthquakeEntity(magnitude, place));

                Log.d("DownloadAsyncTask", magnitude + "-" + place);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return earthquakeList;
    }

    private String downloadData(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000); // Miliseconds
            urlConnection.setConnectTimeout(15000); // Miliseconds
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }
}
