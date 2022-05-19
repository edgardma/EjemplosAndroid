package pe.com.dyd.example.miedadcanina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import pe.com.dyd.example.miedadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final int CANT_ANIOS_PERRO = 7;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dogImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dog));
        Log.d(TAG, "La app fue creada");

        binding.pulsameButton.setOnClickListener(view -> {
            String edadString = binding.edadEdit.getText().toString();
            try {
                int edadInt = Integer.parseInt(edadString);
                edadInt = edadInt * CANT_ANIOS_PERRO;
                String respuesta = getString(R.string.answer);
                binding.answerText.setText(String.format(respuesta, String.valueOf(edadInt)));
                Log.i(TAG, "Se calculó la edad");
            } catch (NumberFormatException e) {
                Log.w(TAG, "Se ingresó un texto que no es número.");
                Toast.makeText(MainActivity.this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        });
    }

}