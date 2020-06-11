package com.example.tallematerialdesign;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Carro> carros = new ArrayList<>();
    public static void guardar(Carro c){
        carros.add(c);
    }

    public static ArrayList<Carro> obtener (){
        return carros;
    }
}
