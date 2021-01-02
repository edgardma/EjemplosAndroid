package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import pe.com.dyd.example.emonitor.General.DownloadAsyncTask;
import pe.com.dyd.example.emonitor.adapter.EarthquakeAdapter;
import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class MainActivity extends AppCompatActivity implements DownloadAsyncTask.DownloadEqsInterface {

    private ListView earthquake_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquake_list_view = (ListView) findViewById(R.id.earthquake_list_view);

        DownloadAsyncTask downloadAsyncTask = null;
        try {
            downloadAsyncTask = new DownloadAsyncTask();
            downloadAsyncTask.delegate = this;
            downloadAsyncTask.execute(new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEqsDownloaded(String eqsData) {
        Log.d("MainActivity", eqsData);
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

                Log.d("MainActivity", magnitude + "-" + place);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, R.layout.eq_list_item, earthquakeList);
        earthquake_list_view.setAdapter(earthquakeAdapter);
    }
}