package pe.com.dyd.example.registrodesuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String LAST_NAME = "last_name";
    public static final String HERO_NAME = "hero_name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = (Button) findViewById(R.id.register_button);

        EditText nameEdit = (EditText)findViewById(R.id.name_edit);
        EditText lastNameEdit = (EditText)findViewById(R.id.last_name_edit);
        EditText heroNameEdit = (EditText)findViewById(R.id.hero_name_edit);
        EditText ageEdit = (EditText)findViewById(R.id.age_edit);
        EditText addressEdit = (EditText)findViewById(R.id.address_edit);
        EditText cityEdit = (EditText)findViewById(R.id.city_edit);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);

                detailIntent.putExtra(NAME, nameEdit.getText().toString());
                detailIntent.putExtra(LAST_NAME, lastNameEdit.getText().toString());
                detailIntent.putExtra(HERO_NAME, heroNameEdit.getText().toString());
                detailIntent.putExtra(AGE, ageEdit.getText().toString());
                detailIntent.putExtra(ADDRESS, addressEdit.getText().toString());
                detailIntent.putExtra(CITY, cityEdit.getText().toString());

                startActivity(detailIntent);
            }
        });
    }
}