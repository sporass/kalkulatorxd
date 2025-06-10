package com.example.kalkulatorxd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView inputScreen, outputScreen;
    private String liczby, koniec, operator;
    private double numer1, numer2, wynik;
    private boolean potega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputScreen = findViewById(R.id.pierwszyWynik);
        outputScreen = findViewById(R.id.koncowyWynik);

        liczby = "";
        koniec = "";
        operator = "";
        numer1 = 0;
        numer2 = 0;
        wynik = 0;
        potega = false;
    }

    public void number(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();
        liczby += text;
        inputScreen.setText(liczby);
    }

    public void operator(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();

        if (liczby.isEmpty()) {
            Toast.makeText(this, "Wprowadź liczbę", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!operator.isEmpty()) {
            equal(null);
        }

        operator = text;
        numer1 = Double.parseDouble(liczby);
        liczby = "";
        inputScreen.setText(numer1 + " " + operator);
        outputScreen.setText("");
        potega = false;
    }

    public void equal(View view) {
        if (liczby.isEmpty() || operator.isEmpty()) {
            Toast.makeText(this, "Wprowadź drugą liczbę i operator", Toast.LENGTH_SHORT).show();
            return;
        }

        numer2 = Double.parseDouble(liczby);

        switch (operator) {
            case "+":
                wynik = numer1 + numer2;
                break;
            case "-":
                wynik = numer1 - numer2;
                break;
            case "*":
                wynik = numer1 * numer2;
                break;
            case "/":
                if (numer2 == 0) {
                    Toast.makeText(this, "Nie można dzielić przez zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                wynik = numer1 / numer2;
                break;
            case "%":
                wynik = numer1 % numer2;
                break;
        }

        koniec = String.valueOf(wynik);
        outputScreen.setText(koniec);
        liczby = "";
        operator = "";
        numer1 = wynik;
        potega = String.valueOf(wynik).contains(".");
    }

    public void clear(View view) {
        liczby = "";
        koniec = "";
        operator = "";
        numer1 = 0;
        numer2 = 0;
        wynik = 0;
        potega = false;
        inputScreen.setText("");
        outputScreen.setText("");
    }

    public void back(View view) {
        if (!liczby.isEmpty()) {
            liczby = liczby.substring(0, liczby.length() - 1);
            inputScreen.setText(liczby);
        }
    }
}
