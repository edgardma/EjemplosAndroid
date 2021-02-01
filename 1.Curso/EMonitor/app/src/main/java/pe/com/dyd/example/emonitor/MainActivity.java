package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import pe.com.dyd.example.emonitor.General.DownloadAsyncTask;
import pe.com.dyd.example.emonitor.adapter.EarthquakeAdapter;
import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class MainActivity extends AppCompatActivity implements DownloadAsyncTask.DownloadEqsInterface {

    public static final String SELECT_EARTHQUEAKE = "select_earthqueake";
    private ListView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquakeListView = (ListView) findViewById(R.id.earthquake_list_view);

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
    public void onEqsDownloaded(ArrayList<EarthquakeEntity> earthquakeList) {
        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, R.layout.eq_list_item, earthquakeList);
        earthquakeListView.setAdapter(earthquakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthquakeEntity selectEarthquakeEntity = earthquakeAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(SELECT_EARTHQUEAKE, selectEarthquakeEntity);

                startActivity(intent);
            }
        });
    }
}