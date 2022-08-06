package pe.com.dyd.pushnotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val bundle : Bundle? = intent!!.extras

        if (bundle != null) {
            val name = bundle.getString("KEY_NAME")
            val email = bundle.getString("KEY_EMAIL")

            Toast.makeText(this, "$name \n$email", Toast.LENGTH_LONG).show()
        }
    }
}