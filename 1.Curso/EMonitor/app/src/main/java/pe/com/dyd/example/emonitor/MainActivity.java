package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private String readFromStream(InputStream inputStream) {
        return "";
    }
}