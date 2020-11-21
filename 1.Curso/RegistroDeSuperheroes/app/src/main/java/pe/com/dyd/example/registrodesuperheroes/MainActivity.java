package pe.com.dyd.example.registrodesuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.com.dyd.example.registrodesuperheroes.bean.Superheroe;

public class MainActivity extends AppCompatActivity {

    public static final String SUPER_HERO_KEY = "super_hero";

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

                final Superheroe ironMan = new Superheroe(nameEdit.getText().toString(),
                        lastNameEdit.getText().toString(),
                        heroNameEdit.getText().toString(),
                        ageEdit.getText().toString(),
                        addressEdit.getText().toString(),
                        cityEdit.getText().toString());

                detailIntent.putExtra(SUPER_HERO_KEY, ironMan);

                startActivity(detailIntent);
            }
        });
    }
}