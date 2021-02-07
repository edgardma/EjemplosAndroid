package pe.com.dyd.example.emonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        EarthquakeEntity earthquakeEntity = extras.getParcelable(MainActivity.SELECT_EARTHQUEAKE);

        if (earthquakeEntity != null) {
            TextView magnitudeTextView = (TextView) findViewById(R.id.eq_detail_magnitude);
            TextView longitudeTextView = (TextView) findViewById(R.id.eq_detail_longitude);
            TextView latitudeTextView = (TextView) findViewById(R.id.eq_detail_latitude);
            TextView placeTextView = (TextView) findViewById(R.id.eq_detail_place);
            TextView dateTimeTextView = (TextView) findViewById(R.id.eq_detail_date_time);

            magnitudeTextView.setText(String.valueOf(earthquakeEntity.getMagnitude()));
            longitudeTextView.setText(earthquakeEntity.getLongitude());
            latitudeTextView.setText(earthquakeEntity.getLatitude());
            placeTextView.setText(earthquakeEntity.getPlace());
            dateTimeTextView.setText(String.valueOf(earthquakeEntity.getDateTime()));
        }
    }
}