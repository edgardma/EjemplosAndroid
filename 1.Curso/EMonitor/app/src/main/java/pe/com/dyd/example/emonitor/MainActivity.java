package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import pe.com.dyd.example.emonitor.General.DownloadAsyncTask;
import pe.com.dyd.example.emonitor.adapter.EarthquakeAdapter;
import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class MainActivity extends AppCompatActivity implements DownloadAsyncTask.DownloadEqsInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView earthquake_list_view = (ListView) findViewById(R.id.earthquake_list_view);
        ArrayList<EarthquakeEntity> earthquakeList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            earthquakeList.add(new EarthquakeEntity("4.6", "97 km S of Wonosari, Indonesia"));
            earthquakeList.add(new EarthquakeEntity("2.3", "16 km S of Joshua Tree, CA"));
            earthquakeList.add(new EarthquakeEntity("3.1", "97 km S of Wonosari, Indonesia"));
        }

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, R.layout.eq_list_item, earthquakeList);
        earthquake_list_view.setAdapter(earthquakeAdapter);

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
    }
}