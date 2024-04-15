package com.example.traffic_lights_shamilova

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.traffic_lights_shamilova.databinding.ActivityMainBinding

private lateinit var red_Circle: ImageView
private lateinit var yellow_Circle: ImageView
private lateinit var green_Circle: ImageView
private var currentLight = 1
private var color_red = Color.RED
private var color_yellow = Color.GRAY
private var color_green = Color.GRAY

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        red_Circle = binding.redCircle
        yellow_Circle = binding.yellowCircle
        green_Circle = binding.greenCircle
        red_Circle.setColorFilter(color_red)

        binding.changeColor.setOnClickListener {
            changingColors()
        }

        if (savedInstanceState == null)
        {
            color_red = ContextCompat.getColor(red_Circle.context, R.color.red)
            color_yellow = ContextCompat.getColor(yellow_Circle.context, R.color.gray)
            color_green = ContextCompat.getColor(green_Circle.context, R.color.gray)
        }
        else
        {
            color_red = savedInstanceState.getInt(KEY_CIRCLE_RED)
            color_yellow = savedInstanceState.getInt(KEY_CIRCLE_YELLOW)
            color_green = savedInstanceState.getInt(KEY_CIRCLE_GREEN)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CIRCLE_RED, color_red)
        outState.putInt(KEY_CIRCLE_YELLOW, color_yellow)
        outState.putInt(KEY_CIRCLE_GREEN, color_green)
    }

    private fun renderState() = with(binding)
    {
        redCircle.setColorFilter(color_red)
        yellowCircle.setColorFilter(color_yellow)
        greenCircle.setColorFilter(color_green)
    }

    companion object {
        @JvmStatic private val KEY_CIRCLE_RED = "RED_CIRCLE"
        @JvmStatic private val KEY_CIRCLE_YELLOW = "YELLOW_CIRCLE"
        @JvmStatic private val KEY_CIRCLE_GREEN = "GREEN_CIRCLE"
    }

    private fun changingColors() {
        when (currentLight) {
            1 -> {
                color_red = Color.GRAY
                red_Circle.setColorFilter(color_red)
                color_yellow = Color.YELLOW
                yellow_Circle.setColorFilter(color_yellow)
                currentLight = 2
            }

            2 -> {
                color_yellow = Color.GRAY
                yellow_Circle.setColorFilter(color_yellow)
                color_green = Color.GREEN
                green_Circle.setColorFilter(color_green)
                currentLight = 3
            }

            3 -> {
                color_green = Color.GRAY
                green_Circle.setColorFilter(color_green)
                color_yellow = Color.YELLOW
                yellow_Circle.setColorFilter(color_yellow)
                currentLight = 4
            }

            4 -> {
                color_yellow = Color.GRAY
                yellow_Circle.setColorFilter(color_yellow)
                color_red = Color.RED
                red_Circle.setColorFilter(color_red)
                currentLight = 1
            }
        }
    }
}