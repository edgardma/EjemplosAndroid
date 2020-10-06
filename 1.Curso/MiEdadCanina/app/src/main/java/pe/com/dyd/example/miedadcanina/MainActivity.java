package pe.com.dyd.example.miedadcanina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CANT_ANIOS_PERRO = 6;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView answerText = (TextView) findViewById(R.id.answer_text);

        final ImageView dogImage = (ImageView) findViewById(R.id.dog_image);
        dogImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dog));

        final EditText edadEdit = (EditText) findViewById(R.id.edad_edit);

        final Button pulsameButton = (Button) findViewById(R.id.pulsame_button);

        Log.d(TAG, "La app fue creada");

        pulsameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edadString = edadEdit.getText().toString();
                try {
                    int edadInt = Integer.parseInt(edadString);
                    edadInt = edadInt * CANT_ANIOS_PERRO;
                    answerText.setText("Si fueras perro, tu edad sería de: " + edadInt);
                    Log.i(TAG, "Se calculó la edad");
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Se ingresó un texto que no es número.");
                    Toast.makeText(MainActivity.this, "Por favor, ingrese un número entero", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}