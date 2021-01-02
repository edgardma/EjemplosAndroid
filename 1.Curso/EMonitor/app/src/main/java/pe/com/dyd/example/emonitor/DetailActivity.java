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

        TextView eqDetailTextView = (TextView) findViewById(R.id.eq_detail_text_view);

        Bundle extras = getIntent().getExtras();
        EarthquakeEntity earthquakeEntity = extras.getParcelable(MainActivity.SELECT_EARTHQUEAKE);

        if (earthquakeEntity != null) {
            eqDetailTextView.setText(earthquakeEntity.getMagnitude() + " : " +
                    earthquakeEntity.getPlace());
        }
    }
}