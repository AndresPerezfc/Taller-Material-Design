package com.example.tallematerialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AgregarCarro extends AppCompatActivity {

    private ArrayList<Integer> fotos;
    private EditText marca, modelo, ano, cilindrada;
    private StorageReference storageReference;
    private Uri uri;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

        marca = findViewById(R.id.txtMarca);
        modelo = findViewById(R.id.txtModelo);
        ano = findViewById(R.id.txtAno);
        cilindrada = findViewById(R.id.txtCilindrada);
        foto = findViewById(R.id.imgFotoSeleccionada);

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void subir_foto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }
}
