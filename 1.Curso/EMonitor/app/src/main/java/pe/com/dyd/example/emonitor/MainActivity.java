package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
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

        for (int i = 0; i < 10; i++) {
            earthquakeList.add(new EarthquakeEntity("4.6", "97 km S of Wonosari, Indonesia"));
            earthquakeList.add(new EarthquakeEntity("2.3", "16 km S of Joshua Tree, CA"));
            earthquakeList.add(new EarthquakeEntity("3.1", "97 km S of Wonosari, Indonesia"));
        }

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, R.layout.eq_list_item, earthquakeList);
        earthquake_list_view.setAdapter(earthquakeAdapter);

        try {
            downloadData(new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            Toast.makeText(this, "Hubo un error al descargar los datos de terremotos " + ex.toString(),
                    Toast.LENGTH_SHORT).show();
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