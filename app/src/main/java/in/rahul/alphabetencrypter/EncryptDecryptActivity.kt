package `in`.rahul.alphabetencrypter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_encrypt_decrypt.*
import java.lang.Exception

class EncryptDecryptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypt_decrypt)

        if (intent.hasExtra("task")) {
            if (intent.getBooleanExtra("task", false)) {

                btn_encrypt.visibility = View.VISIBLE
                btn_decrypt.visibility = View.GONE
            } else {
                btn_encrypt.visibility = View.GONE
                btn_decrypt.visibility = View.VISIBLE
            }
        }

        et_message.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tv_result_message.text = getString(R.string.result_text)
            }

        })

        btn_encrypt.setOnClickListener {
            val stEncryptMessage = et_message.text.toString()
            if (stEncryptMessage.equals("")) {
                et_message.setError("Please Enter Message")
                et_message.requestFocus()
            } else {
                val stBuilder = StringBuilder()
                var count = 1
                for (i in 0 until stEncryptMessage.count()) {
                    Log.e("Main:", "${stEncryptMessage[i]}, i:$i")
                    if ((stEncryptMessage.count() - 1) != i && stEncryptMessage[i].equals(
                            stEncryptMessage[i + 1]
                        )
                    ) {
                        count++
                    } else {
                        stBuilder.append(stEncryptMessage[i])
                        stBuilder.append(count)
                        Log.e("mainAct:", stBuilder.toString())
                        count = 1
                    }

                }
                tv_result_message.text = stBuilder
            }
        }

        btn_decrypt.setOnClickListener {
            val stDecryptMessage = et_message.text.toString()
            if (stDecryptMessage.equals("") || stDecryptMessage.trim().length < 2) {
                et_message.setError("Please Enter valid Message")
                et_message.requestFocus()
            } else {
                val stBuilderDec = StringBuilder()
                try {
                    for (i in 0 until stDecryptMessage.count() step 2) {
                        for (j in 0 until stDecryptMessage[i + 1].toString().toInt()) {
                            stBuilderDec.append(stDecryptMessage[i])
                            Log.e(
                                "MainDec",
                                stBuilderDec.toString() + "j:$j, int:${stDecryptMessage[i + 1].toInt()}"
                            )
                        }
                    }
                    tv_result_message.text = stBuilderDec
                } catch (e:Exception){
                    Toast.makeText(this, "Invalid Text \nPlease check Message format", Toast.LENGTH_SHORT).show()
                }

            }
        }

        iv_copy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData =
                ClipData.newPlainText("Alpha_Enc_message", tv_result_message.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Message Copied to Clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
