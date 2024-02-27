package br.com.menezesmarlon.temporizador

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.menezesmarlon.temporizador.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            try {
                val number = binding.editTime.text.toString().toLong()

                timer?.cancel()

                timer = object : CountDownTimer(number * 60 * 1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        var seconds = millisUntilFinished / 1000
                        var minutes = seconds / 60
                        seconds = seconds % 60
                        binding.textTime.text = String.format("%02d:%02d", minutes, seconds)
                    }

                    override fun onFinish() {
                        binding.textTime.text = "Tempo Esgotado!"
                    }
                }
                timer?.start()

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Insira um número válido!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnStop.setOnClickListener {
            timer?.cancel()
            Toast.makeText(this, "Timer finalizado pelo usuário!", Toast.LENGTH_SHORT).show()
            binding.textTime.text = "00:00"
        }
    }
}
