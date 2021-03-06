package com.example.tallematerialdesign;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {
    private static String db = "Carros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Carro> carros = new ArrayList<>();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Carro c){
        Log.d("myTag", "base de datos: " + db + "c.getId: " + c.getId());
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static void setCarros(ArrayList<Carro> carros){
        carros = carros;
    }

    public static void eliminar (Carro c){
        databaseReference.child(db).child(c.getId()).removeValue();
        storageReference.child(c.getId()).delete();
    }

    public static ArrayList<Carro> obtener(){
        return carros;
    }
}
