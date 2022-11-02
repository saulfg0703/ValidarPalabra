package com.example.validarpalabra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <String> palabras;
    TextView intentos, resultadoTextoPalabra;
    String palabraEscogida;
    EditText letra;
    int intentosReales;
    boolean[] arrayBoolean;
    char[] palabraEnCaracteres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        letra = findViewById(R.id.letra);
        resultadoTextoPalabra = findViewById(R.id.palabra);
    }

    public void iniciarJuego(View view) {
        //Inicializamos los intentos a 5 y guardamos en una variable de tipo entero el texto "5"
        intentos = findViewById(R.id.intentos);
        intentos.setText("5");
        intentosReales = Integer.parseInt(intentos.getText().toString());
        //Inicializamos el arrayList de palabras y añadimos las palabras con las que queramos jugar
        palabras = new ArrayList<>();
        palabras.add("mascarilla");
        palabras.add("pared");
        palabras.add("pantalla");
        palabras.add("avion");
        //Generamos un numero para que escoja una posicion aleatoria
        palabraEscogida = palabras.get((int) (Math.random() * palabras.size()));
        //La palabra escogida aleatoriamente la convertimos en caracteres
        palabraEnCaracteres = new char[palabraEscogida.length()];
        arrayBoolean = new boolean[palabraEscogida.length()];
        //En cada caracter de la palabra se escribe "_"
        for (int i = 0; i < palabraEscogida.length(); i++) {
            palabraEnCaracteres[i] ='_';
        }
        resultadoTextoPalabra.setText(String.valueOf(palabraEnCaracteres));
        letra.setText("");
    }

    public void comprobarLetra(View view) {
        //Variable centinela para disminuir los intentos que tienes en caso de que no aciertes la letra
        boolean fallar = false;
        //Convertimos la palabra escogida en un array de caracteres
        char[] palabraConvertidaChar = palabraEscogida.toCharArray();
        //Letra introducida pra comprobar si existe en la palabra
        char letraIntroducida = letra.getText().charAt(0);

        for (int i = 0; i < palabraConvertidaChar.length; i++) {
            //Si el caracter de la palabra coincide con la letra introducida por el usuario se muestra
            //La letra en su posicion (se intercambia '_' por la letra introducida en caso de que exista
            if (palabraConvertidaChar[i] == letraIntroducida) {
                palabraEnCaracteres[i] = letraIntroducida;
                arrayBoolean[i] = true;
                fallar = true;
            }
            resultadoTextoPalabra.setText(String.valueOf(palabraEnCaracteres));
        }
        if (!fallar) {
            intentosReales--;
            intentos.setText(String.valueOf(intentosReales));
        }if (intentosReales == 0) {
            Toast.makeText(this, "Has perdido, te quedaste sin intentos !!!", Toast.LENGTH_SHORT).show();
        }
    }
}