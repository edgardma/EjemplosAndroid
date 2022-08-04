package pe.com.dyd.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(5000)
        setTheme(R.style.Theme_Splashscreen)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}