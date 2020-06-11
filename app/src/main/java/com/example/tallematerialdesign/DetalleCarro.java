package com.example.tallematerialdesign;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCarro extends AppCompatActivity {
    private Carro c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);

        Bundle bundle;
        Intent intent;
        final ImageView foto;
        TextView marca, modelo, ano, cilindrada, placa;
        String id, marc, mode, an, cilin, plac;
        int fot;

        foto = findViewById(R.id.imgFotoDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        modelo = findViewById(R.id.lblModeloDetalle);
        ano = findViewById(R.id.lblAnoDetallle);
        cilindrada = findViewById(R.id.lblCilindradaDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        id = bundle.getString("id");
        marc = bundle.getString("marca");
        mode = bundle.getString("modelo");
        an = bundle.getString("ano");
        cilin = bundle.getString("cilindrada");
        plac = bundle.getString("placa");

        marca.setText(marc);
        modelo.setText(mode);
        ano.setText(an);
        cilindrada.setText(cilin);
        placa.setText(plac);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        c = new Carro(marc, mode, an, cilin, plac, 0, id);
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleCarro.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.eliminar_carro);
        builder.setMessage((R.string.pregunta_eliminar_carro));
        positivo = getString(R.string.afirmacion);
        negativo = getString(R.string.negacion);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
