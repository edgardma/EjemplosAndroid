package pe.com.dyd.example.registrodesuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString(MainActivity.NAME);
        String lastName = extras.getString(MainActivity.LAST_NAME);
        String heroName = extras.getString(MainActivity.HERO_NAME);
        String age = extras.getString(MainActivity.AGE);
        String address = extras.getString(MainActivity.ADDRESS);
        String city = extras.getString(MainActivity.CITY);

        TextView heroData = (TextView)findViewById(R.id.hero_data);
        heroData.setText(name + ", " +
                lastName + ", " +
                heroName + ", " +
                age + ", " +
                address + ", " +
                city);
    }
}