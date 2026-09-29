package com.example.projek;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etImie;
    EditText etNazwisko;
    EditText etEmail;
    EditText etHaslo;
    Button btnZarejestruj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etImie = findViewById(R.id.etImie);
        etNazwisko = findViewById(R.id.etNazwisko);
        etEmail = findViewById(R.id.etEmail);
        etHaslo = findViewById(R.id.etHaslo);
        btnZarejestruj = findViewById(R.id.btnZarejestruj);
        btnZarejestruj.setOnClickListener(v -> {
            String imie = etImie.getText().toString().trim();
            String nazwisko = etNazwisko.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String haslo = etHaslo.getText().toString().trim();
            if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()) {
                Toast.makeText(MainActivity.this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!email.contains("@") || !email.contains(".")) {
                Toast.makeText(MainActivity.this, "Podaj poprawny adres email", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean duzaLitera = false;
            boolean malaLitera = false;
            boolean znakSpecjalny = false;
            for(int i = 0; i < haslo.length();i++) {
                char znak = haslo.charAt(i);
                if(Character.isUpperCase(znak)) {
                    duzaLitera = true;
                }
                if(Character.isLowerCase(znak)) {
                    malaLitera = true;
                }
                if(!Character.isLetterOrDigit(znak)) {
                    znakSpecjalny =true;
                }
            }
            if(haslo.length() < 8 || !duzaLitera || !malaLitera || !znakSpecjalny) {
                String komunikat = "Hasło musi mieć co najmniej 8 znaków";
                if(!duzaLitera) {
                    komunikat += ", dużą literę";
                }
                if(!malaLitera) {
                    komunikat += ", małą literę";
                }
                if(!znakSpecjalny) {
                    komunikat += ", znak specjalny";
                }
                Toast.makeText(MainActivity.this, komunikat, Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(MainActivity.this, "Dane są poprawne", Toast.LENGTH_SHORT).show();
        });
    }
}