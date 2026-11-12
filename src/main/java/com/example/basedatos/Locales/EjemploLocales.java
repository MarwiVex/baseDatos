package com.example.basedatos.Locales;

import java.util.Locale;

public class EjemploLocales {
    public static void main(String[] args) {
        float valor = 1.5f;
        Locale.setDefault(new Locale("es", "ES"));

        System.out.println(String.format("%f", valor));
    }
}
