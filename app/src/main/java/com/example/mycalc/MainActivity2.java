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

public class MainActivity2 extends AppCompatActivity {

    private TextView myResult2;
    private String current = "";
    private String operator = "";
    private double firstNum = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myResult2 = findViewById(R.id.myResult2);

    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick2(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();

        switch (text) {
            case "C":
                current = "";
                firstNum = 0;
                operator = "";
                myResult2.setText("0");
                break;

            case "=":
                calculate();
                break;

            case "+":
            case "-":
            case "×":
            case "÷":
            case "xʸ":
                if (!current.isEmpty()) {
                    firstNum = Double.parseDouble(current);
                    operator = text;
                    current = "";
                }
                break;

            case "sin":
                if (!current.isEmpty()) {
                    double val = Math.sin(Math.toRadians(Double.parseDouble(current)));
                    myResult2.setText("sin(" + current + ") = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "cos":
                if (!current.isEmpty()) {
                    double val = Math.cos(Math.toRadians(Double.parseDouble(current)));
                    myResult2.setText("cos(" + current + ") = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "tan":
                if (!current.isEmpty()) {
                    double val = Math.tan(Math.toRadians(Double.parseDouble(current)));
                    myResult2.setText("tan(" + current + ") = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "√":
                if (!current.isEmpty()) {
                    double val = Math.sqrt(Double.parseDouble(current));
                    myResult2.setText("√" + current + " = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "log":
                if (!current.isEmpty()) {
                    double val = Math.log10(Double.parseDouble(current));
                    myResult2.setText("log(" + current + ") = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "ln":
                if (!current.isEmpty()) {
                    double val = Math.log(Double.parseDouble(current));
                    myResult2.setText("ln(" + current + ") = " + val);
                    current = String.valueOf(val);
                }
                break;

            case "π":
                current = String.valueOf(Math.PI);
                myResult2.setText(current);
                break;

            case "←":
                if (!current.isEmpty()) {
                    current = current.substring(0, current.length() - 1);
                    myResult2.setText(current.isEmpty() ? "0" : current);
                }
                break;

            case "\uD83D\uDD01":
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                current += text;
                myResult2.setText(current);
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
                    myResult2.setText("Error :P");
                    return;
                }
                break;
            case "xʸ":
                result = Math.pow(firstNum, secondNum);
                break;
        }

        current = String.valueOf(result);
        myResult2.setText(firstNum + " " + operator + " " + secondNum + " = " + current);
        operator = "";
    }

}