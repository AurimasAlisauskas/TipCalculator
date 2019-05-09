package com.example.tipcalculator;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView resultText;
    private TextView textViewSeekBar;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = findViewById(R.id.billAmountID);
        seekBar = findViewById(R.id.seekBarID);
        calculateButton = findViewById(R.id.calculateButtonID);
        resultText = findViewById(R.id.resultID);
        textViewSeekBar = findViewById(R.id.textViewSeekBarID);
        totalAmount = findViewById(R.id.totalTextViewID);

        calculateButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBar.setText(seekBar.getProgress() + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
            }
        });

    }

    @Override
    public void onClick(View v) {
        calculate();
    }

    public void calculate(){
        float result = 0.0f;
        if (!enteredAmount.getText().toString().equals("")){
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            result = enteredBillFloat * seekBarPercentage / 100;
            resultText.setText("Your tip will be: \u20ac " + String.format("%.2f", result));
            totalAmount.setText("Total bill: \u20ac " + String.format("%.2f",result + enteredBillFloat));
        }else {
            Toast.makeText(MainActivity.this, "Please enter amount", Toast.LENGTH_LONG).show();
        }
    }
}
