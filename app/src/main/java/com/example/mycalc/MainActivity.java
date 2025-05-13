package com.example.mycalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView myResult;
    private String current = "";
    private String operator = "";
    private double firstNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myResult = findViewById(R.id.myResult);
    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();

        switch (text) {
            case "C":
                current = "";
                firstNum = 0;
                operator = "";
                myResult.setText("0");
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
                if (!current.isEmpty()) {
                    firstNum = Double.parseDouble(current);
                    operator = text;
                    current = "";
                }
                break;
            case "←":
                if (!current.isEmpty()) {
                    current = current.substring(0, current.length() - 1);
                    myResult.setText(current.isEmpty() ? "0" : current);
                }
                break;

            case "\uD83D\uDD01":
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                break;

            default:
                current += text;
                myResult.setText(current);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        if (operator.isEmpty() || current.isEmpty()) return;

        double secondNum = Double.parseDouble(current);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "×":
                result = firstNum * secondNum;
                break;
            case "÷":
                if (secondNum != 0) {
                    result = firstNum / secondNum;
                } else {
                    myResult.setText("Error :P");
                    return;
                }
                break;
        }

        current = String.valueOf(result);
        myResult.setText(firstNum + " "  +operator +" "+ secondNum + " = " + current);
        operator = "";
    }
}
