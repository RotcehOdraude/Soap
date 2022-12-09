package dgtic.unam.soap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dgtic.unam.soap.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val executor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val datoOne = binding.parameterOne.text.toString().trim()
            val datoTwo = binding.parameterTwo.text.toString().trim()
            getService(datoOne, datoTwo)
        }

    }

    private fun getService(datoOne: String, datoTwo: String) {
        executor.execute {
            val response = CallService().callApi(Utils.METHOD_ADD, datoOne, datoTwo)
            myHandler.post {
                try {
                    binding.textView.text = response
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}