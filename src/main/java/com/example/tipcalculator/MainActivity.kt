package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.time.temporal.TemporalAmount

private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount :EditText
    private lateinit var seekBarTip :SeekBar
    private lateinit var tvTipPercent :TextView
    private lateinit var tvTipAmount :TextView
    private lateinit var tvTotalAmount :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercent = findViewById(R.id.tvTipPrecentage)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvToalAmount)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvTipPercent.text = "$progress%"
                computeTipAmount()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        etBaseAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                computeTipAmount()
            }

        })
    }
    private fun computeTipAmount(){
        if(etBaseAmount.text.isEmpty()){
            tvTipAmount.text =" "
            tvTotalAmount.text =" "
            return
        }

        //1.Get the value of the base and tip percent
        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercentage = seekBarTip.progress
        //2. Compute the tip and total
        val tipAmount = baseAmount * tipPercentage /100
        val totalAmount = baseAmount +tipAmount
        //3. Update the UI
        tvTipAmount.text = tipAmount.toString()
        tvTotalAmount.text = totalAmount.toString()
    }
}