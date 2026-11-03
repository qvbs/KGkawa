package com.example.losowanie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText liczbaKostek;
    private GridLayout poleKostek;
    private TextView tekstWynikow;

    private List<Integer> listaWynikow = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets paski = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(paski.left, paski.top, paski.right, paski.bottom);
            return insets;
        });

        liczbaKostek = findViewById(R.id.poleIloscKostek);
        poleKostek = findViewById(R.id.poleKostek);
        tekstWynikow = findViewById(R.id.wynikText);

        Button losuj = findViewById(R.id.przyciskLosuj);
        Button rosnaco = findViewById(R.id.przyciskRosnaco);
        Button malejaco = findViewById(R.id.przyciskMalejaco);

        losuj.setOnClickListener(v -> losowanie());
        rosnaco.setOnClickListener(v -> sortowanieRosnaco());
        malejaco.setOnClickListener(v -> sortowanieMalejaco());
    }

    private void losowanie() {
        String tekst = liczbaKostek.getText().toString();

        if (tekst.isEmpty()) {
            Toast.makeText(this, "Wpisz liczbę kostek!", Toast.LENGTH_SHORT).show();
            return;
        }
        int liczba = Integer.parseInt(tekst);

        if (liczba <= 0) {
            Toast.makeText(this, "Podaj liczbę większą od zera!", Toast.LENGTH_SHORT).show();
            return;
        }
        listaWynikow.clear();
        Random losowa = new Random();
        for (int i = 0; i < liczba; i++) {
            int wynik = losowa.nextInt(6) + 1;
            listaWynikow.add(wynik);
        }

        pokazWyniki();
    }
    private void sortowanieRosnaco() {
        if (listaWynikow.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }
        listaWynikow.sort(null);
        pokazWyniki();
    }
    private void sortowanieMalejaco() {
        if (listaWynikow.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }
        listaWynikow.sort((a, b) -> b - a);
        pokazWyniki();
    }
    private void pokazWyniki() {
        String wynik = "";
        for (int i = 0; i < listaWynikow.size(); i++) {
            wynik += listaWynikow.get(i);
            if (i < listaWynikow.size() - 1) {
                wynik += ", ";
            }
        }
        tekstWynikow.setText(wynik);
    }
}
