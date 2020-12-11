package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pe.com.dyd.example.emonitor.adapter.EarthquakeAdapter;
import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView earthquake_list_view = (ListView) findViewById(R.id.earthquake_list_view);
        ArrayList<EarthquakeEntity> earthquakeList = new ArrayList<>();

        earthquakeList.add(new EarthquakeEntity("4.6", "97 km S of Wonosari, Indonesia"));
        earthquakeList.add(new EarthquakeEntity("2.3", "16 km S of Joshua Tree, CA"));
        earthquakeList.add(new EarthquakeEntity("3.1", "97 km S of Wonosari, Indonesia"));

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, R.layout.eq_list_item, earthquakeList);
        earthquake_list_view.setAdapter(earthquakeAdapter);
        /*
        ListView earthquake_list_view = (ListView) findViewById(R.id.earthquake_list_view);
        ArrayList<String> countryList = new ArrayList<>();

        countryList.add("México");
        countryList.add("Venezuela");
        countryList.add("Colombia");
        countryList.add("España");
        countryList.add("Perú");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countryList);
        earthquake_list_view.setAdapter(countryAdapter);

         */
    }
}