package com.example.tallematerialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends AppCompatActivity {

    private ArrayList<Integer> fotos;
    private EditText marca, modelo, ano, cilindrada, placa;
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
        placa = findViewById(R.id.txtPlaca);
        foto = findViewById(R.id.imgFotoSeleccionada);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void subir_foto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }

    public void guardar(View v){
        String marc, model, an, cilin, plac, id;
        int foto;
        Carro carro;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        marc = marca.getText().toString();
        model = modelo.getText().toString();
        an = ano.getText().toString();
        cilin = cilindrada.getText().toString();
        plac = placa.getText().toString();
        foto = foto_aleatoria();
        id = Datos.getId();
        carro = new Carro(marc, model, an, cilin, plac, foto, id);
        carro.guardar();
        subir_foto(id);
        limpiar();
        imp.hideSoftInputFromWindow(placa.getWindowToken(), 0);
        Snackbar.make(v, getString(R.string.mensaje_guardar), Snackbar.LENGTH_LONG).show();
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(){
        marca.setText("");
        modelo.setText("");
        ano.setText("");
        cilindrada.setText("");
        placa.setText("");
        marca.requestFocus();
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCarro.this, MainActivity.class);
        startActivity(i);
    }

    public void seleccionarFoto(View v){
        Intent i = new Intent();
        i.setType(("image/*"));
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Seleccione la foto"), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            uri = data.getData();
            if(uri != null){
                foto.setImageURI(uri);
            }
        }
    }


}
