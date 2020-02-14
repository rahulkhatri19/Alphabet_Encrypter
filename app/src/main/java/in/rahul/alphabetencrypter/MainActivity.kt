package `in`.rahul.alphabetencrypter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_encrypt.setOnClickListener {
            startActivity(Intent(this, EncryptDecryptActivity::class.java).putExtra("task", true))
        }
        btn_decrypt.setOnClickListener {
            startActivity(Intent(this, EncryptDecryptActivity::class.java).putExtra("task", false))
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
