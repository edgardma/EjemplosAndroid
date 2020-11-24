package pe.com.dyd.example.registrodesuperheroes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pe.com.dyd.example.registrodesuperheroes.bean.Superheroe;

public class MainActivity extends AppCompatActivity {

    public static final String SUPER_HERO_KEY = "super_hero";
    private static final int CAMERA_REQUEST_CODE = 1000;
    private ImageView heroImage;

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

        heroImage = (ImageView)findViewById(R.id.hero_image);

        heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamara();
            }
        });

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

    private void openCamara() {
        try {
            Log.i("openCamara", "Inicio");
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            Log.i("openCamara", "Fin");
        }catch (Exception ex) {
            Log.w("openCamara", "Error: " + ex.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            Log.i("onActivityResult", "Inicio");
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CAMERA_REQUEST_CODE) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap)extras.get("data");

                heroImage.setImageBitmap(bitmap);
            }

            Log.i("onActivityResult", "Fin");
        }catch (Exception ex) {
            Log.w("onActivityResult", "Error: " + ex.toString());
        }

    }
}