package com.example.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void calcular(View view) {
        // Instanciamento dos objetos da interface XML:
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);

        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        // Obter o texto (conteúdo) dos campos e validar:
        String nome = campoNome.getText().toString().trim();
        String peso = campoPeso.getText().toString().trim();
        String altura = campoAltura.getText().toString().trim();

        if (nome.isEmpty() || peso.isEmpty() || altura.isEmpty()) {
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVÁLIDA");
            return;
        }

        try {
            // Converter as variáveis numéricas (String -> Numérico):
            Double numPeso = Double.parseDouble(peso);
            Double numAltura = Double.parseDouble(altura);

            // Calcular o IMC com os dados fornecidos:
            Double numImc = numPeso / (numAltura * numAltura);

            // Formatação para apresentação de resultado:
            DecimalFormat df = new DecimalFormat("##.##");
            String imc = df.format(numImc);

            // Apresentar o resultado no campo "resultado1":
            resultado1.setText("IMC = " + imc + " kg/m²");

            // Classificar o IMC e apresentar no campo "resultado2":
            String classificacao;

            if (numImc < 18.5) {
                classificacao = "Baixo peso";
            } else if (numImc >= 18.5 && numImc <= 24.9) {
                classificacao = "Peso normal";
            } else if (numImc >= 25 && numImc <= 29.9) {
                classificacao = "Sobrepeso";
            } else if (numImc >= 30 && numImc <= 34.9) {
                classificacao = "Obesidade Grau 1";
            } else if (numImc >= 35 && numImc <= 39.9) {
                classificacao = "Obesidade Grau 2";
            } else {
                classificacao = "Obesidade Extrema";
            }

            resultado2.setText(classificacao);

        } catch (NumberFormatException e) {
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVÁLIDA");
        }
    }

    public void limpar(View view) {
        // Instanciamento dos objetos da interface XML:
        TextInputEditText campoNome = findViewById(R.id.textInputEditNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputEditPeso);
        TextInputEditText campoAltura = findViewById(R.id.textInputEditAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        // Limpar todos os campos:
        campoNome.setText("");
        campoAltura.setText("");
        campoPeso.setText("");
        resultado1.setText("");
        resultado2.setText("");
    }
}
